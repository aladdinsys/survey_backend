/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.repository;

import aladdinsys.aladdin_survey.domains.survey.entity.Survey;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface SurveyRepository extends JpaRepository<Survey, Long> {

  List<Survey> findByOwner(String owner);

  Optional<Survey> findSurveyByPublishId(String publishId);
}
