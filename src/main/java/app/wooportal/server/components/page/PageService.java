package app.wooportal.server.components.page;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageService extends DataService<PageEntity, PagePredicateBuilder> {

  public PageService(
      DataRepository<PageEntity> repo,
      PagePredicateBuilder predicate,
      MediaService mediaService) {
    super(repo, predicate);
    
    addService("images", mediaService);
    addService("titleImage", mediaService);
    addService("video", mediaService);
  }

  @Override
  public Optional<PageEntity> getExisting(PageEntity entity) {
    return entity.getSlug() == null || entity.getSlug().isEmpty() ? Optional.empty()
        : getByName(entity.getSlug());
  }
  public Optional<PageEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
