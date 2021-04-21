package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnCalcolaAnagrammi;

    @FXML
    private TextArea txtOutputCorrect;

    @FXML
    private TextArea txtOutputIncorrect;

    @FXML
    private Button btnReset;

	private Model model;

    @FXML
    void pushCalcolaAnagrammi(ActionEvent event) {
    	this.txtOutputCorrect.clear();
    	this.txtOutputIncorrect.clear();
    	String input = this.txtInput.getText();
    	this.model.updateDizionario();
    	List<String> anagrammi = this.model.cercaAnagrammi(input);
    	for (String s: anagrammi) {
    		if (this.model.isCorrect(s)) {
    			this.txtOutputCorrect.appendText(s+"\n");
    		}else {
    			this.txtOutputIncorrect.appendText(s+"\n");
    		}
    	}
    	
    }

    @FXML
    void pushReset(ActionEvent event) {
    	this.txtOutputCorrect.clear();
    	this.txtOutputIncorrect.clear();
    	this.txtInput.clear();
    }
    
    void setModel (Model model) {
    	this.model=model;
    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaAnagrammi != null : "fx:id=\"btnCalcolaAnagrammi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutputCorrect != null : "fx:id=\"txtOutputCorrect\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutputIncorrect != null : "fx:id=\"txtOutputIncorrect\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
