package app;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import dto.DTOProveedorCU07;
import gestor.GestorProveedor;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller para la view de "Manejar proveedores"
 */
public class CU07Controller {
	private static CU07Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU07Controller() { }

    public static CU07Controller get() {
        if (instance == null){ instance = new CU07Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU07View", "AlChi: Administrar proveedores");
	}
	
	private DTOProveedorCU07 proveedorSeleccionado = null;
	
	@FXML
	private HBox botoneraPrincipal; 
	
	@FXML
	private HBox botoneraAnadir; 
	
	@FXML
	private HBox botoneraEditar; 
	
	@FXML
	private Button btnEditar;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextField telefono;
	
	@FXML
	private TableView<DTOProveedorCU07> tabla;
	
	@FXML
	private TableColumn<DTOProveedorCU07, String> columnaProveedor;
	
	@FXML
	private TableColumn<DTOProveedorCU07, String> columnaTelefono;
    
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
    	List<DTOProveedorCU07> lista = GestorProveedor.get().getProveedores();
    	tabla.getItems().clear();    	
    	Iterator<DTOProveedorCU07> iteratorProveedores = lista.iterator();    	
    	while(iteratorProveedores.hasNext()) {
    		tabla.getItems().add(iteratorProveedores.next());	
    	}
    }
    
	@FXML
	private void btnEditar() {
		if(proveedorSeleccionado != null) {
			nombre.setText(proveedorSeleccionado.getNombre());
			telefono.setText(proveedorSeleccionado.getNumeroTelefono());
		}	
		
		nombre.setDisable(false);
		telefono.setDisable(false);
		botoneraPrincipal.setDisable(true);
		botoneraPrincipal.setVisible(false);
		botoneraEditar.setDisable(false);
		botoneraEditar.setVisible(true);		
	}
	
	@FXML
	private void btnConfirmarEdicion() {
		String nombreP = nombre.getText(), telefonoP = telefono.getText();
		if(!nombreP.isBlank()) {
			
    		nombreP = nombreP.toLowerCase();
    		nombreP = nombreP.substring(0, 1).toUpperCase() + nombreP.substring(1);
    		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Confirmar edición de proveedor");
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
    			proveedorSeleccionado.setNombre(nombreP);
    			proveedorSeleccionado.setNumeroTelefono(telefonoP);
    			
    			if(GestorProveedor.get().editarProveedor(proveedorSeleccionado)) {
    				alert = new Alert(AlertType.INFORMATION);    				
    				stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("El proveedor '"+nombreP+"' correctamente actualizado.");
                    alert.showAndWait();  
                    
                    tabla.getItems().set(tabla.getSelectionModel().getSelectedIndex(), proveedorSeleccionado);
                                        
            		nombre.setDisable(true);
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
			//TODO CU07 cambiar color al equivocarse
			if(proveedorSeleccionado == null) {
				cadena = "Debe seleccionar un proveedor a editar.";
			}
			else {
				cadena = "El proveedor debe tener un nombre.";
			}
			
			Alert alert = new Alert(AlertType.ERROR);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Aviso");
    		alert.setHeaderText(null);
    		alert.setContentText(cadena);
    		alert.showAndWait();
		}
	}
	
	@FXML
	private void btnCancelarEdicion() {
		nombre.setDisable(true);
		telefono.setDisable(true);
		botoneraPrincipal.setDisable(false);
		botoneraPrincipal.setVisible(true);
		botoneraEditar.setDisable(true);
		botoneraEditar.setVisible(false);	
	}
	
	@FXML
	private void btnAnadir() {
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
    			DTOProveedorCU07 dto = new DTOProveedorCU07();
    			dto.setNombre(nombreP);
    			dto.setNumeroTelefono(telefonoP);
    			
    			if(GestorProveedor.get().agregarProveedor(dto)) {
    				alert = new Alert(AlertType.INFORMATION);
    				stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("El proveedor '"+nombreP+"' correctamente añadido.");
                    alert.showAndWait();  
                    
                    tabla.getItems().add(dto);
                    
            		nombre.setDisable(true);
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
			//TODO CU07 cambiar color al equivocarse
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
	private void btnCancelarAdicion() {
		nombre.setDisable(true);
		telefono.setDisable(true);
		tabla.setDisable(false);
		botoneraPrincipal.setDisable(false);
		botoneraPrincipal.setVisible(true);
		botoneraAnadir.setDisable(true);
		botoneraAnadir.setVisible(false);	
	}	
	
	@FXML
	private void seleccionarCliente() {
		proveedorSeleccionado = tabla.getSelectionModel().getSelectedItem();
		if(proveedorSeleccionado != null) {
			btnEditar.setDisable(false);
			nombre.setText(proveedorSeleccionado.getNombre());
			telefono.setText(proveedorSeleccionado.getNumeroTelefono());
		}		
	}
	
	@FXML
	private void listenerTelefono() {
		//TODO CU07 implementar
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
