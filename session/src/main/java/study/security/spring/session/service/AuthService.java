package study.security.spring.session.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public String getCurrentId() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public List<String> getCurrentRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication.getAuthorities().stream()
			.map((authority) -> authority.getAuthority())
			.toList();
	}

}