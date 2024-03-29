package app.wooportal.server.base.cms.components.page.embedding;

import org.springframework.stereotype.Service;
import app.wooportal.server.base.cms.components.page.attribute.PageAttributeService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class PageEmbeddingService
    extends DataService<PageEmbeddingEntity, PageEmbeddingPredicateBuilder> {

  public PageEmbeddingService(
      DataRepository<PageEmbeddingEntity> repo,
      PageEmbeddingPredicateBuilder predicate,
      PageAttributeService attributeService) {
    super(repo, predicate);
    
    addService("attributes", attributeService);
  }
}
