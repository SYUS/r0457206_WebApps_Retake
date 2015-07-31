package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author SYUS
 *
 */
public class Person {
	private String name;
	private String lastName;
	private String nickName;
	private String email;
	private String password;
	private String status;
	private static final String EMAIL_PATTERN_REGEX = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_REGEX, Pattern.CASE_INSENSITIVE);
	
	private ArrayList<Notification> notifications;
	private ArrayList<String> friendEmails;
	private UserPresence presence = UserPresence.OFFLINE;

	public Person() {}

	public Person(String name, String lastName, String nickName, String email, String password) {
		setPresenceOffline();
		setNotifications(new ArrayList<Notification>());
		setFriendEmails(new ArrayList<String>());
		setName(name);
		setLastName(lastName);
		setNickName(nickName);
		setEmail(email);
		setPassword(password);
	}
	
	public Person(String name, String lastName, String nickName, String email, String password, String status) {
		this(name, lastName, nickName, email, password);
		setStatus(status);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new ModelException("Name should not be empty");
		}
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.isEmpty()) {
			throw new ModelException("Email should not be empty");
		}
		
		if(!validateEmail(email)) {
			throw new ModelException("Email is not in the right format: email@address.domain");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password == null || password.isEmpty()) {
			throw new ModelException("Password should not be empty");
		}
		this.password = password;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null || lastName.isEmpty()) {
			throw new ModelException("Last name should not be empty");
		}
		this.lastName = lastName;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		if (nickName == null || nickName.isEmpty()) {
			throw new ModelException("Nickname should not be empty");
		}
		this.nickName = nickName;
	}
	
	public String getPresence() {
		return getPresenceObject().getPresenceString();
	}
	
	public UserPresence getPresenceObject() {
		return presence;
	}

	public void setPresence(UserPresence presence) {
		this.presence = presence;
	}

	public ArrayList<String> getFriendEmails() {
		return friendEmails;
	}

	public void setFriendEmails(ArrayList<String> friendEmails) {
		this.friendEmails = friendEmails;
	}

	public void setPresenceOnline() {
		setPresence(UserPresence.ONLINE);
	}
	
	public void setPresenceOffline() {
		setPresence(UserPresence.OFFLINE);
	}
	
	public void setPresenceAway() {
		setPresence(UserPresence.AWAY);
	}
	
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	
	public static boolean validateEmail(String email) {
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.find();
	}
	
	//pass true to get only the unread
	public List<Notification> getNotifications(boolean onlyUnread) {
		if(onlyUnread) {
			ArrayList<Notification> onlyUnreadNotifs = new ArrayList<Notification>();
			for(Notification notif: getNotifications()) {
				if(!notif.isRead()) {
					onlyUnreadNotifs.add(notif);
				}
			}
			return onlyUnreadNotifs;
		}
		return notifications;
	}

	//pass true to get only the unread, link of notif is dependant on type
	public List<String> getNotificationMessages(boolean onlyUnread) {
		ArrayList<String> output = new ArrayList<String>();
		if(getNotifications(onlyUnread) != null) {
			for(Notification notif : getNotifications(onlyUnread)) {
				output.add(notif.getMessage());
			}
		} else {
			output.add("No new notifications");
		}
		return output;
	}
	
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(Notification notification) {
		getNotifications().add(notification);
	}

	public void addFriend(String friendEmail) {
		if(getFriendEmails().contains(friendEmail)) {
			throw new ModelException(friendEmail + " is already a friend!");
		}
		getFriendEmails().add(friendEmail);
	}
	
	public void deleteFriend(String friendEmail) {
		if(!getFriendEmails().contains(friendEmail)) {
			throw new ModelException(friendEmail + " is not in your friends list");
		}
		getFriendEmails().remove(friendEmail);
	}
	
	public void setHashedPassword(String password) {
		if (password == null || password.isEmpty()) {
			throw new ModelException("Password should not be empty");
		}
		this.password = hashPassword(password);
	}
	
	public boolean isPasswordCorrect(String password) {
		//String hashedPassword = hashPassword(password);
		//return getPassword().equals(hashedPassword);
		return getPassword().equals(password);
	}
	
	private String hashPassword(String password) {
		String encrypted = password;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
		} catch (NoSuchAlgorithmException e) {
			throw new ModelException(e.getMessage(), e);
		}
		//TODO Salt
		digest.update(password.getBytes());
		byte[] encryptedBytes = new byte[40];
		encryptedBytes= digest.digest();
		encrypted = new BigInteger(1, encryptedBytes).toString(16);
		return encrypted;
	}
}
