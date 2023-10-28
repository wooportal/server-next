package app.wooportal.server.features.article.components.publicAuthor;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ArticlePublicAuthorPredicateBuilder extends PredicateBuilder<QArticlePublicAuthorEntity, ArticlePublicAuthorEntity> {

  public ArticlePublicAuthorPredicateBuilder() {
    super(QArticlePublicAuthorEntity.articlePublicAuthorEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}
