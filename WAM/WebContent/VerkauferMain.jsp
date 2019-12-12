<%@page import="de.fs.webarch.serialize.ProbefahrtDAO"%>
<%@page import="de.fs.webarch.serialize.Probefahrt"%>
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
	
	<form action=Probefahrt method="post">
	
	<div class="contentPart">
	<%int id = (Integer) request.getSession().getAttribute("userId"); System.out.println("User id: " + id);%>
	
	<%ArrayList<Probefahrt> probefahrten = ProbefahrtDAO.instance.getProbefahrt(id); %>
		
		Probefahrten:<br>
		
		
	
	
		<%if(probefahrten.size()>0){ %>
		<table>
		
			<tr>
		    	<th>Nr:</th>
		    	<th>Datum:</th> 
		    	<th>Zusagen:</th>
		    	<th>Absagen:</th>
		    	<th>   </th>
		  	</tr>
			
			<% for( Probefahrt p : probefahrten){ %>
		     	
		       	<tr>
		    		<td><%= p.probe_id%> <input type="hidden" value="<%= p.probe_id%>" name="probe_id"></td>
		    		<td><%= p.datum %></td>
		    		<td><input type="radio" value="zugesagt" name="termin"></td>
		    		<td><input type="radio" value="abgelehnt" name="termin"></td>
		    		<td><button type="submit">abschicken</button></td>
  				</tr>
		       
			<%} %>
				</table>
		<%} else{ %>
			Es wurden keine Probefahrten angefragt
		
		<%} %>
		
		
	</div>
	</form>
	
	<form action=FrageStellen method="post">
	<% ArrayList<Frage> fragen = FrageDAO.instance.getFrage(id); %>
	
		 Fragen: <br>
		 
  		<%if(fragen.size()>0){ %>	
  		
  		<table>
		
			<tr>
		    	<th>Frage ID:</th>
		    	<th>Fragender:</th> 
		    	<th>Frage:</th>
		    	<th>Auto:</th>
		    	<th>Antwort:</th>
		    	<th>   </th>
		  	</tr>
  			
  			<% for(Frage f : fragen) { %>
		        	
		        <tr>
		    		<td><%= f.frage_id%><input type=hidden name="frage_id" value=<%=f.frage_id %>></td>
		    		<td><%= f.fragender%></td>
		    		<td><%= f.frage %></td>
		    		<td><%=f.auto_id%><input type=hidden name="auto_id" value=<%=f.auto_id %>></td>
		    		<td><input type=text name="antwort"></input></td>
		    		<td><button type="submit">Antworten</button></td>
  				</tr>
		        	
			<% } %>
		    </table>
		
		<%} else{ %>
					Sie haben keine offenen Fragen
		<%} %>
	</form>

	
</body>
</html>