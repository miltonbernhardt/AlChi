package app;

import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Buscar combos"
 */
public class CU14Controller {
	private static CU14Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU14Controller() { }

    public static CU14Controller get() {
        if (instance == null){ instance = new CU14Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();	
		App.setRoot("CU14View", "AlChi: Buscar combos");
	}
	
	@FXML
	private DatePicker fechaAntes;
	
	@FXML
	private DatePicker fechaDespues;
	
	@FXML
	private TextField precioMenor;
	
	@FXML
	private TextField precioMayor;
	
	@FXML
	private TableView<TipoProducto> tabla;
    
    @FXML
    private void initialize(){
    	
    }
	
	@FXML
	private void btnBuscar() {
    	
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
