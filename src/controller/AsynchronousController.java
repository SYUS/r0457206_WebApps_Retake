package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Person;
import service.UserSystem;
import service.UserSystemFactory;

@WebServlet("/AsynchronousController")
public class AsynchronousController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserSystem userService;
		
	public AsynchronousController() {
		super();
		//quoteRepository = new FakeQuoteRepository();
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
        
		//String quote = quoteRepository.getRandomQuote();
		userService = UserSystemFactory.getInstance();
		userService.init(null);
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person user = (Person) request.getSession().getAttribute("user");
		System.out.println(user.getEmail());
		String friendsJSON = this.friendsToJSON(userService.getPersonFriends(userService.read(user.getEmail())));
		response.getWriter().write(friendsJSON);
	}  	
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}
