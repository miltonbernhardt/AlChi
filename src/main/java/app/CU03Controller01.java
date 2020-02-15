package app;

import dto.DTOProveedor;
import dto.DTOTipoProductoCU02;
import entity.ProductoInicial;
import gestor.GestorProductos;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
	
	public static CU03Controller01 get() {
        if (instance == null){
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU03Controller01) App.setRoot("CU03View01", "AlChi: Registrar entrada de productos");
        }    
        return instance;
    }
	
	@FXML
	private ComboBox<DTOTipoProductoCU02> productos;
	
	@FXML
	private ComboBox<DTOProveedor> proveedores;
	
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
	private Button btnEliminarFila;
	
	@FXML
	private Button btnDuplicarFila;
	
	@FXML
	private Button btnEditarFila;
	
	@FXML
	private TableView<ProductoInicial> tabla;
    
	public CU03Controller01() {	}
	
    @FXML
    private void initialize(){
    	setCombos();
    }
    
    private void setCombos() {
    	productos.getItems().clear();
    	productos.getItems().add(new DTOTipoProductoCU02("Seleccionar producto"));
    	productos.getItems().addAll(GestorProductos.get().getTiposProductosCu02());
    	productos.getSelectionModel().selectFirst();
    	
    	proveedores.getItems().clear();
    	proveedores.getItems().add(new DTOProveedor("Seleccionar proveedor"));
    	proveedores.getItems().addAll(GestorProveedor.get().getProveedores());
    	proveedores.getSelectionModel().selectFirst();
    }
    
    @FXML
	private void btnAgregarTipoProducto() {
    	CU01Controller.get().setControllerCu03(this);;
    }
    
    @FXML
	private void btnAgregarProveedor() {
    	CU07Controller02.get();
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
        CU03Controller02.get();
    }    

    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	sceneAnterior = null;
    	tituloAnterior = null;
    	instance = null;
	}
    
    
    //---------------------------------------------------
	public void setearTipoProducto(Integer id, String nombre) { 
		DTOTipoProductoCU02 dto = new DTOTipoProductoCU02(id, nombre);
		productos.getItems().add(dto);
    	productos.getSelectionModel().clearSelection();
    	productos.getSelectionModel().select(dto);
    	
    }
    
    @SuppressWarnings("exports")
	public void setearProveedor(DTOProveedor dto) {   	
    	if(dto != null) {
    		proveedores.getItems().add(dto);
    		proveedores.getSelectionModel().clearSelection();
        	proveedores.getSelectionModel().select(dto);
    	}
    }
    
    //TODO CU03 addlistener para cuando se finalice de ezscribir los campos de peso y precio
}
