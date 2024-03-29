package app.wooportal.server.core.security.components.userDeletion.base;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.repository.DataRepository;

@Repository
public interface UserDeletionRepository extends DataRepository<UserDeletionEntity> {

}
