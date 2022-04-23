    package app.wooportal.server.core.security.components.role;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class RoleService extends DataService<RoleEntity, RolePredicateBuilder> {
  
  public static String admin = "admin";
  
  public static String approved = "approved";
  
  public static String superviser = "superviser";
  
  public static String verified = "verified";
  
  public RoleService(
      DataRepository<RoleEntity> repo,
      RolePredicateBuilder predicate) {
    super(repo, predicate);
  }

  public List<RoleEntity> getByUser(UserEntity user) {
    return repo.findAll(query()
        .and(predicate.withUserId(user.getId()))).getList();
  }

  public RoleEntity getAdminRole() {
    return repo.findOne(predicate.withName(admin)).get();
  }
  
  public RoleEntity getApprovedRole() {
    return repo.findOne(predicate.withName(approved)).get();
  }
  
  public RoleEntity getSuperviserRole() {
    return repo.findOne(predicate.withName(superviser)).get();
  }
  
  public RoleEntity getVerifiedRole() {
    return repo.findOne(predicate.withName(verified)).get();
  }
  
  @PostConstruct
  public void checkRoles() {
    if (!repo.exists(predicate.withName(approved))
        || !repo.exists(predicate.withName(verified))
        || !repo.exists(predicate.withName(admin))
        || !repo.exists(predicate.withName(superviser))) {
      throw new NotFoundException("Not all roles exists!");
    }
  }

}
