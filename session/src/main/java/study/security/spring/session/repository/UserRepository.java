package study.security.spring.session.repository;

import static study.security.spring.session.exception.ErrorMessage.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import study.security.spring.session.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);

	default User findByUsernameOrElseThrow(String username) {
		return findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND.getMessage(username)));
	}

}