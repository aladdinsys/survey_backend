package aladdinsys.aladdin_survey.domains.survey.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import aladdinsys.aladdin_survey.domains.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

	@Column(name = "content")
	private String content;

	@CreatedBy
	@JoinColumn(name = "user_id")
	@Column(nullable = false, updatable = false)
	@ManyToOne(optional = false, targetEntity = User.class, fetch = FetchType.LAZY)
	private String owner;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	private LocalDateTime publishedAt;

	public Survey(final String title, final String description, final String content, final String owner) {
		this.title = title;
		this.description = description;
		this.content = content;
		this.owner = owner;
	}

	public void update(final String title, final String description, final String content) {

		if(publishedAt != null) {
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
