package app;

import entity.TipoProducto;
import enums.TipoPaquete;
import entity.ProductoEmpaquetado;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * Controller para la view de "Registrar empaquetamientos"
 */
public class CU10Controller {
	
	private static CU10Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU10Controller get() {
        if (instance == null){ 
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU10Controller) App.setRoot("CU10View", "AlChi: Registrar empaquetamientos");
        }
        return instance;
    }
	
	@FXML
	private ComboBox<TipoProducto> tipoProducto;
	
	@FXML
	private ComboBox<TipoPaquete> tipoPaquete;
	
	@FXML
	private ComboBox<Integer> cantidad;
	
	@FXML
	private TableView<ProductoEmpaquetado> tabla; //Corregir
    
    public CU10Controller() { }
	
    @FXML
    private void initialize(){
    	
    }
    
	@FXML
    private void btnAnadirEmpaquetamiento() {
		
	}
		
	@FXML
    private void btnEliminar() {
		
	}
	
	@FXML
    private void btnEditar() {
		
	}
	
	@FXML
    private void btnConfirmarEmpaquetamiento() {
		
	}

    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
}
