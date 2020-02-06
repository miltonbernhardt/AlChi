package app;

import java.io.IOException;

import entity.Proveedor;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Manejar proveedores"
 */
public class CU07Controller {
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextField telefono;
	
	@FXML
	private TableView<Proveedor> tabla;

	public CU07Controller(){ }
    
    @FXML
    private void initialize(){
    	
    }
	
	@FXML
	private void btnAnadir() throws IOException {
		
	}
    
	@FXML
	private void btnEditar() throws IOException {
		
	}
	
	@FXML
	private void btnVolver() throws IOException {
    	App.volver();
	}
}
