package app;

import java.io.IOException;

import entity.TipoProducto;
import enums.Categoria;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Buscar tipos de productos"
 */
public class CU02Controller {
	
	@FXML
	private ComboBox<Categoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private CheckBox enVenta;
	
	@FXML
	private TableView<TipoProducto> tabla;
	
	
	public CU02Controller(){ }
    
    @FXML
    private void initialize(){
    	setCombo();
    }
     
    @FXML
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().addAll(Categoria.values());
    }	

	
    @FXML
    private void btnBuscar() throws IOException {
    	Boolean seleccionCombo = true, completoNombre = true, seVende = true;
    	
    	if(categoria.getValue() == null) {
    		seleccionCombo = false;
    	}

    	if(nombre.getText().isBlank()) {
    		completoNombre = false;
    	}
    	
    	if(!enVenta.isSelected()) {
    		seVende = false;
    	}

    	if(seleccionCombo && completoNombre && seVende) {
    		//TODO CU02 buscar
    	}
    	llenarTabla();
	}
    
    @FXML
    private void llenarTabla() {
    	
    }

    @FXML
    private void btnVolver() throws IOException {
    	App.volver();
	}
}
