package de.fs.webarch.serialize;
import java.sql.*;
import java.util.ArrayList;

import de.fs.webarch.ContextListener;

public class ProbefahrtDAO {
	public static ProbefahrtDAO instance = new ProbefahrtDAO();
	
	public ArrayList<Probefahrt> getProbefahrt(int id){
		ArrayList<Probefahrt> probefahrten = new ArrayList<>();
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		
		int probe_id;
		String datum;
		int kunde_id;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="Select id, Datum, kunde_id from Probefahrt where besitzer_id=? and Genehmigt='offen'";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) 
			{
				Probefahrt p = new Probefahrt();
				
				probe_id = rs.getInt(1);
				datum= rs.getString(2);
				kunde_id=rs.getInt(3);
				
				p.probe_id=probe_id;
				p.datum=datum;
				p.kunde_id=kunde_id;		

				probefahrten.add(p);
			}
		}

		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
		
		return probefahrten;	
	}

	public String termin(int auto_id, int kunde_id, String datum) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int count = 0;
		String text = null;
		int besitzer=0;
		String genehmigt="offen";
		
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="Select COUNT(Datum) from Probefahrt where auto_id=? and Datum=?";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, auto_id);
			stmt.setString(2, datum);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			
			if(count == 0) {
				
				
				String query2=	/*"Select idUser from User " + 
								"inner join Autos on Autos.Eigentuemer=User.idUser " + 
								"inner join Probefahrt on Autos.id=Probefahrt.auto_id " + 
								"where Autos.id=?";*/
								"SELECT Eigentuemer FROM Autos WHERE id = ?";
			
				stmt= con.prepareStatement(query2);
				stmt.setInt(1, auto_id);
				rs=stmt.executeQuery();
				
				if(rs.next()) besitzer=rs.getInt(1);
				else System.out.println("Oh boy, das nicht gut");
			
				String query3="Insert into Probefahrt(Datum,kunde_id,besitzer_id, auto_id, Genehmigt) Values(?,?,?,?,?)";
				stmt= con.prepareStatement(query3);
				stmt.setString(1, datum);
				stmt.setInt(2, kunde_id);
				stmt.setInt(3, besitzer);
				stmt.setInt(4, auto_id);
				stmt.setString(5, genehmigt);
				stmt.executeUpdate();
			
				text="Ihre Anfrage wurde abgeschickt";
			
			}
			else {
				text="Termin ist schon vergeben";
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
		return text;
	}
	public void validate(String termin, int probe_id) {
		Connection con = null;
		PreparedStatement stmt = null;
	
		
		
		try {
			con = ContextListener.getDataSource().getConnection();
			System.out.println("[DEBUG: ProbefahrtDAO] genehmigt: "+ termin);
			System.out.println("[DEBUG: ProbefahrtDAO] id: "+ probe_id);
			String query="UPDATE Probefahrt SET Genehmigt=? WHERE id=?";

			stmt = con.prepareStatement(query);
			stmt.setString(1, termin);
			stmt.setInt(2, probe_id);
			stmt.executeUpdate();
		}

		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
	}
	public ArrayList<Probefahrt> getTerminKunde(int id){
		ArrayList<Probefahrt> termine = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String datum;
		String genehmigt;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="Select Datum, Genehmigt from Probefahrt where kunde_id=?";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) 
			{
				Probefahrt t = new Probefahrt();
				datum = rs.getString(1);
				genehmigt =rs.getString(2);
				t.datum=datum;
				t.genehmigt=genehmigt;
				
				termine.add(t);	
			}
		}

		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
		
		return termine;
		
	}
	public ArrayList<Probefahrt> getTerminBesitzer(int id){
		ArrayList<Probefahrt> termine2 = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String datum;
		String genehmigt;
		
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="SELECT Datum, Genehmigt from Probefahrt where besitzer_id=? and Genehmigt='zugesagt'";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) 
			{
				Probefahrt t2 = new Probefahrt();
				datum = rs.getString(1);
				genehmigt =rs.getString(2);
				t2.datum=datum;
				t2.genehmigt=genehmigt;
				
				termine2.add(t2);	
			}
		}

		catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch(SQLException e) {}
			try { stmt.close(); } catch(SQLException e) {}
			try { con.close(); } catch(SQLException e) {}
		}
		
		return termine2;
}
}
