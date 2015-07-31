<%@ page import="model.Person"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Friends" />
	</jsp:include>

<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Friends Overview Container -->
	<div class="container">
	
		<!-- Header -->
		<div class="page-header">
  			<h1>
  				<c:choose>
  					<c:when test="${user.status ne null}">"${user.status}"</c:when>
  					<c:otherwise>Nothing... Fill in a status below</c:otherwise>
  				</c:choose>
  				<small>You are <c:out value="${user.presence}"></c:out>.</small>
  			</h1>
  		</div>
	
		<!-- User status -->
		<div class="panel panel-default">
  			<div class="panel-heading">Status & Presence</div>
  			<div class="panel-body">
  			
				<form class="form-inline" method="post" action="Controller?action=saveStatus" >
					<!-- IMHO This is too many hidden fields and a separate saveStatus action should be defined -->
					<!-- Email hidden -->
					<input type="hidden" name="email" value="${user.email}">
					<!-- Name hidden -->
					<input type="hidden" name="name" value="${user.name}">
					<!-- Last name hidden -->
					<input type="hidden" name="lastName" value="${user.lastName}">
					<!-- Nickname hidden -->
					<input type="hidden" name="nickName" value="${user.nickName}">
					
		  			<!-- Status -->
		  			<div class="form-group">
		    			<label for="status">Status</label>
			    			<input type="text" class="form-control" id="status" name="status" value="<c:out value="${user.status}"/>">
			  		</div>
			  		
			  		<!-- Presence -->
		  			<div class="form-group">
		    			<label for="presence">Presence</label>
			    			<select class="form-control" id="presence" name="presence">
			    				<option>Online</option>
			    				<option>Away</option>
			    				<option>Offline</option>
			    			</select>
			  		</div>
			  		
			  		<button type="submit" id="save" class="btn btn-success">Save</button>
				</form>
				
  			</div>
  		</div>
	
		<div class="panel panel-default">
  			<div class="panel-heading">Friends</div>
  			<div class="panel-body">
  				<button class="btn btn-primary col-md-2" type="button" data-toggle="collapse" data-target="#collapseFriendSearch" aria-expanded="false" aria-controls="collapseFriendSearch">Add friends</button>
  			</div>
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
							<td><a href="Controller?action=openChat&email=${friend.email}" id="openChat">Chat</a></td>
							<td><a href="Controller?action=deleteFriend&email=${friend.email}" id="deleteFriend">Delete</a></td>
						</tr>
						</c:forEach>
						
					</tbody>
				
				</table>
				<!-- /Table -->				
  		</div>
	
		
		
	</div>
	<!-- /Friends Overview Container -->
	
	<!-- User search Container -->
	<div class="container">
		
		<div class="collapse" id="collapseFriendSearch">
		  <div class="panel panel-default">
		  			<div class="panel-heading">Search users</div>
		  			<div class="panel-body">
		  			
		  				<!-- Friend Search -->
						<form class="form-inline" role="search" action="Controller?action=friendSearch" method="post">
							<!-- Email hidden -->
							<input type="hidden" name="email" value="${user.email}">
						
							<!-- General search field -->
				  			<div class="form-group">
				    			<input type="text" class="form-control" id="generalSearchString" name="generalSearchString" placeholder="Search" value="<c:out value="${generalSearchString}"/>">
				  			</div>
				  			
				  			<!-- Name -->
				  			<div class="form-group">
				    			<input type="text" class="form-control" id="searchName" name="searchName" placeholder="Name" value="<c:out value="${searchName}"/>" >
				  			</div>
				  			
				  			<!-- Last name -->
				  			<div class="form-group">
				    			<input type="text" class="form-control" id="searchLastName" name="searchLastName" placeholder="Last name" value="<c:out value="${searchLastName}"/>" >
				  			</div>
				  			
				  			<!-- Nickname -->
				  			<div class="form-group">
				    			<input type="text" class="form-control" id="searchNickName" name="searchNickName" placeholder="Nickname" value="<c:out value="${searchNickName}"/>" >
				  			</div>
				  			
				  			<button type="submit" class="btn btn-default">Submit</button>
						</form>
						<!-- /Friend Search -->
		  			</div>
		  		</div>
		</div>
		<!-- /User search panel -->
		
		<c:if test="${searchResults ne null}">
			<!-- User search results panel -->
			<div class="panel panel-default">
				<div class="panel-heading">Search results</div>
				<!-- Table -->
				<table class="table">
					<thead>
						<tr>
							<th>Name</th>
							<th>Last name</th>
							<th>Nickname</th>
							<th></th>
						</tr>
					</thead>
									
					<tbody>			
						<c:forEach var="result" items="${searchResults}">
							<tr>
								<td><c:out value="${result.name}"/></td>
								<td><c:out value="${result.lastName}"/></td>
								<td><c:out value="${result.nickName}"/></td>
								<td><a href="Controller?action=addFriend&email=${result.nickName}" id="addFriend">Add friend</a></td>
							</tr>
						</c:forEach>	
					</tbody>
								
				</table>
				<!-- /Table -->					
			</div>
			<!-- /User search results panel -->
		</c:if>
		
	</div>
	<!-- /User search Container -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>