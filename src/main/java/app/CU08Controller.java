package app;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import dto.DTOCU08;
import dto.DTOCategoria;
import dto.DTOProveedor;
import dto.DTOTipoProducto;
import gestor.GestorCategoria;
import gestor.GestorProductos;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller para la view de "Búsqueda de productos en stock"
 */
public class CU08Controller {
	
	private static CU08Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU08Controller get() {
        if (instance == null){ 
    		sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU08Controller) App.setRoot("CU08View", "AlChi: Búsqueda de productos en stock");
        }    
        return instance;
    }
	
	@FXML private ComboBox<DTOCategoria> categorias;
	@FXML private ComboBox<DTOProveedor> proveedores;
	@FXML private ComboBox<DTOTipoProducto> productos;
	
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
	@FXML private TableColumn<LocalDate, DTOCU08> columnaVencimiento;
	@FXML private TableColumn<String, DTOCU08> columnaDisponible;
    
	//TODO CU08 agregar precioCompra
    public CU08Controller() { }
	
    @FXML private void initialize(){
    	//TODO CU08 doble click abre opciones
    	setCombos();
    	iniciarTabla();
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
	   	columnaVencimiento.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
	  	columnaDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
	}
	
    private void cargarTabla(List<DTOCU08> lista) {
    	tabla.getItems().clear();    	
    	Iterator<DTOCU08> iteratorProductos = lista.iterator();    	
    	while(iteratorProductos.hasNext()) {
    		tabla.getItems().add(iteratorProductos.next());	
    	}
    }

	@FXML private void btnBuscar() {
		Integer idCategoria=categorias.getValue().getId(), idProveedor = proveedores.getValue().getId(), idProducto=null;
		String textCodigoBarra=codigoBarra.getText();
		LocalDate fechaIngAntes=fechaIngresoAntes.getValue(), fechaIngDespues=fechaIngresoDespues.getValue();
		Boolean disponible = null;
		
		if(!productos.isDisable())
			idProducto=productos.getValue().getId();
		
		if(siDisponible.isSelected()) 
			disponible = true;
		else {
			if(noDisponible.isSelected())
				disponible = false;
		}
		
		cargarTabla(GestorProductos.get().buscarProductosIniciales(idCategoria, idProveedor, textCodigoBarra, idProducto, fechaIngAntes, fechaIngDespues, disponible));
	}
	
    @FXML private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	instance = null;
	}
    
    @FXML private void seleccionarCategoria() {
    	Integer idCategoria = categorias.getValue().getId();
    	if(idCategoria != null) {
    		productos.setDisable(false);
    		productos.getItems().clear();
    		productos.getItems().add(new DTOTipoProducto(null, "Seleccionar producto"));
    		productos.getItems().addAll(GestorProductos.get().getTiposProducto(idCategoria));
    		productos.getSelectionModel().selectFirst();
    	}
    	else {
    		productos.setDisable(true);
    		productos.getItems().clear();
    	}
    }
    
    @FXML private void vaciarFechaAntes() {
    	fechaIngresoAntes.setValue(null);
    }
    
    @FXML private void vaciarFechaDespues() {
    	fechaIngresoDespues.setValue(null);
    }
    //TODO CU08 arreglar datepicker y establecer comportamientos
}
