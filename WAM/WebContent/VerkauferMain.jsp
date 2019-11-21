<%@page import="de.fs.webarch.serialize.FrageDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="de.fs.webarch.serialize.FrageDAO" %>
 <%@ page import="de.fs.webarch.serialize.Frage" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.contentPart {
		margin: 8px;
	}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp"/>
	
	<form>

	 	
		
	
	<div class="contentPart">

		Probefahrten:
	<ul>
  		<li>Probefahrt 1</li>
  		<li>Probefahrt 2</li>
  		<li>Probefahrt 3</li>
	</ul>
	</div>
	<%int id = (Integer) request.getSession().getAttribute("userId"); System.out.println("User id: " + id);%>
	<% ArrayList<Frage> fragen = FrageDAO.instance.getFrage(id); %>
	
  	<% for(Frage f : fragen) { %>
		  <div class="container">
      		<div class="text-block">
		        <p>
		        	Fragen: <br>
		        	<%= f.frage_id %>: <%= f.frage %> <br>
		        </p>
        	</div>
      	</div>
	<% } %>
	</form>

	
</body>
</html>