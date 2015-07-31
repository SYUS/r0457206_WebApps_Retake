package model;

/**
 * 
 * @author SYUS
 *
 */
public enum UserPresence {
	ONLINE("Online"),
	AWAY("Away"),
	OFFLINE("Offline");
	
	private final String presenceString;
	
	UserPresence(String presenceString) {
		this.presenceString = presenceString;
	}
	
	public String getPresenceString() {
		return presenceString;
	}
}
