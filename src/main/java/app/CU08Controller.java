package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import dto.DTOCU08;
import dto.DTOCategoria;
import dto.DTOProductoInicialCU10;
import dto.DTOProveedor;
import gestor.GestorCategoria;
import gestor.GestorEntradaSalida;
import gestor.GestorProductos;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controller para la view de "Buscar stock de productos"
 * para productos iniciales
 */
public class CU08Controller {	
	private static CU08Controller instance = null;

    public static CU08Controller get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU08Controller) App.setRoot("CU08View", "Buscar stock de productos");
        }    
        return instance;
    }
    
    
    private DTOCU08 productoSeleccionado = null;
	
	@FXML private ComboBox<DTOCategoria> categorias;
	@FXML private ComboBox<DTOProveedor> proveedores;
	
	@FXML private TextField nombreProducto;	
	@FXML private TextField codigoBarra;	
	
	@FXML private DatePicker fechaIngresoAntes;	
	@FXML private DatePicker fechaIngresoDespues;
	
	@FXML private RadioButton noImporta;	
	@FXML private RadioButton siDisponible;	
	@FXML private RadioButton noDisponible;	
	
	@FXML private TableView<DTOCU08> tabla;
	
	@FXML private TableColumn<String, DTOCU08> columnaCategoria;
	@FXML private TableColumn<String, DTOCU08> columnaCodigoBarra;
	@FXML private TableColumn<String, DTOCU08> columnaNombre;
	@FXML private TableColumn<String, DTOCU08> columnaProveedor;
	@FXML private TableColumn<LocalDate, DTOCU08> columnaIngreso;
	@FXML private TableColumn<LocalDate, DTOCU08> columnaPrecioCompra;
	@FXML private TableColumn<LocalDate, DTOCU08> columnaNoVendido;
	@FXML private TableColumn<LocalDate, DTOCU08> columnaVencimiento;
	@FXML private TableColumn<String, DTOCU08> columnaDisponible;
    
    public CU08Controller() { }
	
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
	}
	
	private void iniciarTabla() {
	   	tabla.setPlaceholder(new Label("No hay productos que mostrar."));
	   	columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
	   	columnaCodigoBarra.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
	   	columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
	   	columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
	   	columnaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
	   	columnaPrecioCompra.setCellValueFactory(new PropertyValueFactory<>("precioCompra"));
	   	columnaNoVendido.setCellValueFactory(new PropertyValueFactory<>("cantidadNoVendida"));
	   	columnaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
	  	columnaDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
	  	
    	tabla.setRowFactory( tv -> {
    	    TableRow<DTOCU08> fila = new TableRow<>();
    	    fila.setOnMouseClicked(event -> {
    	    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	        if (event.getClickCount() == 2 && (! fila.isEmpty()) && productoSeleccionado != null ) {
    	        	productoSeleccionado = fila.getItem();
    	        	if(productoSeleccionado.getDisponibleB()) {
    	        		getOptions();
    	        	}
    	        	else
    	        		PanelAlerta.getInformation("Aviso", null, "No hay opciones sobre el producto seleccionado.");
    	        }
    	    });
    	    return fila ;
    	});
    	
	}
	
    private void cargarTabla(List<DTOCU08> lista) {
    	tabla.getItems().clear();    	
    	tabla.getItems().addAll(lista);
    }
    
    private void iniciarCalendario() {   	
    	LocalDate minDate = LocalDate.now();    	
    	fechaIngresoAntes.setDayCellFactory(d -> new DateCell() {
    		@Override
    		public void updateItem(LocalDate item, boolean empty) {
    			super.updateItem(item, empty);
    	        setDisable(item.isAfter(minDate));
    	}});
    	
    	fechaIngresoDespues.setDayCellFactory(d -> new DateCell() {
    		@Override
    		public void updateItem(LocalDate item, boolean empty) {
    			super.updateItem(item, empty);
    	        setDisable(item.isAfter(minDate));
    	}});
    }

	@FXML public void btnBuscar() {
		Integer idCategoria=categorias.getValue().getId(), idProveedor = proveedores.getValue().getId();
		String textCodigoBarra=codigoBarra.getText(), textNombreProducto = null;
		LocalDate fechaIngAntes=fechaIngresoAntes.getValue(), fechaIngDespues=fechaIngresoDespues.getValue();
		Boolean disponible = null;
		
		if(!nombreProducto.getText().isBlank()) 
			textNombreProducto = nombreProducto.getText();
		
		if(siDisponible.isSelected()) 
			disponible = true;
		else {
			if(noDisponible.isSelected())
				disponible = false;
		}
		
		cargarTabla(GestorProductos.get().buscarProductosIniciales(idCategoria, idProveedor, textCodigoBarra, textNombreProducto, fechaIngAntes, fechaIngDespues, disponible));
	}
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
    @FXML private void seleccionarProducto() {
    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    }
    
    @FXML private void vaciarFechaAntes() {
    	fechaIngresoAntes.setValue(null);
    }
    
    @FXML private void vaciarFechaDespues() {
    	fechaIngresoDespues.setValue(null);
    }
    
	private void getOptions() {
		ButtonType b1 = new ButtonType("Empaquetarlo"), b2 = new ButtonType("Darlo de baja"), b3 = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		
		if(productoSeleccionado.getPrecioUnidad()>0f) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", b2, b3);
	    	alert.setTitle("Acción sobre '"+productoSeleccionado.getNombreProducto()+"'");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¿Que desea hacer sobre '"+productoSeleccionado.getNombreProducto()+"'?");
	    	
	    	App.setStyle(alert.getDialogPane());
			
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
	            if (KeyCode.ESCAPE == event.getCode()) {
	                stage.close();
	            }
	        });
	    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
	    	Optional<ButtonType> options = alert.showAndWait();
	    	
	    	if(options.get().equals(b2)) {
	    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar baja", null, "¿Desea confirmar la baja del producto empaquetado '"+productoSeleccionado.getNombreProducto()+"'?");
    			
    			if (result.get() == ButtonType.OK){
	        		if(GestorEntradaSalida.get().darBaja(productoSeleccionado)) {
	        			productoSeleccionado.setDisponible(false);
	        			tabla.getColumns().get(8).setVisible(false);
	        			tabla.getColumns().get(8).setVisible(true);
	        			PanelAlerta.getInformation("Aviso", null, "El producto empaquetado de '"+productoSeleccionado.getNombreProducto()+"' ha sido correctamente dado de baja.");
	        		}
    			}
        	}
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", b1, b2, b3);
	    	alert.setTitle("Acción sobre '"+productoSeleccionado.getNombreProducto()+"'");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¿Que desea hacer sobre '"+productoSeleccionado.getNombreProducto()+"'?");
	    	
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
	    		CU10Controller01 controller = CU10Controller01.get();
	    		controller.setControllerCu08(this);
	    		controller.empaquetarProducto(new DTOProductoInicialCU10(productoSeleccionado));
	    		
	    	}
	    	else {
	    		if(options.get().equals(b2)) {
	    			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar baja", null, "¿Desea confirmar la baja del producto empaquetado '"+productoSeleccionado.getNombreProducto()+"'?");
	    			
	    			if (result.get() == ButtonType.OK){
	    				if(GestorEntradaSalida.get().darBaja(productoSeleccionado)) {
		        			productoSeleccionado.setDisponible(false);
		        			tabla.getColumns().get(8).setVisible(false);
		        			tabla.getColumns().get(8).setVisible(true);
		        			PanelAlerta.getInformation("Aviso", null, "El producto empaquetado de '"+productoSeleccionado.getNombreProducto()+"' ha sido correctamente dado de baja.");
		    			}
	    			}
	        	}
	    	}
		}		
		
	}	
}
