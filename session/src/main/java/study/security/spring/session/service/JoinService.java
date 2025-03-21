package study.security.spring.session.service;

import static study.security.spring.session.exception.ErrorMessage.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import study.security.spring.session.dto.UserJoinRequest;
import study.security.spring.session.entity.User;
import study.security.spring.session.exception.DuplicateUsernameException;
import study.security.spring.session.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class JoinService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public void join(UserJoinRequest request) {
		String username = request.username();

		// 회원 중복 검증
		if (userRepository.existsByUsername(username)) {
			throw new DuplicateUsernameException(DUPLICATE_USERNAME.getMessage(username));
		}

		String encodedPassword = bCryptPasswordEncoder.encode(request.password());
		String role = "ROLE_USER";

		// 회원 생성
		User user = new User(username, encodedPassword, role);

		// 회원 저장
		userRepository.save(user);
	}

}