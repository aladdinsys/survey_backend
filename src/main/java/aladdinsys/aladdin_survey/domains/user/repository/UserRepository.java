/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.user.repository;

import aladdinsys.aladdin_survey.domains.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserId(String userid);

  Optional<User> findByEmail(String email);
}
