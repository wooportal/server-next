package app.wooportal.server.components.messaging.call;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class CallApi extends CrudApi<CallEntity, CallService> {

  public CallApi(CallService eventCallService) {
    super(eventCallService);
  }

  @Override
  @GraphQLQuery(name = "getCalls")
  @ApprovedAndVerifiedPermission
  public PageableList<CallEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getCall")
  @ApprovedAndVerifiedPermission
  public Optional<CallEntity> readOne(@GraphQLArgument(name = CrudApi.entity) CallEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveCalls")
  @ApprovedAndVerifiedPermission
  public List<CallEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<CallEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveCall")
  @ApprovedAndVerifiedPermission
  public CallEntity saveOne(@GraphQLArgument(name = CrudApi.entity) CallEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteCalls")
  @ApprovedAndVerifiedPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteCall")
  @ApprovedAndVerifiedPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}


