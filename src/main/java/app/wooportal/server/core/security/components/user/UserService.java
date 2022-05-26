package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidVerificationException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetEntity;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetService;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.components.verification.VerificationEntity;
import app.wooportal.server.core.security.components.verification.VerificationService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {

  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  private final RoleService roleService;
  
  public UserService(
      DataRepository<UserEntity> repo,
      UserPredicateBuilder predicate,
      BCryptPasswordEncoder encoder,
      MediaService mediaService,
      RoleService roleService,
      PasswordResetService passwordResetService,
      VerificationService verificationService,
      CourseService courseService) {
    super(repo, predicate);

    this.bcryptPasswordEncoder = encoder;
    this.roleService = roleService;
    addService("course", courseService);
    addService("passwordReset", passwordResetService);
    addService("profilePicture", mediaService);
    addService("uploads", mediaService);
    addService("verification", verificationService);
  }

  @Override
  public Optional<UserEntity> getExisting(UserEntity entity) {
    return entity.getEmail() == null || entity.getEmail().isEmpty() ? Optional.empty()
        : getByLoginName(entity.getEmail());
  }

  public Optional<UserEntity> getByLoginName(String name) {
    return repo.findOne(predicate.withLoginName(name));
  }

  public List<UserEntity> getNotVerifiedBefore(OffsetDateTime date) {
    return repo
        .findAll(query(false).and(predicate.createdBefore(date)).and(predicate.notVerified()))
        .getList();
  }

  @Override
  public void preSave(UserEntity entity, UserEntity newEntity, JsonNode context) {
    if (newEntity.getPassword() != null) {
      newEntity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
    }

    if (entity.getId() == null || entity.getId().isBlank()) {
      newEntity.setVerification(new VerificationEntity());
    }
  }
  
  public Optional<UserEntity> addJobAddFavorite(String jobAdId) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public Optional<UserEntity> approve(String userId) {
    var user = getById(userId);
    
    if (user.isPresent()) {
      user.get().getRoles().add(roleService.getVerifiedRole());
      return Optional.of(repo.save(user.get()));
    }
    return user;
  }

  public Boolean createPasswordReset(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    if (result.get().getPasswordReset() != null) {
      getService(PasswordResetService.class).deleteById(result.get().getPasswordReset().getId());
    }

    var copy = ReflectionUtils.copy(result.get());
    copy.setPasswordReset(new PasswordResetEntity());
    persist(result.get(), copy, createContext("passwordReset"));
    return true;
  }

  public Boolean resetPassword(String key, String password) {
    var passwordReset = getService(PasswordResetService.class).getByKey(key);
    if (passwordReset.isEmpty()) {
      throw new InvalidPasswordResetException("Password reset not requested", key);
    }
    var user = passwordReset.get().getUser();
    user.setPassword(bcryptPasswordEncoder.encode(password));
    repo.save(user);
    return true;
  }

  public Boolean createVerification(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    if (result.get().getRoles().stream()
        .anyMatch(r -> r.getName().equalsIgnoreCase(RoleService.verified))) {
      throw new AlreadyVerifiedException("Already verified");
    }

    if (result.get().getVerification() != null) {
      getService(VerificationService.class).deleteById(result.get().getVerification().getId());
    }

    var copy = ReflectionUtils.copy(result.get());
    copy.setVerification(new VerificationEntity());
    persist(result.get(), copy, createContext("verification"));
    return true;
  }

  public UserEntity verify(String key) {
    var verification = getService(VerificationService.class).getByKey(key);
    if (verification.isPresent()) {
      var user = verification.get().getUser();
      user.getRoles().add(roleService.getVerifiedRole());
      getService(VerificationService.class).deleteById(verification.get().getId());
      return repo.save(user);
    }
    throw new InvalidVerificationException("Verification invalid", key);
  }

  public List<UserEntity> getAllStudents(String... graph) {
    return repo.findAll(query(false).and(predicate.withCourseNotNull()).addGraph(graph(graph)))
        .getList();
  }

}
