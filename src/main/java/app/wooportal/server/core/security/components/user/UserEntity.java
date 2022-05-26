package app.wooportal.server.core.security.components.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import app.wooportal.server.components.documents.usertemplate.UserTemplateEntity;
import app.wooportal.server.components.evaluation.assignment.AssignmentEntity;
import app.wooportal.server.components.event.base.EventEntity;
import app.wooportal.server.components.group.course.CourseEntity;
import app.wooportal.server.components.jobad.base.JobAdEntity;
import app.wooportal.server.components.message.participant.ParticipantEntity;
import app.wooportal.server.components.push.notification.NotificationEntity;
import app.wooportal.server.components.push.subscription.SubscriptionEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetEntity;
import app.wooportal.server.core.security.components.role.RoleEntity;
import app.wooportal.server.core.security.components.verification.VerificationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "users")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
public class UserEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  private Set<AssignmentEntity> assignments;

  @ManyToOne(fetch = FetchType.LAZY)
  private CourseEntity course;

  @Column(nullable = false)
  private Boolean evaluateCourse;
  
  @Column(unique = true, nullable = false)
  private String email;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_events", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "event_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "event_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<EventEntity> favoriteEvents = new ArrayList<>();

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_job_ads", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "job_ad_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "job_ad_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<JobAdEntity> favoriteJobAds = new ArrayList<>();

  private String fullname;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  private Set<NotificationEntity> notifications;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  private Set<ParticipantEntity> participants;

  @Column(nullable = false)
  private String password;

  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  private PasswordResetEntity passwordReset;
  
  @Column(unique = true)
  private String phone;

  @OneToOne(fetch = FetchType.LAZY)
  private MediaEntity profilePicture;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<RoleEntity> roles = new ArrayList<>();

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @EqualsAndHashCode.Exclude
  private Set<UserTemplateEntity> userTemplates;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "user_media", joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "media_id"})})
  @CollectionId(column = @Column(name = "id"), type = @Type(type = "uuid-char"), generator = "UUID")
  private List<MediaEntity> uploads = new ArrayList<>();

  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  private VerificationEntity verification;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<SubscriptionEntity> subscriptions;

}
