package app.wooportal.server.components.event.schedule;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.components.event.base.EventEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class ScheduleEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(name = "end_date")
  private OffsetDateTime endDate;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private EventEntity event;
  
  @Column(name = "start_date")
  private OffsetDateTime startDate;

}
