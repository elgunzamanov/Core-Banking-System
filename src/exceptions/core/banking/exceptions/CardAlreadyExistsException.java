package exceptions.core.banking.exceptions;

public class CardAlreadyExistsException extends RuntimeException {
	public CardAlreadyExistsException() {
		super();
	}
	
	public CardAlreadyExistsException(String message) {
		super(message);
	}
	
	public CardAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CardAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}