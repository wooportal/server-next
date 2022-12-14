package app.wooportal.server.features.contests.contestState;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ContestStatePredicateBuilder
    extends PredicateBuilder<QContestStateEntity, ContestStateEntity> {

  public ContestStatePredicateBuilder() {
    super(QContestStateEntity.contestStateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term);
  }
}
