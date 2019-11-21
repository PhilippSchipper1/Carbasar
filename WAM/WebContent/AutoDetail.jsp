 <%@ page import="de.fs.webarch.serialize.AutoDAO" %>
 <%@ page import="de.fs.webarch.serialize.Auto" %>
    
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <style>
      img {
  width: 50%;
  height: auto;
}
    </style>
    <title>Auto Detail</title>
  </head>
<body>
	 <jsp:include page="navbar.jsp"/>
	 
	 <% int auto_id = Integer.parseInt(request.getParameter("auto_id"));
	 	Auto a = AutoDAO.instance.AutoDetail(auto_id); %>
	 
    <p>
      <img src="resources/<%= a.bild%>">
    </p>

        <div class="text-block">
		        <p>
		        	Beschreibung: <%= a.beschreibung %> <br>
		        	Eigentümer: <%= a.eigentumer %> <br>
		        	Leistung: <%= a.leistung %> <br>
		        	Preis: <%= a.preis %> <br>
		        </p>
        </div>
    <p>
      <button type="button" onclick="window.location.href='Probefahrt.jsp';">Probefahrt</button><br>
      <button type="button" onclick="window.location.href='FrageStellen.jsp?auto_id=<%= auto_id %>';">Frage</button><br>
    </p>
  </body>
	
</body>
</html>