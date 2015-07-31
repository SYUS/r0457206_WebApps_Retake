/**
 * @author SYUS
 * asynchronous data delivery in JSON format, polling active
 */

var xHRObject = new XMLHttpRequest();

function getFriends() {
	//xHRObject.open("GET", "AsynchronousController", true);
	xHRObject.open("GET", "Controller?action=friendOverviewAsync&async=true", true);
	//xHRObject.open(method, url, async, username, password)
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData () {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			//alert(xHRObject.responseText);
			var serverResponse = JSON.parse(xHRObject.responseText);
			//alert(serverResponse.friends[0].nickName);
			var friends = serverResponse.friends; // serverResponse["quote"]
				
			var friendsTableBody = document.getElementById("friendsTableBody");
			//var friendsTableBody = friendsTable.childNodes[0];
			
			if (friendsTableBody == null) {
				for(var i=0; i < friends.length; i++) {
					var friendsTableBodyTr = document.createElement('tr');
					
					//tdNickName = document.createElement('td');
					var tdNickName = document.createElement('td');
					tdNickName.appendChild(document.createTextNode(friends[i].nickName));
					
					//tdStatus = document.createElement('td');
					var tdStatus = document.createElement('td');
					tdStatus.appendChild(document.createTextNode(friends[i].status));
					
					//tdPresence = document.createElement('td');
					var tdPresence = document.createElement('td');
					tdPresence.appendChild(document.createTextNode(friends[i].presence));
					
					//tdOpenChat = document.createElement('td');
					var tdOpenChat = document.createElement('td');
					//tdOpenChatLink = document.createElement('a');
					var tdOpenChatLink = document.createElement('a');
					tdOpenChatLink.href = "Controller?action=openChat&email=" + friends[i].email;
					tdOpenChatLink.id = "openChat";
					tdOpenChatLink.appendChild(document.createTextNode("Chat"));
					
					tdOpenChat.appendChild(tdOpenChatLink);
					
					//tdDeleteFriend = document.createElement('td');
					var tdDeleteFriend = document.createElement('td');
					//tdDeleteFriendLink = document.createElement('a');
					var tdDeleteFriendLink = document.createElement('a');
					tdDeleteFriendLink.href = "Controller?action=deleteFriend&email=" + friends[i].email;
					tdDeleteFriendLink.id = "deleteFriend";
					tdDeleteFriendLink.appendChild(document.createTextNode("Delete"));
					
					tdDeleteFriend.appendChild(tdDeleteFriendLink);
					
					friendsTableBodyTr.appendChild(tdNickName);
					friendsTableBodyTr.appendChild(tdStatus);
					friendsTableBodyTr.appendChild(tdPresence);
					friendsTableBodyTr.appendChild(tdOpenChat);
					friendsTableBodyTr.appendChild(tdDeleteFriend);
					
					//friendsTable.appendChild(friendsTableBodyTr);
					friendsTableBody.appendChild(friendsTableBodyTr);
				}
			}
			else {
				//alert("else");
				
				//while (friendsTableBody.firstChild) {
				//	friendsTableBody.removeChild(friendsTableBody.firstChild);
				//}
				//while( friendsTableBody.hasChildNodes() ){
				//	alert("remove");
				//	friendsTableBody.removeChild(friendsTableBody.lastChild);
				//}
				
				empty(friendsTableBody);
				
				//friendsTableBody.removeChild(friendsTableBody.childNodes[0]);
				//friends = serverResponse.friends;
				
				for(var i=0; i < friends.length; i++) {
					var friendsTableBodyTr = document.createElement('tr');
					
					//tdNickName = document.createElement('td');
					var tdNickName = document.createElement('td');
					tdNickName.appendChild(document.createTextNode(friends[i].nickName));
					
					//tdStatus = document.createElement('td');
					var tdStatus = document.createElement('td');
					tdStatus.appendChild(document.createTextNode(friends[i].status));
					
					//tdPresence = document.createElement('td');
					var tdPresence = document.createElement('td');
					tdPresence.appendChild(document.createTextNode(friends[i].presence));
					
					//tdOpenChat = document.createElement('td');
					var tdOpenChat = document.createElement('td');
					//tdOpenChatLink = document.createElement('a');
					var tdOpenChatLink = document.createElement('a');
					tdOpenChatLink.href = "Controller?action=openChat&email=" + friends[i].email;
					tdOpenChatLink.id = "openChat";
					tdOpenChatLink.appendChild(document.createTextNode("Chat"));
					
					tdOpenChat.appendChild(tdOpenChatLink);
					
					//tdDeleteFriend = document.createElement('td');
					var tdDeleteFriend = document.createElement('td');
					//tdDeleteFriendLink = document.createElement('a');
					var tdDeleteFriendLink = document.createElement('a');
					tdDeleteFriendLink.href = "Controller?action=deleteFriend&email=" + friends[i].email;
					tdDeleteFriendLink.id = "deleteFriend";
					tdDeleteFriendLink.appendChild(document.createTextNode("Delete"));
					
					tdDeleteFriend.appendChild(tdDeleteFriendLink);
					
					friendsTableBodyTr.appendChild(tdNickName);
					friendsTableBodyTr.appendChild(tdStatus);
					friendsTableBodyTr.appendChild(tdPresence);
					friendsTableBodyTr.appendChild(tdOpenChat);
					friendsTableBodyTr.appendChild(tdDeleteFriend);
					
					//friendsTable.appendChild(friendsTableBodyTr);
					friendsTableBody.appendChild(friendsTableBodyTr);
				}
				//alert("executed else");
			}
			setTimeout("getFriends()", 1000);
			//setInterval("getFriends()", 10000);
			//setInterval("getFriends()", 1000);
		}
	}
}

function empty(element) {
	while( element.hasChildNodes() ){
		//alert("remove");
		element.removeChild(element.lastChild);
	}
}

/*
 * <!-- Table -->
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
 */

/*
var xHRObject = new XMLHttpRequest();

function getNewQuote () {
	xHRObject.open("GET", "ManageQuoteServlet", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData () {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var serverResponse = JSON.parse(xHRObject.responseText);
			var quote = serverResponse.quote; // serverResponse["quote"]
	
			var quoteDiv = document.getElementById("quote");
			var quoteParagraph = quoteDiv.childNodes[0];
			
			if (quoteParagraph == null) {
				quoteParagraph = document.createElement('p');
				quoteParagraph.id = "quoteText";
				var quoteText = document.createTextNode(quote);
				quoteParagraph.appendChild(quoteText);
				quoteDiv.appendChild(quoteParagraph);
			}
			else {
				var quoteText = document.createTextNode(quote);
				quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
				quoteParagraph.appendChild(quoteText);
			}	
		}
	}
}
*/