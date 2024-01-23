package aladdinsys.lifelong_learning_survey.domains.user.service;

import static aladdinsys.lifelong_learning_survey.global.constant.ErrorCode.*;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aladdinsys.lifelong_learning_survey.domains.user.dto.UserResponseDto;
import aladdinsys.lifelong_learning_survey.domains.user.entity.User;
import aladdinsys.lifelong_learning_survey.domains.user.repository.UserRepository;
import aladdinsys.lifelong_learning_survey.global.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;

	@Transactional(readOnly = true)
	public List<UserResponseDto> findAll() {
		List<User> users = repository.findAll();

		return users.stream()
			.map(user -> new UserResponseDto(
				user.getUserId(),
				user.getName(),
				user.getEmail(),
				user.getCode(),
				user.getRole()
			))
			.toList();
	}

	@Transactional(readOnly = true)
	public UserResponseDto myInfo(Principal principal) {
		var userId = principal.getName();
		User user = repository.findByUserId(userId)
			.orElseThrow(() -> new CustomException(NOT_FOUND_USER));

		return new UserResponseDto(
			user.getUserId(),
			user.getName(),
			user.getEmail(),
			user.getCode(),
			user.getRole()
		);
	}
}
