package app;

import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Buscar combos"
 */
public class CU14Controller {	
	private static CU14Controller instance = null;

    public static CU14Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU14Controller) App.setRoot("CU14View", "AlChi: Buscar combos");
        }    
        return instance;
    }
	
	@FXML private DatePicker fechaAntes;
	
	@FXML private DatePicker fechaDespues;
	
	@FXML private TextField precioMenor;
	
	@FXML private TextField precioMayor;
	
	@FXML private TableView<TipoProducto> tabla;
    
    public CU14Controller() { }
	
    @FXML private void initialize(){
    	
    }
	
	@FXML private void btnBuscar() {
    	
	}
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
