package app.wooportal.server.core.security.components.role.application;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.role.RoleEntity;
import app.wooportal.server.core.security.components.role.application.translation.RoleApplicationTranslatableEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
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
@Table(name = "role_applications")
public class RoleApplicationEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  private Boolean accepted;
  
  @Transient
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private RoleEntity role;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  private Set<RoleApplicationTranslatableEntity> translatables;
}
