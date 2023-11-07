package app.wooportal.server.features.event.base;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import app.wooportal.server.base.address.base.AddressService;
import app.wooportal.server.base.contact.ContactService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.features.event.attendeeConfiguration.EventAttendeeConfigurationService;
import app.wooportal.server.features.event.media.EventMediaService;
import app.wooportal.server.features.event.schedule.EventScheduleService;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {
  
  public EventService(DataRepository<EventEntity> repo,
      EventPredicateBuilder predicate,
      EventAttendeeConfigurationService attendeeConfigurationService,
      AddressService addressService,
      EventScheduleService scheduleService,
      EventMediaService eventMediaService,
      ContactService contactService) {
    super(repo, predicate);

    addService("attendeeConfiguration", attendeeConfigurationService);
    addService("address", addressService);
    addService("schedules", scheduleService);
    addService("uploads", eventMediaService);
    addService("contact", contactService);
   
  }
  
  @Override
  public void preCreate(EventEntity entity, EventEntity newEntity, JsonNode context) {
    newEntity.setSponsored(false);
    addContext("sponsored", context);
  }
  
  public Boolean sponsor(String eventId) {
    var event = getById(eventId);
    
    if (event.isPresent()) {
      event.get().setSponsored(true);
      repo.save(event.get());
      
      unsponsorOther(eventId);
      
      //TODO: Send notifications
      
      return true;
    }
    return false;
  }

  private void unsponsorOther(String eventId) {
	var other = readAll(collectionQuery(predicate.withSponsoredTrue().and(predicate.withoutId(eventId))));
    if (other != null && !other.isEmpty()) {
      other.getList().stream().forEach(event -> {
        event.setSponsored(false);
        repo.save(event);
      });
    }
  }  
}
