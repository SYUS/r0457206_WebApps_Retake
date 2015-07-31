package service;

/**
 * 
 * @author SYUS
 *
 */
public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable exception) {
		super(message, exception);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable exception) {
		super(exception);
	}

}
