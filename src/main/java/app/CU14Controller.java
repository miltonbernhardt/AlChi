package app;

import java.io.IOException;

import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Buscar combos"
 */
public class CU14Controller {
	
	@FXML
	private DatePicker fechaAntes;
	
	@FXML
	private DatePicker fechaDespues;
	
	@FXML
	private TextField precioMenor;
	
	@FXML
	private TextField precioMayor;
	
	@FXML
	private TableView<TipoProducto> tabla;

	public CU14Controller(){ }
    
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
