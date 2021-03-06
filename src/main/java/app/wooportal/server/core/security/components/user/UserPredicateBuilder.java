package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;
import app.wooportal.server.core.security.components.role.RoleService;

@Service
public class UserPredicateBuilder extends PredicateBuilder<QUserEntity, UserEntity> {

  public UserPredicateBuilder() {
    super(QUserEntity.userEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.email.likeIgnoreCase(term).or(query.roles.any().name.likeIgnoreCase(term));
  }

  public BooleanExpression withLoginName(String loginName) {
    return loginName != null && !loginName.isBlank() ? query.email.equalsIgnoreCase(loginName)
        : null;
  }

  public BooleanExpression withRole(String name) {
    return name != null && !name.isBlank() ? query.roles.any().name.equalsIgnoreCase(name) : null;
  }

  public BooleanExpression withStudentRole() {
    return query.roles.any().key.eq(RoleService.student);
  }

  public BooleanExpression createdBefore(OffsetDateTime date) {
    return date != null ? query.created.before(date) : null;
  }

  public BooleanExpression notVerified() {
    return query.verified.isFalse();
  }

  public BooleanExpression withChat(String chatId) {
    return chatId != null && !chatId.isBlank()
        ? query.participants.any().chat.id.equalsIgnoreCase(chatId)
        : null;
  }

  public BooleanExpression withMessage(String messagerId) {
    return messagerId != null && !messagerId.isBlank()
        ? query.participants.any().messages.any().id.equalsIgnoreCase(messagerId)
        : null;
  }

  public Predicate withGroup(String groupId) {
    return query.course.group.id.equalsIgnoreCase(groupId);
  }

}
