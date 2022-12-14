package app.wooportal.server.features.events.eventTargetGroups;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.features.events.translations.EventTargetGroupTranslatableEntity;
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
@Table(name = "event_target_groups")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class EventTargetGroupEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean approved;

  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
  private Set<EventTargetGroupTranslatableEntity> translateable;
}
