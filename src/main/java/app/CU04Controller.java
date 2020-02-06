package app;

import java.io.IOException;

import javafx.fxml.FXML;

public class CU04Controller {
    
	public void btnVolver() throws IOException {
    	App.volver();
	}
	
	public void btnAnadirVenta() {
		
	}
	
	@FXML
	public void btnAnadirCombo() throws IOException {
        App.setRoot("CU06View");
	}
	
	
	public void btnEliminarVenta() {
		
	}
	
	public void btnDuplicarVenta() {
		
	}
	
	public void btnEditarVenta() {
		
	}
	
	public void btnConfirmarVenta() {
		
	}
	
}
