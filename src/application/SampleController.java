package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bean.Circuit;
import bean.Driver;
import bean.Race;
import bean.Season;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m ){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Season> comboAnno;

    @FXML
    private ComboBox<Circuit> comboCircuito;

    @FXML
    private Button btnInfo;

    @FXML
    private ComboBox<Driver> comboPilota;

    @FXML
    private Button btnFanta;

    @FXML
    private TextArea txtResult;
    
    @FXML
    void doSelez(ActionEvent event) {             //metodo di combo anno //poi faccio refresh
    	Season  anno = comboAnno.getValue();
    	if(anno==null){
    		txtResult.appendText("Seleziona un anno!\n");
    		return;
    	}
    	comboCircuito.getItems().addAll(m.getCircuitiAnno(anno.getYear()));
    	

    }

    @FXML
    void doFanta(ActionEvent event) {

    }

    @FXML
    void doInfo(ActionEvent event) {   //deve aggiornarsi automaticamnete
    	txtResult.clear();
    	Season  anno = comboAnno.getValue();
    	if(anno==null){
    		txtResult.appendText("Seleziona un anno!\n");
    		return;
    	}
    	
    	Circuit c = comboCircuito.getValue();
    	if(c==null){
    		txtResult.appendText("Seleziona un circuito!\n");
    		return;
    	}
    	
    	List<Race> infoGara = m.getInfo(anno.getYear(), c.getCircuitId());
    	for(Race r : infoGara){
    	txtResult.appendText(r +  " \n");
    	}
    	
    	List<Driver> piloti = m.getPiloti(anno.getYear(), c.getCircuitId())   ;
    	txtResult.appendText("I piloti che hanno partecipato alla gara sono : \n ");
    	for(Driver r : piloti){
    		txtResult.appendText(r+" \n");
    	}
    }

    @FXML
    void initialize() {
        assert comboAnno != null : "fx:id=\"comboAnno\" was not injected: check your FXML file 'Sample.fxml'.";
        assert comboCircuito != null : "fx:id=\"comboCircuito\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnInfo != null : "fx:id=\"btnInfo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert comboPilota != null : "fx:id=\"comboPilota\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnFanta != null : "fx:id=\"btnFanta\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        comboAnno.getItems().addAll(m.getAnni());
        
     
    }
}
