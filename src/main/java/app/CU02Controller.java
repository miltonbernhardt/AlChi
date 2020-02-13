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
        	instance = new CU02Controller();
        }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU02View", "AlChi: Buscar productos");
	}

	private DTOTipoProductoCU02 productosSeleccionado = null;
	
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
    }
     
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorCategoria.get().getDTOCategorias());
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
    	
    	if(categoria.getValue() != null) {
    		idCategoria = categoria.getValue().getId();
    	}

    	if(!nombre.getText().isBlank()) {
    		nombreProducto = nombre.getText();
    	}
    	
    	if(siVende.isSelected()) {
    		vende = true;
    	}

    	if(noVende.isSelected()) {
    		vende = false;
    	}
    	
    	cargarTabla(GestorProductos.get().buscarTiposProductos(idCategoria, nombreProducto, vende));    	 
	}
    
    @FXML
    private void btnAgregar() {
    	new CU01Controller();
    	CU01Controller.get().setView();
	}
    
    @FXML
    private void btnEditar(){
    	App.setObjectScene(productosSeleccionado.getIdProducto());
    	new CU05Controller();
    	CU05Controller.get().setView(productosSeleccionado.getIdProducto());
    	//CU05Controller.get().setProducto();
	}

    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
    
    @FXML
    private void seleccionarProducto() {
    	productosSeleccionado = tabla.getSelectionModel().getSelectedItem();
		if(productosSeleccionado != null) {
			btnEditar.setDisable(false);
		}
    	//TODO CU02 proseguir de aquí AGREGAR accion para cuando se clickea un cliente y un boton para seleccionar o doble click
    }
}
