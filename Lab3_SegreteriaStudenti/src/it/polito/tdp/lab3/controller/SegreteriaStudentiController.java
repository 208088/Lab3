package it.polito.tdp.lab3.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab3.DB.CorsoDAO;
import it.polito.tdp.lab3.model.Corso;
import it.polito.tdp.lab3.model.SegreteriaModel;
import it.polito.tdp.lab3.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SegreteriaStudentiController {
	
	private SegreteriaModel segreteria= new SegreteriaModel();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxcorso;

    @FXML
    private TextField lblmatricola;

    @FXML
    private ImageView btncomplete;

    @FXML
    private TextField lblnome;

    @FXML
    private TextField lblcognome;

    @FXML
    private Button btnCerca;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCerca(ActionEvent event) {
    	if(boxcorso.getValue().compareTo("")==0 && !lblmatricola.getText().isEmpty()){
    		List<Corso> frequenta= segreteria.corsiFrequentati(lblmatricola.getText());
    		txtResult.clear();
    		for (Corso c : frequenta) {
    			txtResult.setText(txtResult.getText()+"\n"+c.getId()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd());	
			}
    		
    	}
    	else{
    	lblmatricola.setText("");
    	lblnome.setText("");
    	lblcognome.setText("");
    	String nome=boxcorso.getValue();
    	if(nome.compareTo("")!=0){
    	List<Studente> stu= segreteria.getIscritti(boxcorso.getValue());
    	for (Studente s : stu) {
			txtResult.setText(txtResult.getText()+"\n"+s.getMatricola()+" "+s.getCognome()+" "+s.getNome());
		 }
    	}
    	else {
    		List<Corso> corsi=segreteria.getCorsi();
    		for (Corso c : corsi) {
    			List<Studente> stu= segreteria.getIscritti(c.getNome());
    	    	for (Studente s : stu) {
    				txtResult.setText(txtResult+"\n"+s.getMatricola()+" "+s.getCognome()+" "+s.getNome());
    			 }
			}
    	}
    	}

    }

    @FXML
    void doComplete(MouseEvent event) {
    	Studente s= segreteria.getStudente(Integer.parseInt(lblmatricola.getText()));
    	if(s!=null){
    		lblnome.setText(s.getNome());
    		lblcognome.setText(s.getCognome());
    	}
    	else lblnome.setText(""+Integer.parseInt(lblmatricola.getText()));
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	lblmatricola.clear();
    	lblcognome.clear();
    	lblnome.clear();
    	boxcorso.setValue("");

    }

    @FXML
    void initialize() {
        assert boxcorso != null : "fx:id=\"boxcorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert lblmatricola != null : "fx:id=\"lblmatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btncomplete != null : "fx:id=\"btncomplete\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert lblnome != null : "fx:id=\"lblnome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert lblcognome != null : "fx:id=\"lblcognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

     
       boxcorso.getItems().add("");
       List<Corso> lista= segreteria.getCorsi();
       for (Corso s : lista) {
		boxcorso.getItems().add(s.getNome());
	}
    }
}
