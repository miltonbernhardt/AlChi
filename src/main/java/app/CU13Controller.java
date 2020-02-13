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
	
    public CU13Controller() { }

    public static CU13Controller get() {
        if (instance == null){ instance = new CU13Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU13View", "AlChi: Cálculo de pérdidas");
	}
	
	
	
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
