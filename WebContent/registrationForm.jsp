<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Register" />
	</jsp:include>
	
<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Container -->
	<div class="container">
		
		<!-- Registration form -->
		<form method="post" action="Controller?action=register">
			<!-- Name -->
  			<div class="form-group">
    			<label for="name">Name</label>
    			<input type="text" class="form-control" id="name" name="name" placeholder="Name" value="<c:out value="${param.name}"/>" >
  			</div>
  			
  			<!-- Last name -->
  			<div class="form-group">
    			<label for="lastName">Last name</label>
    			<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" value="<c:out value="${param.lastName}"/>" >
  			</div>
  			
  			<!-- Nickname -->
  			<div class="form-group">
    			<label for="nickName">Nickname</label>
    			<input type="text" class="form-control" id="nickName" name="nickName" placeholder="Nickname" value="<c:out value="${param.nickName}"/>" >
  			</div>
  			
  			<!-- Email -->
  			<div class="form-group">
    			<label for="email">Email address</label>
    			<input type="email" class="form-control" id="email" name="email" placeholder="Email" value="<c:out value="${param.email}"/>" >
  			</div>
  			
  			<!-- Password -->
  			<div class="form-group">
    			<label for="password">Password</label>
    			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
  			</div>
  			
  			<!-- Repeat Password -->
  			<div class="form-group">
    			<label for="password">Repeat Password</label>
    			<input type="password" class="form-control" id="passwordRepeat" name="passwordRepeat" placeholder="Password">
  			</div>
  
  			<!-- Register -->
  			<button type="submit" class="btn btn-success">Register</button>
		</form>
		<!-- /Registration form -->
	</div>
	<!-- /Container -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>