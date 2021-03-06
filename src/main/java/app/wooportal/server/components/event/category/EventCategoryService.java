package app.wooportal.server.components.event.category;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventCategoryService extends DataService<EventCategoryEntity, EventCategoryPredicateBuilder> {

  public EventCategoryService(
      DataRepository<EventCategoryEntity> repo, 
      EventCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  @Override
  public Optional<EventCategoryEntity> getExisting(EventCategoryEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty()
        ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<EventCategoryEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}

