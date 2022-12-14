package app.wooportal.server.features.organisations.organisationVisitor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.visit.visitor.VisitorEntity;
import app.wooportal.server.features.organisations.base.OrganisationEntity;
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
@Table(name = "organisation_visitors")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class OrganisationVisitorEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrganisationEntity parent;

  @ManyToOne(fetch = FetchType.LAZY)
  private VisitorEntity visitor;

  private Integer visits;
}
