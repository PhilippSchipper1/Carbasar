<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
</style>
<title>Farge stellen</title>
</head>
<body>
	<jsp:include page="navbar.jsp"/>
      <form action="FrageStellenServlet" method="post">
      <p>
      	<input type="text" name="Frage" size="35" height="70" width="70">
      	<input type="hidden" name="autoid" value="<%= request.getParameter("auto_id") %>">
      </p>
      
    <button type="submit" name="FrageStellen" onclick="window.alert('Frage wurde abgeschickt');">Frage abschicken</button>
   
      </form>
      
</body>
</html>