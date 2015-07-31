<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Edit user" />
	</jsp:include>

<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Container -->
	<div class="container">
	
		<!-- Edit Form -->
		<form method="post" action="Controller?action=saveUser">
			
			<!-- Name -->
  			<div class="form-group">
    			<label for="name">Name</label>
    			<input type="text" class="form-control" id="name" name="name" placeholder="Name" value="<c:out value="${name}"/>" >
  			</div>
  			
  			<!-- Last name -->
  			<div class="form-group">
    			<label for="lastName">Last name</label>
    			<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" value="<c:out value="${lastName}"/>" >
  			</div>
  			
  			<!-- Nickname -->
  			<div class="form-group">
    			<label for="nickName">Nickname</label>
    			<input type="text" class="form-control" id="nickName" name="nickName" placeholder="Nickname" value="<c:out value="${nickName}"/>" >
  			</div>

			<!-- Status -->
  			<div class="form-group">
    			<label for="status">Status</label>
	    			<input type="text" class="form-control" id="status" name="status" value="<c:out value="${status}"/>">
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
			
			<!-- Save -->
			<button type="submit" id="save" class="btn btn-success col-md-1">Save</button>
		</form>
		<!-- /Edit Form -->
		
	</div>
	<!-- /Container -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>