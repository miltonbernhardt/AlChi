package app;

import java.util.Optional;
import dto.DTOProveedor;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class CU07Controller02 {
	private static CU07Controller02 instance = null;
	
	public static CU07Controller02 get() {
        if (instance == null){
        	App.setViewAnterior();	
        	instance = (CU07Controller02) App.setRoot("CU07View02", "AlChi: Agregar proveedor");; 
        }    
        return instance;
    }
			
	@FXML private TextField nombre;	
	@FXML private TextField telefono;
	
    public CU07Controller02() { }
	
    @FXML  private void initialize(){ }
	
	@FXML private void btnConfirmarAdicion() {
		String nombreP = nombre.getText(), telefonoP = telefono.getText();
		if(!nombreP.isBlank()) {
			
    		nombreP = nombreP.toLowerCase();
    		nombreP = nombreP.substring(0, 1).toUpperCase() + nombreP.substring(1);
			
    		

    		String cadena = "";
    		if(telefonoP.isBlank()) {
    			cadena = "Nombre proveedor: "+nombreP+"\n"
        				+ "Sin número teléfonico\n";
    		}
    		else {
    			cadena = "Nombre proveedor: "+nombreP+"\n"
        				+ "Teléfono del proveedor: "+telefonoP+"\n";
    		}    		
    		
    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar adición de proveedor", "¿Desea confirmar los siguientes datos?", cadena);
    		if (result.get() == ButtonType.OK){   
    			DTOProveedor dto = new DTOProveedor();
    			dto.setNombre(nombreP);
    			dto.setNumeroTelefono(telefonoP);
    			
    			if(GestorProveedor.get().agregarProveedor(dto)) {
    				PanelAlerta.getInformation("Confirmación", null, "El proveedor '"+nombreP+"' fue correctamente añadido.");                    
                    CU03Controller01.get().setearProveedor(dto);
                    volver();                    
    			}
    		} 
		}
		else {
			App.setError(nombre);
			PanelAlerta.getError("Aviso", null, "El proveedor debe tener un nombre.");
		}
	}
	
	@FXML private void nombreValido() {
		App.setValido(nombre);
	}
	
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
}
