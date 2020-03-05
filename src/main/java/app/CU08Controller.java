package app;

import java.net.MalformedURLException;
import java.net.URI;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
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
	
	@FXML private TextArea descripcion;	
	@FXML private ImageView imagen;	
	@FXML private Rectangle rectangulo;
    
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
    	        	if(productoSeleccionado.getCantidadNoVendidaF()>0.05f) {
    	        		getOptions();
    	        	}
    	        	else {
    	        		PanelAlerta.getInformation("Aviso", null, "No hay opciones válidas sobre el paquete inicial");
    	        	}
    	        	
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
    	if(productoSeleccionado != null) {
        	if(!productoSeleccionado.getDirectorioImagen().isEmpty()) {
        		URI imagenPath = URI.create(productoSeleccionado.getDirectorioImagen());
        		Image image;
    			try {
    				image = new Image(imagenPath.toURL().toString());
    				imagen.setImage(image);
    				rectangulo.setVisible(false);
    			} catch (MalformedURLException e) {
    				e.printStackTrace();
    			}    		
        	}	
        	else {
        		imagen.setImage(null);
        		rectangulo.setVisible(true);
        	}
    	}
    	else {
    		imagen.setImage(null);
    		rectangulo.setVisible(true);
    	}
    }
    
    @FXML private void vaciarFechaAntes() {
    	fechaIngresoAntes.setValue(null);
    }
    
    @FXML private void vaciarFechaDespues() {
    	fechaIngresoDespues.setValue(null);
    }
    
	private void getOptions() {
		ButtonType b1 = new ButtonType("Empaquetar"), b2 = new ButtonType("Dar de baja"), b3 = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE), b4 = new ButtonType("Dar de alta");
		
		if(!productoSeleccionado.getDisponibleB()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", b4, b3);
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
	    	stage.getIcons().add(new Image("app/icon/logoVentana.png"));
	    	Optional<ButtonType> options = alert.showAndWait();
	    	
	    	if(options.get().equals(b4)) {
	    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar alta", null, "¿Desea confirmar el alta del paquete inicial del producto '"+productoSeleccionado.getNombreProducto()+"'?");
    			
    			if (result.get() == ButtonType.OK){
	        		if(GestorEntradaSalida.get().darAltaProductoInicial(productoSeleccionado.getIdProducto(), productoSeleccionado.getIdTipoProducto())) {
	        			productoSeleccionado.setDisponible(true);
	        			tabla.getColumns().get(2).setVisible(false);
	        			tabla.getColumns().get(2).setVisible(true);
	        			PanelAlerta.getInformation("Aviso", null, "El paquete inicial en stock del producto '"+productoSeleccionado.getNombreProducto()+"' ha sido correctamente dado de alta.");
	        		}
    			}
        	}
		}
		else {
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
		    	stage.getIcons().add(new Image("app/icon/logoVentana.png"));
		    	Optional<ButtonType> options = alert.showAndWait();
		    	
		    	if(options.get().equals(b2)) {
		    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar baja", null, "¿Desea confirmar la baja del paquete inicial del producto '"+productoSeleccionado.getNombreProducto()+"'?");
	    			
	    			if (result.get() == ButtonType.OK){
		        		if(GestorEntradaSalida.get().darBajaProductoInicial(productoSeleccionado.getIdProducto(), productoSeleccionado.getIdTipoProducto())) {
		        			productoSeleccionado.setDisponible(false);
		        			tabla.getColumns().get(2).setVisible(false);
		        			tabla.getColumns().get(2).setVisible(true);
		        			PanelAlerta.getInformation("Aviso", null, "El paquete inicial en stock del producto '"+productoSeleccionado.getNombreProducto()+"' ha sido correctamente dado de baja.");
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
		    	stage.getIcons().add(new Image("app/icon/logoVentana.png"));
		    	Optional<ButtonType> options = alert.showAndWait();
		    	
		    	if(options.get().equals(b1)) {
		    		CU10Controller01 controller = CU10Controller01.get();
		    		controller.setControllerCu08(this);
		    		controller.empaquetarProducto(new DTOProductoInicialCU10(productoSeleccionado));
		    		
		    	}
		    	else {
		    		if(options.get().equals(b2)) {
		    			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar baja", null, "¿Desea confirmar la baja del paquete inicial del producto '"+productoSeleccionado.getNombreProducto()+"'?");
		    			
		    			if (result.get() == ButtonType.OK){
		    				if(GestorEntradaSalida.get().darBajaProductoInicial(productoSeleccionado.getIdProducto(), productoSeleccionado.getIdTipoProducto())) {
			        			productoSeleccionado.setDisponible(false);
			        			tabla.getColumns().get(8).setVisible(false);
			        			tabla.getColumns().get(8).setVisible(true);
			        			PanelAlerta.getInformation("Aviso", null, "El paquete inicial en stock del producto '"+productoSeleccionado.getNombreProducto()+"' ha sido correctamente dado de baja.");
			    			}
		    			}
		        	}
		    	}
			}	
		}
	}	
}
