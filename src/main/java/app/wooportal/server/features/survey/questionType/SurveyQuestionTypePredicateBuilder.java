package app.wooportal.server.features.survey.questionType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SurveyQuestionTypePredicateBuilder
    extends PredicateBuilder<QSurveyQuestionTypeEntity, SurveyQuestionTypeEntity> {

  public SurveyQuestionTypePredicateBuilder() {
    super(QSurveyQuestionTypeEntity.surveyQuestionTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return null;
  }
}