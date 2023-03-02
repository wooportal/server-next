package app.wooportal.server.features.organisation.rating;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class OrganisationRatingApi
    extends CrudApi<OrganisationRatingEntity, OrganisationRatingService> {


  public OrganisationRatingApi(OrganisationRatingService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationRatings")
  @ApprovedAndVerifiedPermission
  public PageableList<OrganisationRatingEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganisationRating")
  @ApprovedAndVerifiedPermission
  public Optional<OrganisationRatingEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationRatingEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationRatings")
  @AdminPermission
  public List<OrganisationRatingEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganisationRatingEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganisationRating")
  public OrganisationRatingEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) OrganisationRatingEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationRatings")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganisationRating")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}