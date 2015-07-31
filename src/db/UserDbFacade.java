package db;

import java.util.List;
import java.util.Properties;

import model.Notification;
import model.Person;

/**
 * 
 * @author SYUS
 *
 */
public class UserDbFacade{
	
	private IUserDb userDb;
	private UserDbType userDbType;
	
	public UserDbFacade(){}
	
	public UserDbFacade(Properties properties) {
		createUserDb(properties);
	}

	private void createUserDb(Properties properties) {
		
		/*
		 * try {
			userDb = new UserDb(properties);
			userDbType = UserDbType.DATABASE;
		} catch (DbException e) {
			userDb = new UserDbLocal(properties);
			userDbType = UserDbType.LOCAL;
		}
		 */
		userDb = new UserDbLocal(properties);
		userDbType = UserDbType.LOCAL;
	}

	public String getUserDbType(){
		return userDbType.name();
	}
	
	public void delete(String email) {
		userDb.delete(email);
	}

	public void update(Person person) {
		userDb.update(person);
	}

	public void create(Person person) {
		userDb.create(person);
	}

	public Person read(String email) {
		return userDb.read(email);
	}

	public List<Person> readAll() {
		return userDb.readAll();
	}
	
	public void addPersonFriend(Person person, String friendEmail) {
		userDb.addPersonFriend(person, friendEmail);
	}
	
	public void deletePersonFriend(Person person, String friendEmail) {
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
	
}
