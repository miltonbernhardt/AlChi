package app;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/*
 * Controller para la view de "Cálculo de ganancias"
 */
public class CU12Controller {
	
	private static CU12Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU12Controller get() {
        if (instance == null){ 
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU12Controller) App.setRoot("CU12View", "AlChi: Cálculo de ganancias");
        }    
        return instance;
    }	
	
    public CU12Controller() { }
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
}
