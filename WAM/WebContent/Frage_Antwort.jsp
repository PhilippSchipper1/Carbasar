<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="de.fs.webarch.serialize.FrageDAO"%>
<%@ page import="de.fs.webarch.serialize.Frage" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farge/Antwort:</title>
</head>
<body>
	<form>
	<jsp:include page="navbar.jsp"/>
<%int id = (Integer) request.getSession().getAttribute("userId"); System.out.println("User id: " + id);%>
	<% ArrayList<Frage> antworten = FrageDAO.instance.getAntwort(id); %>
	
	Meine gestellten Fragen:
	<table style="width:50%">
	<tr>
    	<th>Frage</th>
    	<th>Antwort</th> 
  	</tr>

	<%if(antworten.size()>0) {%>
		<% for(Frage a : antworten) { %>
    	<tr>
    		<td><%= a.frage%></td>
    		<td><%= a.antwort %></td>
  		</tr>
  	
	<%} %>
	</table>
	<%} else{%>
		Leider noch keine Antwort vorhanden
	<%} %>

</form>
</body>
</html>