package de.fs.webarch.serialize;

import java.sql.*;
import java.util.ArrayList;

import de.fs.webarch.ContextListener;

public class FrageDAO {
	public static FrageDAO instance = new FrageDAO();
	
	public void frage_stellen(int askid, int auto_id, String frage) {
		Connection con = null;
		PreparedStatement stmt = null;
		String antwort="offen";
		
		try {
			con = ContextListener.getDataSource().getConnection();
			 String query="Insert into Frage(Fragender, auto_id,Frage, Antwort) Values(?,?,?,?)";
			 stmt= con.prepareStatement(query);
			 stmt.setInt(1, askid);
			 stmt.setInt(2, auto_id);
			 stmt.setString(3, frage);
			 stmt.setString(4, antwort);
			 stmt.executeUpdate();
			
	}
		catch(SQLException ex) {
			
		} finally {
			try { stmt.close(); } catch(SQLException ex) {}
			try { con.close(); } catch(SQLException ex) {}
		}
	}
	
	public ArrayList<Frage> getFrage(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Frage> fragen = new ArrayList<>();

		System.out.println(id);
		int fragender;
		int frage_id;
		int auto_id;
		String frage;
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="SELECT Fragender, Frage, FrageID, auto_id FROM Frage as f" + 
					"inner join Autos a on auto_id = a.id " + 
					"inner join User u on a.Eigentuemer = u.idUser " + 
					"where u.idUser=? and Antwort='offen'";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) 
			{
				Frage f = new Frage();
				fragender = rs.getInt(1);
				frage= rs.getString(2);
				frage_id=rs.getInt(3);
				auto_id=rs.getInt(4);
				f.fragender=fragender;
				f.frage=frage;	
				f.frage_id=frage_id;
				f.auto_id=auto_id;

				fragen.add(f);
			}
		}

		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}


		return fragen;

	}
	public void antwort(String antwort, int frage_id) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		
		try {
			con = ContextListener.getDataSource().getConnection();
			 String query="UPDATE Frage "
			 		+ "Set Antwort=? "
			 		+ "Where FrageID=?";
			 stmt= con.prepareStatement(query);
			 stmt.setString(1, antwort);
			 stmt.setInt(2, frage_id);
			 
			 
			 stmt.executeUpdate();
			
	}
		catch(SQLException ex) {
			
		} finally {
			try { stmt.close(); } catch(SQLException ex) {}
			try { con.close(); } catch(SQLException ex) {}
		}
	}
	public ArrayList<Frage> getAntwort(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Frage> antworten = new ArrayList<>();
		String frage;
		String antwort;
		
		
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="SELECT Frage, Antwort From Frage Where Fragender=?";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) 
			{
				Frage a = new Frage();
				
				frage=rs.getString(1);
				antwort=rs.getString(2);
				
				a.frage=frage;
				a.antwort=antwort;
				
				antworten.add(a);
			}
		}
			catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try { rs.close(); } catch(SQLException e) {}
				try { stmt.close(); } catch(SQLException e) {}
				try { con.close(); } catch(SQLException e) {}
			}
		return antworten;
	}
}
