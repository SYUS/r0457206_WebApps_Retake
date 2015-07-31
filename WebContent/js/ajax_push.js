/**
 * @author SYUS
 * asynchronous data delivery via web sockets, push
 */

var webSocket;
var messages = document.getElementById("messages");
           
function openSocket(url){
	if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
		writeResponse("WebSocket is already opened.");
		return;
	}
	//webSocket = new WebSocket("ws://localhost:8080/GWT_Ajax_Example_Chat_Push/echo");//${requestScope['javax.servlet.forward.request_uri']}
	webSocket = new WebSocket("ws://" + url + "/echo");
                 
	webSocket.onopen = function(event){
		writeResponse(event.data);
	};
 
	webSocket.onmessage = function(event){
		writeResponse(event.data);
	};
 
	webSocket.onclose = function(event){
		writeResponse("Connection closed");
	};
}
           
function send(){
	var text = document.getElementById("messageinput").value;
	webSocket.send(text);
}
           
function closeSocket(){
	webSocket.close();
}
 
function writeResponse(text){
	messages.innerHTML += "<br/>" + text;
}

/*
		window.addEventListener("load", init, false);  
		
		var wsUri = "ws://echo.websocket.org/"; 
		var output;  

		function init() { 
			output = document.getElementById("output"); 
			helloWorldWebSocket(); 
		}  

		function helloWorldWebSocket() { 
			websocket = new WebSocket(wsUri); // websocket readyState onopen
			websocket.onopen = function (evt) { 
				onOpen(evt) 
			}; 
			websocket.onclose = function (evt) { 
				onClose(evt) 
			}; 
			websocket.onmessage = function (evt) {
				onMessage(evt) 
			}; 
			websocket.onerror = function (evt) { 
				onError(evt) 
			}; 
		}  

		function onOpen (evt) { 
			writeToScreen("CONNECTED"); 
			doSend("HELLO WORLD"); 
		}  

		function onClose (evt) { 
			writeToScreen("DISCONNECTED"); 
		}  

		function onMessage (evt) { 
			writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>'); 
			websocket.close(); // readyState onclose
		}  

		function onError (evt) { 
			writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
		}  

		function doSend (message) { 
			writeToScreen("SENT: " + message);  
			websocket.send(message); // readyState onmessage
		}  

		function writeToScreen (message) { 
			var pre = document.createElement("p"); 
			pre.style.wordWrap = "break-word";
			pre.innerHTML = message;
			output.appendChild(pre); 
		} 
*/