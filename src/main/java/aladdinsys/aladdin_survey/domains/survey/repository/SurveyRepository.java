package aladdinsys.aladdin_survey.domains.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aladdinsys.aladdin_survey.domains.survey.entity.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
