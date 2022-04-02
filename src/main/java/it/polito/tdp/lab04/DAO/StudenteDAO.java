package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteByMatricola (String matricola) {
		
		final String sql = "SELECT * "
				+ "FROM studente s "
				+ "WHERE s.matricola = ? ";
		
		Studente stud = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				stud = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}
			
			st.close();
			rs.close();
			conn.close();
			
			return stud;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
		
	}
	
	public List<Corso> getCorsiByMatricola (String matricola){
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola = i.matricola "
				+ "AND c.codins = i.codins "
				+ "AND s.matricola = ?";
		
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));
				
			}

			st.close();
			rs.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}
	
	
 
}
