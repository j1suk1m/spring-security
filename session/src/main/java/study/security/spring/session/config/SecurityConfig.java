package study.security.spring.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import study.security.spring.session.security.handler.CustomLoginFailureHandler;
import study.security.spring.session.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 설정
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomLoginFailureHandler authenticationFailureHandler;
	private final CustomUserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/login", "/join").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("/my/**").hasAnyRole("USER")
				.anyRequest().authenticated() // 가장 일반적인 경우를 마지막에 처리
			);

		http
			.formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지
				.loginProcessingUrl("/login") // 로그인 폼 액션 URL
				.failureHandler(authenticationFailureHandler) // 로그인 실패 처리
				.permitAll()
			);

		http
			.sessionManagement((auth) -> auth // 세션 관리
				.maximumSessions(1) // 허용 가능한 최대 세션 개수 // 동일 아이디에 대한 다중 로그인 횟수
				.maxSessionsPreventsLogin(true) // 최대 세션 개수를 초과한 경우 처리
												// true: 다중 로그인 차단, false(default): 기존 세션 만료
			);

		http
			.sessionManagement((auth) -> auth
				.sessionFixation().changeSessionId()); // 로그인 시 동일 세션에 대한 세션 아이디 변경

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(bCryptPasswordEncoder());

		// UsernameNotFoundException과 BadCredentialsException을 별개로 처리
		provider.setHideUserNotFoundExceptions(false);

		return provider;
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		return RoleHierarchyImpl.withDefaultRolePrefix() // ROLE_ 접두사 자동 추가
			.role("ADMIN").implies("USER") // ROLE_ADMIN > ROLE_USER
			.build();
	}

}