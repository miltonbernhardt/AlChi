package app;

import java.time.LocalDate;
import dto.DTOProductoInicial;
import dto.DTOProveedor;
import dto.DTOTipoProductoCU02;
import gestor.GestorProductos;
import gestor.GestorProveedor;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/*
 * Controller para la view "Registro de entrada de productos"
 */
public class CU03Controller01 {
	
	private static CU03Controller01 instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
	public static CU03Controller01 get() {
        if (instance == null){
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU03Controller01) App.setRoot("CU03View01", "AlChi: Registrar entrada de productos");
        }    
        return instance;
    }
	
	@FXML private ComboBox<DTOTipoProductoCU02> productos;	
	@FXML private ComboBox<DTOProveedor> proveedores;
	
	@FXML private DatePicker vencimiento;
	
	@FXML private TextField precioCompra;	
	@FXML private TextField cantidad;
	@FXML private TextField codigoBarra;
	
	@FXML private Button btnEliminarFila;	
	@FXML private Button btnDuplicarFila;	
	@FXML private Button btnEditarFila;
	
	@FXML private TableView<DTOProductoInicial> tabla;
	
	@FXML private TableColumn<DTOProductoInicial, String> columnaProducto;	
	@FXML private TableColumn<DTOProductoInicial, String> columnaProveedor;
	@FXML private TableColumn<DTOProductoInicial, String> columnaCodigoBarra;	
	@FXML private TableColumn<DTOProductoInicial, LocalDate> columnaVencimiento;
	@FXML private TableColumn<DTOProductoInicial, String> columnaCantidad;	
	@FXML private TableColumn<DTOProductoInicial, Float> columnaPCompra;

	
	private DTOProductoInicial productoSeleccionado = null;
    
	public CU03Controller01() {	}
	
    @FXML private void initialize(){
    	setCombos();
    	iniciarTabla();
    	iniciarCalendario();  
    	inicializarListenerCampos();
    	
    	//PRUEBA
    	productos.getSelectionModel().select(1);
    	proveedores.getSelectionModel().select(1);
    	
		vencimiento.setValue(LocalDate.now());;
		precioCompra.setText("2000");	
		cantidad.setText("2");	
		codigoBarra.setText("9827378962834");
    	
    }
    
    private void setCombos() {
    	productos.getItems().clear();
    	productos.getItems().add(new DTOTipoProductoCU02("Seleccionar producto"));
    	productos.getItems().addAll(GestorProductos.get().buscarTiposProductos(null, null, true));
    	productos.getSelectionModel().selectFirst();
    	
    	proveedores.getItems().clear();
    	proveedores.getItems().add(new DTOProveedor(null, "Seleccionar proveedor"));
    	proveedores.getItems().addAll(GestorProveedor.get().getProveedores());
    	proveedores.getSelectionModel().selectFirst();
    }
    
    private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay productos que mostrar."));
    	columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombreTipoProducto"));
    	columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
    	columnaCodigoBarra.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
    	columnaVencimiento.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
    	columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadNoVendida"));
    	columnaPCompra.setCellValueFactory(new PropertyValueFactory<>("precioComprado"));
    }
    
    private void inicializarListenerCampos() {
    	precioCompra.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	precioCompra.setText(precioCompra.getText().replace(',', '.'));
            }
        }); 
    	
    	cantidad.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	cantidad.setText(cantidad.getText().replace(',', '.'));
            }
        });  
    	
    	precioCompra.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	cantidad.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    }
    
    private void iniciarCalendario() {   	
    	LocalDate minDate = LocalDate.now();    	
    	vencimiento.setDayCellFactory(d -> new DateCell() {
    		@Override
    		public void updateItem(LocalDate item, boolean empty) {
    			super.updateItem(item, empty);
    	        setDisable(item.isBefore(minDate));
    	}});
    }
    
    @SuppressWarnings("unused")
	private void setearCamposNull() {
    	productos.getSelectionModel().selectFirst();
    	proveedores.getSelectionModel().selectFirst();
		vencimiento.setValue(null);
		precioCompra.setText("");	
		cantidad.setText("");	
		codigoBarra.setText("");	
    }
    
    @FXML private void btnAgregarTipoProducto() {
    	CU01Controller.get().setControllerCu03(this);;
    }
    
    @FXML private void btnAgregarProveedor() {
    	CU07Controller02.get();
    }
    
    @FXML private void btnAnadirProducto() {
    	Boolean seleccionProveedor = true, seleccionProducto = true, completoPrecio = true, completoCantidad = true, completoCodigoBarra= true, seleccionoVencimiento = true;
    	String cadenaError = "Debe determinar los siguientes campos para poder registrar la entrada de productos:\n";
    	Integer nroCampo = 1;
    	
    	if(proveedores.getValue().getId() == null) {
    		cadenaError += nroCampo.toString()+") Proveedor.\n";
    		nroCampo++;
    		seleccionProveedor = false;
    	}

    	if(productos.getValue().getIdProducto() == null) {
    		cadenaError += nroCampo.toString()+") Producto.\n";
    		nroCampo++;
    		seleccionProducto = false;
    	}

    	if(precioCompra.getText().isBlank()) {
    		cadenaError += nroCampo.toString()+") Precio de compra.\n";
    		nroCampo++;
    		completoPrecio = false;
    	}
    	
    	if(cantidad.getText().isBlank()) {
    		cadenaError += nroCampo.toString()+") Cantidad de producto.\n";
    		nroCampo++;
    		completoCantidad = false;
    	}
    	
    	if(codigoBarra.getText().isBlank()) {
    		cadenaError += nroCampo.toString()+") Código de barra.\n";
    		nroCampo++;
    		completoCodigoBarra = false;
    	} 
    	
    	if(vencimiento.getValue() == null) {
    		cadenaError += nroCampo.toString()+") Vencimiento.\n";
    		seleccionoVencimiento = false;
    	}  
    	
    	if(seleccionProveedor && seleccionProducto && completoPrecio && completoCantidad && completoCodigoBarra && seleccionoVencimiento) {
    		DTOProductoInicial dto = new DTOProductoInicial();   
    		dto.setProveedor(proveedores.getValue());
    		dto.setTipoProducto(productos.getValue());
    		dto.setNombreCategoria(productos.getValue().getNombreCategoria());
    		
    		dto.setPrecioComprado(Float.valueOf(precioCompra.getText()));
    		dto.setCantidadNoVendida(Float.valueOf(cantidad.getText()));    
    		
    		dto.setCodigoBarra(codigoBarra.getText());
    		dto.setVencimiento(vencimiento.getValue());
    		    		
    		tabla.getItems().add(dto);
    		//setearCamposNull(); //TODO CU03.1 descomentar
    	}
    	else {
        	// TODO ZZZ cambiar color al equivocarse              	
    		PanelAlerta.getError("Aviso", null, cadenaError);
    	}
    }
    
    @FXML private void btnEliminarFila() {
    	if(productoSeleccionado != null) {
    		tabla.getItems().remove(productoSeleccionado);
    		productoSeleccionado = null;    		
    		btnEliminarFila.setDisable(true);
    		btnDuplicarFila.setDisable(true);
    		btnEditarFila.setDisable(true);
    		tabla.getSelectionModel().clearSelection();
		}
    	else {
    		PanelAlerta.getError("Aviso", null, "Se debe elegir un producto de la tabla para poder eliminarlo.");
    	}    	
    }
    
    @FXML private void btnEditarFila() {
    	if(productoSeleccionado != null) {    		
        	productos.getSelectionModel().select(productoSeleccionado.getTipoProducto());
        	proveedores.getSelectionModel().select(productoSeleccionado.getProveedor());
        	
    		vencimiento.setValue(productoSeleccionado.getVencimiento());
    		precioCompra.setText(productoSeleccionado.getPrecioCompradoF().toString());	
    		cantidad.setText(productoSeleccionado.getCantidadNoVendidaF().toString());	
    		codigoBarra.setText(productoSeleccionado.getCodigoBarra());
    		
    		tabla.getItems().remove(productoSeleccionado);
    		productoSeleccionado = null;    		
    		btnEliminarFila.setDisable(true);
    		btnDuplicarFila.setDisable(true);
    		btnEditarFila.setDisable(true);
    		tabla.getSelectionModel().clearSelection();
		}
    	else {
    		PanelAlerta.getError("Aviso", null, "Se debe elegir un producto de la tabla para poder editarlo.");
    	}
    }
    
    @FXML private void btnDuplicarFila() {
    	if(productoSeleccionado != null) {
    		tabla.getItems().add(new DTOProductoInicial(productoSeleccionado));
		}
    	else {
    		PanelAlerta.getError("Aviso", null, "Se debe elegir un producto de la tabla para poder duplicarlo.");
    	}    	
    }  
    
    @FXML private void btnConfirmarProductos() {
    	if(tabla.getItems().size() > 0)
    		CU03Controller02.get().setListaProductos(tabla.getItems());
    	else
    		PanelAlerta.getError("Aviso", null, "Se debe registrar al menos la entrada de 1 producto.");
        
    }    

    @FXML public void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	sceneAnterior = null;
    	tituloAnterior = null;
    	instance = null;
	}

    public void setearTipoProducto(@SuppressWarnings("exports") DTOTipoProductoCU02 dto) { 
		productos.getItems().add(dto);
    	productos.getSelectionModel().clearSelection();
    	productos.getSelectionModel().select(dto);
    	
    }
    
	public void setearProveedor(@SuppressWarnings("exports") DTOProveedor dto) {
    	if(dto != null) {
    		proveedores.getItems().add(dto);
    		proveedores.getSelectionModel().clearSelection();
        	proveedores.getSelectionModel().select(dto);
    	}
    }
    
    private void validarCampos(KeyEvent e) {
		TextField campo = (TextField) e.getSource();
    	Character caracter = e.getCharacter().charAt(0);
    	String temporal = "";
		if(Character.isDigit(caracter) ||  caracter == '.' || caracter == ','){
			temporal = campo.getText();			
			if(( temporal.contains(".") || temporal.contains(",")) && ( caracter == '.' || caracter == ',' )) {
				e.consume();
			}	
		}
		else{
			e.consume();			
		}
    }
    
    @FXML private void seleccionarProducto() {
    	productoSeleccionado = tabla.getSelectionModel().getSelectedItem();
    	if(productoSeleccionado != null) {
    		btnEliminarFila.setDisable(false);
    		btnDuplicarFila.setDisable(false);
    		btnEditarFila.setDisable(false); 
    	}
    	else {
    		btnEliminarFila.setDisable(true);
    		btnDuplicarFila.setDisable(true);
    		btnEditarFila.setDisable(true); 
    	}
    	
    }
}
