package exceptions.core.banking.exceptions;

public class NoCardFoundException extends RuntimeException {
	public NoCardFoundException() {
		super();
	}
	
	public NoCardFoundException(String message) {
		super(message);
	}
	
	public NoCardFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NoCardFoundException(Throwable cause) {
		super(cause);
	}
}