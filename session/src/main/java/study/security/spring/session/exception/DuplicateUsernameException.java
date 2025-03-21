package study.security.spring.session.exception;

public class DuplicateUsernameException extends RuntimeException {

	public DuplicateUsernameException(String message) {
		super(message);
	}

}