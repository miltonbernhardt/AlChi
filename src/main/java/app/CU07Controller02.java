package app;

import java.util.Optional;
import dto.DTOProveedor;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CU07Controller02 {
	private static CU07Controller02 instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
	public static CU07Controller02 get() {
        if (instance == null){
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU07Controller02) App.setRoot("CU07View02", "AlChi: Agregar proveedor");; 
        }    
        return instance;
    }
			
	@FXML
	private TextField nombre;
	
	@FXML
	private TextField telefono;
	
    public CU07Controller02() { }
	
    @FXML
    private void initialize(){ }
	
	@FXML
	private void btnConfirmarAdicion() {
		String nombreP = nombre.getText(), telefonoP = telefono.getText();
		if(!nombreP.isBlank()) {
			
    		nombreP = nombreP.toLowerCase();
    		nombreP = nombreP.substring(0, 1).toUpperCase() + nombreP.substring(1);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Confirmar adición de proveedor");
    		alert.setHeaderText("¿Desea confirmar los siguientes datos?");
    		
    		if(telefonoP.isBlank()) {
    			alert.setContentText("Nombre proveedor: "+nombreP+"\n"
        				+ "Sin número teléfonico\n");
    		}
    		else {
    			alert.setContentText("Nombre proveedor: "+nombreP+"\n"
        				+ "Teléfono del proveedor: "+telefonoP+"\n");
    		}    		
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){   
    			DTOProveedor dto = new DTOProveedor();
    			dto.setNombre(nombreP);
    			dto.setNumeroTelefono(telefonoP);
    			
    			if(GestorProveedor.get().agregarProveedor(dto)) {
    				alert = new Alert(AlertType.INFORMATION);
    				stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("El proveedor '"+nombreP+"' fue correctamente añadido.");
                    alert.showAndWait();  
                    
                    CU03Controller01.get().setearProveedor(dto);
                    volver();
                    
    			}
    		} 
		}
		else {
			//TODO CU07-02 cambiar color al equivocarse
			Alert alert = new Alert(AlertType.ERROR);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Aviso");
    		alert.setHeaderText(null);
    		alert.setContentText("El proveedor debe tener un nombre.");
    		alert.showAndWait();
		}
	}
	
	@FXML
	private void listenerTelefono() {
		//TODO CU07 implementar
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	sceneAnterior = null;
    	tituloAnterior = null;
    	instance = null;
	}
}
