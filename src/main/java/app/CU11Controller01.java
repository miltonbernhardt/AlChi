package app;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

/*
 * Controller para la view "Exportar archivos"
 */
public class CU11Controller01 {	
	private static CU11Controller01 instance = null;
	
    public CU11Controller01() { }

    public static CU11Controller01 get() {
        if (instance == null){ 
        	App.setViewAnterior();	
        	instance = (CU11Controller01) App.setRoot("CU11View01", "AlChi: Exportar archivos");
        }    
        return instance;
    }
    
    @FXML private RadioButton treintaDias;
    @FXML private RadioButton seisMeses;
    
    @FXML  private void initialize(){
    	
    }
    
    @FXML private void  informeGananciasPerdidas() {
    	
    }
    
    @FXML private void  informeStockDisponible() {
    	
    }
    
    @FXML private void  generarPlanilaProductos() {
    	
    }
    
    @FXML private void  generarImagenesRedesSociales() {
    	//CU11Controller02.get();
    	//TODO CU11 implementar    	
    }
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
