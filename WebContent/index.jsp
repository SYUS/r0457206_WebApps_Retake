<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Home" />
	</jsp:include>
	
<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Container -->
	<div class="container">
		<div class="jumbotron">
        	<h1>A Social Network</h1>
        	<c:choose>
        		<c:when test="${user.name ne null}"><p>You are connected to the world</p></c:when>
        		<c:otherwise><p>Connect to the world</p></c:otherwise>
        	</c:choose>
      	</div>
      	
      	<!-- Info display -->
      	<div class="container">
      	
      		<c:choose>
      			<c:when test="${user.name ne null}">
      				<!-- If logged in user info -->
      				<h1>Welcome, ${user.name}</h1>
      				<blockquote>
      					<footer>What's on your mind?</footer>
  						<p>
  							<c:choose>
  								<c:when test="${user.status ne null}">${user.status}</c:when>
  								<c:otherwise>Nothing... Fill in a status below</c:otherwise>
  							</c:choose>
  						</p>
					</blockquote>
      			</c:when>
      			
      			<c:otherwise>
      				<!-- Else button to login and register -->
      				<div class="center-block">
	      				<form  method="post" action="login.jsp" class="col-md-6">
							<button type="submit" id="register" class="btn btn-primary btn-lg btn-block">Log in</button>
						</form>
	      				<form  method="post" action="registrationForm.jsp" class="col-md-6">
							<button type="submit" id="register" class="btn btn-default btn-lg btn-block">Register</button>
						</form>
      				</div>
      			</c:otherwise>
      		</c:choose>
      		
      		<c:if test="${user.name ne  null}">
      			<div class="panel panel-default">
      				<div class="panel-heading">Previous Statuses</div>
      				<div class="panel-body">
      					<div class="panel panel-default">
      				
      				<div class="panel-body">
      					Body
      				</div>
      				
      				<table>
      				
      				</table>
      				
      			</div>
      				</div>
      			</div>
      		</c:if>
      		
      	</div>
      	<!-- /Info display -->
      	
      	
      	
	</div>
	<!-- /Container -->
	
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins)
     <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>
