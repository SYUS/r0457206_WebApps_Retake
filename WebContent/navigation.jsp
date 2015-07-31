<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <!-- Fixed navbar -->
  <div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="Controller">${param.title}</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
          	<!-- Dynamic navigation -->
          	<c:forEach var="action" items="${menuItems}">
				<li><a href="Controller?action=${action.action}" id="${action.action}">${action.description}</a></li>
			</c:forEach>
            <!-- Active nav link like this: <li class="active"><a href="#">Home</a></li> ; is for later, how to do dynamically? -->
            
            <!-- Display the notification icon if a user is logged in -->
            <c:if test="${user ne null}">
          	<li><!-- href="Controller?action=notifications" -->
          		<a href="#" data-container="body" data-trigger="focus" data-toggle="popover" data-placement="bottom" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus." title="Dismissible popover">
  					<span class="glyphicon glyphicon-user" aria-hidden="true"></span> News
  					<span class="badge">16</span>
				</a>
			</li>
          	</c:if>
          
          </ul>
          
        </div><!--/.nav-collapse -->
      </div>
    </nav>
  </div> <!-- /container navbar-->
  
  <!-- Alerts -->
  <div class="container">
  	<div id="errors">
  		<c:forEach var="error" items="${messages}">
  			<div class="alert alert-danger" role="alert" >
        		${error}
    		</div>
		</c:forEach>
  	</div>
  </div> <!-- /container alerts -->