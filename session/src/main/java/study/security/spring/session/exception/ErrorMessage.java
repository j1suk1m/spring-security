package study.security.spring.session.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessage {

	DUPLICATE_USERNAME("이미 존재하는 아이디입니다: %s");

	private final String message;

	public String getMessage(String arg) {
		return String.format(message, arg);
	}

}