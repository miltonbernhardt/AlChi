package app;

import entity.Categoria;
import entity.ProductoInicial;
import entity.Proveedor;
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
	
    public CU08Controller() { }

    public static CU08Controller get() {
        if (instance == null){ instance = new CU08Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU08View", "AlChi: Búsqueda de productos en stock");
	}
	
	@FXML
	private ComboBox<Categoria> categoria;
		
	@FXML
	private TextField codigoBarra;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private ComboBox<Proveedor> proveedores;
	
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
