package de.fs.webarch.serialize;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = digest.digest(p.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for(byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			String pw = sb.toString();
			
			con = ContextListener.getDataSource().getConnection();
			String query= "Select Benutzername from User where Benutzername=? and Passwort=?";
			stmt = con.prepareStatement(query);

			stmt.setString(1, u);
			stmt.setString(2, pw);

			rs= stmt.executeQuery();
			x = rs.next();

		}
		catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try { if(rs!=null) rs.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(stmt!=null) stmt.close(); }  catch(Exception e) {e.printStackTrace();};
			try { if(con!=null)con.close(); }  catch(Exception e) {e.printStackTrace();};
		}
		return x;
	}

	public void registrieren(String u, String p, String e) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = digest.digest(p.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for(byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			String pw = sb.toString();
			
			con = ContextListener.getDataSource().getConnection();
			
			String query="Insert into User Values(NULL,?,?,?)";
			stmt= con.prepareStatement(query);
			stmt.setString(1, u);
			stmt.setString(2, pw);
			stmt.setString(3, e);
			stmt.executeUpdate();
			
	}
	 catch(Exception ex) {
		
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
	public void forgot(String us, String p){
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = digest.digest(p.getBytes(StandardCharsets.UTF_8));
			
			StringBuilder sb = new StringBuilder();
			for(byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			String pw = sb.toString();
			
			con = ContextListener.getDataSource().getConnection();
			 String query="UPDATE User "
			 		+ "Set Passwort=? "
			 		+ "Where Benutzername=?";
			 stmt= con.prepareStatement(query);
			 stmt.setString(1, pw);
			 stmt.setString(2, us);
			 
			 
			 stmt.executeUpdate();
			
	}
		catch(Exception ex) {
			
		} finally {
			try { stmt.close(); } catch(SQLException ex) {}
			try { con.close(); } catch(SQLException ex) {}
		}
	}

}

