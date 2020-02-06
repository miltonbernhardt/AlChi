package app;

import java.io.IOException;

import javafx.fxml.FXML;

public class CU05Controller {
	
	 @FXML
	public void btnBuscarTipoProducto() throws IOException{
		 App.setRoot("CU02View");    	
    }

    public void btnCambiarImagen() {
    	
    }
    
    public void btnActualizar() {
    	
    }
    
	public void btnVolver() throws IOException {
    	App.volver();
	}
	
}
