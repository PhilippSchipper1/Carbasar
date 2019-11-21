<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrieren</title>
</head>
<body>
<p>Benutzer: <br>
      
      <form action = "RegistrationServlet" method="post">
      <input type="text" name="Benutzer" value="">
   

    <p>E-Mail: <br>
      <input type="text" name="E-Mail" value="">
    </p>

    <p>Passwort: <br>
        <input type="password" name="Passwort" value="">
    </p>

    <p>Passwort wiederholen: <br>
        <input type="password" name="Passwort_wdh" value="">
    </p>

    <p>
      <button type="submit">Registrieren</button>
    </p>
    </form>
</body>
</html>