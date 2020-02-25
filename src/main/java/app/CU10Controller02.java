package app;

import dto.DTOFormaVentaCU10;
import dto.DTOEmpaquetadoCU10;
import dto.DTOProductoInicialCU10;
import dto.DTOTipoProductoCU10;
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
	@FXML private ComboBox<DTOTipoProductoCU10> tipoProducto;	
	@FXML private ComboBox<DTOProductoInicialCU10> productoInicial;
	@FXML private ComboBox<DTOFormaVentaCU10> formaVenta;
	@FXML private TextField cantidad;
	@FXML private Label productoRestante;
	@FXML private Label cantidadMaximaPaquete;
	@FXML private TableView<DTOEmpaquetadoCU10> tabla;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaProducto;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaProveedor;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaCodigoBarra;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaVencimiento;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaFormaVenta;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaCantidad;
    
    public CU10Controller02() { }
	
    @FXML private void initialize(){
    	
    }
	//TODO CU10.2 implementar
    
	@FXML private void btnAnadirEmpaquetamiento() {

	}
	
	@FXML private void btnConfirmarEmpaquetamiento() {
		
	}

    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
