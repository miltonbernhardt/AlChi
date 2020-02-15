package app;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/*
 * Controller para la view "Dar de baja paquetes"
 */
public class CU09Controller {
	private static CU09Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU09Controller get() {
        if (instance == null){ 
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU09Controller) App.setRoot("CU09View", "AlChi: Dar de baja paquetes de productos");
        }    
        return instance;
    }

    public CU09Controller() { }	
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
}
