package study.security.spring.session.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

	DUPLICATE_USERNAME("이미 존재하는 아이디입니다: %s"),
	USERNAME_NOT_FOUND("존재하지 않는 아이디입니다: %s"),
	LOGIN_FAILED("로그인에 실패했습니다."),
	BAD_CREDENTIALS("아이디와 패스워드를 확인하세요.");

	private final String message;

	public String getMessage(String arg) {
		return String.format(message, arg);
	}

}