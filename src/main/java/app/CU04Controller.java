package app;

import java.util.List;
import java.util.Optional;

import dto.DTOCU06;
import gestor.GestorEntradaSalida;
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
 * Controller para la view de "Registro de salida de productos"
 */
public class CU04Controller {
	private static CU04Controller instance = null;
	
    public CU04Controller() { }

    public static CU04Controller get() {
        if (instance == null){
        	App.setViewAnterior();	
    		instance = (CU04Controller) App.setRoot("CU04View", "AlChi: Registro de salida de productos");
    	}    
        return instance;
    }
    
    private DTOCU06 paqueteSeleccionado1 = null;
    private DTOCU06 paqueteSeleccionado2 = null;
	
	@FXML private TextField precioVenta;	
	@FXML private Button btnAnadirVenta;
	@FXML private Button btnEliminarVenta;
	@FXML private Button btnEditarPrecio;
	
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
	
	private CU16Controller cu16Controller = null;

    @FXML  private void initialize(){
    	iniciarTabla();
    	cargarTabla();
    	addListenerCampos();
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
    	        	btnAnadirVenta();
    	        }
    	    });
    	    return fila ;
    	});
    	
    	tabla2.setPlaceholder(new Label("No hay productos en venta que mostrar."));
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
    	        	btnEliminarVenta();
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
	public void addComboToTable(List<DTOCU06> itemsNoUsados, DTOCU06 dtoCombo) {
    	tabla1.getItems().clear();
    	tabla1.getItems().addAll(itemsNoUsados);
    	tabla2.getItems().add(dtoCombo);
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
	
    @FXML private void btnAnadirVenta() {
    	if(paqueteSeleccionado1 != null) {
    		String f = precioVenta.getText();
    		if(!f.isBlank() || (Float.parseFloat(f) > 0) ) {
    			paqueteSeleccionado1.setPrecioVenta(Float.parseFloat(f));
    		}
			tabla2.getItems().add(paqueteSeleccionado1);
			tabla1.getItems().remove(paqueteSeleccionado1);
			tabla1.getSelectionModel().clearSelection();
			precioVenta.setText("");
			btnAnadirVenta.setDisable(true);
		}
	}
	
    @FXML private void btnAnadirCombo() {
        CU06Controller.get().setControllerCu04(this, tabla1.getItems());;
	}
    
    @FXML private void btnEmpaquetar() {
		CU10Controller01.get().setControllerCu04(this);
	}
	
    @FXML private void btnEliminarVenta() {
    	if(paqueteSeleccionado2 != null) {
    		
    		if(paqueteSeleccionado2.getListaCombo() != null) {
    			tabla1.getItems().addAll(paqueteSeleccionado2.getListaCombo());
    			tabla2.getItems().remove(paqueteSeleccionado2);
    		}
    		else {
    			tabla1.getItems().add(paqueteSeleccionado2);
    			tabla2.getItems().remove(paqueteSeleccionado2);
    		}    		
			
			tabla2.getSelectionModel().clearSelection();
			precioVenta.setText("");
			btnEliminarVenta.setDisable(true);
		}
	}
    
    @FXML private void btnEditarPrecio() {
    	if(paqueteSeleccionado2 != null) {
    		tabla2.getSelectionModel().getSelectedItem().setPrecioVenta(Float.parseFloat(precioVenta.getText()));
    		Integer index = tabla2.getSelectionModel().getSelectedIndex();
    		tabla2.getColumns().get(index).setVisible(false);
    		tabla2.getColumns().get(index).setVisible(true);
    		btnEditarPrecio.setDisable(true);
    		precioVenta.setText("");
    		tabla2.getSelectionModel().clearSelection();
		}
	}
	
    @FXML private void btnConfirmarVenta() {
    	if(tabla2.getItems().size() > 0) {
			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar venta", null, "¿Desea confirmar la venta de los productos?");
			if (result.get() == ButtonType.OK){
				if(GestorEntradaSalida.get().registrarSalida(tabla2.getItems())) {
					PanelAlerta.getInformation("Confirmación", null, "La transacción ocurrió de manera efectiva.");
					if(cu16Controller != null) {
						cu16Controller.btnBuscar();
					}
					volver();
				}
			}  
		}
    	else    		
    		PanelAlerta.getError("Aviso", null,"Se debe registrar la venta de al menos un producto.");
	}
	
    @FXML
    private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
    @FXML private void selecionarPaquete1(){
		DTOCU06 dto = tabla1.getSelectionModel().getSelectedItem();		
		if(dto != null) {
			paqueteSeleccionado1 = dto;
			precioVenta.setText(paqueteSeleccionado1.getPrecioVentaF().toString());
			btnAnadirVenta.setDisable(false);
		}
		else {
			btnAnadirVenta.setDisable(true);
		}
    }
    
    @FXML private void selecionarPaquete2(){
		DTOCU06 dto = tabla2.getSelectionModel().getSelectedItem();		
		if(dto != null) {
			paqueteSeleccionado2 = dto;
			precioVenta.setText(paqueteSeleccionado2.getPrecioVentaF().toString());
			btnEditarPrecio.setDisable(false);
			btnEliminarVenta.setDisable(false);
		}
		else {
			btnEditarPrecio.setDisable(true);
			btnEliminarVenta.setDisable(true);
		}
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

	public void setController(CU16Controller cu16Controller) {
		this.cu16Controller = cu16Controller;		
	}
	
	@SuppressWarnings("exports")
	public void addToVenta(DTOCU06 dto) {
		tabla2.getItems().add(dto);
		tabla1.getItems().remove(dto);
		//RODO CU16 remover de la tabla 1
		tabla1.getSelectionModel().clearSelection();
		precioVenta.setText("");
		btnAnadirVenta.setDisable(true);
	}

}
