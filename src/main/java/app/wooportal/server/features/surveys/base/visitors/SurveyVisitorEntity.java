package app.wooportal.server.features.surveys.base.visitors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.visit.visitor.VisitorEntity;
import app.wooportal.server.features.surveys.base.SurveyEntity;
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
@Table(name = "survey_visitors")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class SurveyVisitorEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Integer visits;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private SurveyEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private VisitorEntity visitor;

}