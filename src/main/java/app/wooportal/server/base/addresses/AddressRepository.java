package app.wooportal.server.base.addresses;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends DataRepository<AddressEntity> {

}