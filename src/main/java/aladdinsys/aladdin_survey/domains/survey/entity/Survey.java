/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "surveys")
public class Survey {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false, length = 20)
  private String title;

  @Column(name = "description", nullable = false, length = 100)
  private String description;

  @Lob
  @Column(name = "content")
  private String content;

  @CreatedBy private String owner;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  private LocalDateTime publishedAt;

  @Builder
  public Survey(
      final String title, final String description, final String content, final String owner) {
    this.title = title;
    this.description = description;
    this.content = content;
    this.owner = owner;
  }

  public void update(final String title, final String description, final String content) {

    if (publishedAt != null) {
      throw new IllegalStateException("이미 게시된 설문입니다.");
    }

    this.title = title;
    this.description = description;
    this.content = content;
  }

  public void publish() {
    this.publishedAt = LocalDateTime.now();
  }
}
