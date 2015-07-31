package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import db.UserDbFacade;
import model.Notification;
import model.Person;

/**
 * 
 * @author SYUS
 *
 */
public class UserSystem {
	private UserDbFacade userDb;
	private Map<String, MenuItem> menuItems = new HashMap<String, MenuItem>();

	public UserSystem(){}
	
	public void init(Properties properties) {
		userDb = new UserDbFacade(properties);
		createMenu();
	}
	
	public String getUserDbType() {
		return userDb.getUserDbType();
	}
	
	public void create(Person user) {
		userDb.create(user);
	}
	
	public Person read(String email) {
		return userDb.read(email);
	}

	public List<Person> readAll() {
		return userDb.readAll();
	}
	
	public void update(Person user) {
		userDb.update(user);
	}
	
	public void delete(String email) {
		userDb.delete(email);
	}
	
	public List<Person> searchUsers(Person user, String generalString, String name, String lastName, String nickName) {
		List<Person> resultUsers = new ArrayList<Person>();
		
		if(!generalString.isEmpty() || generalString != null) {
			for(Person p: readAll()) {
				if(!user.getEmail().equals(p.getEmail()) && !user.getFriendEmails().contains(p.getEmail())) {
					if(!resultUsers.contains(p)) {
						if(p.getName().contains(generalString) || p.getName().equals(generalString)
								|| p.getLastName().contains(generalString) || p.getLastName().equals(generalString)
								|| p.getNickName().contains(generalString) || p.getNickName().equals(generalString)) {
							resultUsers.add(p);
						}
					}
				}
			}
		}
		
		if(!name.isEmpty() || name != null) {
			for(Person p: readAll()) {
				if(!user.getEmail().equals(p.getEmail()) && !user.getFriendEmails().contains(p.getEmail())) {
					if(!resultUsers.contains(p)) {
						if(p.getName().equals(name) || p.getName().contains(name)) {
							resultUsers.add(p);
						}
					}
				}
			}
		}
		
		if(!lastName.isEmpty() || lastName != null) {
			for(Person p: readAll()) {
				if(!user.getEmail().equals(p.getEmail()) && !user.getFriendEmails().contains(p.getEmail())) {
					if(!resultUsers.contains(p)) {
						if(p.getLastName().equals(lastName) || p.getLastName().contains(lastName)) {
							resultUsers.add(p);
						}
					}
				}
			}
		}
		
		if(!nickName.isEmpty() || nickName != null) {
			for(Person p: readAll()) {
				if(!user.getEmail().equals(p.getEmail()) && !user.getFriendEmails().contains(p.getEmail())) {
					if(!resultUsers.contains(p)) {
						if(p.getNickName().equals(nickName) || p.getNickName().contains(nickName)) {
							resultUsers.add(p);
						}
					}
				}	
			}
		}
		
		return resultUsers;
	}
	
	public void addPersonFriend(Person person, String friendEmail) {
		userDb.addPersonFriend(person, friendEmail);
	}
	
	public void deletePersonFriend(Person person, String friendEmail){
		userDb.deletePersonFriend(person, friendEmail);
	}
	
	public List<Person> getPersonFriends(Person person) {
		return userDb.getPersonFriends(person);
	}
	
	public List<Notification> getPersonNotifications(Person person, boolean onlyUnread) {
		return userDb.getPersonNotifications(person, onlyUnread);
	}
	
	public List<String> getPersonNotificationMessages(Person person, boolean onlyUnread) {
		return userDb.getPersonNotificationMessages(person, onlyUnread);
	}
	
	public void addNotificationToPerson(Person person, Notification notification) {
		userDb.addNotificationToPerson(person, notification);
	}
	
	public void setPersonPresenceOnline(Person person) {
		userDb.setPersonPresenceOnline(person);
	}
	
	public void setPersonPresenceAway(Person person) {
		userDb.setPersonPresenceAway(person);
	}
		
	public void setPersonPresenceOffline(Person person) {
		userDb.setPersonPresenceOffline(person);
	}
		
	public void updatePersonStatus(Person person) {
		userDb.updatePersonStatus(person);
	}
	
	public List<Person> readUserFriends(Person user) {
		List<Person> friends = new ArrayList<Person>();
		for(Person p:userDb.readAll()) {
			if(user.getFriendEmails().contains(p.getEmail())) {
				friends.add(p);
			}
		}
		return friends;
	}
	
	public List<MenuItem> getMenu(boolean authenticated) {
		List<MenuItem> items = new ArrayList<MenuItem>();
		for (MenuItem item : menuItems.values()) {
			if (item.isAuthenticationNeeded() == authenticated) {
				items.add(item);
			}
		}
		return items;
	}

	public boolean isAuthenticationNeeded(String action) {
		MenuItem item = menuItems.get(action);
		boolean authenticationNeeded;
		if(item == null){
			authenticationNeeded = false;
		} else {
			authenticationNeeded = item.isAuthenticationNeeded();
		}
		return authenticationNeeded;
	}
	
	public Person getAuthenticatedUser(String email, String password){
		Person user = userDb.read(email);
		if(user!=null && !user.isPasswordCorrect(password)){
			user = null;
		}
		return user;
	}

	private void createMenu() {
		//new MenuItem(action, description, authNeeded)
		
		//auth not needed
		menuItems.put("navigateToLogin", new MenuItem("navigateToLogin", "Log in", false));

		//auth needed
		menuItems.put("newUser", new MenuItem("newUser", "Register", true));
		menuItems.put("friendOverview", new MenuItem("friendOverview", "Friends", true));
		menuItems.put("userOverview", new MenuItem("userOverview", "Users", true));
		menuItems.put("logout", new MenuItem("logout", "Log out", true));

	}
}
