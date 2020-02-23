package app;

import javafx.fxml.FXML;

/*
 * Controller para la view de "Cálculo de ganancias"
 */
public class CU12Controller {	
	private static CU12Controller instance = null;
	
    public static CU12Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU12Controller) App.setRoot("CU12View", "AlChi: Cálculo de ganancias");
        }    
        return instance;
    }	
	
    public CU12Controller() { }
	
    @FXML
    private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
