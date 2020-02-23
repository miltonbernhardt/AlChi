package app;

import javafx.fxml.FXML;

/*
 * Controller para la view "Exportar archivos"
 */
public class CU11Controller {	
	private static CU11Controller instance = null;
	
    public CU11Controller() { }

    public static CU11Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU11Controller) App.setRoot("CU11View", "AlChi: Exportar archivos");
        }    
        return instance;
    }
	
	
	
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
