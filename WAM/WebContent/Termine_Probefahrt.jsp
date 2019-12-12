<%@page import="de.fs.webarch.serialize.ProbefahrtDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
    <%@page import="de.fs.webarch.serialize.ProbefahrtDAO"%>
<%@page import="de.fs.webarch.serialize.Probefahrt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Probefahretn und Termine</title>
</head>
<body>
	<jsp:include page="navbar.jsp"/>
	<form action="">
	<%int id = (Integer) request.getSession().getAttribute("userId"); System.out.println("User id: " + id);%>
	<% ArrayList<Probefahrt> termin2 = ProbefahrtDAO.instance.getTerminBesitzer(id); %>
	
	Bestätigte Termine von meinen Autos: <br>
	
	
	
	<%if(termin2.size()>0){ %>
		
		<% for(Probefahrt t2 : termin2) { %>
			<%=t2.datum%> <%=t2.genehmigt%> <br>
	<%} %>
	
	<%} else { %>
		Sie haben keine ausgemachten Termine <br>
	<%} %>
	
	
	
	
	
	
		
		
	<% ArrayList<Probefahrt> termin = ProbefahrtDAO.instance.getTerminKunde(id); %>
		
	<br> Angefragte Probefahrten(als Kunde): <br>
	 
  	<%if(termin2.size()>0){ %>
  	
  		<% for(Probefahrt t : termin) { %>
  			<%= t.datum %> <%=t.genehmigt %> <br>
  		<%} %>
  		
  	<%} else{ %>
  		Sie haben keine ausgemachten Termine <br>
  		<%} %>
	</form>
</body>
</html>