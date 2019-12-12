<html>
<head><style type="text/css">
/* Add a black background color to the top navigation */
.topnav {
  background-color: #333;
  overflow: hidden;
}

/* Style the links inside the navigation bar */
.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

/* Change the color of links on hover */
.topnav a:hover {
  background-color: #ddd;
  color: black;
}

/* Add a color to the active/current link */
.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
</style></head>
<body>
<% if(session.getAttribute("user") == null) { %>
	<jsp:forward page="Login.jsp"/>
<% } %>
<div class="topnav">
	<a href="BesucherTyp.jsp">Home</a>
	<a href="Login?logout=1">Logout</a>
	<a href="Frage_Antwort.jsp">Fragen und Antworten</a>
	<a href="Termine_Probefahrt.jsp">Terminübersicht</a>
</div>
</body>
</html>