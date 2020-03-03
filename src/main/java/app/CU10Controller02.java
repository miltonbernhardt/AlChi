package app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import dto.DTOEmpaquetadoCU10;
import dto.DTOFormaVentaCU10;
import dto.DTOProductoInicialCU10;
import dto.DTOTipoProductoCU10;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CU10Controller02 {	
	private static CU10Controller01 controller = null;
	private static CU10Controller02 instance = null;
	private static Alert alert = null;
	
    @SuppressWarnings("exports")
	public static void get(DTOTipoProductoCU10 tipoProducto, DTOProductoInicialCU10 dto, DTOFormaVentaCU10 formaVenta) {
    	alert = new Alert(AlertType.NONE);
    	App.setStyle(alert.getDialogPane());
    	alert.setTitle("Empaquetar a partir de dos paquetes iniciales");
    	alert.setHeaderText(null);
    	alert.setContentText(""); 	
    	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
    	
    	Window window = alert.getDialogPane().getScene().getWindow();
    	window.setOnCloseRequest(event -> stage.close());
    	
    	try {                      
    		FXMLLoader fxml = new FXMLLoader(App.class.getResource("CU10View02.fxml"));    
    		Parent root = fxml.load();    
            alert.getDialogPane().setContent(root);
            instance = fxml.getController();
            instance.setDtoProductoInicialPrimario(dto);
            instance.setDtoTipoProducto(tipoProducto);
            instance.setTipoPaquete(formaVenta);
            instance.cargarTodo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	alert.showAndWait();   
    }

	@FXML private ComboBox<DTOProductoInicialCU10> productoInicial;
	@FXML private TextField tipoProducto;
	@FXML private TextField productoInicialPrimario;
	@FXML private TextField cantidadPrimaria;
	@FXML private TextField cantidadSecundaria;
	@FXML private TextField formaVenta;
	@FXML private TextField cantidadNoVendidaRestante;
	
	private DTOTipoProductoCU10 dtoTipoProducto = null;
	private DTOProductoInicialCU10 dtoProductoInicialPrimario = null;
	private DTOProductoInicialCU10 dtoProductoInicialSecundario = null;
	private DTOFormaVentaCU10 dtoFormaVenta = null;

	public CU10Controller02() { }
	
    @FXML private void initialize(){ }
    
	private void cargarTodo() {
		Iterator<DTOProductoInicialCU10> iterator = dtoTipoProducto.getProductosIniciales().iterator();
		
		Float f = dtoFormaVenta.getTipoPaquete().getCantidad() - dtoProductoInicialPrimario.getCantidadNoVendida();
		
		while(iterator.hasNext()) {
			DTOProductoInicialCU10 dto = iterator.next();
			if(dto.getCantidadNoVendida() >= f) {
				productoInicial.getItems().add(dto);
			}
		}
		productoInicial.getItems().remove(dtoProductoInicialPrimario);
		productoInicial.getSelectionModel().selectFirst();
		
		
		productoInicialPrimario.setText(dtoProductoInicialPrimario.toString());
		productoInicialPrimario.setPrefWidth(productoInicialPrimario.getText().length() * 7);
		
		
		Float cantInicial = dtoProductoInicialPrimario.getCantidadNoVendida();	
		cantidadPrimaria.setText(App.floatSinCero(cantInicial)+" Kg");
		cantidadPrimaria.setPrefWidth(cantidadPrimaria.getText().length() * 9 + 20);
		
		formaVenta.setText(dtoFormaVenta.getTipoPaquete().toString());
		formaVenta.setPrefWidth(formaVenta.getText().length() * 9 + 20);
		
		tipoProducto.setText(dtoTipoProducto.getNombre());
		tipoProducto.setPrefWidth(tipoProducto.getText().length() * 9 + 20);
		
		
		
		
		dtoProductoInicialSecundario = productoInicial.getValue();
		
		calcular();	
	}
	
	@FXML private void confirmar() {
		if(controller != null) {
			if(dtoProductoInicialSecundario != null) {
				DTOEmpaquetadoCU10 dto = new DTOEmpaquetadoCU10(); 				
				
				DTOTipoProductoCU10 dtoTipoProd = dtoTipoProducto;	
				dto.setDtoTipoProducto(dtoTipoProd);
				dto.setIdProductoInicial(dtoTipoProd.getId());
				dto.setNombreTipoProducto(dtoTipoProd.getNombre());
				
				DTOFormaVentaCU10 dtoForma = dtoFormaVenta;
				dto.setDtoFormaVenta(dtoForma);
				dto.setTipoPaquete(dtoForma.getTipoPaquete());							
				dto.setCantidadPaquetes(1);		
				
				
				DTOProductoInicialCU10 dtoProdIni = dtoProductoInicialPrimario;	
				DTOProductoInicialCU10 dtoProdSec = dtoProductoInicialSecundario;	
				
				LocalDate vencInicial = dtoProdIni.getVencimiento();
				LocalDate vencSecundario = dtoProdSec.getVencimiento();
				
				if(vencSecundario.isBefore(vencInicial)) {
					dto.setVencimiento(vencSecundario);
				}
				else {
					dto.setVencimiento(vencInicial);
				}					
				
				
				dto.setCantPrimario(dtoProdIni.getCantidadNoVendida());		
				
				dtoProdIni.setDisponible(false);
				Float cantidadRestante = dtoProdSec.getCantidadNoVendida() - dtoForma.getTipoPaquete().getCantidad() + dtoProdIni.getCantidadNoVendida();
				App.redondear(cantidadRestante);
				if(cantidadRestante<0.01) {
					cantidadRestante = 0f;
				}
				
				dtoProdSec.setCantidadNoVendida(cantidadRestante);
				dtoProdIni.setCantidadNoVendida(0f);
				
				dto.setDtoProductoInicial(dtoProdIni);
				dto.setDtoProductoInicialSecundario(dtoProdSec);
				
				dto.setIdProductoInicial(dtoProdIni.getIdProductoInicial());
				dto.setIdProductoSecundario(dtoProdSec.getIdProductoInicial());
				
				dto.setCodigoBarra(dtoProdIni.getCodigoBarra());		
				dto.setNombreProveedor(dtoProdIni.getProveedor());
				dto.setSecundario(true);
				controller.addToTable(dto);
				cancelar();				
			}
		}
	}

    @FXML private void cancelar() {
    	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.close();
	}
    
    @FXML private void seleccionarProducto() {
    	DTOProductoInicialCU10 dto = productoInicial.getValue();
		if(dto != null) {
			dtoProductoInicialSecundario = dto;
			calcular();
		}
		else {
			cantidadNoVendidaRestante.setText("");
		}    
    }
    
    private void calcular() {
    	Float cantInicial = dtoProductoInicialPrimario.getCantidadNoVendida();	
    	Float cantSecundario = dtoFormaVenta.getTipoPaquete().getCantidad() - cantInicial;
		Float cantRestante = 0f;
		
		if(dtoProductoInicialSecundario != null) {
			cantRestante = dtoProductoInicialSecundario.getCantidadNoVendida() - cantSecundario;
			App.redondear(cantRestante);
		}				
		
		cantidadSecundaria.setText(App.floatSinCero(cantSecundario)+" Kg");
		cantidadSecundaria.setPrefWidth(cantidadSecundaria.getText().length() * 9 + 20);
		
		cantidadNoVendidaRestante.setText(App.floatSinCero(cantRestante)+ " Kg");
		cantidadNoVendidaRestante.setPrefWidth(cantidadNoVendidaRestante.getText().length() * 9 + 20);	
    }

    private void setDtoProductoInicialPrimario(DTOProductoInicialCU10 dtoProductoInicialPrimario) {
		this.dtoProductoInicialPrimario = dtoProductoInicialPrimario;
	}
	
    private void setDtoTipoProducto(DTOTipoProductoCU10 dtoTipoProducto) {
		this.dtoTipoProducto = dtoTipoProducto;
	}

	@SuppressWarnings("exports")
	public void setTipoPaquete(DTOFormaVentaCU10 dtoFormaVenta) {
		this.dtoFormaVenta = dtoFormaVenta;
	}

	public static void setController(CU10Controller01 controller) {
		CU10Controller02.controller = controller;
	}
}
