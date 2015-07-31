package model;

/**
 * 
 * @author SYUS
 *
 */
public class Notification {
	private boolean read;
	private String message;
	private NotificationType type;
	
	public Notification() {}
	
	public Notification(String message) {
		setMessage(message);
		setType(NotificationType.UPDATE);
	}
	
	public Notification(String message, NotificationType type) {
		this(message);
		setType(type);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead() {
		setRead(true);
	}
	
	public void setRead(boolean read) {
		this.read = read;
	}
}
