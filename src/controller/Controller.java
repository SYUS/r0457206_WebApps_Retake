package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import service.MenuItem;
import service.UserSystem;
import service.UserSystemFactory;


/**
 * TODO: After logging in and registering another new user, the new user can not log in and will cause an exception
 * 		 Problem may lie in the registering or login sequence, or the database from the userservice is not updated
 * @author SYUS
 *
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserSystem userService;

	public Controller() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		Properties properties = new Properties();
		ServletContext context = getServletContext();
        Enumeration<String> parameterNames = context.getInitParameterNames();
		while (parameterNames.hasMoreElements()){
        	String propertyName = parameterNames.nextElement();
        	properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }
        
		//userService = new UserSystem(properties);
		userService = UserSystemFactory.getInstance();
		userService.init(properties);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		/*
		String action = request.getParameter("action");
		String async = request.getParameter("async");
		
		if (action != null && async != null) {
			if(async.equals("true")) {
				processRequestAsync(request, response);
				showFriendsAsyncJSON(request, response);
			} else {
				processRequest(request, response);
			}
		} else {
			processRequest(request, response);
		}
		*/
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequestAsync(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
	}
	
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String destination = "index.jsp";
		String action = request.getParameter("action");
		String async = request.getParameter("async");
		
		if (!isUserAuthenticated(request) 
				&& userService.isAuthenticationNeeded(action)) {
			destination = navigateToLogin(request, response);
		} else {
			if (action == null) {
				destination = "index.jsp";
			} else if (action.equals("navigateToLogin")) {
				destination = navigateToLogin(request, response);
			} else if (action.equals("login")) {
				destination = logIn(request, response);
			} else if (action.equals("logout")) {
				destination = logOut(request, response);
			} else if (action.equals("userOverview")) {
				destination = showUsers(request, response);
			} else if (action.equals("newUser")) {
				destination = newUser(request, response);
			} else if (action.equals("deleteUser")) {
				destination = deleteUser(request, response);
			} else if (action.equals("editUser")) {
				destination = editUser(request, response);
			} else if (action.equals("saveUser")) {
				destination = saveUser(request, response);
			} else if (action.equals("register")) {
				destination = register(request, response);
			} else if (action.equals("friendOverview")) {
				destination = showFriendsAsync(request, response);
				//destination = showFriends(request, response, new ArrayList<String>());//probably not a nice way to do this
			} else if (action.equals("openChat")) {
				destination = openChat(request, response);
			} else if (action.equals("saveStatus")) {
				destination = saveStatus(request, response);
			} else if (action.equals("friendSearch")) {
				destination = searchUsers(request, response);
			} else if (action.equals("friendOverviewAsync")) {
				showFriendsAsyncJSON(request, response);
			} else {
				destination = "index.jsp";
			}
		}
		
		if(async == null || async.equals("false")) {
			setMenuItems(request);
			RequestDispatcher view = request.getRequestDispatcher(destination);
			view.forward(request, response);
		}
	}
	
	private String searchUsers(HttpServletRequest request, HttpServletResponse response) {
		
		Person user = (Person) request.getSession().getAttribute("user");
		String generalSearchString = request.getParameter("generalSearchString");
		String searchName = request.getParameter("searchName");
		String searchLastName = request.getParameter("searchLastName");
		String searchNickName = request.getParameter("searchNickName");
		
		request.setAttribute("searchResults", userService.searchUsers(user, generalSearchString, searchName, searchLastName, searchNickName));
		
		request.setAttribute("generalSearchString", generalSearchString);
		request.setAttribute("searchName", searchName);
		request.setAttribute("searchLastName", searchLastName);
		request.setAttribute("searchNickName", searchNickName);
		
		return showFriends(request, response, new ArrayList<String>());
	}
	
	private String saveStatus(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		Person user = userService.read(email);
		
		List<String> errorMessages = new ArrayList<String>();
		setName(request, user, errorMessages);
		setLastName(request, user, errorMessages);
		setNickName(request, user, errorMessages);
		setStatus(request, user, errorMessages);
		setPresence(request, user, errorMessages);
		
		if (errorMessages.size() != 0) {
			request.setAttribute("messages", errorMessages);
		} else {
			userService.update(user);
		}
		return showFriends(request, response, errorMessages);
	}
	
	private String openChat(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("title", "Chat with " + request.getAttribute("name"));
		return "chat.jsp";
	}

	private String showFriends(HttpServletRequest request, HttpServletResponse response, List<String> errorMessages) {
		//add friends, after search add previous values and results
		Person user = (Person) request.getSession().getAttribute("user");
		request.setAttribute("friends", userService.getPersonFriends(user));
		if (errorMessages.size() != 0) {
			request.setAttribute("messages", errorMessages);
		}
		return "friendOverviewAsync.jsp";
	}
	
	private String showFriendsAsync(HttpServletRequest request, HttpServletResponse response) {
		Person user = (Person) request.getSession().getAttribute("user");
		try {
			response.getWriter().write(friendsToJSON(userService.getPersonFriends(user)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "friendOverviewAsync.jsp";
	}
	
	private void showFriendsAsyncJSON(HttpServletRequest request, HttpServletResponse response) {
		Person user = (Person) request.getSession().getAttribute("user");
		try {
			response.getWriter().write(friendsToJSON(userService.getPersonFriends(user)));
			System.out.println(friendsToJSON(userService.getPersonFriends(user)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "friendOverviewAsync.jsp";
	}
	
	public String friendsToJSON (List<Person> friends) {
		StringBuffer jsonDoc = new StringBuffer();					
				
		//JSON header
		jsonDoc.append("{");
		jsonDoc.append("\"friends\":[");
		for(Person friend : friends) {
			jsonDoc.append("{\"email\":\""+ friend.getEmail() + "\"");
			jsonDoc.append(",");
			jsonDoc.append("\"nickName\":\""+ friend.getNickName() + "\"");
			jsonDoc.append(",");
			jsonDoc.append("\"status\":\""+ friend.getStatus() + "\"");
			jsonDoc.append(",");
			jsonDoc.append("\"presence\":\""+ friend.getPresence() + "\"}");
			if(friends.indexOf(friend) != friends.size()-1) {
				jsonDoc.append(",");
			}
		}
		jsonDoc.append("]}");
		return jsonDoc.toString();
	}
	

	private void setMenuItems(HttpServletRequest request) {
		List<MenuItem> menuItems = userService.getMenu(isUserAuthenticated(request));
		request.setAttribute("menuItems", menuItems);
	}

	private boolean isUserAuthenticated(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}

	private String navigateToLogin(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie emailCookie = null;
		emailCookie = getCookie(request, "email");
		if(emailCookie != null){
			request.setAttribute("email", emailCookie.getValue());
		}
		return "login.jsp";
	}
	
	private String logIn(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Person user = userService.getAuthenticatedUser(email, password);

		List<String> errorMessages = new ArrayList<String>();
		String destination = "";
		if (user == null) {
			errorMessages.add("Unknown username and password combination");
			request.setAttribute("email", email);
			request.setAttribute("messages", errorMessages);
			destination = "login.jsp";
		} else {
			remember(request, response, email);
			request.getSession().setAttribute("user", user);
			//request.getSession().setAttribute("userService",userService);
			userService.setPersonPresenceOnline(((Person) request.getSession().getAttribute("user")));
			destination = "index.jsp";
		}
		return destination;
	}

	private void remember(HttpServletRequest request,
			HttpServletResponse response, String email) {
		String remember = request.getParameter("remember");
		boolean rememberMe = Boolean.parseBoolean(remember);
		if (rememberMe) {
			Cookie cookie = new Cookie("email", email);
			response.addCookie(cookie);
		} else {
			Cookie cookie = getCookie(request, "email");
			if(cookie != null){
				cookie.setMaxAge(0);
			}
		}
	}

	private String logOut(HttpServletRequest request,
			HttpServletResponse response) {
		((Person) request.getSession().getAttribute("user")).setPresenceOffline();
		request.getSession().invalidate();
		return "index.jsp";
	}

	private String editUser(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		Person user = userService.read(email);
		request.setAttribute("email", user.getEmail());
		request.setAttribute("name", user.getName());
		request.setAttribute("lastName", user.getLastName());
		request.setAttribute("nickName", user.getNickName());
		request.setAttribute("status", user.getStatus());
		request.setAttribute("presence", user.getPresence());
		return "userForm.jsp";
	}

	private String saveUser(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		Person user = userService.read(email);
		List<String> errorMessages = new ArrayList<String>();
		setName(request, user, errorMessages);
		setLastName(request, user, errorMessages);
		setNickName(request, user, errorMessages);
		setStatus(request, user, errorMessages);
		setPresence(request, user, errorMessages);
		
		String destination = "";
		if (errorMessages.size() != 0) {
			destination = "userForm.jsp";
			request.setAttribute("messages", errorMessages);
		} else {
			userService.update(user);
			destination = showUsers(request, response);
		}
		return destination;
	}

	private String deleteUser(HttpServletRequest request,
			HttpServletResponse response) {
		String email = request.getParameter("email");
		userService.delete(email);
		return showUsers(request, response);
	}

	private String newUser(HttpServletRequest request,
			HttpServletResponse response) {
		return "registrationForm.jsp";
	}

	private String showUsers(HttpServletRequest request,
			HttpServletResponse response) {
		List<Person> users = userService.readAll();
		request.setAttribute("users", users);
		return "userOverview.jsp";
	}

	private String register(HttpServletRequest request,
			HttpServletResponse response) {
		Person user = new Person();
		List<String> errorMessages = new ArrayList<String>();
		setName(request, user, errorMessages);
		setLastName(request, user, errorMessages);
		setNickName(request, user, errorMessages);
		setEmail(request, user, errorMessages);
		setPassword(request, user, errorMessages);

		String destination = "";
		if (errorMessages.size() != 0) {
			destination = "registrationForm.jsp";
			request.setAttribute("messages", errorMessages);
		} else {
			userService.create(user);
			destination = showUsers(request, response);
		}
		return destination;
	}

	private void setPassword(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordRepeat");
		try {
			if(!password.equals(passwordRepeat)) {
				throw new Exception("Passwords did not match");
			}
			user.setHashedPassword(password);
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}

	private void setEmail(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String email = request.getParameter("email");
		try {
			user.setEmail(email);
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}

	private void setName(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String name = request.getParameter("name");
		try {
			user.setName(name);
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}
	
	private void setLastName(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String lastName = request.getParameter("lastName");
		try {
			user.setLastName(lastName);
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}
	
	private void setNickName(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String nickName = request.getParameter("nickName");
		try {
			if(nickNameUsed(user)) {
				throw new Exception("Nickname already in use");
			}
			user.setNickName(nickName);
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}
	
	private boolean nickNameUsed(Person user) {
		for(Person other:userService.readAll()) {
			if( (!user.equals(other)) && (other.getNickName().equals(user.getNickName())) ) {
				return true;
			}
		}
		return false;
	}
	
	private void setStatus(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String status = request.getParameter("status");
		try {
			user.setStatus(status);
			userService.updatePersonStatus(user);
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}
	
	private void setPresence(HttpServletRequest request, Person user,
			List<String> errorMessages) {
		String presence = request.getParameter("presence");
		try {
			switch(presence) {
			case "Online" : setPersonPresenceOnline(user);
				break;
			case "Away" : setPersonPresenceAway(user);
				break;
			case "Offline" : setPersonPresenceOffline(user);
				break;
				default: setPersonPresenceOffline(user);
					throw new Exception("Requesting to set unknown userPresence");
				
			}
			/*
			for(UserPresence p:UserPresence.values()) {
				if(presence.equals(p.getPresenceString())) {
					user.setPresence(p);
				}
			}
			*/
		} catch (Exception exc) {
			errorMessages.add(exc.getMessage());
		}
	}
	
	public void setPersonPresenceOnline(Person person) {
		userService.setPersonPresenceOnline(person);
	}
	
	public void setPersonPresenceAway(Person person) {
		userService.setPersonPresenceAway(person);
	}
		
	public void setPersonPresenceOffline(Person person) {
		userService.setPersonPresenceOffline(person);
	}

	private Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie cookieSearched = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookieSearched = cookie;
					return cookieSearched;
				}
			}
		}
		return null;
	}
}
