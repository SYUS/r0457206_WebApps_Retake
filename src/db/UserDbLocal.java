package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import model.Notification;
import model.Person;

/**
 * 
 * @author SYUS
 *
 */
public class UserDbLocal implements IUserDb{
	
	private HashMap<String, Person> userDbLocal;

	public UserDbLocal(){}
	
	public UserDbLocal(Properties properties) {
		try {
			userDbLocal = new HashMap<String, Person>();
			populate();
		} catch (Exception e) {
			throw new DbException("Failed to create in memory database!");
		}
	}

	private void populate() {
		//Create dummy persons
		Person user1 = new Person("user1", "user1Lastname", "user1Nickname", "user1@gmail.com", "password", "user2 is on my mind");
		Person user2 = new Person("user2", "user2Lastname", "user2Nickname", "user2@gmail.com", "password", "user3 is on my mind");
		Person user3 = new Person("user3", "user3Lastname", "user3Nickname", "user3@gmail.com", "password", "user4 is on my mind");
		Person user4 = new Person("user4", "user4Lastname", "user4Nickname", "user4@gmail.com", "password", "user5 is on my mind");
		Person user5 = new Person("user5", "user5Lastname", "user5Nickname", "user5@gmail.com", "password", "user6 is on my mind");
		Person user6 = new Person("user6", "user6Lastname", "user6Nickname", "user6@gmail.com", "password", "user7 is on my mind");
		Person user7 = new Person("user7", "user7Lastname", "user7Nickname", "user7@gmail.com", "password", "user8 is on my mind");
		Person user8 = new Person("user8", "user8Lastname", "user8Nickname", "user8@gmail.com", "password", "user9 is on my mind");
		Person user9 = new Person("user9", "user9Lastname", "user9Nickname", "user9@gmail.com", "password");

		//Set dummy persons presences
		user1.setPresenceOffline();
		user2.setPresenceOffline();
		user3.setPresenceOffline();
		user4.setPresenceOffline();
		user5.setPresenceOffline();
		user6.setPresenceOffline();
		user7.setPresenceOffline();
		user8.setPresenceOffline();
		user9.setPresenceOffline();
		
		//Give dummy persons friends
		user1.addFriend(user2.getEmail());
		user1.addFriend(user9.getEmail());	
		
		user2.addFriend(user3.getEmail());
		user2.addFriend(user1.getEmail());
		
		user3.addFriend(user4.getEmail());
		user3.addFriend(user2.getEmail());
		
		user4.addFriend(user5.getEmail());
		user4.addFriend(user3.getEmail());
		
		user5.addFriend(user6.getEmail());
		user5.addFriend(user4.getEmail());
		
		user6.addFriend(user7.getEmail());
		user6.addFriend(user5.getEmail());
		
		user7.addFriend(user8.getEmail());
		user7.addFriend(user6.getEmail());
		
		user8.addFriend(user9.getEmail());
		user8.addFriend(user7.getEmail());
		
		user9.addFriend(user1.getEmail());
		user9.addFriend(user8.getEmail());
		
		//Add dummy persons to local DB for testing etc
		userDbLocal.put(user1.getEmail(), user1);
		userDbLocal.put(user2.getEmail(), user2);
		userDbLocal.put(user3.getEmail(), user3);
		userDbLocal.put(user4.getEmail(), user4);
		userDbLocal.put(user5.getEmail(), user5);
		userDbLocal.put(user6.getEmail(), user6);
		userDbLocal.put(user7.getEmail(), user7);
		userDbLocal.put(user8.getEmail(), user8);
		userDbLocal.put(user9.getEmail(), user9);
	}

	public void delete(String email) {
		if (email == null) {
			throw new DbException("Nothing to delete.");
		}
		if (!userDbLocal.containsKey(email)) {
			throw new DbException("Nothing to delete");
		}
		userDbLocal.remove(email);
	}

	public void update(Person person) {
		if (person == null) {
			throw new DbException("No user info received to update.");
		}
		if (!userDbLocal.containsValue(person)) {
			throw new DbException("Requested update user not existant.");
		}
		userDbLocal.remove(person.getEmail());//necessary?
		userDbLocal.put(person.getEmail(), person);
	}

	public void create(Person person) {
		if (person == null) {
			throw new DbException("Nothing to add.");
		}
		if (userDbLocal.containsValue(person)) {
			throw new DbException("User already exists in database");
		}
		userDbLocal.put(person.getEmail(), person);
	}

	public Person read(String email) {
		/*
		if (!userDbLocal.containsKey(email)) {
			throw new DbException("Nothing to get");
		}
		*/
		Person person = null;
		person = userDbLocal.get(email);
		return person;
	}

	public List<Person> readAll() {
		List<Person> persons = new ArrayList<Person>();
		if(userDbLocal.isEmpty()) {
			throw new DbException("Nothing to get");
		}
		for (Person person : userDbLocal.values()) {
			persons.add(person);
		}
		return persons;
	}
	
	public void addPersonFriend(Person person, String friendEmail) {
		if(person == null) {
			throw new DbException("No user to add friend to.");
		}
		if(friendEmail == null || friendEmail.equals("")) {
			throw new DbException("Nothing to add as a friend.");
		}
		userDbLocal.get(person.getEmail()).addFriend(friendEmail);
	}
	
	public void deletePersonFriend(Person person, String friendEmail) {
		if(person == null) {
			throw new DbException("No user to remove friend from.");
		}
		if(friendEmail == null || friendEmail.equals("")) {
			throw new DbException("Nothing to remove as a friend.");
		}
		userDbLocal.get(person.getEmail()).deleteFriend(friendEmail);
	}
	
	public List<Person> getPersonFriends(Person person) {
		if(person == null) {
			throw new DbException("No user to get friends for");
		}
		List<Person> friends = new ArrayList<Person>();
		for(Person user: userDbLocal.values()) {
			if(person.getFriendEmails().contains(user.getEmail())) {
				friends.add(user);
			}
		}
		return friends;
	}
	
	public List<Notification> getPersonNotifications(Person person, boolean onlyUnread) {
		if(person == null) {
			throw new DbException("No user to get notifications from.");
		}
		return userDbLocal.get(person.getEmail()).getNotifications(onlyUnread);
	}
	
	public List<String> getPersonNotificationMessages(Person person, boolean onlyUnread) {
		if(person == null) {
			throw new DbException("No user to get notification messages from.");
		}
		return userDbLocal.get(person.getEmail()).getNotificationMessages(onlyUnread);
	}
	
	public void addNotificationToPerson(Person person, Notification notification) {
		if(person == null) {
			throw new DbException("No user to add notification to.");
		}
		if(notification == null || notification.getMessage() == null || notification.getMessage().equals("")) {
			throw new DbException("No notification to add.");
		}
		userDbLocal.get(person.getEmail()).addNotification(notification);
	}
	
	public void setPersonPresenceOnline(Person person) {
		if(person == null) {
			throw new DbException("No user to change presence to online.");
		}
		userDbLocal.get(person.getEmail()).setPresenceOnline();
	}
	
	public void setPersonPresenceAway(Person person) {
		if(person == null) {
			throw new DbException("No user to change presence to away.");
		}
		userDbLocal.get(person.getEmail()).setPresenceAway();
	}
		
	public void setPersonPresenceOffline(Person person) {
		if(person == null) {
			throw new DbException("No user to change presence to offline.");
		}
		userDbLocal.get(person.getEmail()).setPresenceOffline();
	}
		
	public void updatePersonStatus(Person person) {
		if(person == null) {
			throw new DbException("No user to change status.");
		}
		userDbLocal.get(person.getEmail()).setStatus(person.getStatus());
	}
}
