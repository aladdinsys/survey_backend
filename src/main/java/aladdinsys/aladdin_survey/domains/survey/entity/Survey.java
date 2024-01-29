/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.entity;

import static aladdinsys.aladdin_survey.global.constant.ErrorCode.*;

import aladdinsys.aladdin_survey.global.exception.CustomException;
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

  public void update(String title, String description, String content) {

    if (publishedAt != null) {
      throw new CustomException(NOT_ACCEPTABLE_SURVEY);
    }

    if(StringUtils.hasLength(title)) {
		this.title = title;
	}

    if(StringUtils.hasLength(description)) {
		this.description = description;
	}

    if(StringUtils.hasLength(content)) {
		this.content = content;
	}

  }

  public void publish() {

    if (publishedAt != null) {
      throw new CustomException(NOT_ACCEPTABLE_SURVEY);
    }

    this.publishedAt = LocalDateTime.now();
  }
}
