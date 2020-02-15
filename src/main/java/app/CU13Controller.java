package app;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/*
 * Controller para la view "Cálculo de pérdidas"
 */
public class CU13Controller {
	
	private static CU13Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU13Controller get() {
        if (instance == null){ 
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU13Controller) App.setRoot("CU13View", "AlChi: Cálculo de pérdidas");
        }    
        return instance;
    }
	
    public CU13Controller() { }	
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
}
