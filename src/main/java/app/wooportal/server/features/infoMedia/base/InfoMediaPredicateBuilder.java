package app.wooportal.server.features.infoMedia.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class InfoMediaPredicateBuilder extends PredicateBuilder<QInfoMediaEntity, InfoMediaEntity> {

  public InfoMediaPredicateBuilder() {
    super(QInfoMediaEntity.infoMediaEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.videoChatLink.likeIgnoreCase(term)
        .or(query.metaDescription.likeIgnoreCase(term))
        .or(query.slug.likeIgnoreCase(term))
        .or(query.translatables.any().content.likeIgnoreCase(term))
        .or(query.translatables.any().name.likeIgnoreCase(term))
        .or(query.translatables.any().shortDescription.likeIgnoreCase(term))
        .or(query.category.translatables.any().name.likeIgnoreCase(term));
  }
}
