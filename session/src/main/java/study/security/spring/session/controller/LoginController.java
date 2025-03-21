package study.security.spring.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String renderLoginView(
		@RequestParam(required = false, name = "error") String errorMessage,
		Model model
	) {
		if (errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
		}

		return "login";
	}

}