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
	
    public CU09Controller() { }

    public static CU09Controller get() {
        if (instance == null){ instance = new CU09Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU09View", "AlChi: Dar de baja paquetes de productos");
	}
	
	
	
	
	
	
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
