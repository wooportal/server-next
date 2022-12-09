package app.wooportal.server.core.messaging.notifications.definition;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class MessageDefinitionPredicateBuilder
    extends PredicateBuilder<QMessageDefinitionEntity, MessageDefinitionEntity> {

  public MessageDefinitionPredicateBuilder() {
    super(QMessageDefinitionEntity.messageDefinitionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term).or(query.user.email.likeIgnoreCase(term))
        .or(query.channels.any().name.likeIgnoreCase(term))
        .or(query.template.name.likeIgnoreCase(term));
  }
}
