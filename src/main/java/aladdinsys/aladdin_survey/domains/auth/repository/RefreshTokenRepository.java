/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.auth.repository;

import aladdinsys.aladdin_survey.domains.auth.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

  Optional<RefreshToken> findByUserId(String userId);

  Optional<RefreshToken> findByToken(String token);
}
