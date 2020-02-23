package app;

import entity.Categoria;
import entity.Empaquetado;
import entity.ProductoInicial;
import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Registrar venta de combos"
 */
public class CU06Controller {
	private static CU06Controller instance = null;
	
    public CU06Controller() { }

    public static CU06Controller get() {
    	App.setViewAnterior();	
        if (instance == null){ 
    		instance = (CU06Controller) App.setRoot("CU06View", "AlChi: Registrar venta de combos");
    	}    
        return instance;
    }
	
	@FXML
	private ComboBox<Categoria> categoria;
	
	@FXML
	private ComboBox<TipoProducto> producto;
	
	@FXML
	private ComboBox<Empaquetado> paquete;
	
	@FXML
	private ComboBox<Integer> cantidad;
	
	@FXML
	private TableView<ProductoInicial> tabla; //VER
	
	@FXML
	private TextField precioVenta;
    
    @FXML
    private void initialize(){
    	
    }
		
    @FXML
    private void btnAnadirAlCombo() {
		
	}
	
    @FXML
    private void btnEliminarProducto() {
		
	}
	
	@FXML
    private void btnEditarProducto() {
		
	}
	
	@FXML
    private void btnConfirmarCombo() {
		
	}
	
    @FXML
    private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}

}
