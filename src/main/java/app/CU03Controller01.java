package app;

import java.io.IOException;

import entity.ProductoInicial;
import entity.Proveedor;
import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CU03Controller01 {	
	
	@FXML
	private ComboBox<TipoProducto> productos;
	
	@FXML
	private ComboBox<Proveedor> proveedores;
	
	@FXML
	private TextField precioCompra;
	
	@FXML
	private TextField cantidad;
	
	@FXML
	private TextField precio100;
	
	@FXML
	private TextField precio250;
	
	@FXML
	private TextField precio500;
	
	@FXML
	private TextField precio1000;
	
	@FXML
	private TextField precioUnidad;
	
	@FXML
	private TableView<ProductoInicial> tabla;
	
	public CU03Controller01(){ }
    
    @FXML
    private void initialize(){
    	
    }
    
    @FXML
	private void btnAgregarTipoProducto() {
    	
    }
    
    @FXML
	private void btnAgregarProveedor() {

    }
    
    @FXML
	private void btnAnadirProducto() {

    }
    
    @FXML
	private void btnEliminarFila() {

    }
    
    @FXML
	private void btnEditarFila() {

    }
    
    @FXML
	private void btnDuplicarFila() {
    	
    }  
    
    @FXML
    private void btnConfirmarProductos() throws IOException {
        App.setRoot("CU03View02");
    }    
    
    @FXML
	private void btnVolver() throws IOException {
    	App.volver();
	}
	
}
