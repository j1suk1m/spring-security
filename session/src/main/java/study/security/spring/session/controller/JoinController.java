package study.security.spring.session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import study.security.spring.session.dto.UserJoinRequest;
import study.security.spring.session.service.JoinService;

@Controller
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

	private final JoinService joinService;

	@GetMapping
	public String renderJoinView() {
		return "join";
	}

	@PostMapping
	public String join(@Valid UserJoinRequest requestDto) {
		joinService.join(requestDto);
		return "redirect:/login";
	}

}