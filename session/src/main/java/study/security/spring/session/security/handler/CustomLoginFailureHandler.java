package study.security.spring.session.security.handler;

import static study.security.spring.session.exception.ErrorMessage.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException ex
	) throws IOException, ServletException {
		String errorMessage = LOGIN_FAILED.getMessage();

		if (ex instanceof BadCredentialsException) {
			errorMessage = BAD_CREDENTIALS.getMessage();
		} else if (ex instanceof UsernameNotFoundException) {
			errorMessage = ex.getMessage();
		}

		errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
		response.sendRedirect("/login?error=" + errorMessage);
	}

}