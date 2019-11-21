package de.fs.webarch.serialize;

import java.util.ArrayList;

import de.fs.webarch.ContextListener;

import java.sql.*;

public class AutoDAO {
	 
 
	public static AutoDAO instance = new AutoDAO();
	
	
	
	public ArrayList<Auto> getAutos() throws ClassNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Auto> autos = new ArrayList<>();
		
		
		String marke, modell, bild;
		int km, leistung, id;
	
		
		try {
			
			con = ContextListener.getDataSource().getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * from Autos");
			
			while(rs.next()) 
			{
				Auto a = new Auto();
			id= rs.getInt(1);
			marke = rs.getString(2);
			modell = rs.getString(3);
			leistung = rs.getInt(4);
			km = rs.getInt(5);	
			bild = rs.getString(8);
		
			
			
			a.id=id;
			a.bild=bild;
			a.marke=marke;
			a.modell=modell;
			a.leistung=leistung;
			a.kilometer=km;
		
			autos.add(a);
			}
		}
			catch(SQLException e) {
				
			} finally {
				try { rs.close(); } catch(SQLException e) {}
				try { stmt.close(); } catch(SQLException e) {}
				try { con.close(); } catch(SQLException e) {}
			}
		
		return autos;
	}
	public Auto AutoDetail(int auto_id) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String beschreibung,eigentumer, bild;
		int preis, leistung;
		Auto a = new Auto();
		
		try {
			con = ContextListener.getDataSource().getConnection();
			
			String query= "Select a.preis, a.beschreibung,a.bild, a.leistung, u.benutzername from Autos"
					+ " a inner join User u on u.idUser=a.eigentuemer "
					+ "where a.id=?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, auto_id);
			rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
			
			preis=rs.getInt(1);
			beschreibung=rs.getString(2);
			bild= rs.getString(3);
			leistung = rs.getInt(4);
			eigentumer=rs.getString(5);
		
			
			a.preis = preis;
			a.beschreibung= beschreibung;
			a.eigentumer= eigentumer;
			a.bild=bild;
			a.leistung=leistung;
			
			
			}
		}
			
			catch(SQLException e) {
				
			} finally {
				try { rs.close(); } catch(SQLException e) {}
				try { stmt.close(); } catch(SQLException e) {}
				try { con.close(); } catch(SQLException e) {}
			}
		
		return a;
	}
	public ArrayList<Auto> AutoFilter(String autoname) throws ClassNotFoundException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Auto> autos = new ArrayList<>();
		String marke, modell, bild;
		int km, leistung, id;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			
			
			String query="Select * from Autos where marke=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, autoname);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) 
			{
			Auto a = new Auto();
			id= rs.getInt(1);
			marke = rs.getString(2);
			modell = rs.getString(3);
			leistung = rs.getInt(4);
			km = rs.getInt(5);
			bild = rs.getString(8);
			
			
			a.id=id;
			a.bild=bild;
			a.marke=marke;
			a.modell=modell;
			a.leistung=leistung;
			a.kilometer=km;
			autos.add(a);
		
			}
		}
			
		catch(SQLException e) {
			
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
		return autos;
		
		
	
	}
	
}
