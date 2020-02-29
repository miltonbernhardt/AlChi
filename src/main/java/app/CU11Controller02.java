package app;

import javafx.fxml.FXML;

public class CU11Controller02 {
	private static CU11Controller02 instance = null;
	
    public CU11Controller02() { }

    public static CU11Controller02 get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU11Controller02) App.setRoot("CU11View02", "AlChi: Exportar archivos");
        }    
        return instance;
    }
	
	
    /*
   //TODO CU11 implementar
     */
    
    @FXML  private void initialize(){
    	
    }
	
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
