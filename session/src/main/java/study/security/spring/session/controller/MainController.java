package study.security.spring.session.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import study.security.spring.session.service.AuthService;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

	private final AuthService authService;

	@GetMapping
	public String renderMainView(Model model) {
		String id = authService.getCurrentId();
		List<String> roles = authService.getCurrentRoles();

		model.addAttribute("id", id);
		model.addAttribute("roles", roles);

		return "main";
	}

}