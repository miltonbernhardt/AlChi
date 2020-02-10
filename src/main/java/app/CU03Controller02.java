package app;

import java.io.IOException;
import entity.ProductoInicial;
import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CU03Controller02 {
	
	@FXML
	private ComboBox<TipoProducto> productos;
	
	@FXML
	private TextField precio100Anterior;
	
	@FXML
	private TextField precio100Actual;
	
	@FXML
	private TextField precio250Anterior;
	
	@FXML
	private TextField precio250Actual;
	
	@FXML
	private TextField precio500Anterior;
	
	@FXML
	private TextField precio500Actual;
	
	@FXML
	private TextField precio1000Anterior;
	
	@FXML
	private TextField precio1000Actual;
	
	@FXML
	private TextField precioUnidadAnterior;
	
	@FXML
	private TextField precioUnidadActual;
	
	@FXML
	private RadioButton rd100Anterior;
	
	@FXML
	private RadioButton rd100Actual;
	
	@FXML
	private RadioButton rd250Anterior;
	
	@FXML
	private RadioButton rd250Actual;
	
	@FXML
	private RadioButton rd500Anterior;
	
	@FXML
	private RadioButton rd500Actual;
	
	@FXML
	private RadioButton rd1000Anterior;
	
	@FXML
	private RadioButton rd1000Actual;
	
	@FXML
	private RadioButton rdUnidadAnterior;
	
	@FXML
	private RadioButton rdUnidadActual;
	
	@FXML
	private TableView<ProductoInicial> tabla;
	
	public CU03Controller02(){ }
    
    @FXML
    private void initialize(){
    	
    }
	
	@FXML
    private void btnConfirmarPrecios() {
		
	}
    
	@FXML
    private void btnFinalizar() throws IOException {
    	
    }

    @FXML
    private void btnVolverCU0301() throws IOException {
    	App.setRoot("CU03View01");;
    }
}
