package app.wooportal.server.features.organisations.organisationComments;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganisationCommentService extends DataService<OrganisationCommentEntity, OrganisationCommentPredicateBuilder> {

  public OrganisationCommentService(DataRepository<OrganisationCommentEntity> repo, OrganisationCommentPredicateBuilder predicate) {
    super(repo, predicate);
  }
}