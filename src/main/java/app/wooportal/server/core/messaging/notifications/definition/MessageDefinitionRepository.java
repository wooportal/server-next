package app.wooportal.server.core.messaging.notifications.definition;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface MessageDefinitionRepository extends DataRepository<MessageDefinitionEntity> {

}
