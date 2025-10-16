package dev.caobaoqi6040.backend.modules.user.exception;

public class UserAlreadyExistException extends RuntimeException {
	public UserAlreadyExistException(String message) {
		super(message);
	}
}
