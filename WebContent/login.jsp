<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Login" />
	</jsp:include>
	
<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Container -->
	<div class="container">
	
		<!-- Login form -->
		<form  method="post" action="Controller?action=login">
			<!-- Email -->
  			<div class="form-group">
    			<label for="email">Email address</label>
	    			<input type="email" class="form-control" id="email" name="email" placeholder="Email" value=" <c:out value="${email}"/> ">
	  			</div>
  			
  			<!-- Password -->
  			<div class="form-group">
    			<label for="password">Password</label>
    			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
  			</div>
  
  			<!-- Remember me -->
  			<div class="checkbox">
    			<label>
      				<input type="checkbox" name="remember" id="remember"> Remember me
    			</label>
  			</div>
  
  			<!-- Log in -->
  			<button type="submit" class="btn btn-success col-md-1">Log in</button>
		</form>
		<!-- /Login form -->
		
        <!-- Register redirect form -->
		<form  method="post" action="registrationForm.jsp" class="col-md-2">
			<button type="submit" id="register" class="btn btn-default">Register</button>
		</form>
		<!-- /Register redirect form -->
		
	</div>
	<!-- /Container -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>