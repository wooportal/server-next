package app.wooportal.server.components.messaging.message;

import java.io.Serial;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.components.messaging.readReceipt.ReadReceiptEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class MessageEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ChatEntity chat;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity media;

  @ManyToOne(fetch = FetchType.LAZY)
  private MessageEntity parent;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "message")
  private Set<ReadReceiptEntity> readReceipts;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ParticipantEntity participant;

}
