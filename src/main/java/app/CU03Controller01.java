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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
	//TODO CU03 CAMBIAR DISPOSICION DEL PANEL
	
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
	@FXML private TextField precio100;	
	@FXML private TextField precio250;	
	@FXML private TextField precio500;	
	@FXML private TextField precio1000;	
	@FXML private TextField precio2000;	
	@FXML private TextField precioUnidad;
	@FXML private TextField codigoBarra;	
	
	@FXML private CheckBox checkPrecio100;	
	@FXML private CheckBox checkPrecio250;	
	@FXML private CheckBox checkPrecio500;	
	@FXML private CheckBox checkPrecio1000;	
	@FXML private CheckBox checkPrecio2000;	
	@FXML private CheckBox checkPrecioUnidad;	
	
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
	@FXML private TableColumn<DTOProductoInicial, Float> columnaP100;	
	@FXML private TableColumn<DTOProductoInicial, Float> columnaP250;
	@FXML private TableColumn<DTOProductoInicial, Float> columnaP500;	
	@FXML private TableColumn<DTOProductoInicial, Float> columnaP1000;
	@FXML private TableColumn<DTOProductoInicial, Float> columnaP2000;	
	@FXML private TableColumn<DTOProductoInicial, Float> columnaPUnidad;
    
	public CU03Controller01() {	}
	
    @FXML private void initialize(){
    	setCombos();
    	iniciarTabla();
    	iniciarCalendario();  
    	inicializarListenerCampos();
    }
    
    private void setCombos() {
    	productos.getItems().clear();
    	productos.getItems().add(new DTOTipoProductoCU02("Seleccionar producto"));
    	productos.getItems().addAll(GestorProductos.get().getTiposProductosCu02());
    	productos.getSelectionModel().selectFirst();
    	
    	proveedores.getItems().clear();
    	proveedores.getItems().add(new DTOProveedor("Seleccionar proveedor"));
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
    	columnaP100.setCellValueFactory(new PropertyValueFactory<>("precio100"));
    	columnaP250.setCellValueFactory(new PropertyValueFactory<>("precio250"));
    	columnaP500.setCellValueFactory(new PropertyValueFactory<>("precio500"));
    	columnaP1000.setCellValueFactory(new PropertyValueFactory<>("precio1000"));
    	columnaP2000.setCellValueFactory(new PropertyValueFactory<>("precio2000"));
    	columnaPUnidad.setCellValueFactory(new PropertyValueFactory<>("precioUnidad"));
    }
    
    private void inicializarListenerCampos() {
    	precioCompra.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { //when focus lost
                if(!precioCompra.getText().matches("\\d*?[[.]\\d*]")){
                	
                    //when it not matches the pattern (1.0 - 6.0)
                    //set the textField empty
                	precioCompra.setText("");
                }
            }

        }); 	
    	
    	
    	precioCompra.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	cantidad.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	precio100.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	precio250.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	precio500.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	precio1000.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	precio2000.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	precioUnidad.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    }
    
    private void iniciarCalendario() {
    	//TODO CU03-1 inicializar calendario en fecha actual
    }
    
    @FXML private void btnAgregarTipoProducto() {
    	CU01Controller.get().setControllerCu03(this);;
    }
    
    @FXML private void btnAgregarProveedor() {
    	CU07Controller02.get();
    }
    
    @FXML private void btnAnadirProducto() {    	
    	Boolean seleccionProveedor = true, seleccionProducto = true, completoPrecio = true, completoCantidad = true, completoCodigoBarra= true, seleccionoVencimiento = true;
    	String cadenaError = "Debe determinar los siguientes campos para registrar la entrada de productos:\n";
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
    	
    	/*if(vencimiento.getConverter().LocalDate.now()) {
    		cadenaError += nroCampo.toString()+") Vencimiento.\n";
    		seleccionoVencimiento = false;
    	} TODO CU03-1 hacer condición de fecha para el vencimiento 
    	*/ 
    	
    	
    	if(seleccionProveedor && seleccionProducto && completoPrecio && completoCantidad && completoCodigoBarra && seleccionoVencimiento) {
    		DTOProductoInicial dto = new DTOProductoInicial();
    		
    		dto.setIdTipoProducto(proveedores.getValue().getId());
    		dto.setNombreTipoProducto(proveedores.getValue().getNombre());
    		
    		dto.setIdTipoProducto(productos.getValue().getIdProducto());
    		dto.setNombreTipoProducto(productos.getValue().getNombreTipoProducto());
    		
    		dto.setPrecioComprado(Float.valueOf(precioCompra.getText()));
    		dto.setCantidadNoVendida(Float.valueOf(cantidad.getText()));
    		
    		dto.setCodigoBarra(codigoBarra.getText());
    		
    		//TODO CU03-1 cambiar
    		dto.setVencimiento(LocalDate.now());
    		
    		tabla.getItems().add(dto);	
    		  		
    	}
    	else {
        	// TODO ZZZ cambiar color al equivocarse              	
    		PanelAlerta.getError("Aviso", null, cadenaError);
    	}
    }
    
    @FXML private void btnEliminarFila() {

    }
    
    @FXML private void btnEditarFila() {

    }
    
    @FXML private void btnDuplicarFila() {
    	
    }  
    
    @FXML private void btnConfirmarProductos() {
        CU03Controller02.get();
    }    

    @FXML private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	sceneAnterior = null;
    	tituloAnterior = null;
    	instance = null;
	}
    
    
    //---------------------------------------------------
	public void setearTipoProducto(Integer id, String nombre) { 
		DTOTipoProductoCU02 dto = new DTOTipoProductoCU02(id, nombre);
		productos.getItems().add(dto);
    	productos.getSelectionModel().clearSelection();
    	productos.getSelectionModel().select(dto);
    	
    }
    
    @SuppressWarnings("exports")
	public void setearProveedor(DTOProveedor dto) {   	
    	if(dto != null) {
    		proveedores.getItems().add(dto);
    		proveedores.getSelectionModel().clearSelection();
        	proveedores.getSelectionModel().select(dto);
    	}
    }
    
    @FXML
    private void actualizarCampos() {
    	if(checkPrecio100.isSelected())
    		precio100.setDisable(false);
    	else
    		precio100.setDisable(true);    	

    	if(checkPrecio250.isSelected())
    		precio250.setDisable(false);
    	else
    		precio250.setDisable(true);    	

    	if(checkPrecio500.isSelected())
    		precio500.setDisable(false);
    	else
    		precio500.setDisable(true);
    	
    	if(checkPrecio1000.isSelected())
    		precio1000.setDisable(false);
    	else
    		precio1000.setDisable(true);
    	
    	if(checkPrecio2000.isSelected())
    		precio2000.setDisable(false);
    	else
    		precio2000.setDisable(true);
    	
    	if(checkPrecioUnidad.isSelected())
    		precioUnidad.setDisable(false);
    	else
    		precioUnidad.setDisable(true);
    }
    
    @FXML private void validarCampos(KeyEvent e) {
		TextField campo = (TextField) e.getSource();
    	Character caracter = e.getCharacter().charAt(0);
    	String temporal = "";
    	
    	//System.out.print(caracter);
		if(Character.isDigit(caracter) ||  caracter == '.' || caracter == ','){
			temporal = campo.getText();			
			if(( temporal.contains(".") || temporal.contains(",")) && ( caracter == '.' || caracter == ',' )) {
				e.consume();
				//TODO CU03-1 reemplazar coma con punto
			}	
		}
		else{
			e.consume();			
		}
    }
    
    //TODO CU03-1 listener para campos precio
}
