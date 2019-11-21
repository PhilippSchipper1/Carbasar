package de.fs.webarch.serialize;

import java.sql.*;

import de.fs.webarch.ContextListener;
import de.fs.webarch.RegistrationServlet;

public class UserDAO {
	
	 
	public static UserDAO instance = new UserDAO();
	
	public static boolean LoginCheck(String u, String p) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean x = false;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			String query= "Select Benutzername from User where Benutzername=? and Passwort=?";
			stmt = con.prepareStatement(query);

			stmt.setString(1, u);
			stmt.setString(2, p);

			rs= stmt.executeQuery();
			x = rs.next();

		}
		catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException ex) {}
			try { stmt.close(); } catch(SQLException ex) {}
			try { con.close(); } catch(SQLException ex) {}
		}
		return x;
	}

	public static void registrieren(String u, String p, String e) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			
			String query="Insert into User(Buntzername, Passwort, EMail) Values(?,?,?)";
			stmt= con.prepareStatement(query);
			stmt.setString(1, u);
			stmt.setString(2, p);
			stmt.setString(3, e);
			stmt.executeUpdate();
			
	}
	 catch(SQLException ex) {
		
	} finally {
		try { stmt.close(); } catch(SQLException ex) {}
		try { con.close(); } catch(SQLException ex) {}
	}
	}
	
	public User getUserByName(String uname){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User u = null;
		try {
			con = ContextListener.getDataSource().getConnection();
			stmt= con.prepareStatement("SELECT * FROM User WHERE Benutzername = ?");	
			stmt.setString(1, uname);
			
			rs = stmt.executeQuery();
			rs.next();
			u = new User(rs);
		} catch(SQLException e) {
			
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
		return u;
	}
	public void frage_stellen(int askid, int auto_id, String frage) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			 String query="Insert into Frage(Fragender, auto_id,Frage) Values(?,?,?)";
			 stmt= con.prepareStatement(query);
			 stmt.setInt(1, askid);
			 stmt.setInt(2, auto_id);
			 stmt.setString(3, frage);
			 stmt.executeUpdate();
			
	}
		catch(SQLException ex) {
			
		} finally {
			try { stmt.close(); } catch(SQLException ex) {}
			try { con.close(); } catch(SQLException ex) {}
		}
	}
}

