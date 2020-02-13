package app;

import javafx.fxml.FXML;
import javafx.scene.Parent;

/**
 * Controller para la view de "Administrar categorías"
 * TODO RECOPIAR del CU07
 */
public class CU15Controller {	
	private static CU15Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU15Controller() { }

    public static CU15Controller get() {
        if (instance == null){ instance = new CU15Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU15View", "AlChi: Administrar categorías");
	}
    
    @FXML
    private void initialize(){
    	
    }
	
	@FXML
	private void btnAnadir() {
		
	}
    
	@FXML
	private void btnEditar() {
		
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
