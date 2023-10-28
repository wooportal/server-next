package app.wooportal.server.core.visit;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.error.ErrorMailService;
import app.wooportal.server.core.visit.visitable.VisitableEntity;
import app.wooportal.server.core.visit.visitable.VisitableService;

@Component
@Aspect
public class VisitInterceptor<V extends VisitableEntity<?>> {

  @Pointcut("execution(* app.wooportal.server.core.base.CrudApi+.readOne(..))")
  private void readOne() { }
  
  private final ErrorMailService errorMailService;
  
  @Autowired
  protected HttpServletRequest request;

  private VisitableService<V> visitableService;
  
  public VisitInterceptor(
      VisitableService<V> visitableService,
      ErrorMailService errorMailService) {
    this.visitableService = visitableService;
    this.errorMailService = errorMailService;
  }
  
  @SuppressWarnings("unchecked")
  @Around("readOne()")
  public <E extends BaseEntity> Object saveVisitForEntity(ProceedingJoinPoint pjp) 
        throws Throwable {
    var result = (Optional<E>) pjp.proceed();
    
    visitableService.setRequest(request);
    CompletableFuture.runAsync(() -> {
      if (result.isPresent() && visitableService.isValidVisitor()) {
        try {
          visitableService.saveEntityVisit(result.get());
        } catch (Throwable e) {
          e.printStackTrace();
          errorMailService.sendErrorMail(e.getStackTrace().toString());
        }
      }
    });
    return result;
  }

}
