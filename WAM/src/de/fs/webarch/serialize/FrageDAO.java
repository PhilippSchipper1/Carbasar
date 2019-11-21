package de.fs.webarch.serialize;

import java.sql.*;
import java.util.ArrayList;

import de.fs.webarch.ContextListener;

public class FrageDAO {
	public static FrageDAO instance = new FrageDAO();
	
	public ArrayList<Frage> getFrage(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<Frage> fragen = new ArrayList<>();


		int frage_id, fragender, auto_id;
		String frage, antwort;
		try {
			con = ContextListener.getDataSource().getConnection();
			String query="SELECT FrageID, Frage FROM Carbasar.Frage f " + 
					"inner join Autos a on f.auto_id = a.id " + 
					"inner join User u on a.Eigentuemer = u.idUser " + 
					"where id =?";

			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			while(rs.next()) 
			{
				Frage f = new Frage();
				frage_id = rs.getInt(1);
				frage= rs.getString(2);
				f.frage=frage;
				f.frage_id=frage_id;			

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

}
