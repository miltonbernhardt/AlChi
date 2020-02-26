package app;

import java.util.List;
import java.util.Optional;

import dto.DTOCU06;
import enums.Porcentaje;
import gestor.GestorProductos;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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
    
    private CU04Controller controllerCu04 = null;
    
    private DTOCU06 paqueteSeleccionado1 = null;
    private DTOCU06 paqueteSeleccionado2 = null;
    private Float precioSugerido = 0f;
    
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
    	addListenerCampos();
    	precioVenta.setFocusTraversable(false);
    }
    
    private void iniciarTabla() {    	
    	tabla1.setPlaceholder(new Label("No hay productos empaquetados que mostrar."));
    	columnaProducto1.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	columnaFormaVenta1.setCellValueFactory(new PropertyValueFactory<>("formaVenta"));
    	columnaPrecioVenta1.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	columnaProveedor1.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
    	columnaCodigoBarra1.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
    	columnaVencimiento1.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
    	
    	tabla1.setRowFactory( tv -> {
    	    TableRow<DTOCU06> fila = new TableRow<>();
    	    fila.setOnMouseClicked(event -> {
    	    	paqueteSeleccionado1 = tabla1.getSelectionModel().getSelectedItem();
    	        if (event.getClickCount() == 2 && (! fila.isEmpty()) && paqueteSeleccionado1 != null ) {
    	        	btnAnadirAlCombo();
    	        }
    	    });
    	    return fila ;
    	});
    	
    	tabla2.setPlaceholder(new Label("No hay productos agregados al combo que mostrar."));
    	columnaProducto2.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
    	columnaFormaVenta2.setCellValueFactory(new PropertyValueFactory<>("formaVenta"));
    	columnaPrecioVenta2.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	columnaProveedor2.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
    	columnaCodigoBarra2.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
    	columnaVencimiento2.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
    	
    	tabla2.setRowFactory( tv -> {
    	    TableRow<DTOCU06> fila = new TableRow<>();
    	    fila.setOnMouseClicked(event -> {
    	    	paqueteSeleccionado2 = tabla2.getSelectionModel().getSelectedItem();
    	        if (event.getClickCount() == 2 && (! fila.isEmpty()) && paqueteSeleccionado2 != null ) {
    	        	btnEliminarProducto();
    	        }
    	    });
    	    return fila ;
    	});
    }
    
    private void cargarTabla() {
    	List<DTOCU06> lista = GestorProductos.get().getPaquetes();
    	tabla1.getItems().clear();    
    	tabla1.getItems().addAll(lista);
    }
    
    @SuppressWarnings("exports")
	public void addToTabla(List<DTOCU06> lista){
    	tabla1.getItems().addAll(lista);
    }
    
    private void addListenerCampos() {
    	precioVenta.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	if(! precioVenta.getText().isBlank()) {
            		App.setValido(precioVenta);
            		precioVenta.setText(precioVenta.getText().replace(',', '.'));   
            		App.setValido(precioVenta);
            	}
            	else
            		precioVenta.setText("");
            }
        }); 
    	
    	precioVenta.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
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
		if(tabla2.getItems().size() > 0 && !precioVenta.getText().isBlank()) {
			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar combo", null, "¿Desea confirmar los productos en el combo?");
			if (result.get() == ButtonType.OK){
				if(controllerCu04 != null) {
					controllerCu04.addComboToTable(tabla1.getItems(), new DTOCU06(tabla2.getItems(), Float.parseFloat(precioVenta.getText())));
					volver();
				} 
			}			
		}
    	else {
    		String cadena = "";    		
    		if(tabla2.getItems().size() == 0) {
    			cadena =  "Se debe agregar al menos 1 producto al combo.";
    			
    		}
    		else {
    			App.setError(precioVenta);
    			cadena = "El precio de venta del combo no debe ser cero.";
    		}    		
    		PanelAlerta.getError("Aviso", null,cadena);
    	}
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
    	if(agrego) {
    		if(paqueteSeleccionado1 != null) {
    			Float precio = paqueteSeleccionado1.getPrecioVentaF()/Porcentaje.NORMAL.getValue();
    			Float modulo5 = precio%5;
    	    	if(modulo5 >= 2.5) {
    	    		precio = precio - modulo5 + 5;
    	    	}
    	    	else {
    	    		precio = precio - modulo5;
    	    	}   	
    			precioSugerido = precioSugerido + precio;
    			
    		}
    	}
    	else {
    		if(paqueteSeleccionado2 != null) {
    			Float precio = paqueteSeleccionado2.getPrecioVentaF()/Porcentaje.NORMAL.getValue();
    			Float modulo5 = precio%5;
    	    	if(modulo5 >= 2.5) {
    	    		precio = precio - modulo5 + 5;
    	    	}
    	    	else {
    	    		precio = precio - modulo5;
    	    	}   	
    			precioSugerido = precioSugerido - precio;
    		}    		
    	}
    	
    	Float precioSugetido = precioSugerido*Porcentaje.COMBO.getValue();
		Float modulo5 = precioSugetido%5;
    	if(modulo5 >= 2.5) {
    		precioSugetido = precioSugetido - modulo5 + 5;
    	}
    	else {
    		precioSugetido = precioSugetido - modulo5;
    	}   	
		precioVenta.setText(precioSugetido.toString());
    	
    }
    
    private void validarCampos(KeyEvent e) {
    	Character caracter = e.getCharacter().charAt(0);
    	String temporal = "";
		if(Character.isDigit(caracter) ||  caracter == '.' || caracter == ','){
			temporal = precioVenta.getText();	
			if(( temporal.contains(".") || temporal.contains(",")) && ( caracter == '.' || caracter == ',' )) {
				e.consume();
			}	
		}
		else{
			e.consume();			
		}
    }

	@SuppressWarnings("exports")
	public void setControllerCu04(CU04Controller controllerCu04, List<DTOCU06> itemsDisponibles) {
		this.controllerCu04 = controllerCu04;
		tabla1.getItems().clear();
		addToTabla(itemsDisponibles);
	}
}
