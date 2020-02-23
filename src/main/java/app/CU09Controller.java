package app;

import javafx.fxml.FXML;
/*
 * Controller para la view "Dar de baja paquetes"
 */
public class CU09Controller {
	private static CU09Controller instance = null;

    public static CU09Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU09Controller) App.setRoot("CU09View", "AlChi: Dar de baja paquetes de productos");
        }    
        return instance;
    }

    public CU09Controller() { }	
    
    //TODO CU09 actualizar en venta del tipo producto
	
    @FXML
    private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
