package aladdinsys.lifelong_learning_survey.domains.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aladdinsys.lifelong_learning_survey.domains.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUserid(String userid);
	Optional<User> findByEmail(String email);

}
