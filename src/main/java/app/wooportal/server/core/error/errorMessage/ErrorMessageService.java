package app.wooportal.server.core.error.errorMessage;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.error.exception.InvalidVerificationException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.error.exception.XmppException;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ErrorMessageService extends DataService<ErrorMessageEntity, ErrorMessagePredicateBuilder> {
  
  private ErrorMailService errorMailService;

  public ErrorMessageService(
      DataRepository<ErrorMessageEntity> repo,
      ErrorMessagePredicateBuilder predicate,
      ErrorMailService errorMailService) {
    super(repo, predicate);
    
    this.errorMailService = errorMailService;
  }

  //TODO: Get messages from DB
  public String getLocalizedMessageByException(Exception e) {
    if (e instanceof AlreadyVerifiedException) {
      return "Benutzer bereits verfiziert. Logge dich ein.";
    }
    
    if (e instanceof BadParamsException) {
      return "Eingaben fehlerhaft. Probiere es mit anderen Eingaben.";
    }
    
    if (e instanceof InvalidPasswordResetException) {
      return "Passwort zurücksetzen hat nicht geklappt. Bitte erneut prüfen.";
    }
    
    if (e instanceof InvalidTokenException) {
      return "Sicherheitstoken ungültig. Bitte neu einloggen.";
    }
    
    if (e instanceof InvalidVerificationException) {
      return "Verifizierung ungültig. Bitte erneute Verifizierungsmail erzeugen.";
    }
    
    if (e instanceof NotFoundException) {
      return "Inhalt(e) konnte(n) nicht gefunden werden. Probiere es mit anderen Eingaben.";
    }
    
    if (e instanceof XmppException) {
      return "Chat aktuell nicht möglich. Bitte App neu starten und erneut probieren.";
    }
    
    this.errorMailService.sendErrorMail(e);
    return "Unbekannter Fehler. Wende dich bitte an den Support.";
  }
  
  public Boolean sendErrorMail(String stackTrace) {
    this.errorMailService.sendErrorMail(stackTrace);
    return true;
  }

}
