package app.wooportal.server.base.cms.menuItems;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.cms.features.FeatureEntity;
import app.wooportal.server.base.cms.menues.MenuEntity;
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
@Table(name = "menu_items")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class MenuItemEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Integer order;

  @ManyToOne(fetch = FetchType.LAZY)
  private FeatureEntity module;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private MenuEntity parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private PageEntity page;

  @ManyToOne(fetch = FetchType.LAZY)
  private MenuEntity subMenu;
}