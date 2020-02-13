package app;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/*
 * Controller para la view "Exportar archivos"
 */
public class CU11Controller {
	private static CU11Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU11Controller() { }

    public static CU11Controller get() {
        if (instance == null){ instance = new CU11Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU11View", "AlChi: Exportar archivos");
	}
	
	
	
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
