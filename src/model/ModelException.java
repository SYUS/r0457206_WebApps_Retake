package model;

/**
 * 
 * @author SYUS
 *
 */
public class ModelException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ModelException () {
		super();
	}
	
	public ModelException (String message) {
		super(message);
	}
	
	public ModelException (String message, Throwable exception) {
		super(message, exception);
	}

}
