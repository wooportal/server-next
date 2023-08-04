package app.wooportal.server.features.infoMedia.category;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.infoMedia.base.InfoMediaEntity;
import app.wooportal.server.features.infoMedia.category.translations.InfoMediaCategoryTranslatableEntity;
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
@Table(name = "info_media_categories")
public class InfoMediaCategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String color;
  
  private String icon;
  
  @Translatable
  private String name;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private Set<InfoMediaEntity> infoMedia;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<InfoMediaCategoryTranslatableEntity> translatables;
}
