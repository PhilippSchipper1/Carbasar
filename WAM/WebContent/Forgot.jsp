<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vergessen</title>
</head>
<body>
<form action="PasswordForgot" method=post>
	  <p>
    Nutzername:<br>
    <input type="text" name="Nutzer"></input><br>
    
    Passwort: <br>
    <input type="text" name="Passwort" value=""><br>
    
    Passwort Wiederholen: <br>
     <input type="text" name="Passwortwdh" value=""><br>
   <button type="submit" onclick="window.alert('Passwort wurde aktualisiert');">Passwort zurücksetzen</button><br></p>

 </form>
</body>
</html>