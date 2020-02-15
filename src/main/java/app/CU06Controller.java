package app;

import entity.Categoria;
import entity.ProductoEmpaquetado;
import entity.ProductoInicial;
import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Registrar venta de combos"
 */
public class CU06Controller {
	private static CU06Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU06Controller() { }

    public static CU06Controller get() {
        if (instance == null){ 
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
    		instance = (CU06Controller) App.setRoot("CU06View", "AlChi: Registrar venta de combos");
    	}    
        return instance;
    }
	
	@FXML
	private ComboBox<Categoria> categoria;
	
	@FXML
	private ComboBox<TipoProducto> producto;
	
	@FXML
	private ComboBox<ProductoEmpaquetado> paquete;
	
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
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}

}
