package app.wooportal.server.features.deal.components.base;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import app.wooportal.server.base.contact.ContactService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.deal.components.base.media.DealMediaService;

@Service
public class DealService extends DataService<DealEntity, DealPredicateBuilder> {

  public DealService(
      DataRepository<DealEntity> repo, 
      DealPredicateBuilder predicate,
      ContactService contactService,
      DealMediaService mediaService) {
    super(repo, predicate);
    
    addService("contact", contactService);
    addService("uploads", mediaService);
  }
  
  @Override
  public void preCreate(DealEntity entity, DealEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
  }
  
  public Boolean sponsor(String dealId) {
    var Deal = getById(dealId);
    
    if (Deal.isPresent()) {
      Deal.get().setSponsored(true);
      repo.save(Deal.get());
      
      unsponsorOthers(dealId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOthers(String dealId) {
    var others = readAll(collectionQuery(predicate.withoutId(dealId)));
    if (others != null && !others.isEmpty()) {
      others.getList().stream().forEach(Deal -> {
        Deal.setSponsored(false);
        repo.save(Deal);
      });
    }
  }
}