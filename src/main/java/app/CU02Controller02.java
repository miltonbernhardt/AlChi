package app;

import java.util.Optional;
import dto.DTOCU03;
import enums.Porcentaje;
import gestor.GestorProductos;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CU02Controller02 {
	private static CU02Controller02 instance = null;

    public static CU02Controller02 get() {
        if (instance == null){
        	App.setViewAnterior();	
        	instance = (CU02Controller02) App.setRoot("CU02View02", "Editar precios");
        }    
        return instance;
    }
	
    private DTOCU03 tipoProducto ;
    
    @FXML private Label titulo;
	
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
	
	@FXML private Button btnConfirmarPrecios;

	private CU02Controller01 controllerCu02 = null;
    
	public CU02Controller02() { }
	
    @FXML private void initialize(){
    	addListenerCampos();
    }
	
	@FXML private void btnConfirmarPrecios() {
		DTOCU03 dto = tipoProducto;
		String cadena = "", header = "Confirmación de precios para el producto: '"+dto.getNombreTipoProducto()+"' no válida.";
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
			
			Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar ingreso de productos", null, "¿Desea confirmar el ingreso de los productos y la actualización de los precios?");
			
			if (result.get() == ButtonType.OK){
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

				if( GestorProductos.get().actualizarPrecios(dto) ) {
					PanelAlerta.getInformation("Confirmación", null, "Los precios de '"+dto.getNombreTipoProducto()+"' fueron correctamente actualizados.");
					controllerCu02.actualizarProductoEditado(dto.getIdTipoProducto());
					volver();
				}				
			}  			
		}
		else {
			PanelAlerta.getError("Error", header, cadena);
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
    		
			if(!(tipoProducto.getP100Anterior() == 0f)) {
				rd100Ant.setDisable(false);
				rd100Ant.setSelected(true);
				
				p100Anterior.setDisable(false);
				p100Anterior.setText(tipoProducto.getP100Anterior().toString());
			}			
    	}
    	else {
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
    		
    		if(!(tipoProducto.getP250Anterior() == 0f)) {
				rd250Ant.setDisable(false);
				rd250Ant.setSelected(true);
				
				p250Anterior.setDisable(false); 
				p250Anterior.setText(tipoProducto.getP250Anterior().toString());
			}
    	}
    	else {
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
    		
    		if(!(tipoProducto.getP500Anterior() == 0f)) {
    			rd500Ant.setDisable(false);
    			rd500Ant.setSelected(true);
    			
    			p500Anterior.setDisable(false); 
    			p500Anterior.setText(tipoProducto.getP500Anterior().toString());
			}
    	}
    	else {
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
    		
    		if(!(tipoProducto.getP1000Anterior() == 0f)) {
    			rd1000Ant.setDisable(false);
    			rd1000Ant.setSelected(true);
    			
    			p1000Anterior.setDisable(false);   
    			p1000Anterior.setText(tipoProducto.getP1000Anterior().toString());
			}
    	}
    	else {
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
    		
    		if(!(tipoProducto.getP2000Anterior() == 0f)) {
    			rd2000Ant.setDisable(false);
    			rd2000Ant.setSelected(true);
    			
    			p2000Anterior.setDisable(false);
    			p2000Anterior.setText(tipoProducto.getP2000Anterior().toString());
			}
    	}
    	else {
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
    		
    		if(!(tipoProducto.getPUnidadAnterior() == 0f)) {
    			rdUnidadAnt.setDisable(false);
    			rdUnidadAnt.setSelected(true);
    			
				pUnidadAnterior.setDisable(false);
				pUnidadAnterior.setText(tipoProducto.getPUnidadAnterior().toString());
			}
    	}
    	else {
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
	
	@SuppressWarnings("exports")
	public void addDTOCU03( DTOCU03 dto) {
		this.tipoProducto = dto;
		titulo.setText("Precios de '"+tipoProducto.getNombreTipoProducto()+"'");
		setearCampos();
	}
	
	private void setearCampos() {		
		if( !(tipoProducto.getP100Anterior() == 0f) ) 
			checkP100.setSelected(true);
		else 
			checkP100.setSelected(false);
		if( !(tipoProducto.getP250Anterior() == 0f) )
			checkP250.setSelected(true);
		else
			checkP250.setSelected(false);
		if( !(tipoProducto.getP500Anterior() == 0f) )
			checkP500.setSelected(true);	
		else
			checkP500.setSelected(false);	
		if( !(tipoProducto.getP1000Anterior() == 0f) )
			checkP1000.setSelected(true);
		else
			checkP1000.setSelected(false);
		if( !(tipoProducto.getP2000Anterior() == 0f) )
			checkP2000.setSelected(true);
		else
			checkP2000.setSelected(false);
		if( !(tipoProducto.getPUnidadAnterior() == 0f) )
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
	 
    private void addListenerCampos() {
    	p100.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p100.setText(p100.getText().replace(',', '.'));   
            	if(! p100.getText().isBlank()) {
            		App.setValido(p100);
            		p100Nuevo.setText(calcularPrecioNuevo(Float.parseFloat(p100.getText())).toString());
            	}
            	else
            		p100Nuevo.setText("");
            }
        }); 
    	
    	p250.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p250.setText(p250.getText().replace(',', '.'));
            	if(! p250.getText().isBlank()) {
            		App.setValido(p250);
            		p250Nuevo.setText(calcularPrecioNuevo(Float.parseFloat(p250.getText())).toString());
            	}
            	else
            		p250Nuevo.setText("");
            }
        }); 
    	
    	p500.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p500.setText(p500.getText().replace(',', '.'));
            	
            	if(! p500.getText().isBlank()) {
            		App.setValido(p500);
            		p500Nuevo.setText(calcularPrecioNuevo(Float.parseFloat(p500.getText())).toString());
            	}
            	else
            		p500Nuevo.setText("");
            }
        }); 
    	
    	p1000.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p1000.setText(p1000.getText().replace(',', '.'));
            	
            	if(! p1000.getText().isBlank()) {
            		App.setValido(p1000);
            		p1000Nuevo.setText(calcularPrecioNuevo(Float.parseFloat(p1000.getText())).toString());
            	}
            	else
            		p1000Nuevo.setText("");
            }
        }); 
    	
    	p2000.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p2000.setText(p2000.getText().replace(',', '.'));
            	
            	if(! p2000.getText().isBlank()) {
            		App.setValido(p2000);
            		p2000Nuevo.setText(calcularPrecioNuevo(Float.parseFloat(p2000.getText())).toString());
            	}
            	else
            		p2000Nuevo.setText("");
            }
        }); 
    	
    	pUnidad.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	pUnidad.setText(pUnidad.getText().replace(',', '.'));
            	
            	if(! pUnidad.getText().isBlank()) {
            		App.setValido(pUnidad);
            		pUnidadNuevo.setText(calcularPrecioNuevo(Float.parseFloat(pUnidad.getText())).toString());
            	}
            	else
            		pUnidadNuevo.setText("");
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
    
	private Float calcularPrecioNuevo(Float valorSugerido) {
    	Float valor = valorSugerido*Porcentaje.NORMAL.getValue();
    	
    	Float modulo5 = valor%5;
    	if(modulo5 >= 2.5) {
    		valor = valor - modulo5 + 5;
    	}
    	else {
    		valor = valor - modulo5;
    	}   	
    	
    	return valor;
    }
    
    private void validarChecks() {    	
    	App.setValido(checkP100);    	
		App.setValido(checkP250);		
		App.setValido(checkP500);
		App.setValido(checkP1000);		
		App.setValido(checkP2000);		
		App.setValido(checkPUnidad);
    }

    public void setController(CU02Controller01 cu02Controller) {
		this.controllerCu02 = cu02Controller;		
	}
}
