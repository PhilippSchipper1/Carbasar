<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="de.fs.webarch.serialize.AutoDAO" %>
    <%@ page import="de.fs.webarch.serialize.Auto" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title>Kaufen</title>
    <style>
      img {
  width: 70%;
  height: auto;
}
</style>
</head>
<body>
 <jsp:include page="navbar.jsp"/>
	
	<% String search = request.getParameter("MarkenSuche"); %>
	<% ArrayList<Auto> autos; %>
	<% if(search != null) { 
		autos = AutoDAO.instance.AutoFilter(search);
	} else {
		autos = AutoDAO.instance.getAutos();
	}%>
    <p>Nach einer Marke suchen: <br>
     <form action = "KauferMain.jsp" method="post">
        <input type="text" name="MarkenSuche" value=""> <br>
        <button type="submit">Suchen</button><br>
     </form>
  	</p>
  	
  	<% for(Auto p : autos) { %>
		  <div class="container">
		  	<a href="AutoDetail.jsp?auto_id=<%= p.id %>">
      			<img src="resources/<%= p.bild %>">
      		</a>
      		<div class="text-block">
		        <p> <%= p.marke %>
		        	<%= p.modell %>
		        	Leistung (PS)<%= p.leistung %>
		        	Kilometer<%= p.kilometer %>
		        </p>
        	</div>
      	</div>
	<% } %>

    <p>
    </p>
</body>
</html>