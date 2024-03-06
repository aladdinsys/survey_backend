/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.repository;

import aladdinsys.aladdin_survey.domains.auth.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

  ApiKey findByKey(String key);
}
