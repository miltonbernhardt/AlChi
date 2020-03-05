package app;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dto.DTOCU03;
import dto.DTOCategoria;
import dto.DTOTipoProductoCU02;
import gestor.GestorCategoria;
import gestor.GestorEntradaSalida;
import gestor.GestorProductos;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Controller para la view de "Buscar tipos de productos"
 */
public class CU02Controller01 {	
	private static CU02Controller01 instance = null;
	
    public CU02Controller01() { }

    public static CU02Controller01 get() {
        if (instance == null){
        	App.setViewAnterior();	
        	instance = (CU02Controller01) App.setRoot("CU02View01", "Buscar productos");
        }    
        return instance;
    }
	
	private DTOTipoProductoCU02 productoSeleccionado = null;
	
	@FXML private ComboBox<DTOCategoria> categoria;
	
	@FXML private TextField nombre;
	
	@FXML private RadioButton noImporta;	
	@FXML private RadioButton siVende;	
	@FXML private RadioButton noVende;
	
	@FXML private TableView<DTOTipoProductoCU02> tabla;
	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaCategoria;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaNombre;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaEnVenta;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaTieneImagen;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaPrecio100;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaPrecio250;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaPrecio500;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaPrecio1000;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaPrecio2000;	
	@FXML private TableColumn<DTOTipoProductoCU02, String> columnaPrecioUnidad;
	
	@FXML private TextArea descripcion;	
	@FXML private ImageView imagen;	
	@FXML private Rectangle rectangulo;
    
    @FXML private void initialize(){
    	setCombo();
    	iniciarTabla();
    }
     
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorCategoria.get().getCategorias());
    	categoria.getSelectionModel().selectFirst();
    }
    
    private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay productos que mostrar."));
    	columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));
    	columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreTipoProducto"));
    	columnaEnVenta.setCellValueFactory(new PropertyValueFactory<>("enVenta"));
    	columnaTieneImagen.setCellValueFactory(new PropertyValueFactory<>("directorioImagen"));
    	columnaPrecio100.setCellValueFactory(new PropertyValueFactory<>("precio100"));
    	columnaPrecio250.setCellValueFactory(new PropertyValueFactory<>("precio250"));
    	columnaPrecio500.setCellValueFactory(new PropertyValueFactory<>("precio500"));
    	columnaPrecio1000.setCellValueFactory(new PropertyValueFactory<>("precio1000"));
    	columnaPrecio2000.setCellValueFactory(new PropertyValueFactory<>("precio2000"));
    	columnaPrecioUnidad.setCellValueFactory(new PropertyValueFactory<>("precioUnidad"));
    	
    	tabla.setRowFactory( tv -> {
    	    TableRow<DTOTipoProductoCU02> fila = new TableRow<>();
    	    fila.setOnMouseClicked(event -> {
    	    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	        if (event.getClickCount() == 2 && (! fila.isEmpty()) && productoSeleccionado != null ) {
    	        	productoSeleccionado = fila.getItem();
    	        	getOptions();
    	        }
    	    });
    	    return fila ;
    	});
    }

    private void cargarTabla(List<DTOTipoProductoCU02> lista) {
    	tabla.getItems().clear();    	
    	Iterator<DTOTipoProductoCU02> iteratorProductos = lista.iterator();    	
    	while(iteratorProductos.hasNext()) {
    		tabla.getItems().add(iteratorProductos.next());	
    	}
    }
	
    @FXML private void btnBuscar() {
    	Integer idCategoria = null;
    	String nombreProducto = null;
    	Boolean vende = null;    	
    	
    	if(categoria.getValue() != null) idCategoria = categoria.getValue().getId();

    	if(!nombre.getText().isBlank()) nombreProducto = nombre.getText();
    	
    	if(siVende.isSelected()) vende = true;

    	if(noVende.isSelected()) vende = false;
    	
    	cargarTabla(GestorProductos.get().buscarTiposProductos(idCategoria, nombreProducto, vende));    	 
	}
    
    @FXML private void btnAgregar() {
    	CU01Controller.get();
	}
    
    public void actualizarProductoEditado(Integer id) {
    	DTOTipoProductoCU02 dto =  GestorProductos.get().getTipoProductoCU02(id);
    	tabla.getItems().set(tabla.getSelectionModel().getSelectedIndex(), dto);
    	productoSeleccionado = dto;
    }
    
    @FXML private void seleccionarProducto() {
    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	if(productoSeleccionado != null) {
        	if(!productoSeleccionado.getDirectorioImagenS().isEmpty()) {
        		URI imagenPath = URI.create(productoSeleccionado.getDirectorioImagenS());
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
    
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
	private void getOptions() {
		ButtonType b1 = new ButtonType("Editar características"), b2 = new ButtonType("Editar precio"), b3 = new ButtonType("Dejar de vender todo el producto"), b4 = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		
		
		if(productoSeleccionado.getEnVentaF()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", b1, b2, b3, b4);
	    	alert.setTitle("Acción sobre '"+productoSeleccionado.getNombreTipoProducto()+"'");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¿Que desea hacer sobre '"+productoSeleccionado.getNombreTipoProducto()+"'?");
	    	
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
	    		CU05Controller cu05 = CU05Controller.get();
	    		cu05.setControllerCU02(this);
	    		cu05.setProducto(productoSeleccionado.getIdProducto());
	    	}
	    	else {
	    		if(options.get().equals(b2)) {
	    			editarPrecios();
	        	}
	    		else {
	        		if(options.get().equals(b3)) {
	        			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar baja", null, "¿Desea confirmar la baja de el producto '"+productoSeleccionado.getNombreTipoProducto()+"' (incluyendo el stock y los empaquetados disponiles)?");
		    			if (result.get() == ButtonType.OK){
		    				if(GestorEntradaSalida.get().dejarVender(productoSeleccionado.getIdProducto())) {
		    					productoSeleccionado.setEnVenta(false);
			        			tabla.getColumns().get(2).setVisible(false);
			        			tabla.getColumns().get(2).setVisible(true);
			        			PanelAlerta.getInformation("Aviso", null, "El producto de '"+productoSeleccionado.getNombreTipoProducto()+"' ha sido correctamente dado de baja.");
			    			}
		    			}
	            	}
	    		}
	    	}
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION, "", b1, b2, b4);
	    	alert.setTitle("Acción sobre '"+productoSeleccionado.getNombreTipoProducto()+"'");
	    	alert.setHeaderText(null);
	    	alert.setContentText("¿Que desea hacer sobre '"+productoSeleccionado.getNombreTipoProducto()+"'?");
	    	
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
	    		CU05Controller cu05 = CU05Controller.get();
	    		cu05.setControllerCU02(this);
	    		cu05.setProducto(productoSeleccionado.getIdProducto());
	    	}
	    	else {
	    		if(options.get().equals(b2)) {
	    			editarPrecios();
	        	}
	    	}
		}
	}
	
	private void editarPrecios() {
		CU02Controller02 controller = CU02Controller02.get();
		controller.addDTOCU03(new DTOCU03(productoSeleccionado));
		controller.setController(this);
	}
}
