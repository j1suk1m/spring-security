package study.security.spring.session.global;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import study.security.spring.session.exception.DuplicateUsernameException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateUsernameException.class)
	public String handleDuplicateUserException(DuplicateUsernameException ex, Model model) {
		model.addAttribute("errorMessage", ex.getMessage());
		return "join";
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, Model model) {
		List<String> errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map((error) -> error.getDefaultMessage())
			    .toList();

		model.addAttribute("errorMessage", errorMessage);
		return "join";
	}

}