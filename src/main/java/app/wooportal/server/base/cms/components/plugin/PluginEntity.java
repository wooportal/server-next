package app.wooportal.server.base.cms.components.plugin;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.base.cms.components.menuItem.MenuItemEntity;
import app.wooportal.server.base.cms.components.plugin.translations.PluginTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "plugins")
public class PluginEntity extends BaseEntity {
  	
  private Boolean active;

  private static final long serialVersionUID = 1L;
  
  private String code;
  
  private String icon;
  
  @Translatable
  private String name;
  
  @Translatable
  private String shortDescription;  
  
  private Boolean released;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "plugin")
  private Set<MenuItemEntity> menuItems;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<PluginTranslatableEntity> translatables;
}
