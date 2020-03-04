package app;

import java.util.List;
import java.util.Optional;
import dto.DTOProveedor;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 * Controller para la view de "Manejar proveedores"
 */
public class CU07Controller01 {	
	private static CU07Controller01 instance = null;

    public static CU07Controller01 get() {
        if (instance == null){ 
        	instance = (CU07Controller01) App.setRoot("CU07View01", "Administrar proveedores");
        }    
        return instance;
    }
	
	private DTOProveedor proveedorSeleccionado = null;
	
	@FXML private HBox botoneraPrincipal; 	
	@FXML private HBox botoneraAnadir; 	
	@FXML private HBox botoneraEditar; 
	
	@FXML private Button btnEditar;
	
	@FXML private TextField nombre;	
	@FXML private TextField telefono;
	
	@FXML private TableView<DTOProveedor> tabla;
	
	@FXML private TableColumn<DTOProveedor, String> columnaProveedor;	
	@FXML private TableColumn<DTOProveedor, String> columnaTelefono;
    
    public CU07Controller01() { }
	
    @FXML
    private void initialize(){
    	iniciarTabla();
    	cargarTabla();
    }
    
    private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay proveedores que mostrar."));
    	columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("numeroTelefono"));
    }
      
    private void cargarTabla() {	
    	List<DTOProveedor> lista = GestorProveedor.get().getProveedores();
    	tabla.getItems().clear(); 
    	tabla.getItems().addAll(lista);
    }
    
	@FXML private void btnEditar() {
		if(proveedorSeleccionado != null) {
			nombre.setText(proveedorSeleccionado.getNombre());
			telefono.setText(proveedorSeleccionado.getNumeroTelefono());
			tabla.setDisable(true);
			nombre.setDisable(false);
			telefono.setDisable(false);
			botoneraPrincipal.setDisable(true);
			botoneraPrincipal.setVisible(false);
			botoneraEditar.setDisable(false);
			botoneraEditar.setVisible(true);	
		}		
	}
	
	@FXML private void btnConfirmarEdicion() {
		String nombreP = nombre.getText(), telefonoP = telefono.getText();
		if(!nombreP.isBlank()) {
			
    		nombreP = nombreP.toLowerCase();
    		nombreP = nombreP.substring(0, 1).toUpperCase() + nombreP.substring(1);   
 
    		String cadena = "";
    		if(telefonoP.isBlank()) {
    			cadena = "Nombre proveedor: "+nombreP+".\n"
        				+ "Sin número teléfonico.\n";
    		}
    		else {
    			cadena = "Nombre proveedor: "+nombreP+".\n"
        				+ "Teléfono del proveedor: "+telefonoP+".\n";
    		}
    		
    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar edición de proveedor", 
    				"¿Desea confirmar los siguientes datos?", cadena);    		
    		
    		if (result.get() == ButtonType.OK){
    			DTOProveedor dto = new DTOProveedor();
    			dto.setId(proveedorSeleccionado.getId());
    			dto.setNombre(nombreP);
    			dto.setNumeroTelefono(telefonoP);
    			
    			if(GestorProveedor.get().editarProveedor(dto)) {
    				proveedorSeleccionado.setNombre(nombreP);
        			proveedorSeleccionado.setNumeroTelefono(telefonoP);
        			
        			PanelAlerta.getInformation("Confirmación", null, "El proveedor '"+nombreP+"' fue correctamente actualizado.");
                    
                    tabla.getItems().set(tabla.getSelectionModel().getSelectedIndex(), proveedorSeleccionado);
                    tabla.setDisable(false);     
            		nombre.setDisable(true);
            		nombre.setText("");
            		telefono.setText("");
            		telefono.setDisable(true);
            		botoneraPrincipal.setDisable(false);
            		botoneraPrincipal.setVisible(true);
            		botoneraEditar.setDisable(true);
            		botoneraEditar.setVisible(false);
    			}
    			
    		}    
		}
		else {
			String cadena = "";   
			if(proveedorSeleccionado == null) {
				cadena = "Debe seleccionar un proveedor a editar.";
			}
			else {
				App.setError(nombre);
				cadena = "El proveedor debe tener un nombre.";
			}
			PanelAlerta.getError("Aviso", null, cadena);
		}
	}
	
	@FXML private void btnAnadir() {
		nombre.setDisable(false);
		telefono.setDisable(false);
		nombre.clear();
		telefono.clear();
		tabla.setDisable(true);
		botoneraPrincipal.setDisable(true);
		botoneraPrincipal.setVisible(false);
		botoneraAnadir.setDisable(false);
		botoneraAnadir.setVisible(true);	
	}
	
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
    		
    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar adición de proveedor", 
    				"¿Desea confirmar los siguientes datos?", cadena);   
    		
    		
    		if (result.get() == ButtonType.OK){   
    			DTOProveedor dto = new DTOProveedor();
    			dto.setNombre(nombreP);
    			dto.setNumeroTelefono(telefonoP);
    			
    			if(GestorProveedor.get().agregarProveedor(dto)) {    				
    				PanelAlerta.getInformation("Confirmación", null, "El proveedor '"+nombreP+"' fue correctamente añadido.");
                    
                    tabla.getItems().add(dto);
                    
            		nombre.setDisable(true);
            		nombre.setText("");
            		telefono.setText("");
            		telefono.setDisable(true);
            		tabla.setDisable(false);
            		botoneraPrincipal.setDisable(false);
            		botoneraPrincipal.setVisible(true);
            		botoneraAnadir.setDisable(true);
            		botoneraAnadir.setVisible(false);
    			}
    		} 
		}
		else {
			App.setError(nombre);
			PanelAlerta.getError("Aviso", null, "El proveedor debe tener un nombre.");
		}
	}
	
	@FXML private void btnCancelar() {
		nombre.setDisable(true);
		telefono.setDisable(true);
		tabla.setDisable(false);
		botoneraPrincipal.setDisable(false);
		botoneraPrincipal.setVisible(true);
		botoneraAnadir.setDisable(true);
		botoneraAnadir.setVisible(false);	
	}	
	
	@FXML private void seleccionarProveedor() {
		proveedorSeleccionado = tabla.getSelectionModel().getSelectedItem();
		if(proveedorSeleccionado != null) {
			btnEditar.setDisable(false);
			nombre.setText(proveedorSeleccionado.getNombre());
			telefono.setText(proveedorSeleccionado.getNumeroTelefono());
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
