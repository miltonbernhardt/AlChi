package app;

import java.io.IOException;

import entity.ProductoTotal;
import entity.Proveedor;
import enums.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Búsqueda de productos"
 */
public class CU08Controller {
	
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
	private TableView<ProductoTotal> tabla;
	
	@FXML
	private TableColumn<?, ?> categoria2;
	
	public CU08Controller(){ }
    
    @FXML
    private void initialize(){
    	
    }
	 
	@FXML
	private void btnBuscar() throws IOException {
		
	}
	
	@FXML
	private void btnVolver() throws IOException {
		App.volver();
	}

}
