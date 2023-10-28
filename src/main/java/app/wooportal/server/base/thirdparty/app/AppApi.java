package app.wooportal.server.base.thirdparty.app;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.base.thirdparty.app.authorization.permissions.AppAdminPermission;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class AppApi extends CrudApi<AppEntity, AppService> {

  public AppApi(AppService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getApps")
  public PageableList<AppEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getApp")
  public Optional<AppEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AppEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveApps")
  @AppAdminPermission
  public List<AppEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AppEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveApp")
  @AppAdminPermission
  public AppEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AppEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteApps")
  @AppAdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteApp")
  @AppAdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
