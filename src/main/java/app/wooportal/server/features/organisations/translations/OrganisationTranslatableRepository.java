package app.wooportal.server.features.organisations.translations;


import org.springframework.stereotype.Repository;
import app.wooportal.server.core.i18n.language.translation.TranslationRepository;

@Repository
public interface OrganisationTranslatableRepository
    extends TranslationRepository<OrganisationTranslatableEntity> {
  
}
