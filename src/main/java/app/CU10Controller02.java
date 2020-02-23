package app;

import dto.DTOCU10FormaVenta;
import dto.DTOCU10Empaquetado;
import dto.DTOCU10ProductoInicial;
import dto.DTOCU10TipoProducto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CU10Controller02 {	
	private static CU10Controller02 instance = null;

    public static CU10Controller02 get() {
        if (instance == null){ 
        	App.setViewAnterior();
        	instance = (CU10Controller02) App.setRoot("CU10View02", "AlChi: Registrar empaquetamientos");
        }
        return instance;
    }
	
    @FXML private Button btnAnadirEmpaquetamiento;
    @FXML private Button btnDarBajaProductoInicial;
    @FXML private Button btnAnadirConOtroPaquete;
    @FXML private Button btnEditarFila;
    @FXML private Button btnEliminarFila;
	@FXML private ComboBox<DTOCU10TipoProducto> tipoProducto;	
	@FXML private ComboBox<DTOCU10ProductoInicial> productoInicial;
	@FXML private ComboBox<DTOCU10FormaVenta> formaVenta;
	@FXML private TextField cantidad;
	@FXML private Label productoRestante;
	@FXML private Label cantidadMaximaPaquete;
	@FXML private TableView<DTOCU10Empaquetado> tabla;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaProducto;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaProveedor;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaCodigoBarra;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaVencimiento;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaFormaVenta;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaCantidad;
    
    public CU10Controller02() { }
	
    @FXML private void initialize(){
    	
    }

    
	@FXML private void btnAnadirEmpaquetamiento() {

	}
	
	@FXML private void btnConfirmarEmpaquetamiento() {
		
	}

    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
