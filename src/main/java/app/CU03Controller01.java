package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class CU03Controller01 {
    
    public void btnAgregarTipoProducto() {
    	
    }
    
    public void btnAgregarProveedor() {

    }
    
    public void btnAnadirProducto() {

    }
    
    public void btnEliminarFila() {

    }
    
    public void btnEditarFila() {

    }
    
    public void btnDuplicarFila() {
    }  
    
    @FXML
    private void btnConfirmarProductos() throws IOException {
        App.setRoot("CU03View02");
    }    
    
	public void btnVolver() throws IOException {
    	App.volver();
	}
	
}
