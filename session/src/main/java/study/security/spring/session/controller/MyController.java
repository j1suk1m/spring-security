package study.security.spring.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import study.security.spring.session.service.AuthService;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {

	private final AuthService authService;

	@GetMapping
	public String renderMyView(Model model) {
		String id = authService.getCurrentId();
		model.addAttribute("id", id);
		return "my";
	}

}