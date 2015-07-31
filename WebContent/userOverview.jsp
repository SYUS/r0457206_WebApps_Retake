<%@ page import="model.Person"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Overview" />
	</jsp:include>

<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>

	<!-- Container -->
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">Users overview</div>
			<!-- Table -->
			<table class="table">
			
				<thead>
					<tr>
						<th>Name</th>
						<th>Last name</th>
						<th>Nickname</th>
						<th>E-mail</th>
						<th>Status</th>
						<th>Presence</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
					
					<c:forEach var="user" items="${users}">
					<tr>
						<td><c:out value="${user.name}"/></td>
						<td><c:out value="${user.lastName}"/></td>
						<td><c:out value="${user.nickName}"/></td>
						<td><c:out value="${user.email}"/></td>
						<td><c:out value="${user.status}"/></td>
						<td><c:out value="${user.presence}"/></td>
						<td><a href="Controller?action=editUser&email=${user.email}" id="updateUser">Edit</a></td>
						<td><a href="Controller?action=deleteUser&email=${user.email}" id="deleteUser">Delete</a></td>
					</tr>
					</c:forEach>
					
				</tbody>
			
			</table>
			<!-- /Table -->
		</div>
		<p>
			<a href="Controller?action=newUser">Add new user</a>
		</p>
	</div>
	<!-- /Container -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>