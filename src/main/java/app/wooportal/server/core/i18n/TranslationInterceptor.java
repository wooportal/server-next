package app.wooportal.server.core.i18n;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.i18n.translation.TranslationService;

@Aspect
@Service
public class TranslationInterceptor {
  
  private TranslationService translationService;
  
  public TranslationInterceptor(
      TranslationService translationService) {
    this.translationService = translationService;
  }
  
  @Pointcut("execution(* app.wooportal.server.core.repository.DataRepository+.save(..))")
  private void save() {
  }

  @Pointcut(
      "execution(public * app.wooportal.server.core.repository.DataRepository+.findOne(..))")
  private void findOne() {
  }
  
  @Pointcut(
      "execution(* app.wooportal.server.core.repository.DataRepository+.findAll(..))")
  private void findAll() {
  }
  
  
  @Around("findAll()")
  public Object replaceIterableWithTranslations(ProceedingJoinPoint pjp) throws Throwable {
    var result = pjp.proceed();
    if (result instanceof PageableList<?>) {
      var list = ((PageableList<?>) result).getList();
      translationService.localizeList(list);
    }
    return result;
  }

  @Around("findOne()")
  public Object replaceSingleEntityWithTranslation(ProceedingJoinPoint pjp) throws Throwable {
    Object result = pjp.proceed();
    if (result instanceof Optional<?> && ((Optional<?>) result).isPresent()) {
      Object entity = ((Optional<?>) result).get();
      translationService.localizeSingle(entity);
      return Optional.of(entity);
    }
    return result;
  }
  
  @SuppressWarnings("unchecked")
  @Around("save()")
  public <E extends BaseEntity> Object saveTranslation(ProceedingJoinPoint pjp) throws Throwable {
      Object result = pjp.proceed();
      var savedEntity = pjp.getArgs()[0];

      CompletableFuture.runAsync(() -> {
          try {
              translationService.save((E) savedEntity);
          } catch (Throwable e) {
              
              e.printStackTrace();
          }
      }).exceptionally(throwable -> {
         
          throwable.printStackTrace();
          return null; 
      });

      return result;
  }

}
