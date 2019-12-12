 <%@ page import="de.fs.webarch.serialize.AutoDAO" %>
 <%@ page import="de.fs.webarch.serialize.Auto" %>
 <%@ page import="de.fs.webarch.serialize.ProbefahrtDAO" %>
 <%@ page import="de.fs.webarch.serialize.Probefahrt" %>
    
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
    
    <script>
    function today() {
	    	
	    var today = new Date();
	    var dd = String(today.getDate()).padStart(2, '0');
	    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
	    var yyyy = today.getFullYear();
	
	    today = yyyy + '-' + mm + '-' + dd;
	    document.getElementById("drive-start").value = today;
	    document.getElementById("drive-start").min = today;
    }
    </script>
    <title>Auto Detail</title>
  </head>
	<body onload="today()">
	 <jsp:include page="navbar.jsp"/>
	 
	 <% int auto_id = Integer.parseInt((String) request.getParameter("auto_id"));
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
         <% String termin =(String)request.getAttribute("termin"); 
         	if(termin!=null){%>
         		<script>
         			window.alert("<%=termin%>");
         		</script>
         <%	}%>
    <p>
    <form action="Probefahrt" method="post">
    		Probefahrt vereinbaren: <br>
    		<input type="date" name="drive-start"
    		id="drive-start"
      		 value="2019-07-22"
      		 min="2019-01-01" max="2020-12-31">
      		 <input type="hidden" name="auto_id" value="<%= request.getParameter("auto_id") %>">
      		 
      		 
      		 <button type="submit" name="probefahrt">Probefahrt Anfragen</button>
      		 
    </form>
    
    
    <form action="FrageStellen" method="post">
      <p>
      	<input type="text" name="Frage" size="35" height="70" width="70">
      	<input type="hidden" name="auto_id" value="<%= request.getParameter("auto_id") %>">
      </p>
      
    	<button type="submit" name="FrageStellen" onclick="window.alert('Frage wurde abgeschickt');">Frage abschicken</button>
   
      </form>
     
    </p>
  </body>
	
</body>
</html>