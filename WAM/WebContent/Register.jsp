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
      
      <form action = "Registration" method="post">
      <input type="text" name="Benutzer">
   

    <p>E-Mail: <br>
      <input type="text" name="E-Mail">
    </p>

    <p>Passwort: <br>
        <input type="password" name="Passwort">
    </p>

    <p>Passwort wiederholen: <br>
        <input type="password" name="Passwort_wdh">
    </p>

    <p>
      <button type="submit">Registrieren</button>
    </p>
    </form>
</body>
</html>