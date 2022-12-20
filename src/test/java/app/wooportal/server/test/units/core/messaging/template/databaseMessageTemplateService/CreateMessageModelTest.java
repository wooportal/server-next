package app.wooportal.server.test.units.core.messaging.template.databaseMessageTemplateService;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.messaging.notifications.definition.MessageDefinitionEntity;
import app.wooportal.server.core.messaging.notifications.template.MessageTemplateEntity;
import app.wooportal.server.core.messaging.template.DatabaseMessageTemplateService;
import app.wooportal.server.test.units.core.setup.entities.base.TestEntity;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildEntity;
import app.wooportal.server.test.units.core.setup.services.ObjectFactory;

public class CreateMessageModelTest {

  private DatabaseMessageTemplateService databaseMessageTemplateService = new DatabaseMessageTemplateService(null);

  @Test
  public void checkCreatedMessageModel() throws Exception {

    var messageDefinition = ObjectFactory.newInstance(MessageDefinitionEntity.class,
        Map.of("template", ObjectFactory.newInstance(MessageTemplateEntity.class, Map.of(
            "content",
                """
                    Hey,
        
                    this inspection has been created on ${child.created} and was modified on ${child.modified}.
                """,
            "name", "TestTemplate"))));

    var entity =
        ObjectFactory.newInstance(TestEntity.class, Map.of(
            "child", ObjectFactory.newInstance(TestChildEntity.class, Map.of(
                "created", OffsetDateTime.parse("2007-12-03T10:15:30+01:00"),
                "modified", OffsetDateTime.parse("2007-12-08T10:17:30+01:00")))));

    var map = Map.of(
        "child.created", "03.12.2007 10:15",
        "child.modified", "08.12.2007 10:17");
    
    var result = databaseMessageTemplateService.createMessageModel(messageDefinition, entity); 
    
    assertThat(result).isEqualTo(map);
  }

  @Test
  public void checkModelWhenInputNoMatches() throws Exception {

    var messageDefinitiOn = ObjectFactory.newInstance(MessageDefinitionEntity.class,
        Map.of("template", ObjectFactory.newInstance(MessageTemplateEntity.class, Map.of(
            "content", "Hello World",
            "name", "TestTemplate"))));

    var inspectionItem =
        ObjectFactory.newInstance(TestEntity.class, Map.of(
            "child", ObjectFactory.newInstance(TestChildEntity.class, Map.of(
                "created", OffsetDateTime.parse("2007-12-03T10:15:30+01:00"),
                "modified", OffsetDateTime.parse("2007-12-08T10:17:30+01:00")))));

    var map = new HashMap<String, String>();
    
    assertThat(
        databaseMessageTemplateService.createMessageModel(messageDefinitiOn, inspectionItem))
            .isEqualTo(map);
  }
  
  @Test
  public void checkModelWhenInputNull() throws Exception {

    var notificationDefinition = ObjectFactory.newInstance(MessageDefinitionEntity.class, Map.of(
        "template", ObjectFactory.newInstance(MessageTemplateEntity.class, Map.of())));

    var inspectionItem =
        ObjectFactory.newInstance(TestEntity.class, Map.of(
            "child", ObjectFactory.newInstance(TestChildEntity.class, Map.of(
                "created", OffsetDateTime.parse("2007-12-03T10:15:30+01:00"),
                "modified", OffsetDateTime.parse("2007-12-08T10:17:30+01:00")))));

    var map = new HashMap<String, String>();
    
    assertThat(
        databaseMessageTemplateService.createMessageModel(notificationDefinition, inspectionItem))
            .isEqualTo(map);
  }
}