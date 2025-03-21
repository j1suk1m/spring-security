package study.security.spring.session.dto;

import jakarta.validation.constraints.NotBlank;

public record UserJoinRequest(

	@NotBlank(message = "아이디는 필수로 입력해야 합니다.")
	String username,

	@NotBlank(message = "패스워드는 필수로 입력해야 합니다.")
	String password

) {
}