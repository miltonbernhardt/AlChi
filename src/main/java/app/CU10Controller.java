package app;

import java.io.IOException;

import entity.TipoProducto;
import enums.TipoPaquete;
import entity.ProductoEmpaquetado;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * Controller para la view de "Registrar empaquetamientos"
 */
public class CU10Controller {
	
	@FXML
	private ComboBox<TipoProducto> tipoProducto;
	
	@FXML
	private ComboBox<TipoPaquete> tipoPaquete;
	
	@FXML
	private ComboBox<Integer> cantidad;
	
	@FXML
	private TableView<ProductoEmpaquetado> tabla; //Corregir
	
	public CU10Controller(){ }
    
    @FXML
    private void initialize(){
    	
    }
    
	@FXML
    private void btnAnadirEmpaquetamiento() {
		
	}
		
	@FXML
    private void btnEliminar() {
		
	}
	
	@FXML
    private void btnEditar() {
		
	}
	
	@FXML
    private void btnConfirmarEmpaquetamiento() {
		
	}
	
    @FXML
    private void btnVolver() throws IOException {
    	App.volver();
	}
}
