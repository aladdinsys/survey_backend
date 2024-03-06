package aladdinsys.aladdin_survey.domains.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import aladdinsys.aladdin_survey.domains.auth.entity.ApiKey;


public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

	ApiKey findByKey(String key);
}
