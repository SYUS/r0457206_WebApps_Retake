<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Friends" />
	</jsp:include>
	
<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Chat Overview Container -->
	<div class="container">
	
		<!-- Chat overview panel -->
		<div class="panel panel-default">
  			<div class="panel-heading">All Chats</div>
  			
  			<c:choose>
  				<c:when test="${chats ne null}">
  					<!-- Table -->
					<table class="table">
					
						<thead>
							<tr>
								<th>Nickname</th>
								<th>Status</th>
								<th>Presence</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							
							<c:forEach var="friend" items="${friends}">
							<tr>
								<td><c:out value="${friend.nickName}"/></td>
								<td><c:out value="${friend.status}"/></td>
								<td><c:out value="${friend.presence}"/></td>
								<td><a href="Controller?action=openFriendChat&email=${friend.email}" id="openFriendChat">Chat</a></td>
								<td><a href="Controller?action=deleteFriend&email=${friend.email}" id="deleteFriend">Delete</a></td>
							</tr>
							</c:forEach>
							
						</tbody>
					
					</table>
					<!-- /Table -->				
  				</c:when>
  				<c:otherwise>
  					<div class="panel-body">
  						<p>No currently active chats</p>
  					</div>
  				</c:otherwise>
  			</c:choose>
  		</div>
	
	</div>
	<!-- /Friends Overview Container -->
	
	<!-- Chat list container -->
	<div class="container">
		<!-- Out of loop panel for testing -->
		<!-- Friend chat panel -->
			<div class="panel panel-default">
				<div class="panel-heading">Chat with ${friendChat.user}user</div>
				<!-- <div class="panel-body"> Chat information</div>  -->
				
				<!-- Panel message table -->
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>${friendChat.user}user</th>
							<th>You</th>
							<!-- <th></th> extra th's for message editing and removing?-->
							<!-- <th></th> -->
						</tr>
					</thead>
					<tbody>
						<!-- Out of loop content for testing -->
						<td>${friendChat.user}user says: blablabla</td><!-- <td>${name} says: ${message.content} blabla</td> -->
						<td>reply</td>
						<c:forEach var="message" items="${friendChat.messages}">
							<td>${friendChat.user} says: ${message.content}</td><!-- <td>${name} says: ${message.content} blabla</td> -->
							<td>reply</td>
						</c:forEach>
					</tbody>
				</table>
				
				<div class="panel-body">
					<!-- Panel message reply form -->
					<form class="form-inline" action="#" onsubmit="">
						<!-- Name -->
			  			<div class="form-group">
			    			<label for="message">Message:</label>
			    			<input type="text" class="form-control" id="message" name="message" placeholder="Type a message">
			  			</div>
			  			<button type="submit" id="chatButton" value="Send" onclick="" class="btn btn-success">Send</button>
					</form>
				</div>
				
			</div>
	
		<!-- forEach to display all chat panels -->
		<c:forEach var="friendChat" items="${friendChats}">
		
			<!-- Friend chat panel -->
			<div class="panel panel-default">
				<div class="panel-heading">Chat with ${friendChat.user}user</div>
				<!-- <div class="panel-body"> Chat information</div>  -->
				
				<!-- Panel message table -->
				<table class="table table-striped">
					<thead>
						<tr>
							<th>${friendChat.user}</th>
							<th>You</th>
							<!-- <th></th> extra th's for message editing and removing?-->
							<!-- <th></th> -->
						</tr>
					</thead>
					<tbody>
						<!-- Out of loop content for testing -->
						<td>${friendChat.user} says: blablabla</td><!-- <td>${name} says: ${message.content} blabla</td> -->
						<td>reply</td>
						<c:forEach var="message" items="${friendChat.messages}">
							<td>${friendChat.user} says: ${message.content}</td><!-- <td>${name} says: ${message.content} blabla</td> -->
							<td>reply</td>
						</c:forEach>
					</tbody>
				</table>
				
				<!-- Panel message reply form -->
				<form action="#" onsubmit="">
					<!-- Name -->
		  			<div class="form-group">
		    			<label for="message">Message:</label>
		    			<input type="text" class="form-control" id="message" name="message" placeholder="Type a message">
		  			</div>
		  			<button type="submit" id="chatButton" value="Send" onclick="" class="btn btn-primary btn-lg btn-block">Send</button>
				</form>
				
			</div>
			
		</c:forEach>
	</div>
	<!-- /Chat list container -->
	
</body>
</html>