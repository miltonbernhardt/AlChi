package app;

import gestor.GestorProductos;
import dto.DTOCU10FormaVenta;
import dto.DTOCU10ProductoInicial;
import dto.DTOCU10TipoProducto;
import entity.ProductoEmpaquetado;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 * Controller para la view de "Registrar empaquetamientos"
 */
public class CU10Controller {
	private static CU10Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU10Controller get() {
        if (instance == null){ 
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU10Controller) App.setRoot("CU10View", "AlChi: Registrar empaquetamientos");
        }
        return instance;
    }
	
    @FXML private Button btnAnadirEmpaquetamiento;
	@FXML private ComboBox<DTOCU10TipoProducto> tipoProducto;	
	@FXML private ComboBox<DTOCU10ProductoInicial> productoInicial;
	@FXML private ComboBox<DTOCU10FormaVenta> formaVenta;	
	@FXML private ComboBox<Integer> cantidad;	
	@FXML private Label vencimiento;	
	@FXML private Label proveedor;		
	@FXML private Label codigoBarra;	
	@FXML private TableView<ProductoEmpaquetado> tabla;
    
    public CU10Controller() { }
	
    @FXML private void initialize(){
    	setCombo();
    }
    
    private void setCombo() {
    	tipoProducto.getItems().clear();
    	tipoProducto.getItems().add(new DTOCU10TipoProducto(null, "Seleccionar producto"));
    	tipoProducto.getItems().addAll(GestorProductos.get().getTiposProducto());
    	tipoProducto.getSelectionModel().selectFirst();
    }
    
	@FXML private void btnAnadirEmpaquetamiento() {
		
	}
		
	@FXML  private void btnEliminar() {
		
	}
	
	@FXML private void btnEditar() {
		
	}
	
	@FXML private void btnConfirmarEmpaquetamiento() {
		
	}

    @FXML private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
    
    @FXML private void listenerProductos(){
    	DTOCU10TipoProducto dto = tipoProducto.getValue();
		if(dto.getId() != null && (dto.getProductosIniciales().size()>0)) {
			productoInicial.setDisable(false);
			productoInicial.getItems().clear();
			productoInicial.getItems().addAll(dto.getProductosIniciales());
			productoInicial.getSelectionModel().selectFirst();	
			
			formaVenta.setDisable(false);
			formaVenta.getItems().clear();
			formaVenta.getItems().addAll(dto.getFormasVenta());
			formaVenta.getSelectionModel().selectFirst();	
		}
		else {
			productoInicial.setDisable(true);
			productoInicial.getItems().clear();
			
			formaVenta.setDisable(true);
			formaVenta.getItems().clear();
		}    	
    }
    
    @FXML private void listenerProductosIniciales(){
    	
    }
}
