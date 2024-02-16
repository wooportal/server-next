package app.wooportal.server.base.cms.components.plugin.translations;

import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.base.cms.components.plugin.PluginEntity;
import app.wooportal.server.core.i18n.entities.TranslatableEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "plugin_translatables")
public class PluginTranslatableEntity extends TranslatableEntity<PluginEntity> {

  private static final long serialVersionUID = 1L;

  private String name;
  
  private String shortDescription;
}
