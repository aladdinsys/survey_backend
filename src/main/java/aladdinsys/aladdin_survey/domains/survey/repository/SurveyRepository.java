package aladdinsys.aladdin_survey.domains.survey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aladdinsys.aladdin_survey.domains.survey.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

	List<Survey> findByOwner(String owner);

}
