package app;

import java.util.List;
import dto.DTOCU03;
import dto.DTOProductoInicial;
import gestor.GestorEntrada;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
 * Controller para la view de "Actualizació del registro de entrada"
 */
public class CU03Controller02 {
	private static CU03Controller02 instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU03Controller02 get() {
        if (instance == null){
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU03Controller02) App.setRoot("CU03View02", "AlChi: Actualización de precios para los productos de entrada");
        }    
        return instance;
    }
	
    private DTOCU03 tipoProducto = null;
    
	@FXML private ComboBox<DTOCU03> productos;
	
	@FXML private TextField p100;	
	@FXML private TextField p250;
	@FXML private TextField p500;
	@FXML private TextField p1000;
	@FXML private TextField p2000;	
	@FXML private TextField pUnidad;
	
	@FXML private TextField p100Actual;	
	@FXML private TextField p250Actual;		
	@FXML private TextField p500Actual;		
	@FXML private TextField p1000Actual;
	@FXML private TextField p2000Actual;
	@FXML private TextField pUnidadActual;
	
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
	
	@FXML private TableView<DTOCU03> tabla;
	
	@FXML private TableColumn<DTOCU03, Float> columnaProducto;	
	@FXML private TableColumn<DTOCU03, Float> columnaP100;	
	@FXML private TableColumn<DTOCU03, Float> columnaP250;
	@FXML private TableColumn<DTOCU03, Float> columnaP500;	
	@FXML private TableColumn<DTOCU03, Float> columnaP1000;
	@FXML private TableColumn<DTOCU03, Float> columnaP2000;	
	@FXML private TableColumn<DTOCU03, Float> columnaPUnidad;
    
	public CU03Controller02() { }
	
    @FXML private void initialize(){
    	setCombo();
    	iniciarTabla();
    	addListenerCampos();
    }
    
    private void setCombo() {
		// TODO CU03.2 crear lista unica de tipos productos	
	}

	private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay productos que mostrar."));
    	columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombreTipoProducto"));
    	columnaP100.setCellValueFactory(new PropertyValueFactory<>("precio100"));
    	columnaP250.setCellValueFactory(new PropertyValueFactory<>("precio250"));
    	columnaP500.setCellValueFactory(new PropertyValueFactory<>("precio500"));
    	columnaP1000.setCellValueFactory(new PropertyValueFactory<>("precio1000"));
    	columnaP2000.setCellValueFactory(new PropertyValueFactory<>("precio2000"));
    	columnaPUnidad.setCellValueFactory(new PropertyValueFactory<>("precioUnidad"));
    }
	
	@FXML private void btnConfirmarPrecios() {
		//TODO CU03.2 validar que si se clickeo en un check tenga un numero valido
	}
	
	@FXML private void btnEditarPrecios() {
		
	}
    
	@FXML private void btnFinalizar() {
    	//VEr que se hayan editado todos los precios
		
		if(! p100.getText().isBlank())
			//dto.setPrecio100(Float.valueOf(p100.getText()));
		if(! p250.getText().isBlank())
			//dto.setPrecio250(Float.valueOf(p250.getText()));		
		if(! p500.getText().isBlank())
			//dto.setPrecio500(Float.valueOf(p500.getText()));		
		if(! p1000.getText().isBlank())
			//dto.setPrecio1000(Float.valueOf(p1000.getText()));	
		if(! p2000.getText().isBlank())    		
			//dto.setPrecio2000(Float.valueOf(p2000.getText()));		
		if(! pUnidad.getText().isBlank());
			//dto.setPrecioUnidad(Float.valueOf(pUnidad.getText()));
    }

    @FXML private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
    	tituloAnterior = null;
    	sceneAnterior = null;
	}
    
    //TODO CU03.2 si el viejo es cero solo se toma el nuevo
	@FXML private void checkP100() {
		if(checkP100.isSelected()) {
    		p100.setDisable(false);
    		rd100.setDisable(false);
    		rd100Ant.setDisable(false);
    	}
    	else {
    		p100.setDisable(true);
    		rd100.setDisable(true);
    		rd100Ant.setDisable(true);
    		rd100.setSelected(true);
    	}
	}
	
	@FXML private void checkP250() {
		if(checkP250.isSelected()) {
    		p250.setDisable(false);
    		rd250.setDisable(false);
    		rd250Ant.setDisable(false);
    	}
    	else {
    		p250.setDisable(true);
    		rd250.setDisable(true);
    		rd250Ant.setDisable(true);
    		rd250.setSelected(true);
    	}
	}
	
	@FXML private void checkP500() {
		if(checkP500.isSelected()) {
    		p500.setDisable(false);
    		rd500.setDisable(false);
    		rd500Ant.setDisable(false);
    	}
    	else {
    		p500.setDisable(true);
    		rd500.setDisable(true);
    		rd500Ant.setDisable(true);
    		rd500.setSelected(true);
    	}
	}
	
	@FXML private void checkP1000() {
		if(checkP1000.isSelected()) {
    		p1000.setDisable(false);
    		rd1000.setDisable(false);
    		rd1000Ant.setDisable(false);
    	}
    	else {
    		p1000.setDisable(true);
    		rd1000.setDisable(true);
    		rd1000Ant.setDisable(true);
    		rd1000.setSelected(true);
    	}
	}
	
	@FXML private void checkP2000() {
		if(checkP2000.isSelected()) {
    		p2000.setDisable(false);
    		rd2000.setDisable(false);
    		rd2000Ant.setDisable(false);
    	}
    	else {
    		p2000.setDisable(true);
    		rd2000.setDisable(true);
    		rd2000Ant.setDisable(true);
    		rd2000.setSelected(true);
    	}
	}
	
	@FXML private void checkPUnidad() {
		if(checkPUnidad.isSelected()) {
    		pUnidad.setDisable(false);
    		rdUnidad.setDisable(false);
    		rdUnidadAnt.setDisable(false);
    	}
    	else {
    		pUnidad.setDisable(true);
    		rdUnidad.setDisable(true);
    		rdUnidadAnt.setDisable(true);
    		rdUnidad.setSelected(true);
    	}
	}
	
    private void addListenerCampos() {
    	p100.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p100.setText(p100.getText().replace(',', '.'));
            }
        }); 
    	
    	p250.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p250.setText(p250.getText().replace(',', '.'));
            }
        }); 
    	
    	p500.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p500.setText(p500.getText().replace(',', '.'));
            }
        }); 
    	
    	p1000.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p1000.setText(p1000.getText().replace(',', '.'));
            }
        }); 
    	
    	p2000.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	p2000.setText(p2000.getText().replace(',', '.'));
            }
        }); 
    	
    	pUnidad.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) { 
            	pUnidad.setText(pUnidad.getText().replace(',', '.'));
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

	@SuppressWarnings("exports")
	public void setListaProductos( List<DTOProductoInicial> listaProductos) {
		productos.getItems().clear();
		productos.getItems().addAll(GestorEntrada.get().getProductosActualizar(listaProductos));
		productos.getSelectionModel().selectFirst();
		tipoProducto = productos.getValue();;
		setearCampos();
	}
	
	@FXML private void setearCampos() {
		DTOCU03 dto = productos.getValue();
		if(dto != null) {
			if(! (tipoProducto.equals(dto))) {
				tipoProducto = dto;
				
				p100Anterior.setText(tipoProducto.getP100().toString());
				checkP100.setSelected(false);
				checkP100();
				
				p250Anterior.setText(tipoProducto.getP250().toString());
				checkP250.setSelected(false);
				checkP250();
				
				p500Anterior.setText(tipoProducto.getP500().toString());
				checkP500.setSelected(false);
				checkP500();
				
				p1000Anterior.setText(tipoProducto.getP1000().toString());
				checkP1000.setSelected(false);
				checkP1000();
				
				p2000Anterior.setText(tipoProducto.getP2000().toString());
				checkP2000.setSelected(false);
				checkP2000();
				
				pUnidadAnterior.setText(tipoProducto.getPUnidad().toString());
				checkPUnidad.setSelected(false);
				checkPUnidad();
			}	
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
}
