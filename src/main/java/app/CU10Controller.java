package app;

import entity.TipoProducto;
import enums.TipoPaquete;
import entity.ProductoEmpaquetado;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * Controller para la view de "Registrar empaquetamientos"
 */
public class CU10Controller {
	private static CU10Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU10Controller() { }

    public static CU10Controller get() {
        if (instance == null){ instance = new CU10Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU10View", "AlChi: Registrar empaquetamientos");
	}
	
	@FXML
	private ComboBox<TipoProducto> tipoProducto;
	
	@FXML
	private ComboBox<TipoPaquete> tipoPaquete;
	
	@FXML
	private ComboBox<Integer> cantidad;
	
	@FXML
	private TableView<ProductoEmpaquetado> tabla; //Corregir
    
    @FXML
    private void initialize(){
    	
    }
    
	@FXML
    private void btnAnadirEmpaquetamiento() {
		
	}
		
	@FXML
    private void btnEliminar() {
		
	}
	
	@FXML
    private void btnEditar() {
		
	}
	
	@FXML
    private void btnConfirmarEmpaquetamiento() {
		
	}

    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
