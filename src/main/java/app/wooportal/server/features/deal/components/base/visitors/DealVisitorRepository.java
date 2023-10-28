package app.wooportal.server.features.deal.components.base.visitors;

import org.springframework.stereotype.Repository;

import app.wooportal.server.core.visit.visitable.VisitableRepository;

@Repository
public interface DealVisitorRepository extends VisitableRepository<DealVisitorEntity> {

}
