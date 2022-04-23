package app.wooportal.server;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.wooportal.server.core.error.ExceptionResolverInterceptor;
import app.wooportal.server.core.error.errorMessage.ErrorMessageService;
import app.wooportal.server.core.repository.CustomRepositoryFactoryBean;
import io.leangen.graphql.ExtensionProvider;
import io.leangen.graphql.GeneratorConfiguration;
import io.leangen.graphql.execution.ResolverInterceptorFactory;
import io.leangen.graphql.metadata.strategy.value.ValueMapperFactory;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAsync
@EnableWebMvc
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class App {

  @Autowired
  private ObjectMapper mapper;
  
  @Autowired
  private ErrorMessageService errorMessageService;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @PostConstruct
  public void setTimeZone() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  @Bean
  public BCryptPasswordEncoder bcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean(name = "auditingDateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now(TimeZone.getDefault().toZoneId()));
  }

  @Bean
  public ValueMapperFactory valueMapperFactory() {
    return (abstractTypes, environment) -> JacksonValueMapperFactory.builder().withPrototype(mapper)
        .build().getValueMapper(abstractTypes, environment);
  }

  @Bean
  public TomcatConnectorCustomizer asyncTimeoutCustomize() {
    return connector -> connector.setAsyncTimeout(180000);
  }

//  @Bean
//  public ExtensionProvider<GeneratorConfiguration, ResolverInterceptorFactory> test() {
//    return (c, r) -> {
//      r.add(params -> List.of(new ExceptionResolverInterceptor(errorMessageService)));
//      return r;
//    };
//  }
}
