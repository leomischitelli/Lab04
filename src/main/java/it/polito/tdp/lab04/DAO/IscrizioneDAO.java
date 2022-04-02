package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.lab04.model.Corso;

public class IscrizioneDAO {
	
	public boolean getStudenteIscrittoACorso(String codins, Integer matricola) {
		
		String sql = "SELECT COUNT(*) AS n "
				+ "FROM iscrizione i "
				+ "WHERE i.codins = ? "
				+ "AND i.matricola = ? "
				+ "GROUP BY i.codins, i.matricola";
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			st.setInt(2, matricola);

			ResultSet rs = st.executeQuery();
			
			int count = 0;
			while(rs.next())
				count = rs.getInt("n");
			
			st.close();
			rs.close();
			conn.close();
			
			if(count > 0)
				return true;
			else
				return false;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean updateIscrizione(String codins, Integer matricola) {
		String sql = "INSERT INTO iscrizione (matricola, codins) "
				+ "VALUES ( ? , ? ) ";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			st.setString(2, codins);
			
			st.close();
			conn.close();
			return true;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}