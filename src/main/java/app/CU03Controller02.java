package app;

import entity.ProductoInicial;
import entity.TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/*
 * Controller para la view de "Confirmación del registro de entrada"
 */
public class CU03Controller02 {
	private static CU03Controller02 instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU03Controller02() { }

    public static CU03Controller02 get() {
        if (instance == null){ instance = new CU03Controller02(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU03View02", "AlChi: Confirmación de registro de entrada de productos");
	}
	
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
    
    @FXML
    private void initialize(){
    	
    }
	
	@FXML
    private void btnConfirmarPrecios() {
		
	}
    
	@FXML
    private void btnFinalizar() {
    	
    }

    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
