package app;

import entity.ProductoEmpaquetado;
import entity.TipoProducto;
import enums.TipoPaquete;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Registro de salida de productos"
 */
public class CU04Controller {
	private static CU04Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU04Controller() { }

    public static CU04Controller get() {
        if (instance == null){
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
    		instance = (CU04Controller) App.setRoot("CU04View", "AlChi: Registro de salida de productos");
    	}    
        return instance;
    }
	
	@FXML
	private ComboBox<TipoProducto> producto;
	
	@FXML
	private ComboBox<TipoPaquete> paquete;
	
	@FXML
	private ComboBox<Integer> cantidad;

	@FXML
	private CheckBox envio;
	
	@FXML
	private TextField precio;
	
	@FXML
	private TableView<ProductoEmpaquetado> tabla;

    @FXML
    private void initialize(){
    	setCombo();
    	//TODO CU04 incluir a los combos
    }
     
    @FXML
    private void setCombo(){
    	
    }
	
    @FXML
    private void btnAnadirVenta() {
		
	}
	
    @FXML
    private void btnAnadirCombo() {
        CU06Controller.get();
	}
	
    @FXML
    private void btnEliminarVenta() {
		
	}
	
    @FXML
    private void btnDuplicarVenta() {
		
	}
	
    @FXML
    private void btnEditarVenta() {
		
	}
	
    @FXML
    private void btnConfirmarVenta() {
		
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
}
