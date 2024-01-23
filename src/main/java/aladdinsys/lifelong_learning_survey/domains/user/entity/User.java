/* (C) 2024 */
package aladdinsys.lifelong_learning_survey.domains.user.entity;

import aladdinsys.lifelong_learning_survey.domains.user.constant.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "tb_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false, unique = true, length = 20)
  private String userId;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "name", nullable = false, length = 10)
  private String name;

  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private Role role;

  @PrePersist
  public void prePersist() {
    this.role = Role.USER;
  }
}
