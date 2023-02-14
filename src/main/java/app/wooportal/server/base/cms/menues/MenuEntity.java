package app.wooportal.server.base.cms.menues;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.cms.features.FeatureEntity;
import app.wooportal.server.base.cms.menues.translations.MenuTranslatableEntity;
import app.wooportal.server.base.cms.pages.PageEntity;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "menues")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class MenuEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  private FeatureEntity module;

  @ManyToOne(fetch = FetchType.LAZY)
  private PageEntity page;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<MenuTranslatableEntity> translatable;
}
