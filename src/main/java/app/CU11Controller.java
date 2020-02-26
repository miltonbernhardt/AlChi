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
	
	//TODO CU11 implementar
    /*
    Va a tener la vista de genear archivos de ganancias o perdidas, y tambien de generar pdfs de otras cosas y las imagenes para reder sociales
     */
	
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
