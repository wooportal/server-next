package app.wooportal.server.features.survey.assignment;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SurveyAssignmentApi extends CrudApi<SurveyAssignmentEntity, SurveyAssignmentService> {


  public SurveyAssignmentApi(SurveyAssignmentService userService) {
    super(userService);
  }

  @Override
  @GraphQLQuery(name = "getSurveyAssignments")
  public PageableList<SurveyAssignmentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getSurveyAssignment")
  public Optional<SurveyAssignmentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SurveyAssignmentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyAssignments")
  @AdminPermission
  public List<SurveyAssignmentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<SurveyAssignmentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSurveyAssignment")
  public SurveyAssignmentEntity saveOne(@GraphQLArgument(name = CrudApi.entity) SurveyAssignmentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyAssignments")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSurveyAssignment")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}