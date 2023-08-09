package app.wooportal.server.core.security.components.role;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class RoleService extends DataService<RoleEntity, RolePredicateBuilder> {
  
  public static String admin = "admin";
  
  public RoleService(
      DataRepository<RoleEntity> repo,
      RolePredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  @Override
  public Optional<RoleEntity> getExisting(RoleEntity entity) {
    return entity != null && entity.getKeyword() != null && !entity.getKeyword().isBlank()
       ? repo.findOne(singleQuery(predicate.withKeyword(entity.getKeyword())))
       : Optional.empty();
  }

  public List<RoleEntity> getByUser(UserEntity user) {
    return repo.findAll(collectionQuery(predicate.withUserId(user.getId()))).getList();
  }

  public RoleEntity getAdminRole() {
    return repo.findOne(singleQuery(predicate.withKeyword(admin))).get();
  }
}
