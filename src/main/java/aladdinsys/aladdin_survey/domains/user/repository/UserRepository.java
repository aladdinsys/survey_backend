/* (C) 2024 AladdinSystem License */
package aladdinsys.aladdin_survey.domains.user.repository;

import aladdinsys.aladdin_survey.domains.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserId(String userid);

  Optional<User> findByEmail(String email);
}
