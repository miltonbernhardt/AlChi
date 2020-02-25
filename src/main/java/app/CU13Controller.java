package app;

import javafx.fxml.FXML;

/*
 * Controller para la view "Cálculo de pérdidas"
 */
public class CU13Controller {	
	private static CU13Controller instance = null;

    public static CU13Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU13Controller) App.setRoot("CU13View", "AlChi: Cálculo de pérdidas");
        }    
        return instance;
    }
	//TODO CU13 implementar
    public CU13Controller() { }	
	
    @FXML
    private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
