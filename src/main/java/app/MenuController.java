package app;

import javafx.fxml.FXML;

public class MenuController  {
	
    @FXML
    private void registrarNuevoProducto() {
        CU01Controller.get();
    }
	
    @FXML
    private void buscarProductos() {
		CU02Controller01.get();
    }

    @FXML
    private void registrarEntradaProductos() {
        CU03Controller01.get();
    } 
    
    @FXML
    private void registrarVentasProductos() {
		CU04Controller.get();
    } 
        
    @FXML
    private void administrarProveedores() {
		CU07Controller01.get();
    }  
    
    @FXML
    private void buscarStockProductos() {
		CU08Controller.get();
    }   

    @FXML
    private void registrarEmpaquetamientos() {
		CU10Controller01.get();
    }
    
    @FXML
    private void buscarCombos() {
		CU14Controller.get();
    } 
        
    @FXML
    private void administrarCategorias() {
		CU15Controller.get();
    }  
    
    @FXML
    private void buscarProductosEmpaquetados() {
		CU16Controller.get();
    }  
    
    @FXML
    private void salir() {
    	App.salir();
    }  
    
    
    @FXML private void  informeGananciasPerdidas() {
    	//TODO CU11 implementar 
    }
    
    @FXML private void  informeStockDisponible() {
    	//TODO CU11 implementar 
    }
    
    @FXML private void  generarPlanilaProductos() {
    	//TODO CU11 implementar 
    }
    
    @FXML private void  generarImagenesRedesSociales() {
    	//TODO CU11 implementar    	
    }
	
}
