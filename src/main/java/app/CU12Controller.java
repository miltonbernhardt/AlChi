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
	
    public CU12Controller() { }

    public static CU12Controller get() {
        if (instance == null){ instance = new CU12Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU12View", "AlChi: Cálculo de ganancias");
	}
	
	
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
