package app.wooportal.server.features.contest.comment;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import app.wooportal.server.base.userContext.base.UserContextEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.i18n.annotations.Translatable;
import app.wooportal.server.features.article.comment.translations.ArticleCommentTranslatableEntity;
import app.wooportal.server.features.contest.base.ContestEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "contest_comments")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class ContestCommentEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Translatable
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ContestEntity contest;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<ArticleCommentTranslatableEntity> translatables;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private UserContextEntity userContext;
}
