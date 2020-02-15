package app;

import dto.DTOCategoria;
import dto.DTOProveedor;
import entity.ProductoInicial;
import gestor.GestorCategoria;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Búsqueda de productos en stock"
 */
public class CU08Controller {
	
	private static CU08Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU08Controller get() {
        if (instance == null){ 
    		sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU08Controller) App.setRoot("CU08View", "AlChi: Búsqueda de productos en stock");
        }    
        return instance;
    }
	
	@FXML
	private ComboBox<DTOCategoria> categoria;
		
	@FXML
	private TextField codigoBarra;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private ComboBox<DTOProveedor> proveedores;
	
	@FXML
	private DatePicker fechaAntes;
	
	@FXML
	private DatePicker fechaDespues;
	
	@FXML
	private CheckBox enVenta;
	
	@FXML
	private TableView<ProductoInicial> tabla;
	
	@FXML
	private TableColumn<?, ?> categoria2;
    
    public CU08Controller() { }
	
    @FXML
    private void initialize(){
    	setCombos();
    }    
	 
	private void setCombos() {
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorCategoria.get().getCategorias());
    	categoria.getSelectionModel().selectFirst();
    	
    	proveedores.getItems().clear();
    	proveedores.getItems().add(new DTOProveedor("Seleccionar proveedor"));
    	proveedores.getItems().addAll(GestorProveedor.get().getProveedores());
    	proveedores.getSelectionModel().selectFirst();
	}

	@FXML
	private void btnBuscar() {
		
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	instance = null;
	}
}
