package app;

import gestor.GestorProductos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dto.DTOFormaVentaCU10;
import dto.DTOCU06;
import dto.DTOEmpaquetadoCU10;
import dto.DTOProductoInicialCU10;
import dto.DTOTipoProductoCU10;
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
    
    private CU04Controller controllerCu04 = null;
    private CU08Controller controllerCu08 = null;
    private DTOEmpaquetadoCU10 productoEmpaquetado = null;
	
    @FXML private Button btnAnadirEmpaquetamiento;
    @FXML private Button btnDarBajaProductoInicial;
    @FXML private Button btnAnadirConOtroPaquete;
    @FXML private Button btnEditarFila;
    @FXML private Button btnEliminarFila;
	@FXML private ComboBox<DTOTipoProductoCU10> tipoProducto;	
	@FXML private ComboBox<DTOProductoInicialCU10> productoInicial;
	@FXML private ComboBox<DTOFormaVentaCU10> formaVenta;
	@FXML private TextField cantidad;
	@FXML private Label productoRestante;
	@FXML private Label cantidadMaximaPaquete;
	@FXML private TableView<DTOEmpaquetadoCU10> tabla;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaProducto;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaProveedor;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaCodigoBarra;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaVencimiento;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaFormaVenta;	
	@FXML private TableColumn<DTOEmpaquetadoCU10, String> columnaCantidad;
    
    public CU10Controller01() { }
	
    @FXML private void initialize(){
    	setCombo();
    	addListenerCampos();
    	iniciarTabla();
    }
    
    private void setCombo() {
    	tipoProducto.getItems().clear();
    	tipoProducto.getItems().add(new DTOTipoProductoCU10(null, "Seleccionar producto"));
    	try {
    		tipoProducto.getItems().addAll(GestorProductos.get().getTiposProducto());
    		
    	}catch(Exception e) {e.printStackTrace();}
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
            	validarCampos();           		
            }
        });
    	
    	
    	cantidad.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    }
    
	@FXML private void btnAnadirEmpaquetamiento() {
		validarCampos();
		if(!cantidad.getText().isBlank()) {
			DTOEmpaquetadoCU10 dto = new DTOEmpaquetadoCU10(); 
			
			
			DTOTipoProductoCU10 dtoTipoProd = tipoProducto.getValue();	
			dto.setDtoTipoProducto(dtoTipoProd);
			dto.setIdProductoInicial(dtoTipoProd.getId());
			dto.setNombreTipoProducto(dtoTipoProd.getNombre());
			
			DTOProductoInicialCU10 dtoProdIni = productoInicial.getValue();	
			dto.setDtoProductoInicial(dtoProdIni);
			dto.setIdProductoInicial(dtoProdIni.getIdProductoInicial());
			dto.setCodigoBarra(dtoProdIni.getCodigoBarra());		
			dto.setNombreProveedor(dtoProdIni.getProveedor());
			dto.setVencimiento(dtoProdIni.getVencimiento());	
			
			DTOFormaVentaCU10 dtoForma = formaVenta.getValue();
			dto.setDtoFormaVenta(dtoForma);
			dto.setTipoPaquete(dtoForma.getTipoPaquete());		
			dto.setCantidadPaquetes(Integer.parseInt(cantidad.getText()));		
			
			cantidadRestante = cantidadRestante - dto.getCantidadPaquetesF()*dto.getTipoPaqueteE().getCantidad();
			productoInicial.getValue().setCantidadNoVendida(cantidadRestante/1000);	
			
			if(cantidadRestante>0) {			
				productoRestante.setText("Cantidad no vendida: "+productoInicial.getValue().getCantidadNoVendida().toString()+" Kg / "+cantidadRestante.toString()+" g ");
				calcularCantidad();			
			}
			else {
				productoInicial.getItems().remove(productoInicial.getValue());
				cambiarProductoSinStock();	
			}		
			
			tabla.getItems().add(dto);
		}
		else {
			PanelAlerta.getError("Aviso", null, "Se debe elegir una cantidad de paquetes válida a empaquetar.");
		}
	}
	
	private void cambiarProductoSinStock() {
		if(productoInicial.getItems().size()>0) {
			productoInicial.getSelectionModel().selectFirst();
		}
		else {
			Boolean todos = true;			
			tipoProducto.getValue().setConStock(false);			
			Iterator<DTOTipoProductoCU10> iterator = tipoProducto.getItems().iterator();			
			
			while(iterator.hasNext()) {
				DTOTipoProductoCU10 dto = iterator.next();
				if(dto.getConStock() && dto.getId()!=null) {
					tipoProducto.getSelectionModel().select(dto);
					todos = false;
					break;
				}						
			}
			
			if(todos) {
				tipoProducto.getSelectionModel().selectFirst();
				tipoProducto.setDisable(true);
			}
		}		
	}		
	
	@FXML  private void btnEliminar() {
		if(productoEmpaquetado != null) {
			DTOTipoProductoCU10 dtoTipoProd = productoEmpaquetado.getDtoTipoProducto();		
			DTOProductoInicialCU10 dtoProdIni = productoEmpaquetado.getDtoProductoInicial();	
			DTOFormaVentaCU10 dtoForma = productoEmpaquetado.getDtoFormaVenta();
			
			Float f = productoEmpaquetado.getDtoProductoInicial().getCantidadNoVendida();
			
			dtoProdIni.setCantidadNoVendida(f + (productoEmpaquetado.getCantidadPaquetesF()*productoEmpaquetado.getTipoPaqueteE().getCantidad())/1000);
			
			if(f<=0) {
				dtoTipoProd.setConStock(true);
				if(tipoProducto.isDisable())
					tipoProducto.setDisable(false);
				tipoProducto.getSelectionModel().clearSelection();
				tipoProducto.getSelectionModel().select(dtoTipoProd);
				productoInicial.getSelectionModel().select(dtoProdIni);
				formaVenta.getSelectionModel().select(dtoForma);
			}		
			cantidadRestante = productoInicial.getValue().getCantidadNoVendida()*1000;
			productoRestante.setText("Cantidad no vendida: "+productoInicial.getValue().getCantidadNoVendida().toString()+" Kg / "+cantidadRestante.toString()+" g ");
			calcularCantidad();
			tabla.getItems().remove(productoEmpaquetado);
			btnEliminarFila.setDisable(true);
			btnEditarFila.setDisable(true);	
		}
	}
	
	@FXML private void btnEditar() {
		if(productoEmpaquetado != null) {
			DTOTipoProductoCU10 dtoTipoProd = productoEmpaquetado.getDtoTipoProducto();		
			DTOProductoInicialCU10 dtoProdIni = productoEmpaquetado.getDtoProductoInicial();	
			DTOFormaVentaCU10 dtoForma = productoEmpaquetado.getDtoFormaVenta();
			
			Float f = dtoProdIni.getCantidadNoVendida();		
			dtoProdIni.setCantidadNoVendida(f + (productoEmpaquetado.getCantidadPaquetesF()*productoEmpaquetado.getTipoPaqueteE().getCantidad())/1000);
			dtoTipoProd.setConStock(true);
			
			tipoProducto.getSelectionModel().clearSelection();
			tipoProducto.getSelectionModel().select(dtoTipoProd);
			productoInicial.getSelectionModel().select(dtoProdIni);
			formaVenta.getSelectionModel().select(dtoForma);
			
			calcularCantidad();
			cantidad.setText(productoEmpaquetado.getCantidadPaquetes().toString());
			
			tabla.getItems().remove(productoEmpaquetado);
			if(tipoProducto.isDisable())
				tipoProducto.setDisable(false);
			btnEliminarFila.setDisable(true);
			btnEditarFila.setDisable(true);			
		}
		
	}
	
	@FXML private void btnConfirmarEmpaquetamiento() {
		if(tabla.getItems().size()>0) {			
			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar registro del empaquetamiento de productos", null, "¿Desea confirmar el ingeso de los productos y la actualización de los precios?");
			if (result.get() == ButtonType.OK){
				List<DTOCU06> itemsParaCu4 = null;
				if(controllerCu04 != null) {
					itemsParaCu4 = new ArrayList<DTOCU06>();
				}
				if(GestorProductos.get().registrarEmpaquetamiemto(tabla.getItems(), itemsParaCu4)) {
					PanelAlerta.getInformation("Confirmación", null, "La transacción ocurrió de manera efectiva.");
	                volver();
	                if(controllerCu04 != null) {
	                	controllerCu04.addToTabla(itemsParaCu4);
					}
	            	if(controllerCu08 != null) {
	            		controllerCu08.btnBuscar();
	            	}
				}
			}    
		}
		else{
			PanelAlerta.getError("Aviso", null, "Se debe confirmar el empaquetamiento de productos según el tipo de venta.");
		}
	}
	
	@FXML private void btnDarBajaPaqueteInicial() {
		if(!cantidad.getText().isBlank()) {
			DTOEmpaquetadoCU10 dto = new DTOEmpaquetadoCU10(); 
			
			
			DTOTipoProductoCU10 dtoTipoProd = tipoProducto.getValue();	
			dto.setDtoTipoProducto(dtoTipoProd);
			dto.setIdProductoInicial(dtoTipoProd.getId());
			dto.setNombreTipoProducto(dtoTipoProd.getNombre());
			
			DTOProductoInicialCU10 dtoProdIni = productoInicial.getValue();	
			dto.setDtoProductoInicial(dtoProdIni);
			dto.setIdProductoInicial(dtoProdIni.getIdProductoInicial());
			dto.setCodigoBarra(dtoProdIni.getCodigoBarra());		
			dto.setNombreProveedor(dtoProdIni.getProveedor());
			dto.setVencimiento(dtoProdIni.getVencimiento());	
			
			dto.setDtoFormaVenta(null);
			dto.setTipoPaquete(null);		
			dto.setCantidadPaquetes(-1);		

			dtoProdIni.setDisponible(false);
			
			if(cantidadRestante>0) {			
				productoRestante.setText("Cantidad no vendida: "+productoInicial.getValue().getCantidadNoVendida().toString()+" Kg / "+cantidadRestante.toString()+" g ");
				calcularCantidad();			
			}
			else {
				productoInicial.getItems().remove(productoInicial.getValue());
				cambiarProductoSinStock();	
			}		
			
			tabla.getItems().add(dto);
		}
		
		//Si se elimina de la tabla de venta no se da de baja
	}
	
	@FXML private void btnAnadirConOtroPaquete() {
		//CU10Controller02.get();
		//TODO CU10.2 implementar, diria de abrir otra pantalla
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
    	DTOTipoProductoCU10 dto = tipoProducto.getValue();
		if(dto != null && (dto.getProductosIniciales().size()>0)) {
			btnAnadirEmpaquetamiento.setDisable(false);
			
			productoInicial.setDisable(false);
			productoInicial.getItems().clear();
			productoInicial.getItems().addAll(dto.getProductosIniciales());
			productoInicial.getSelectionModel().selectFirst();	
			
			formaVenta.setDisable(false);
			formaVenta.getItems().clear();
			formaVenta.getItems().addAll(dto.getFormasVenta());
			formaVenta.getSelectionModel().selectFirst();
		}
		else {
			btnAnadirEmpaquetamiento.setDisable(true);
			
			productoInicial.setDisable(true);
			productoInicial.getItems().clear();
			
			formaVenta.setDisable(true);
			formaVenta.getItems().clear();
			
			cantidad.setDisable(true);
			cantidad.setText("");
			cantidadMaximaPaquete.setText("Cantidad de paquetes:");
		}    	
    }
    
    @FXML private void seleccionarProductoInicial(){
    	DTOProductoInicialCU10 dto = productoInicial.getValue();
		if(dto != null) {
			btnDarBajaProductoInicial.setDisable(false);
			
			cantidadRestante = dto.getCantidadNoVendida()*1000;
			productoRestante.setText("Cantidad no vendida: "+dto.getCantidadNoVendida().toString()+" Kg / "+cantidadRestante.toString()+" g ");
			
			calcularCantidad();
		}
		else {
			App.setValido(cantidad);
			cantidad.setText("");
			cantidad.setDisable(true);
			
			cantidadMaximaPaquete.setText("Cantidad de paquetes:");
			productoRestante.setText("Cantidad no vendida:");
			
			btnAnadirEmpaquetamiento.setDisable(true);
			btnDarBajaProductoInicial.setDisable(true);
		}        	
    }
    
    @FXML private void seleccionarFormaVenta(){
    	DTOFormaVentaCU10 dto = formaVenta.getValue();
		if(dto != null) {
			tamanoPaquete = dto.getTipoPaquete().getCantidad();
			calcularCantidad();
		}
		else {
			btnAnadirEmpaquetamiento.setDisable(true);
			cantidad.setDisable(true);
			cantidad.setText("");
			cantidadMaximaPaquete.setText("");
		}        	
    }
    
    private void calcularCantidad() {    	
    	if( (tamanoPaquete!=null) && (cantidadRestante!=null)) {
    		cantidad.setText("");
    		if(cantidadRestante < tamanoPaquete) {
    			cantidad.setDisable(true);
    			
    			btnAnadirEmpaquetamiento.setDisable(true);
    			btnAnadirConOtroPaquete.setDisable(false);
    			
    			cantidadMaximaPaquete.setText("Cantidad de paquetes:");
    		}
    		else {
            	cantidadMaxima = (int) (cantidadRestante / tamanoPaquete);
            	if( !(cantidadMaxima<1) ) {
            		cantidad.setDisable(false);
            		
            		btnAnadirEmpaquetamiento.setDisable(false);
        			btnAnadirConOtroPaquete.setDisable(true);
        			
            		cantidadMaximaPaquete.setText("Cantidad de paquetes: 1 - "+cantidadMaxima);
            		
            		cantidad.requestFocus();
            	} 
    		}        	        	
    	}
    }

    private void validarCampos() {
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
    
    private void validarCampos(KeyEvent e) {
    	Character caracter = e.getCharacter().charAt(0);    	
		if(!Character.isDigit(caracter)){
			e.consume();
		}
    }

    public void setControllerCu04(CU04Controller controllerCu04) {
		this.controllerCu04 = controllerCu04;
	}
    
    public void setControllerCu08(CU08Controller controllerCu08) {
		this.controllerCu08 = controllerCu08;
	}
    
    @SuppressWarnings("exports")
	public void empaquetarProducto(DTOProductoInicialCU10 dto) {
    	Iterator<DTOTipoProductoCU10> ite = tipoProducto.getItems().iterator();
    	DTOTipoProductoCU10 dtoTP = null;

    	while(ite.hasNext()) {
    		DTOTipoProductoCU10 d = ite.next();
        	if(d.getId()!=null) {
        		if(d.getId().equals(dto.getIdTipoProducto())) {
        			dtoTP = d;
        			break;
        		}
        	}
    	}
    	
    	tipoProducto.getSelectionModel().select(dtoTP);
    	seleccionarTipoProducto();
    	
    	productoInicial.getSelectionModel().select(dto);
    	seleccionarProductoInicial();
    }
    
}
