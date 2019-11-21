package de.fs.webarch.serialize;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	public int id;
	public String benutzername;
	public String passwort;
	public String email;
	public int autoid;

	public User() {}
	public User(ResultSet rs) {
		try {
			id = rs.getInt(1);
			benutzername = rs.getString(2);
			passwort = rs.getString(3);
			email = rs.getString(4);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
