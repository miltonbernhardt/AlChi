package app;

import java.io.IOException;

import entity.ProductoEmpaquetado;
import entity.ProductoInicial;
import entity.TipoProducto;
import enums.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Registrar venta de combos"
 */
public class CU06Controller {
	
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
	
	public CU06Controller(){ }
    
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
	private void btnVolver() throws IOException {
		App.volver();
	}

}
