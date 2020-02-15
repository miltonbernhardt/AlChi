package app;

import java.util.Iterator;
import java.util.List;
import dto.DTOCategoria;
import dto.DTOTipoProductoCU02;
import gestor.GestorCategoria;
import gestor.GestorProductos;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller para la view de "Buscar tipos de productos"
 */
public class CU02Controller {	
	private static CU02Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU02Controller() { }

    public static CU02Controller get() {
        if (instance == null){
    		sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU02Controller) App.setRoot("CU02View", "AlChi: Buscar productos");
        }    
        return instance;
    }
	
	private DTOTipoProductoCU02 productoSeleccionado = null;
	
	@FXML
	private ComboBox<DTOCategoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private RadioButton noImporta;
	
	@FXML
	private RadioButton siVende;
	
	@FXML
	private RadioButton noVende;
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private TableView<DTOTipoProductoCU02> tabla;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaCategoria;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaNombre;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaEnVenta;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaPrecio100;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaPrecio250;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaPrecio500;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaPrecio1000;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaPrecio2000;
	
	@FXML
	private TableColumn<DTOTipoProductoCU02, String> columnaPrecioUnidad;
    
    @FXML
    private void initialize(){
    	setCombo();
    	iniciarTabla();
    	
    	tabla.setRowFactory( tv -> {
    	    TableRow<DTOTipoProductoCU02> fila = new TableRow<>();
    	    fila.setOnMouseClicked(event -> {
    	    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	        if (event.getClickCount() == 2 && (! fila.isEmpty()) && productoSeleccionado != null ) {
    	        	@SuppressWarnings("unused")
					DTOTipoProductoCU02 dto = fila.getItem();    
    	        	//TODO CU02 si lo tengo que retornar al producto buscado
    	        	//Agregar que cuando se pase el mouse encima se avise de la posible accion
    	        }
    	    });
    	    return fila ;
    	});
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
    	columnaPrecio100.setCellValueFactory(new PropertyValueFactory<>("precio100"));
    	columnaPrecio250.setCellValueFactory(new PropertyValueFactory<>("precio250"));
    	columnaPrecio500.setCellValueFactory(new PropertyValueFactory<>("precio500"));
    	columnaPrecio1000.setCellValueFactory(new PropertyValueFactory<>("precio1000"));
    	columnaPrecio2000.setCellValueFactory(new PropertyValueFactory<>("precio2000"));
    	columnaPrecioUnidad.setCellValueFactory(new PropertyValueFactory<>("precioUnidad"));
    }

    private void cargarTabla(List<DTOTipoProductoCU02> lista) {
    	btnEditar.setDisable(true);
    	tabla.getItems().clear();    	
    	Iterator<DTOTipoProductoCU02> iteratorProductos = lista.iterator();    	
    	while(iteratorProductos.hasNext()) {
    		tabla.getItems().add(iteratorProductos.next());	
    	}
    }
	
    @FXML
    private void btnBuscar() {
    	Integer idCategoria = null;
    	String nombreProducto = null;
    	Boolean vende = null;    	
    	
    	if(categoria.getValue() != null) idCategoria = categoria.getValue().getId();

    	if(!nombre.getText().isBlank()) nombreProducto = nombre.getText();
    	
    	if(siVende.isSelected()) vende = true;

    	if(noVende.isSelected()) vende = false;
    	
    	cargarTabla(GestorProductos.get().buscarTiposProductos(idCategoria, nombreProducto, vende));    	 
	}
    
    @FXML
    private void btnAgregar() {
    	CU01Controller.get();
	}
    
    @FXML
    private void btnEditar(){
    	CU05Controller.get().setProducto(productoSeleccionado.getIdProducto());
	}
    
    public void actualizarProductoEditado(Integer id) {
    	DTOTipoProductoCU02 dto =  GestorProductos.get().getTipoProductoCU02(id);
    	tabla.getItems().set(tabla.getItems().indexOf(productoSeleccionado), dto);
    }
    
    @FXML
    private void seleccionarProducto() {
    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
		if(productoSeleccionado != null) {
			btnEditar.setDisable(false);
		}
    }
    
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
}
