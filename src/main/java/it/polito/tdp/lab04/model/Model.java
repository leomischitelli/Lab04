package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrizioneDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	private IscrizioneDAO iscrizioneDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
		this.iscrizioneDAO = new IscrizioneDAO();
	}
	
	public List<Corso> getTuttiCorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudenteByMatricola(String matricola) {
		return this.studenteDAO.getStudenteByMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiByMatricola(String matricola){
		return this.studenteDAO.getCorsiByMatricola(matricola);
	}
	
	public boolean getStudenteIscrittoACorso(String codins, Integer matricola) {
		return this.iscrizioneDAO.getStudenteIscrittoACorso(codins, matricola);
	}
	

}
