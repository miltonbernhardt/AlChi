package app;

import java.io.IOException;

import dto.DTOCategoria;
import entity.TipoProducto;
import gestor.GestorProductos;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Controller para la view de "Buscar tipos de productos"
 */
public class CU02Controller {
	
	@FXML
	private ComboBox<DTOCategoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private RadioButton noImporta;
	
	@FXML
	private RadioButton siVende;
	
	@FXML
	private RadioButton noVende;
	
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
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorProductos.get().getDTOCategorias());
    	categoria.getSelectionModel().selectFirst();
    }	

	
    @FXML
    private void btnBuscar() throws IOException {
    	Integer idCategoria = null;
    	String nombreProducto = null;
    	Boolean vende = null;    	
    	
    	if(categoria.getValue() != null) {
    		idCategoria = categoria.getValue().getId();
    	}

    	if(!nombre.getText().isBlank()) {
    		nombreProducto = nombre.getText();
    	}
    	
    	if(siVende.isSelected()) {
    		vende = true;
    	}

    	if(noVende.isSelected()) {
    		vende = false;
    	}
    	
    	GestorProductos.get().buscarTiposProductos(idCategoria, nombreProducto, vende); //TODO proseguir de aquí
    	
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
