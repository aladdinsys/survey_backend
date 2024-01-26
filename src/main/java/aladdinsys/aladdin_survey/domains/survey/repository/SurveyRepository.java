/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.survey.repository;

import aladdinsys.aladdin_survey.domains.survey.entity.Survey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

  List<Survey> findByOwner(String owner);
}
