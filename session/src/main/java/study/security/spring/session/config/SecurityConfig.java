package study.security.spring.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 설정
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/", "/login", "/join").permitAll()
				.requestMatchers("/admin").hasRole("ADMIN")
				.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated() // 가장 일반적인 경우를 마지막에 처리
			);

		http
			.formLogin((auth) -> auth.loginPage("/login") // 로그인 페이지
				.loginProcessingUrl("/login") // 로그인 폼 액션 URL
				.permitAll()
			);

		http
			.csrf((auth) -> auth.disable()); // CSRF 토큰 없이 로그인을 진행할 수 있도록 임시 비활성화

		return http.build();
	}

}