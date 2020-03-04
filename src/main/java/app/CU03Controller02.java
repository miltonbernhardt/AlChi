package app;

import java.util.List;
import java.util.Optional;
import dto.DTOCU03;
import dto.DTOProductoInicial;
import enums.Porcentaje;
import gestor.GestorEntradaSalida;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/*
 * Controller para la view de "Actualización del registro de entrada"
 */
public class CU03Controller02 {
	private static CU03Controller02 instance = null;

    public static CU03Controller02 get() {
        if (instance == null){
        	App.setViewAnterior();	
        	instance = (CU03Controller02) App.setRoot("CU03View02", "Actualización de precios para los productos de entrada");
        }    
        return instance;
    }
	
    private DTOCU03 tipoProductoCombo = null;
    private DTOCU03 tipoProductoTabla = null;
    
	@FXML private ComboBox<DTOCU03> productos;

	@FXML private TextField p100;	
	@FXML private TextField p250;
	@FXML private TextField p500;
	@FXML private TextField p1000;
	@FXML private TextField p2000;	
	@FXML private TextField pUnidad;
	
	@FXML private TextField p100Nuevo;	
	@FXML private TextField p250Nuevo;		
	@FXML private TextField p500Nuevo;		
	@FXML private TextField p1000Nuevo;
	@FXML private TextField p2000Nuevo;
	@FXML private TextField pUnidadNuevo;
	
	@FXML private TextField p100Anterior;	
	@FXML private TextField p250Anterior;	
	@FXML private TextField p500Anterior;		
	@FXML private TextField p1000Anterior;
	@FXML private TextField p2000Anterior;
	@FXML private TextField pUnidadAnterior;
		
	@FXML private RadioButton rd100;		
	@FXML private RadioButton rd250;	
	@FXML private RadioButton rd500;	
	@FXML private RadioButton rd1000;	
	@FXML private RadioButton rd2000;	
	@FXML private RadioButton rdUnidad;
	
	@FXML private RadioButton rd100Ant;	
	@FXML private RadioButton rd250Ant;
	@FXML private RadioButton rd500Ant;	
	@FXML private RadioButton rd1000Ant;	
	@FXML private RadioButton rd2000Ant;
	@FXML private RadioButton rdUnidadAnt;
	
	@FXML private CheckBox checkP100;	
	@FXML private CheckBox checkP250;	
	@FXML private CheckBox checkP500;	
	@FXML private CheckBox checkP1000;	
	@FXML private CheckBox checkP2000;	
	@FXML private CheckBox checkPUnidad;	
	
	@FXML private Button btnEditarPrecios;
	@FXML private Button btnConfirmarPrecios;
	
	@FXML private TableView<DTOCU03> tabla;
	
	@FXML private TableColumn<DTOCU03, String> columnaProducto;	
	@FXML private TableColumn<DTOCU03, String> columnaP100;	
	@FXML private TableColumn<DTOCU03, String> columnaP250;
	@FXML private TableColumn<DTOCU03, String> columnaP500;	
	@FXML private TableColumn<DTOCU03, String> columnaP1000;
	@FXML private TableColumn<DTOCU03, String> columnaP2000;	
	@FXML private TableColumn<DTOCU03, String> columnaPUnidad;
    
	public CU03Controller02() { }
	
    @FXML private void initialize(){
    	iniciarTabla();
    	addListenerCampos();
    }
    
	private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay productos que mostrar."));
    	columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombreTipoProducto"));
    	columnaP100.setCellValueFactory(new PropertyValueFactory<>("p100Final"));
    	columnaP250.setCellValueFactory(new PropertyValueFactory<>("p250Final"));
    	columnaP500.setCellValueFactory(new PropertyValueFactory<>("p500Final"));
    	columnaP1000.setCellValueFactory(new PropertyValueFactory<>("p1000Final"));
    	columnaP2000.setCellValueFactory(new PropertyValueFactory<>("p2000Final"));
    	columnaPUnidad.setCellValueFactory(new PropertyValueFactory<>("pUnidadFinal"));
    }
	
	@FXML private void btnConfirmarPrecios() {
		validar100();
		validar250();
		validar500();
		validar1000();
		validar2000();
		validarUnidad();
		
		DTOCU03 dto = productos.getValue();
		String cadena = "", header = "Confirmación de precios para el producto: '"+productos.getValue().getNombreTipoProducto()+"' no válida.";
		Integer numError = 1;
		Boolean valido = true, 
				ck100=checkP100.isSelected(), ck250=checkP250.isSelected(), ck500=checkP500.isSelected(),
				ck1000=checkP1000.isSelected(), ck2000=checkP2000.isSelected(), ckUnidad=checkPUnidad.isSelected(),
				sel100=rd100.isSelected(), sel250=rd250.isSelected(), sel500=rd500.isSelected(), sel1000=rd1000.isSelected(),
				sel2000=rd2000.isSelected(), selUnidad=rdUnidad.isSelected();
		
		if(!ck100 && !ck250 && !ck500 && !ck1000 && !ck2000 && !ckUnidad) {
			App.setError(checkP100);
			App.setError(checkP250);
			App.setError(checkP500);
			App.setError(checkP1000);
			App.setError(checkP2000);
			App.setError(checkPUnidad);
			cadena = cadena + "Se debe elegir al menos una forma de venta para el producto.";
			valido = false;
		}
		
		if(ck100 && sel100 && (p100.getText().isBlank() || (Float.parseFloat(p100.getText())==0f) )) {
    		cadena = cadena + numError+ ") Se debe ingresar un valor de precio sugerido para actualizar la forma de venta de 100 gramos.\n";
			numError++;
			valido = false;
			App.setError(p100);
    	}
		
		if(ck250 && sel250 && (p250.getText().isBlank() || (Float.parseFloat(p250.getText())==0f) )) {
			cadena = cadena + numError+ ") Se debe ingresar un valor de precio sugerido para actualizar la forma de venta de 250 gramos.\n";
			numError++;
			valido = false;
			App.setError(p250);
    	}
		
		if(ck500 && sel500 && (p500.getText().isBlank() || (Float.parseFloat(p500.getText())==0f) )) {
			cadena = cadena + numError+ ") Se debe ingresar un valor de precio sugerido para actualizar la forma de venta de 500 gramos.\n";
			numError++;
			valido = false;
			App.setError(p500);
    	}
		
		if(ck1000 && sel1000 && (p1000.getText().isBlank() || (Float.parseFloat(p1000.getText())==0f) )) {
			cadena = cadena + numError+ ") Se debe ingresar un valor de precio sugerido para actualizar la forma de venta de 1000 gramos.\n";
			numError++;
			valido = false;
			App.setError(p1000);
    	}
		
		if(ck2000 && sel2000 && (p2000.getText().isBlank() || (Float.parseFloat(p2000.getText())==0f) )) {
			cadena = cadena + numError+ ") Se debe ingresar un valor de precio sugerido para actualizar la forma de venta de 2000 gramos.\n";
			numError++;
			valido = false;
			App.setError(p2000);
    	}
		
		if(ckUnidad && selUnidad && (pUnidad.getText().isBlank() || (Float.parseFloat(pUnidad.getText())==0f) )) {
			cadena = cadena + numError+ ") Se debe ingresar un valor de precio sugerido para actualizar la forma de venta de 1 unidad.\n";
			numError++;
    		valido = false;
    		App.setError(pUnidad);
    	}
		
		if(valido) {
			if(ck100) {
				if(sel100)
					dto.setP100Final(Float.parseFloat(p100Nuevo.getText()));
				else
					dto.setP100Final(Float.parseFloat(p100Anterior.getText()));
			}
			else {
				dto.setP100Final(0f);
			}
			
			if(ck250) {
				if(sel250)
					dto.setP250Final(Float.parseFloat(p250Nuevo.getText()));
				
				else
					dto.setP250Final(Float.parseFloat(p250Anterior.getText()));
			}
			else {
				dto.setP250Final(0f);
			}
			
			if(ck500) {
				if(sel500)
					dto.setP500Final(Float.parseFloat(p500Nuevo.getText()));
				
				else
					dto.setP500Final(Float.parseFloat(p500Anterior.getText()));
			}
			else {
				dto.setP500Final(0f);
			}
			
			if(ck1000) {
				if(sel1000)
					dto.setP1000Final(Float.parseFloat(p1000Nuevo.getText()));
				
				else
					dto.setP1000Final(Float.parseFloat(p1000Anterior.getText()));
			}
			else {
				dto.setP1000Final(0f);
			}
			
			if(ck2000) {
				if(sel2000)
					dto.setP2000Final(Float.parseFloat(p2000Nuevo.getText()));
				
				else
					dto.setP2000Final(Float.parseFloat(p2000Anterior.getText()));
			}
			else {
				dto.setP2000Final(0f);
			}
			
			if(ckUnidad) {
				if(selUnidad)
					dto.setPUnidadFinal(Float.parseFloat(pUnidadNuevo.getText()));
				
				else
					dto.setPUnidadFinal(Float.parseFloat(pUnidadAnterior.getText()));
			}
			else {
				dto.setPUnidadFinal(0f);
			}
			
			agregarATabla(dto);
		}
		else {
			PanelAlerta.getError("Error", header, cadena);
		}		
	}
	
	@FXML private void btnEditarPrecios() {
		productos.getItems().add(tipoProductoTabla);
		tabla.getItems().remove(tipoProductoTabla);
		productos.getSelectionModel().select(tipoProductoTabla);
		editarProducto(tipoProductoTabla);
		btnEditarPrecios.setDisable(true);
		tabla.getSelectionModel().clearSelection();
		tipoProductoTabla = null;
	}
    
	@FXML private void btnFinalizar() {
		if(tabla.getItems().size()>0) {			
			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar ingreso de productos", null, "¿Desea confirmar el ingreso de los productos y la actualización de los precios?");
			
			if (result.get() == ButtonType.OK){
				if(GestorEntradaSalida.get().registrarIngreso(tabla.getItems())) {
					PanelAlerta.getInformation("Confirmación", null, "La transacción ocurrió de manera efectiva.");
	                volver();
	                CU03Controller01.get().volver();
				}
			}    
		}
		else{
			PanelAlerta.getError("Aviso", null, "Se debe confirmar los precios de los productos a los cuales se le está registrando la entrada.");
		}
    }

    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
	@FXML private void checkP100() {
		validarChecks();
		
		if(checkP100.isSelected() && !checkP100.isDisable()) {
			if(checkPUnidad.isSelected()) {
				checkPUnidad.setSelected(false);
				checkPUnidad();
			}			
			
    		p100.setDisable(false);
    		rd100.setDisable(false);
    		p100Nuevo.setDisable(false);
    		
			if(!(tipoProductoCombo.getP100Anterior() == 0f)) {
				rd100Ant.setDisable(false);
				rd100Ant.setSelected(true);
				
				p100Anterior.setDisable(false);
				p100Anterior.setText(App.floatSinCero(tipoProductoCombo.getP100Anterior()));
			}			
    	}
    	else {
    		App.setValido(p100);
    		checkP100.setSelected(false);
    		
    		p100Anterior.setText("");
    		
    		p100Nuevo.setDisable(true);   
    		p100Anterior.setDisable(true); 
    		p100.setDisable(true);
    		
    		rd100.setDisable(true);
    		rd100Ant.setDisable(true);
    		rd100.setSelected(true);
    	}
		
		p100Nuevo.setText("");
		p100.setText("");
	}
	
	@FXML private void checkP250() {
		validarChecks();
		
		if(checkP250.isSelected() && !checkP250.isDisable()) {
			if(checkPUnidad.isSelected()) {
				checkPUnidad.setSelected(false);
				checkPUnidad();
			}
			
			p250.setDisable(false);
    		rd250.setDisable(false);
    		p250Nuevo.setDisable(false);   
    		
    		if(!(tipoProductoCombo.getP250Anterior() == 0f)) {
				rd250Ant.setDisable(false);
				rd250Ant.setSelected(true);
				
				p250Anterior.setDisable(false); 
				p250Anterior.setText(App.floatSinCero(tipoProductoCombo.getP250Anterior()));
			}
    	}
    	else {
    		App.setValido(p250);
    		checkP250.setSelected(false);
    		
    		p250Anterior.setText("");
    		
    		p250Nuevo.setDisable(true);   
    		p250Anterior.setDisable(true); 
    		p250.setDisable(true);
    		
    		rd250.setDisable(true);
    		rd250Ant.setDisable(true);
    		rd250.setSelected(true);
    	}
		p250Nuevo.setText("");
		p250.setText("");
	}
	
	@FXML private void checkP500() {
		validarChecks();
		
		if(checkP500.isSelected() && !checkP500.isDisable()) {
			if(checkPUnidad.isSelected()) {
				checkPUnidad.setSelected(false);
				checkPUnidad();
			}
			
			p500.setDisable(false);
    		rd500.setDisable(false);  
    		p500Nuevo.setDisable(false); 
    		
    		if(!(tipoProductoCombo.getP500Anterior() == 0f)) {
    			rd500Ant.setDisable(false);
    			rd500Ant.setSelected(true);
    			
    			p500Anterior.setDisable(false); 
    			p500Anterior.setText(App.floatSinCero(tipoProductoCombo.getP500Anterior()));
			}
    	}
    	else {
    		App.setValido(p500);
    		checkP500.setSelected(false);
    		
    		p500Anterior.setText("");
    		
    		p500Nuevo.setDisable(true);   
    		p500Anterior.setDisable(true); 
    		p500.setDisable(true);
    		
    		rd500.setDisable(true);
    		rd500Ant.setDisable(true);
    		rd500.setSelected(true);
    	}
		p500Nuevo.setText("");
		p500.setText("");
	}
	
	@FXML private void checkP1000() {
		validarChecks();

		if(checkP1000.isSelected() && !checkP1000.isDisable()) {
			if(checkPUnidad.isSelected()) {
				checkPUnidad.setSelected(false);
				checkPUnidad();
			}
			
			p1000.setDisable(false);
    		rd1000.setDisable(false);
    		p1000Nuevo.setDisable(false);   		
    		
    		if(!(tipoProductoCombo.getP1000Anterior() == 0f)) {
    			rd1000Ant.setDisable(false);
    			rd1000Ant.setSelected(true);
    			
    			p1000Anterior.setDisable(false);   
    			p1000Anterior.setText(App.floatSinCero(tipoProductoCombo.getP1000Anterior()));
			}
    	}
    	else {
    		App.setValido(p1000);
    		checkP1000.setSelected(false);
    		
    		p1000Anterior.setText("");
    		
    		p1000Nuevo.setDisable(true);   
    		p1000Anterior.setDisable(true); 
    		p1000.setDisable(true);
    		
    		rd1000.setDisable(true);
    		rd1000Ant.setDisable(true);
    		rd1000.setSelected(true);
    	}
		p1000Nuevo.setText("");  
		p1000.setText("");
	}
	
	@FXML private void checkP2000() {
		validarChecks();

		if(checkP2000.isSelected() && !checkP2000.isDisable()) {
			if(checkPUnidad.isSelected()) {
				checkPUnidad.setSelected(false);
				checkPUnidad();
			}
			
    		p2000.setDisable(false);
    		rd2000.setDisable(false);
    		p2000Nuevo.setDisable(false);
    		
    		if(!(tipoProductoCombo.getP2000Anterior() == 0f)) {
    			rd2000Ant.setDisable(false);
    			rd2000Ant.setSelected(true);
    			
    			p2000Anterior.setDisable(false);
    			p2000Anterior.setText(App.floatSinCero(tipoProductoCombo.getP2000Anterior()));
			}
    	}
    	else {
    		App.setValido(p2000);
    		checkP2000.setSelected(false);
    		
    		p2000Anterior.setText("");
    		
    		p2000Nuevo.setDisable(true); 
    		p2000Anterior.setDisable(true);
    		p2000.setDisable(true);
    		
    		rd2000.setDisable(true);
    		rd2000Ant.setDisable(true);
    		rd2000.setSelected(true);
    	}
		p2000Nuevo.setText("");
		p2000.setText("");
	}
	
	@FXML private void checkPUnidad() {
		validarChecks();
		if(checkPUnidad.isSelected() && !checkPUnidad.isDisable()) {
			if(checkP100.isSelected()) {
				checkP100.setSelected(false);
				checkP100();
			}
			if(checkP250.isSelected()) {
				checkP250.setSelected(false);
				checkP250();
			}
			if(checkP500.isSelected()) {
				checkP500.setSelected(false);
				checkP500();
			}
			if(checkP1000.isSelected()) {
				checkP1000.setSelected(false);
				checkP1000();
			}
			if(checkP2000.isSelected()) {
				checkP2000.setSelected(false);
				checkP2000();
			}
			
    		pUnidad.setDisable(false);
    		rdUnidad.setDisable(false);    		
    		pUnidadNuevo.setDisable(false);
    		
    		if(!(tipoProductoCombo.getPUnidadAnterior() == 0f)) {
    			rdUnidadAnt.setDisable(false);
    			rdUnidadAnt.setSelected(true);
    			
				pUnidadAnterior.setDisable(false);
				pUnidadAnterior.setText(App.floatSinCero(tipoProductoCombo.getPUnidadAnterior()));
			}
    	}
    	else {
    		App.setValido(pUnidad);
    		
    		checkPUnidad.setSelected(false);
    		
    		pUnidadAnterior.setText("");
    		
    		pUnidadNuevo.setDisable(true);
    		pUnidadAnterior.setDisable(true);
    		pUnidad.setDisable(true);
    		
    		rdUnidad.setDisable(true);
    		rdUnidadAnt.setDisable(true);
    		rdUnidad.setSelected(true);
    	}
		
		pUnidadNuevo.setText("");
		pUnidad.setText("");
	}
	
	@FXML private void setearCampos() {
		DTOCU03 dto = productos.getValue();
		if(dto != null) {
			if(! (dto.equals(tipoProductoCombo))) {
				
				tipoProductoCombo = dto;			
				if( !(tipoProductoCombo.getP100Anterior() == 0f) ) 
					checkP100.setSelected(true);
				else 
					checkP100.setSelected(false);
				if( !(tipoProductoCombo.getP250Anterior() == 0f) )
					checkP250.setSelected(true);
				else
					checkP250.setSelected(false);
				if( !(tipoProductoCombo.getP500Anterior() == 0f) )
					checkP500.setSelected(true);	
				else
					checkP500.setSelected(false);	
				if( !(tipoProductoCombo.getP1000Anterior() == 0f) )
					checkP1000.setSelected(true);
				else
					checkP1000.setSelected(false);
				if( !(tipoProductoCombo.getP2000Anterior() == 0f) )
					checkP2000.setSelected(true);
				else
					checkP2000.setSelected(false);
				if( !(tipoProductoCombo.getPUnidadAnterior() == 0f) )
					checkPUnidad.setSelected(true);	
				else
					checkPUnidad.setSelected(false);	
					
				checkP100();
				checkP250();
				checkP500();
				checkP1000();				
				checkP2000();
				checkPUnidad();
			}	
		}
	}
	
	@FXML private void seleccionarProducto() {
		DTOCU03 dto = tabla.getSelectionModel().getSelectedItem();		
		if(dto != null) {
			tipoProductoTabla = dto;
			btnEditarPrecios.setDisable(false);
		}
		else {
			btnEditarPrecios.setDisable(true);
		}
	}
	 
    private void addListenerCampos() {
    	p100.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	validar100();
            }
        }); 
    	
    	p250.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	validar250();
            }
        }); 
    	
    	p500.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	validar500();
            }
        }); 
    	
    	p1000.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	validar1000();
            }
        }); 
    	
    	p2000.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	validar2000();
            }
        }); 
    	
    	pUnidad.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	validarUnidad();
            }
        }); 
    	
    	
    	p100.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	p250.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	p500.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	p1000.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	p2000.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    	
    	pUnidad.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override public void handle(KeyEvent e) { validarCampos(e); }
    	});
    }

    private void validar100() {
    	p100.setText(p100.getText().replace(',', '.'));   
    	if(! p100.getText().isBlank()) {
    		App.setValido(p100);
    		p100Nuevo.setText(calcularPrecioNuevo(p100.getText()));
    	}
    	else
    		p100Nuevo.setText("");
	}
    
    private void validar250() {
    	p250.setText(p250.getText().replace(',', '.'));
    	if(! p250.getText().isBlank()) {
    		App.setValido(p250);
    		p250Nuevo.setText(calcularPrecioNuevo(p250.getText()));
    	}
    	else
    		p250Nuevo.setText("");
	}
    
    private void validar500() {
    	p500.setText(p500.getText().replace(',', '.'));
    	
    	if(! p500.getText().isBlank()) {
    		App.setValido(p500);
    		p500Nuevo.setText(calcularPrecioNuevo(p500.getText()));
    	}
    	else
    		p500Nuevo.setText("");
	}
    
    private void validar1000() {
    	p1000.setText(p1000.getText().replace(',', '.'));
    	
    	if(! p1000.getText().isBlank()) {
    		App.setValido(p1000);
    		p1000Nuevo.setText(calcularPrecioNuevo(p1000.getText()));
    	}
    	else
    		p1000Nuevo.setText("");
	}
    
    private void validar2000() {
    	p2000.setText(p2000.getText().replace(',', '.'));
    	
    	if(! p2000.getText().isBlank()) {
    		App.setValido(p2000);
    		p2000Nuevo.setText(calcularPrecioNuevo(p2000.getText()));
    	}
    	else
    		p2000Nuevo.setText("");
	}
    
    private void validarUnidad() {
       	pUnidad.setText(pUnidad.getText().replace(',', '.'));
    	
    	if(! pUnidad.getText().isBlank()) {
    		App.setValido(pUnidad);
    		pUnidadNuevo.setText(calcularPrecioNuevo(pUnidad.getText()));
    	}
    	else
    		pUnidadNuevo.setText("");
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
    
	private String calcularPrecioNuevo(String valorSugerido) {
		Float f = Float.parseFloat(valorSugerido);
    	Float valor = f*Porcentaje.NORMAL.getValue();
    	
    	Float modulo5 = valor%5;
    	if(modulo5 >= 2.5) {
    		valor = valor - modulo5 + 5;
    	}
    	else {
    		valor = valor - modulo5;
    	}   	
    	
    	return App.floatSinCero(valor);
    }
    
	@SuppressWarnings("exports")
	public void setListaProductos( List<DTOProductoInicial> listaProductos) {
		productos.getItems().clear();
		productos.getItems().addAll(GestorEntradaSalida.get().getProductosActualizar(listaProductos));
		productos.getSelectionModel().selectFirst();
		setearCampos();
	}
	
    private void agregarATabla(DTOCU03 dto) {
    	String sug100=p100.getText(), sug250=p250.getText(), sug500=p500.getText(), sug1000=p1000.getText(), sug2000=p2000.getText(), sugUnidad=pUnidad.getText();
    	
    	if(!sug100.isBlank())
    		dto.setP100(Float.parseFloat(sug100));
    	if(!sug250.isBlank())
    		dto.setP250(Float.parseFloat(sug250));
    	if(!sug500.isBlank())
    		dto.setP500(Float.parseFloat(sug500));
    	if(!sug1000.isBlank())
    		dto.setP1000(Float.parseFloat(sug1000));
    	if(!sug2000.isBlank())
    		dto.setP2000(Float.parseFloat(sug2000));
    	if(!sugUnidad.isBlank())
    		dto.setPUnidad(Float.parseFloat(sugUnidad));
    	
    	String nue100=p100Nuevo.getText(), nue250=p250Nuevo.getText(), nue500=p500Nuevo.getText(), nue1000=p1000Nuevo.getText(), nue2000=p2000Nuevo.getText(), nueUnidad=pUnidadNuevo.getText();
    	
    	if(!nue100.isBlank())
    		dto.setP100Nuevo(Float.parseFloat(nue100));
    	if(!nue250.isBlank())
    		dto.setP250Nuevo(Float.parseFloat(nue250));
    	if(!nue500.isBlank())
    		dto.setP500Nuevo(Float.parseFloat(nue500));
    	if(!nue1000.isBlank())
    		dto.setP1000Nuevo(Float.parseFloat(nue1000));
    	if(!nue2000.isBlank())
    		dto.setP2000Nuevo(Float.parseFloat(nue2000));
    	if(!nueUnidad.isBlank())
    		dto.setPUnidadNuevo(Float.parseFloat(nueUnidad));
    	
    	dto.setRd100(rd100.isSelected());
    	dto.setRd250(rd250.isSelected());
    	dto.setRd500(rd500.isSelected());
    	dto.setRd1000(rd1000.isSelected());
    	dto.setRd2000(rd2000.isSelected());
    	dto.setRdUnidad(rdUnidad.isSelected());
    	
    	dto.setCheckP100(checkP100.isSelected());
    	dto.setCheckP250(checkP250.isSelected());
    	dto.setCheckP500(checkP500.isSelected());
    	dto.setCheckP1000(checkP1000.isSelected());
    	dto.setCheckP2000(checkP2000.isSelected());
    	dto.setCheckPUnidad(checkPUnidad.isSelected()); 

		tabla.getItems().add(dto);
		
		productos.getItems().remove(dto);
		
		if(productos.getItems().size() == 0)
			productoTablaCombo(true);
		else {
			productos.getSelectionModel().selectFirst();
			//setearCampos();
		}
    }
    
    private void editarProducto(DTOCU03 dto) {
    	Float sug100=dto.getP100(), sug250=dto.getP250(), sug500=dto.getP500(), sug1000=dto.getP1000(), sug2000=dto.getP2000(), sugUnidad=dto.getPUnidad();
    	
    	Boolean ch100=dto.getCheckP100(), ch250=dto.getCheckP250(), ch500=dto.getCheckP500(), ch1000=dto.getCheckP1000(), ch2000=dto.getCheckP2000(), chUnidad=dto.getCheckPUnidad();
    	
    	checkP100.setSelected(ch100);
    	checkP250.setSelected(ch250);
    	checkP500.setSelected(ch500);
    	checkP1000.setSelected(ch1000);
    	checkP2000.setSelected(ch2000);
    	checkPUnidad.setSelected(chUnidad);
    	
    	productoTablaCombo(false);
    	
    	if(ch100) {
    		if(! (sug100 == 0f)) {
    			p100.setText(App.floatSinCero(sug100));
    			p100Nuevo.setText(App.floatSinCero(dto.getP100Nuevo()));
    		}
    		
    		if(dto.getRd100())
    			rd100.setSelected(true);
    		else
    			rd100Ant.setSelected(true);
    	}
    	
    	if(ch250) {
    		if(! (sug250 == 0f)) {
    			p250.setText(App.floatSinCero(sug250));
    			p250Nuevo.setText(App.floatSinCero(dto.getP250Nuevo()));
    		}
    		
    		if(dto.getRd250())
    			rd250.setSelected(true);
    		else
    			rd250Ant.setSelected(true);
    	}
    	
    	if(ch500) {
    		if(! (sug500 == 0f)) {
    			p500.setText(App.floatSinCero(sug500));
    			p500Nuevo.setText(App.floatSinCero(dto.getP500Nuevo()));
    		}
    		
    		if(dto.getRd500())
    			rd500.setSelected(true);
    		else
    			rd500Ant.setSelected(true);
    	}
    	
    	if(ch1000) {
    		if(! (sug1000 == 0f)) {
    			p1000.setText(App.floatSinCero(sug1000));
    			p1000Nuevo.setText(App.floatSinCero(dto.getP1000Nuevo()));
    		}
    		
    		if(dto.getRd1000())
    			rd1000.setSelected(true);
    		else
    			rd1000Ant.setSelected(true);
    	}
    	
    	if(ch2000) {
    		if(! (sug2000 == 0f)) {
    			p2000.setText(App.floatSinCero(sug2000));
    			p2000Nuevo.setText(App.floatSinCero(dto.getP2000Nuevo()));
    		}
    		
    		if(dto.getRd2000())
    			rd2000.setSelected(true);
    		else
    			rd2000Ant.setSelected(true);
    	}

    	if(chUnidad) {
    		if(! (sugUnidad == 0f)) {
    			pUnidad.setText(App.floatSinCero(sugUnidad));
    			pUnidadNuevo.setText(App.floatSinCero(dto.getPUnidadNuevo()));
    		}
    		
    		if(dto.getRdUnidad())
    			rdUnidad.setSelected(true);
    		else
    			rdUnidadAnt.setSelected(true);
    	}
    }
    
    private void productoTablaCombo(Boolean valor) {
    	productos.setDisable(valor);

		checkP100.setDisable(valor);
		checkP250.setDisable(valor);
		checkP500.setDisable(valor);
		checkP1000.setDisable(valor);
		checkP2000.setDisable(valor);		
		checkPUnidad.setDisable(valor);
		
		btnConfirmarPrecios.setDisable(valor);		
		
		checkP100();
		checkP250();
		checkP500();
		checkP1000();				
		checkP2000();
		checkPUnidad();			
    }
    
    private void validarChecks() {    	
    	App.setValido(checkP100);    	
		App.setValido(checkP250);		
		App.setValido(checkP500);
		App.setValido(checkP1000);		
		App.setValido(checkP2000);		
		App.setValido(checkPUnidad);
    }
}
