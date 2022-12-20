package app.wooportal.server.features.contests.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface ContestTypeTranslatableRepository
    extends TranslationRepository<ContestTypeTranslatableEntity> {

}