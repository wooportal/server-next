package app.wooportal.server.base.adminFooter.item;

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
public class AdminFooterItemApi extends CrudApi<AdminFooterItemEntity, AdminFooterItemService> {

  public AdminFooterItemApi(AdminFooterItemService service) {
    super(service);
  }

  @Override
  @GraphQLQuery(name = "getAdminFooterItems")
  public PageableList<AdminFooterItemEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAdminFooterItem")
  public Optional<AdminFooterItemEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AdminFooterItemEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAdminFooterItems")
  @AdminPermission
  public List<AdminFooterItemEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AdminFooterItemEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAdminFooterItem")
  @AdminPermission
  public AdminFooterItemEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AdminFooterItemEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAdminFooterItems")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAdminFooterItem")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
