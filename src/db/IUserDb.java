package db;

import java.util.List;

import model.Notification;
import model.Person;

/**
 * 
 * @author SYUS
 *
 */
public interface IUserDb {
	//CRUD
	public void create(Person person);
	public Person read(String email);
	public List<Person> readAll();
	public void update(Person person);
	public void delete(String email);
	
	//Person friend management
	public void addPersonFriend(Person person, String friendEmail);
	public void deletePersonFriend(Person person, String friendEmail);
	public List<Person> getPersonFriends(Person person);
	
	//Person notification management
	public List<Notification> getPersonNotifications(Person person, boolean onlyUnread);
	public List<String> getPersonNotificationMessages(Person person, boolean onlyUnread);
	public void addNotificationToPerson(Person person, Notification notification);
	
	//here? Person presence
	public void setPersonPresenceOnline(Person person);
	public void setPersonPresenceAway(Person person);
	public void setPersonPresenceOffline(Person person);
	
	//here? Person status
	public void updatePersonStatus(Person person);
}
