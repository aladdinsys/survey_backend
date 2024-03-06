/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.repository;

import aladdinsys.aladdin_survey.domains.auth.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

  ApiKey findByKey(String key);
}
