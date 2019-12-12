<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
Benutzer: <br>
    
    <form action = "Login" method="post">
    <input type="text" name="Benutzer" value="">

    <p>Passwort: <br>
        <input type="password" name="Passwort" value="">
 
      
    <p><button type="submit">Einloggen</button></p>
    </form>

    <p><a href="Forgot.jsp">Passwort vergessen</a></p>
</body>
</html>