package app.wooportal.server.base.cms.landings;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.cms.landingFeatures.LandingFeatureEntity;
import app.wooportal.server.base.cms.translations.LandingTranslatableEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
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
@Table(name = "landings")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class LandingEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @Column(nullable = false)
  private MediaEntity titleImage;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "landing")
  private Set<LandingFeatureEntity> landingFeatures;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<LandingTranslatableEntity> translatable;
}