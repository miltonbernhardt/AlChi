package app;

import java.io.IOException;
import javafx.fxml.FXML;

public class CU03Controller01 {
    
    public void btnAgregarTipoProducto() {
    	System.out.println("AGREGAR TIPO PRODUCTO\n");
    }
    
    public void btnAgregarProveedor() {
    	System.out.println("PROVEEDOR\n");
    }
    
    public void btnAnadirProducto() {
    	System.out.println("AÑADIR PRODUCTO\n");
    }
    
    public void btnEliminarFila() {
    	System.out.println("ELIMINAR FILA\n");
    }
    
    public void btnEditarFila() {
    	System.out.println("EDITAR FILA\n");
    }
    
    public void btnDuplicarFila() {
    	System.out.println("DUPLICAR FILA\n");
    }  
    
    @FXML
    private void btnConfirmarProductos() throws IOException {
        App.setRoot("CU03View02");
    }    
    
    @FXML
	public void btnVolver() throws IOException {
		App.setRoot("menu");
	}

	
}
