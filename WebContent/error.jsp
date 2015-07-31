<%@page isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="r0457206 WebApps" />
		<jsp:param name="page" value="Error" />
	</jsp:include>
	
<body>
	<jsp:include page="navigation.jsp">
		<jsp:param name="title" value="r0457206 WebApps"/>
	</jsp:include>
	
	<!-- Container -->
	<div class="container">
		<h1>Something went wrong!</h1>
		<p>
			An exception of type 
			<div class="alert alert-danger">${pageContext.exception}</div> 
			appeared on the server!<br><br>
			Don't worry, it's not your fault.<br>
			A team of Goblin mechanics and automated constructs has been dispatched and will fix the problem in no time!<br>
		</p>
 
        <form  method="get" action="Controller">
			<button type="submit" id="home" class="btn btn-primary btn-lg">Home</button>
		</form>
		
		<div class="text-center">
			<img src="images/goblinsAtWork.gif" alt="Working Goblins" class="img-rounded">
		</div>
	</div>
	<!-- /Container -->
	
	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
</body>
</html>