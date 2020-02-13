package app;

import entity.ProductoInicial;
import entity.Proveedor;
import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/*
 * Controller para la view "Registro de entrada de productos"
 */
public class CU03Controller01 {	
	private static CU03Controller01 instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU03Controller01() { }

    public static CU03Controller01 get() {
        if (instance == null){ instance = new CU03Controller01(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU03View01", "AlChi: Registrar entrada de productos");
	}
	
	@FXML
	private ComboBox<TipoProducto> productos;
	
	@FXML
	private ComboBox<Proveedor> proveedores;
	
	@FXML
	private TextField precioCompra;
	
	@FXML
	private TextField cantidad;
	
	@FXML
	private TextField precio100;
	
	@FXML
	private TextField precio250;
	
	@FXML
	private TextField precio500;
	
	@FXML
	private TextField precio1000;
	
	@FXML
	private TextField precioUnidad;
	
	@FXML
	private TableView<ProductoInicial> tabla;
    
    @FXML
    private void initialize(){
    	
    }
    
    @FXML
	private void btnAgregarTipoProducto() {
    	
    }
    
    @FXML
	private void btnAgregarProveedor() {

    }
    
    @FXML
	private void btnAnadirProducto() {

    }
    
    @FXML
	private void btnEliminarFila() {

    }
    
    @FXML
	private void btnEditarFila() {

    }
    
    @FXML
	private void btnDuplicarFila() {
    	
    }  
    
    @FXML
    private void btnConfirmarProductos() {
        new CU03Controller02();
        CU03Controller02.get().setView();
    }    

    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
	
}
