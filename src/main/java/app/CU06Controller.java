package app;

import java.util.List;
import dto.DTOCU06;
import gestor.GestorProductos;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller para la view de "Registrar venta de combos"
 */
public class CU06Controller {
	private static CU06Controller instance = null;
	
    public CU06Controller() { }

    public static CU06Controller get() {
    	App.setViewAnterior();	
        if (instance == null){ 
    		instance = (CU06Controller) App.setRoot("CU06View", "AlChi: Registrar venta de combos");
    	}    
        return instance;
    }
    
    private DTOCU06 paqueteSeleccionado1 = null;
    private DTOCU06 paqueteSeleccionado2 = null;
    
    @FXML private Button btnAnadirAlCombo;
    @FXML private Button btnEliminarDelCombo;
	
	@FXML private TableView<DTOCU06> tabla1;
	@FXML private TableColumn<DTOCU06, String> columnaProducto1;	
	@FXML private TableColumn<DTOCU06, String> columnaFormaVenta1;
	@FXML private TableColumn<DTOCU06, String> columnaPrecioVenta1;	
	@FXML private TableColumn<DTOCU06, String> columnaProveedor1;	
	@FXML private TableColumn<DTOCU06, String> columnaCodigoBarra1;	
	@FXML private TableColumn<DTOCU06, String> columnaVencimiento1;	
	
	@FXML private TableView<DTOCU06> tabla2;
	@FXML private TableColumn<DTOCU06, String> columnaProducto2;	
	@FXML private TableColumn<DTOCU06, String> columnaFormaVenta2;
	@FXML private TableColumn<DTOCU06, String> columnaPrecioVenta2;	
	@FXML private TableColumn<DTOCU06, String> columnaProveedor2;	
	@FXML private TableColumn<DTOCU06, String> columnaCodigoBarra2;	
	@FXML private TableColumn<DTOCU06, String> columnaVencimiento2;

	
	@FXML private TextField precioVenta;
    
    @FXML private void initialize(){
    	iniciarTabla();
    	cargarTabla();
    }
    
    
    private void iniciarTabla() {
    	tabla1.setPlaceholder(new Label("No hay productos empaquetados que mostrar."));
    	columnaProducto1.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	columnaFormaVenta1.setCellValueFactory(new PropertyValueFactory<>("formaVenta"));
    	columnaPrecioVenta1.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	columnaProveedor1.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
    	columnaCodigoBarra1.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
    	columnaVencimiento1.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
    	
    	tabla2.setPlaceholder(new Label("No hay productos empaquetados que mostrar."));
    	columnaProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	columnaFormaVenta2.setCellValueFactory(new PropertyValueFactory<>("formaVenta"));
    	columnaPrecioVenta2.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	columnaProveedor2.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
    	columnaCodigoBarra2.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
    	columnaVencimiento2.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
    }
    
    private void cargarTabla() {
    	List<DTOCU06> lista = GestorProductos.get().getPaquetes();
    	tabla1.getItems().clear();    
    	tabla1.getItems().addAll(lista);
    }
		
    @FXML private void btnAnadirAlCombo() {
		if(paqueteSeleccionado1 != null) {
			tabla2.getItems().add(paqueteSeleccionado1);
			tabla1.getItems().remove(paqueteSeleccionado1);
			calcularPrecioSugerido(true);
			tabla1.getSelectionModel().clearSelection();
			btnAnadirAlCombo.setDisable(true);
		}
	}
    
    @FXML private void btnEmpaquetar() {
		CU10Controller01.get();
		//TODO CU06 actualizar tabla dejando en la seleccion anterior al apriete del boton
	}
	
    @FXML private void btnEliminarProducto() {
		if(paqueteSeleccionado2 != null) {
			tabla1.getItems().add(paqueteSeleccionado2);
			tabla2.getItems().remove(paqueteSeleccionado2);
			calcularPrecioSugerido(false);
			tabla2.getSelectionModel().clearSelection();
			btnEliminarDelCombo.setDisable(true);
		}
	}
	
	@FXML private void btnConfirmarCombo() {
		
	}
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
    @FXML private void selecionarPaquete1(){
		DTOCU06 dto = tabla1.getSelectionModel().getSelectedItem();		
		if(dto != null) {
			paqueteSeleccionado1 = dto;
			btnAnadirAlCombo.setDisable(false);
		}
		else {
			btnAnadirAlCombo.setDisable(true);
		}
    }
    
    @FXML private void selecionarPaquete2(){
		DTOCU06 dto = tabla2.getSelectionModel().getSelectedItem();		
		if(dto != null) {
			paqueteSeleccionado2 = dto;
			btnEliminarDelCombo.setDisable(false);
		}
		else {
			btnEliminarDelCombo.setDisable(true);
		}
    }

    private void calcularPrecioSugerido(Boolean agrego) {
    	//TODO CU06 seguir
    	if(agrego) {
    		
    	}
    	else {
    		
    	}
    	
    }
}
