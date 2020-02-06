package app;

import java.io.IOException;

import entity.ProductoEmpaquetado;
import entity.TipoProducto;
import enums.TipoPaquete;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Registro de salida de productos"
 */
public class CU04Controller {
	
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
	
	public CU04Controller(){ }
    
    @FXML
    private void initialize(){
    	setCombo();
    }
     
    @FXML
    private void setCombo(){
    	
    }
    
    @FXML
    private void btnVolver() throws IOException {
    	App.volver();
	}
	
    @FXML
    private void btnAnadirVenta() {
		
	}
	
    @FXML
    private void btnAnadirCombo() throws IOException {
        App.setRoot("CU06View");
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
	
}
