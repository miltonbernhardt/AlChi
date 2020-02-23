package app;

import gestor.GestorProductos;

import java.util.Optional;

import dto.DTOCU10FormaVenta;
import dto.DTOCU10Empaquetado;
import dto.DTOCU10ProductoInicial;
import dto.DTOCU10TipoProducto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * Controller para la view de "Registrar empaquetamientos"
 */
public class CU10Controller01 {
	
	private static CU10Controller01 instance = null;

    public static CU10Controller01 get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU10Controller01) App.setRoot("CU10View01", "AlChi: Registrar empaquetamientos");
        }
        return instance;
    }
    
    private Float tamanoPaquete = null;
    private Float cantidadRestante = null;
    private Integer cantidadMaxima = null;
    
    private DTOCU10Empaquetado productoEmpaquetado = null;
	
    @FXML private Button btnAnadirEmpaquetamiento;
    @FXML private Button btnDarBajaProductoInicial;
    @FXML private Button btnAnadirConOtroPaquete;
    @FXML private Button btnEditarFila;
    @FXML private Button btnEliminarFila;
	@FXML private ComboBox<DTOCU10TipoProducto> tipoProducto;	
	@FXML private ComboBox<DTOCU10ProductoInicial> productoInicial;
	@FXML private ComboBox<DTOCU10FormaVenta> formaVenta;
	@FXML private TextField cantidad;
	@FXML private Label productoRestante;
	@FXML private Label cantidadMaximaPaquete;
	@FXML private TableView<DTOCU10Empaquetado> tabla;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaProducto;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaProveedor;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaCodigoBarra;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaVencimiento;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaFormaVenta;	
	@FXML private TableColumn<DTOCU10Empaquetado, String> columnaCantidad;
    
    public CU10Controller01() { }
	
    @FXML private void initialize(){
    	setCombo();
    	addListenerCampos();
    	iniciarTabla();
    }
    
    private void setCombo() {
    	tipoProducto.getItems().clear();
    	tipoProducto.getItems().add(new DTOCU10TipoProducto(null, "Seleccionar producto"));
    	tipoProducto.getItems().addAll(GestorProductos.get().getTiposProducto());
    	tipoProducto.getSelectionModel().selectFirst();
    }
    
    private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay productos empaquetados que mostrar."));
    	columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombreTipoProducto"));
    	columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombreProveedor"));
    	columnaCodigoBarra.setCellValueFactory(new PropertyValueFactory<>("codigoBarra"));
    	columnaVencimiento.setCellValueFactory(new PropertyValueFactory<>("vencimiento"));
    	columnaFormaVenta.setCellValueFactory(new PropertyValueFactory<>("tipoPaquete"));
    	columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadPaquetes"));
    }
    
    private void addListenerCampos() {
    	cantidad.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	String s = cantidad.getText();
            	
            	if( !(s.isBlank())) {
            		Integer i = 0;
            		try {
            			i = Integer.parseInt(s);
            		}catch(Exception e) { }
            		
            		if( !(i>=1 && i<=cantidadMaxima) ) {
            			cantidad.setText("");
                		App.setError(cantidad);
            		}
            		else {
            			App.setValido(cantidad);       
            		}
            	}            		
            }
        });
    	
    	
    	cantidad.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    }
    
	@FXML private void btnAnadirEmpaquetamiento() {
		if(!cantidad.getText().isBlank()) {
			DTOCU10Empaquetado dto = new DTOCU10Empaquetado(); 
			
			DTOCU10TipoProducto dtoTipoProd = tipoProducto.getValue();	
			dto.setDtoTipoProducto(dtoTipoProd);
			dto.setIdProductoInicial(dtoTipoProd.getId());
			dto.setNombreTipoProducto(dtoTipoProd.getNombre());
			
			DTOCU10ProductoInicial dtoProdIni = productoInicial.getValue();	
			dto.setDtoProductoInicial(dtoProdIni);
			dto.setIdProductoInicial(dtoProdIni.getIdProductoInicial());
			dto.setCodigoBarra(dtoProdIni.getCodigoBarra());		
			dto.setNombreProveedor(dtoProdIni.getProveedor());
			dto.setVencimiento(dtoProdIni.getVencimiento());	
			
			DTOCU10FormaVenta dtoForma = formaVenta.getValue();
			dto.setDtoFormaVenta(dtoForma);
			dto.setTipoPaquete(dtoForma.getTipoPaquete());		
			dto.setCantidadPaquetes(Integer.parseInt(cantidad.getText()));		
			
			cantidadRestante = cantidadRestante - dto.getCantidadPaquetes()*dto.getTipoPaqueteE().getCantidad();
			productoInicial.getValue().setCantidadNoVendida(cantidadRestante/1000);	
			
			if(cantidadRestante>0) {			
				productoRestante.setText("Cantidad no vendida: "+productoInicial.getValue().getCantidadNoVendida().toString()+" Kg / "+cantidadRestante.toString()+" g ");
				calcularCantidad();			
			}
			else {
				productoInicial.getItems().remove(productoInicial.getValue());
				
				if(productoInicial.getItems().size()>0) {
					productoInicial.getSelectionModel().selectFirst();
				}
				else {
					tipoProducto.getSelectionModel().selectNext();
				}			
			}		
			
			tabla.getItems().add(dto);
		}
		else {
			PanelAlerta.getError("Aviso", null, "Se debe elegir una cantidad de paquetes a empaquetar.");
		}
	}
		
	@FXML  private void btnEliminar() {
		DTOCU10TipoProducto dtoTipoProd = tipoProducto.getValue();		
		DTOCU10ProductoInicial dtoProdIni = productoInicial.getValue();	
		DTOCU10FormaVenta dtoForma = formaVenta.getValue();
		
		Float f = productoEmpaquetado.getDtoProductoInicial().getCantidadNoVendida();
		productoEmpaquetado.getDtoProductoInicial().setCantidadNoVendida(f + (productoEmpaquetado.getCantidadPaquetes()*productoEmpaquetado.getTipoPaqueteE().getCantidad())/1000);
		if(f<=0) {
			tipoProducto.getSelectionModel().clearSelection();
			tipoProducto.getSelectionModel().select(dtoTipoProd);
			productoInicial.getSelectionModel().select(dtoProdIni);
			formaVenta.getSelectionModel().select(dtoForma);
		}		

		tabla.getItems().remove(productoEmpaquetado);
		btnEliminarFila.setDisable(true);
		btnEditarFila.setDisable(true);	
	}
	
	@FXML private void btnEditar() {
		Float f = productoEmpaquetado.getDtoProductoInicial().getCantidadNoVendida();		
		productoEmpaquetado.getDtoProductoInicial().setCantidadNoVendida(f + (productoEmpaquetado.getCantidadPaquetes()*productoEmpaquetado.getTipoPaqueteE().getCantidad())/1000);
		
		tipoProducto.getSelectionModel().clearSelection();
		tipoProducto.getSelectionModel().select(productoEmpaquetado.getDtoTipoProducto());
		
		productoInicial.getSelectionModel().select(productoEmpaquetado.getDtoProductoInicial());
		
		formaVenta.getSelectionModel().select(productoEmpaquetado.getDtoFormaVenta());
		
		calcularCantidad();
		cantidad.setText(productoEmpaquetado.getCantidadPaquetes().toString());
		
		tabla.getItems().remove(productoEmpaquetado);
		btnEliminarFila.setDisable(true);
		btnEditarFila.setDisable(true);
	}
	
	@FXML private void btnConfirmarEmpaquetamiento() {
		if(tabla.getItems().size()>0) {			
			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar registro del empaquetamiento de productos", null, "¿Desea confirmar el ingeso de los productos y la actualización de los precios?");
			
			if (result.get() == ButtonType.OK){
				if(GestorProductos.get().registrarEmpaquetamiemto(tabla.getItems())) {
					PanelAlerta.getInformation("Confirmación", null, "La transacción ocurrió de manera efectiva.");
	                volver();
	                CU10Controller01.get().volver();
				}
			}    
		}
		else{
			PanelAlerta.getError("Aviso", null, "Se debe confirmar el empaquetamiento de productos según el tipo de venta.");
		}
	}
	
	@FXML private void btnDarBajaPaqueteInicial() {
		//TODO CU10 implementar
		//CU09Controller.get();
	}
	
	@FXML private void btnAnadirConOtroPaquete() {
		//CU10Controller02.get();
		//TODO CU10 implementar, diria de abrir otra pantalla
	}

    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
    @FXML private void seleccionarProductoEmpaquetado() {
    	productoEmpaquetado = tabla.getSelectionModel().getSelectedItem();
    	if(productoEmpaquetado != null) {
    		btnEliminarFila.setDisable(false);
    		btnEditarFila.setDisable(false); 
    	}
    	else {
    		btnEliminarFila.setDisable(true);
    		btnEditarFila.setDisable(true); 
    	}
    	
    }
    
    @FXML private void seleccionarTipoProducto(){
    	DTOCU10TipoProducto dto = tipoProducto.getValue();
		if(dto != null && (dto.getProductosIniciales().size()>0)) {
			productoInicial.setDisable(false);
			productoInicial.getItems().clear();
			productoInicial.getItems().addAll(dto.getProductosIniciales());
			productoInicial.getSelectionModel().selectFirst();	
			
			formaVenta.setDisable(false);
			formaVenta.getItems().clear();
			formaVenta.getItems().addAll(dto.getFormasVenta());
			formaVenta.getSelectionModel().selectFirst();	
			
			btnAnadirEmpaquetamiento.setDisable(false);
		}
		else {
			productoInicial.setDisable(true);
			productoInicial.getItems().clear();
			
			formaVenta.setDisable(true);
			formaVenta.getItems().clear();
			
			cantidad.setDisable(true);
			cantidad.setText("");
			cantidadMaximaPaquete.setText("Cantidad de paquetes:");
			
			btnAnadirEmpaquetamiento.setDisable(true);
		}    	
    }
    
    @FXML private void seleccionarProductoInicial(){
    	DTOCU10ProductoInicial dto = productoInicial.getValue();
		if(dto != null) {
			cantidadRestante = dto.getCantidadNoVendida()*1000;
			productoRestante.setText("Cantidad no vendida: "+dto.getCantidadNoVendida().toString()+" Kg / "+cantidadRestante.toString()+" g ");
			btnDarBajaProductoInicial.setDisable(false);
			calcularCantidad();
		}
		else {
			App.setValido(cantidad);
			cantidad.setText("");
			cantidad.setDisable(true);
			cantidadMaximaPaquete.setText("Cantidad de paquetes:");
			productoRestante.setText("Cantidad no vendida:");
			btnDarBajaProductoInicial.setDisable(true);
		}        	
    }
    
    @FXML private void seleccionarFormaVenta(){
    	DTOCU10FormaVenta dto = formaVenta.getValue();
		if(dto != null) {
			tamanoPaquete = dto.getTipoPaquete().getCantidad();
			calcularCantidad();
		}
		else {
			cantidad.setDisable(true);
			cantidad.setText("");
			cantidadMaximaPaquete.setText("");
		}        	
    }
    
    private void calcularCantidad() {    	
    	if( (tamanoPaquete!=null) && (cantidadRestante!=null)) {
    		cantidad.setText("");
    		
    		if(cantidadRestante < tamanoPaquete) {
    			btnDarBajaProductoInicial.setDisable(false);
    			btnAnadirConOtroPaquete.setDisable(false);
    			cantidadMaximaPaquete.setText("Cantidad de paquetes:");
    			cantidad.setDisable(true);
    		}
    		else {
            	cantidadMaxima = (int) (cantidadRestante / tamanoPaquete);
            	if( !(cantidadMaxima<1) ) {
            		btnDarBajaProductoInicial.setDisable(true);
        			btnAnadirConOtroPaquete.setDisable(true);
                	cantidad.setDisable(false);
            		cantidadMaximaPaquete.setText("Cantidad de paquetes: 1 - "+cantidadMaxima);
            	} 
    		}        	        	
    	}
    }
    
    private void validarCampos(KeyEvent e) {
    	Character caracter = e.getCharacter().charAt(0);
		if(!Character.isDigit(caracter) ){	
			e.consume();
		}
    }
}
