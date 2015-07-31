package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Notification;
import model.Person;

/**
 * TODO OUTDATED CLASS, UPDATE NEW PROPERTIES
 * @author SYUS
 *
 */

public class UserDb implements IUserDb{
	private Connection connection;
	private PreparedStatement statement;
	private Properties properties;

	public UserDb(){}
	
	public UserDb(Properties properties) {
		try {
			Class.forName("org.postgresql.Driver");
			setProperties(properties);
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public void delete(String email) {
		if (email == null) {
			throw new DbException("Nothing to delete.");
		}
		String sql = "DELETE FROM web3.person WHERE email=?";
		try {
			initializeStatement(sql);
			statement.setString(1, email);
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e);
		} finally {
			closeConnection();
		}
	}

	public void update(Person person) {
		if (person == null) {
			throw new DbException("Nothing to update.");
		}
		String sql = "UPDATE web3.person SET password=?, name=?, lastname=?, nickname=? WHERE email=?";
		try {
			initializeStatement(sql);
			statement.setString(1, person.getPassword());
			statement.setString(2, person.getName());
			statement.setString(3, person.getLastName());
			statement.setString(4, person.getNickName());
			statement.setString(5, person.getEmail());
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e);
		} finally {
			closeConnection();
		}
	}

	public void create(Person person) {
		if (person == null) {
			throw new DbException("Nothing to add.");
		}
		String sql = "INSERT INTO web3.person (name, lastname, nickname, email, password) VALUES (?, ?, ?, ?, ?)";
		try {
			initializeStatement(sql);
			statement.setString(1, person.getName());
			statement.setString(2, person.getLastName());
			statement.setString(3, person.getNickName());
			statement.setString(4, person.getEmail());
			statement.setString(5, person.getPassword());
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e);
		} finally {
			closeConnection();
		}
	}

	public Person read(String email) {
		Person person = null;
		try {
			String sql = "SELECT * FROM web3.person WHERE email=?";
			initializeStatement(sql);
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				String lastName = result.getString("lastname");
				String nickName = result.getString("nickname");
				String password = result.getString("password");
				person = new Person(name, lastName, nickName, email, password);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
		return person;
	}

	public List<Person> readAll() {
		List<Person> persons = new ArrayList<Person>();
		try {
			String sql = "SELECT * FROM web3.person";
			initializeStatement(sql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String name = result.getString("name");
				String lastName = result.getString("lastname");
				String nickName = result.getString("nickname");
				String email = result.getString("email");
				String password = result.getString("password");
				Person person = new Person(name, lastName, nickName, email, password);
				persons.add(person);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
		return persons;
	}

	private void initializeStatement(String sql) throws SQLException {
		String url = getProperties().getProperty("url");
		connection = DriverManager.getConnection(url, getProperties());
		statement = connection.prepareStatement(sql);
	}

	private void closeConnection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	private Properties getProperties() {
		return properties;
	}

	private void setProperties(Properties properties) {
		if(properties == null){
			throw new DbException("No properties given");
		}
		this.properties = properties;
	}

	@Override
	public void addPersonFriend(Person person, String friendEmail) {
		// TODO Auto-generated method stub
		throw new DbException("addPersonFriend not yet implemented");
	}

	@Override
	public void deletePersonFriend(Person person, String friendEmail) {
		// TODO Auto-generated method stub
		throw new DbException("deletePersonFriend not yet implemented");
	}

	@Override
	public List<Notification> getPersonNotifications(Person person, boolean onlyUnread) {
		// TODO Auto-generated method stub
		throw new DbException("getPersonNotifications not yet implemented");
	}

	@Override
	public List<String> getPersonNotificationMessages(Person person, boolean onlyUnread) {
		// TODO Auto-generated method stub
		throw new DbException("getPersonNotificationMessages not yet implemented");
	}

	@Override
	public void addNotificationToPerson(Person person, Notification notification) {
		// TODO Auto-generated method stub
		throw new DbException("addNotificationToPerson not yet implemented");
	}

	@Override
	public void setPersonPresenceOnline(Person person) {
		// TODO Auto-generated method stub
		throw new DbException("setPersonPresenceOnline not yet implemented");
	}

	@Override
	public void setPersonPresenceAway(Person person) {
		// TODO Auto-generated method stub
		throw new DbException("setPersonPresenceAway not yet implemented");
	}

	@Override
	public void setPersonPresenceOffline(Person person) {
		// TODO Auto-generated method stub
		throw new DbException("setPersonPresenceOffline not yet implemented");
	}

	@Override
	public void updatePersonStatus(Person person) {
		// TODO Auto-generated method stub
		throw new DbException("updatePersonStatus not yet implemented");
	}

	@Override
	public List<Person> getPersonFriends(Person person) {
		// TODO Auto-generated method stub
		throw new DbException("getPersonFriends not yet implemented");
	}
}
