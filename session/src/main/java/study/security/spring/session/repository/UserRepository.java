package study.security.spring.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.security.spring.session.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByUsername(String username);

}