package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btnCerca;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;

	private Model model;
	
	@FXML
    void doCerca(ActionEvent event) {
		
		txtRisultato.clear();
		Corso corso = cmbCorsi.getValue();
		
		if(corso == null || corso.getNome().equals("") ) {
    		txtRisultato.setText("Errore: nessun corso selezionato");
    		return;
		}
		
		Studente studente = model.getStudenteByMatricola(txtMatricola.getText());
		
		if(studente == null) {
    		txtRisultato.setText("Errore: studente non presente!");
    		return;
    	}
		
		boolean esito = model.getStudenteIscrittoACorso(corso.getCodins(), studente.getMatricola());
		
		if(esito == true) {
			txtRisultato.setText("Studente iscritto al corso");
		} else {
			txtRisultato.setText("Studente non iscritto al corso");
		}
		
		
	}

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	txtRisultato.clear();
    	Studente stud = model.getStudenteByMatricola(txtMatricola.getText());
    	
    	if(stud == null) {
    		txtRisultato.setText("Errore: studente non presente!");
    		return;
    	}
    	
    	List<Corso> corsi = this.model.getCorsiByMatricola(txtMatricola.getText());
    	for(Corso c : corsi) {
    		txtRisultato.appendText(c + "\n");
    	}
    	
    	

    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	
    	txtRisultato.clear();
    	Corso corso = cmbCorsi.getValue();
    	
    	if(corso == null || corso.getNome().equals("") ) {
    		txtRisultato.setText("Errore: nessun corso selezionato");
    		return;
    	}
    	List<Studente> studenti = this.model.getStudentiIscrittiAlCorso(corso);
    	for(Studente s : studenti) {
    		txtRisultato.appendText(s + "\n");
    	}

    }

    @FXML
    void doCheck(ActionEvent event) {
    	
    	txtNome.clear();
    	txtCognome.clear();
    	
    	Studente stud = model.getStudenteByMatricola(txtMatricola.getText());
    	
    	if(stud == null) {
    		txtRisultato.setText("Errore: studente non presente!");
    		return;
    	}
    	else {
    		txtNome.setText(stud.getNome());
    		txtCognome.setText(stud.getCognome());
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtRisultato.clear();
    	cmbCorsi.setValue(null);
    	return;

    }
    
    
    
    public void setModel(Model model) {
		this.model = model;
		cmbCorsi.getItems().clear();
		cmbCorsi.getItems().add(new Corso("", null, "", null));
		
        for(Corso c : this.model.getTuttiCorsi()) {
        	cmbCorsi.getItems().add(c);
        }
		
	}

    @FXML
    void initialize() {
    	assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        
        

    }

	

}
