package app.wooportal.server.test.units.components.user;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.GraphBuilder;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.media.base.MediaPredicateBuilder;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetBuilder;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetEntity;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetService;
import app.wooportal.server.core.security.components.role.RoleEntity;
import app.wooportal.server.core.security.components.role.RolePredicateBuilder;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.components.user.UserApi;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserPredicateBuilder;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.components.verification.VerificationEntity;
import app.wooportal.server.core.security.components.verification.VerificationPredicateBuilder;
import app.wooportal.server.core.security.components.verification.VerificationService;
import app.wooportal.server.test.units.core.entities.child.TestChildEntity;
import app.wooportal.server.test.units.services.RepoService;
import app.wooportal.server.test.units.services.TestApiContextAdapter;
import app.wooportal.server.test.units.services.TestMailService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserSetup {

  private UserApi api;

  @Autowired
  private BCryptPasswordEncoder encoder;

  @Autowired
  private EntityManager entityManager;

  private GraphBuilder<TestChildEntity> graphBuilder;

  private TestMailService mailService;

  public void init(List<UserEntity> userData, List<MediaEntity> uploadData,
      List<RoleEntity> roleData, List<PasswordResetEntity> passwordResetData,
      List<VerificationEntity> verificationData) {
    mailService = new TestMailService();

    var service = new UserService(new RepoService<UserEntity>(userData), new UserPredicateBuilder(),
        encoder, createMediaService(uploadData), createRoleService(roleData),
        createPasswordResetData(passwordResetData), createVerificationData(verificationData), null, null, null);

    service.setGraph(new GraphBuilder<>(entityManager));
    service.setContext(new TestApiContextAdapter());
    api = new UserApi(service);
  }

  private MediaService createMediaService(List<MediaEntity> uploadData) {
    return new MediaService(new RepoService<MediaEntity>(uploadData), new MediaPredicateBuilder(),
        null, null);
  }

  private RoleService createRoleService(List<RoleEntity> roleData) {
    return new RoleService(new RepoService<RoleEntity>(roleData), new RolePredicateBuilder());
  }

  private PasswordResetService createPasswordResetData(
      List<PasswordResetEntity> passwordResetData) {
    return new PasswordResetService(new RepoService<PasswordResetEntity>(passwordResetData),
        new PasswordResetBuilder(), mailService.getGeneralConfig(), mailService.getService());
  }

  private VerificationService createVerificationData(List<VerificationEntity> verificationData) {
    return new VerificationService(new RepoService<VerificationEntity>(verificationData),
        new VerificationPredicateBuilder(), mailService.getGeneralConfig(),
        mailService.getService());
  }

}
