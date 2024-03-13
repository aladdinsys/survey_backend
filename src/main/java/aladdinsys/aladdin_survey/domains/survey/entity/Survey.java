/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

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

  @Column(columnDefinition = "TEXT", name = "content")
  private String content;

  @Column(name = "publish_id", length = 36, unique = true)
  private String publishId;

  @Embedded
  @AttributeOverride(name = "x", column = @Column(name = "center_x"))
  @AttributeOverride(name = "y", column = @Column(name = "center_y"))
  private Spatial center;

  @CreatedBy private String owner;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate private LocalDateTime updatedAt;

  private LocalDateTime publishedAt;

  @Builder
  public Survey(
      final String title,
      final String description,
      final String content,
      final Spatial center,
      final String owner) {
    this.title = title;
    this.description = description;
    this.content = content;
    this.center = center;
    this.owner = owner;
  }

  public void update(String title, String description, String content, Spatial center) {

    // FIXME 나중에 배포 전에 수정 해야 함
    // if (publishedAt != null) {
    //   throw new CustomException(NOT_ACCEPTABLE_SURVEY);
    // }

    if (StringUtils.hasLength(title)) {
      this.title = title;
    }

    if (StringUtils.hasLength(description)) {
      this.description = description;
    }

    if (StringUtils.hasLength(content)) {
      this.content = content;
    }

    if (center != null) {
      this.center = center;
    }
  }

  public void publish() {
    UUID uuid = UUID.randomUUID();
    this.publishId = uuid.toString();
    this.publishedAt = LocalDateTime.now();
  }
}
