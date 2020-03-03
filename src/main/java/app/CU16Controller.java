package app;

import java.time.LocalDate;
import java.util.Optional;
import dto.DTOCU16;
import dto.DTOCategoria;
import dto.DTOProveedor;
import enums.TipoPaquete;
import gestor.GestorCategoria;
import gestor.GestorEntradaSalida;
import gestor.GestorProductos;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CU16Controller {
	private static CU16Controller instance = null;

    public static CU16Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU16Controller) App.setRoot("CU16View", "AlChi: Búsqueda de productos empaquetados");
        }    
        return instance;
    }
    
    private DTOCU16 productoSeleccionado = null;
	
	@FXML private ComboBox<DTOCategoria> categorias;
	@FXML private ComboBox<DTOProveedor> proveedores;
	@FXML private ComboBox<String> formaVenta;
	
	@FXML private TextField codigoBarra;	
	@FXML private TextField nombreProducto;
	
	@FXML private DatePicker fechaVentaAntes;	
	@FXML private DatePicker fechaVentaDespues;
	
	@FXML private RadioButton noImportaDadoBaja;	
	@FXML private RadioButton siDadoBaja;	
	@FXML private RadioButton noDadoBaja;	
	
	@FXML private RadioButton noImportaVendido;	
	@FXML private RadioButton siVendido;	
	@FXML private RadioButton noVendido;	
	
	@FXML private TableView<DTOCU16> tabla;
	
	@FXML private TableColumn<String, DTOCU16> columnaCategoria;
	@FXML private TableColumn<String, DTOCU16> columnaNombre;
	@FXML private TableColumn<String, DTOCU16> columnaFormaVenta;
	@FXML private TableColumn<String, DTOCU16> columnaProveedor;
	@FXML private TableColumn<String, DTOCU16> columnaCodigoBarra;
	@FXML private TableColumn<String, DTOCU16> columnaVencimiento;
	@FXML private TableColumn<String, DTOCU16> columnaDadoBaja;
	@FXML private TableColumn<String, DTOCU16> columnaVendido;
	@FXML private TableColumn<String, DTOCU16> columnaFechaVenta;
	@FXML private TableColumn<String, DTOCU16> columnaPrecioVenta;
    
    public CU16Controller() { }
	
    @FXML private void initialize(){
    	setCombos();
    	iniciarTabla();
    	iniciarCalendario();
    }  
	 
	private void setCombos() {
    	categorias.getItems().clear();
    	categorias.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categorias.getItems().addAll(GestorCategoria.get().getCategorias());
    	categorias.getSelectionModel().selectFirst();
    	
    	proveedores.getItems().clear();
    	proveedores.getItems().add(new DTOProveedor(null, "Seleccionar proveedor"));
    	proveedores.getItems().addAll(GestorProveedor.get().getProveedores());
    	proveedores.getSelectionModel().selectFirst();    	
    	
    	formaVenta.getItems().clear();
    	formaVenta.getItems().add("Seleccionar forma de venta");
    	
    	TipoPaquete[] t = TipoPaquete.values();
    	
    	for(int i = 0; i<TipoPaquete.values().length; i++) {
    		formaVenta.getItems().add(t[i].name());
    	}
    	
    	formaVenta.getSelectionModel().selectFirst();   
	}
	
	private void iniciarTabla() {
	   	tabla.setPlaceholder(new Label("No hay productos empaquetados que mostrar."));
	   	columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
	   	columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
	   	columnaFormaVenta.setCellValueFactory(new PropertyValueFactory<>("formaVenta"));
	   	columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
	   	columnaCodigoBarra.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
	   	columnaVencimiento.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
	   	columnaDadoBaja.setCellValueFactory(new PropertyValueFactory<>("dadoBaja"));
	   	columnaVendido.setCellValueFactory(new PropertyValueFactory<>("vendido"));
	   	columnaFechaVenta.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
	   	columnaPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
	  	
    	tabla.setRowFactory( tv -> {
    	    TableRow<DTOCU16> fila = new TableRow<>();
    	    fila.setOnMouseClicked(event -> {
    	    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	        if (event.getClickCount() == 2 && (! fila.isEmpty()) && productoSeleccionado != null ) {
    	        	productoSeleccionado = fila.getItem();
    	        	if(!productoSeleccionado.getDadoBajaB() && !productoSeleccionado.getVendidoB()) {
    	        		getOptions();
    	        	}
    	        	else
    	        		PanelAlerta.getInformation("Aviso", null, "No hay opciones sobre el producto empaquetado seleccionado.");
    	        }
    	    });
    	    return fila ;
    	});
    	
	}
    
    private void iniciarCalendario() {   
    	LocalDate minDate = LocalDate.now();    	
    	fechaVentaAntes.setDayCellFactory(d -> new DateCell() {
    		@Override
    		public void updateItem(LocalDate item, boolean empty) {
    			super.updateItem(item, empty);
    	        setDisable(item.isAfter(minDate));
    	}});
    	
    	fechaVentaDespues.setDayCellFactory(d -> new DateCell() {
    		@Override
    		public void updateItem(LocalDate item, boolean empty) {
    			super.updateItem(item, empty);
    	        setDisable(item.isAfter(minDate));
    	}});
    }

	@FXML public void btnBuscar() {
		Integer idCategoria=categorias.getValue().getId(), idProveedor = proveedores.getValue().getId();
		String textCodigoBarra=codigoBarra.getText(), textFormaVenta = formaVenta.getValue(), textNombreProducto = null;
		LocalDate fechaVenAntes=fechaVentaAntes.getValue(), fechaVenDespues=fechaVentaDespues.getValue();
		Boolean dadoBaja = null, vendido = null;
		
		if(textFormaVenta.equals("Seleccionar forma de venta")) {
			textFormaVenta = "";
		}
		
		if(!nombreProducto.getText().isBlank()) 
			textNombreProducto = nombreProducto.getText();
		
		if(siDadoBaja.isSelected()) 
			dadoBaja = true;
		else {
			if(noDadoBaja.isSelected())
				dadoBaja = false;
		}
		
		if( !siVendido.isDisabled() ) {
			if(siVendido.isSelected()) 
				vendido = true;
			else {
				if(noVendido.isSelected())
					vendido = false;
			}
		}
		
		tabla.getItems().clear();
		tabla.getItems().addAll(GestorProductos.get().buscarProductosEmpaquetados(idCategoria, textNombreProducto, textFormaVenta, textCodigoBarra, idProveedor, dadoBaja, vendido, fechaVenAntes, fechaVenDespues));
	}
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
    @FXML private void seleccionarProducto() {
    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    }
    
    @FXML private void vaciarFechaAntes() {
    	fechaVentaAntes.setValue(null);
    }
    
    @FXML private void vaciarFechaDespues() {
    	fechaVentaDespues.setValue(null);
    }
    
    @FXML private void selecionarSiDadoBaja() {
    	if(siDadoBaja.isSelected()) {
    		siVendido.setDisable(true);
    		noVendido.setDisable(true);
    		noImportaVendido.setDisable(true);
    	}
    	else {
    		siVendido.setDisable(false);
    		noVendido.setDisable(false);
    		noImportaVendido.setDisable(false);
    	}
    }
    
	private void getOptions() {
		ButtonType b1 = new ButtonType("Venderlo"), b2 = new ButtonType("Darlo de baja"), b3 = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		Alert alert = new Alert(AlertType.CONFIRMATION, "", b1, b2, b3);
    	alert.setTitle("Acción sobre '"+productoSeleccionado.getNombreProducto()+"': "+productoSeleccionado.getFormaVentaE());
    	alert.setHeaderText(null);
    	alert.setContentText("¿Que desea hacer sobre '"+productoSeleccionado.getNombreProducto()+"': "+productoSeleccionado.getFormaVentaE()+"?");
    	
    	App.setStyle(alert.getDialogPane());
		
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	Optional<ButtonType> options = alert.showAndWait();
    	
    	if(options.get().equals(b1)) {
    		CU04Controller controller = CU04Controller.get();
    		controller.addToVenta(GestorProductos.get().getDTOCU06(productoSeleccionado.getIdProductoEmpaquetado()));
    		controller.setController(this);    		
    	}
    	else {
    		if(options.get().equals(b2)) {
        		if(GestorEntradaSalida.get().darBajaProductoEmpaquetado(productoSeleccionado)) {
        			tabla.getColumns().get(7).setVisible(false);
        			tabla.getColumns().get(7).setVisible(true);
        			PanelAlerta.getInformation("Aviso", null, "El producto empaquetado de '"+productoSeleccionado.getNombreProducto()+"'\nha sido correctamente dado de baja.");
        			
    			}
        	}
    	}
		
	}	
	
}
