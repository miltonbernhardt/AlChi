package app;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import dto.DTOCategoria;
import gestor.GestorCategoria;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller para la view de "Administrar categorías"
 */
public class CU15Controller {	
	
	private static CU15Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;

    public static CU15Controller get() {
        if (instance == null){
        	sceneAnterior = App.getSceneAnterior();
    		tituloAnterior = App.getTituloAnterior();
        	instance = (CU15Controller) App.setRoot("CU15View", "AlChi: Administrar categorías"); 
        }    
        return instance;
    }
	
	private DTOCategoria categoriaSeleccionada = null;
	
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
	private TableView<DTOCategoria> tabla;
	
	@FXML
	private TableColumn<DTOCategoria, String> columnaCategoria;

    public CU15Controller() { }
	
    @FXML
    private void initialize(){
    	iniciarTabla();
    	cargarTabla();
    }
    
    private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay categorías que mostrar."));
    	columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }
      
    private void cargarTabla() {	
    	//CAMBIAR
    	List<DTOCategoria> lista = GestorCategoria.get().getCategorias();
    	tabla.getItems().clear();    	
    	Iterator<DTOCategoria> iteratorCategorias = lista.iterator();    	
    	while(iteratorCategorias.hasNext()) {
    		tabla.getItems().add(iteratorCategorias.next());	
    	}
    }
    
	@FXML
	private void btnEditar() {
		if(categoriaSeleccionada != null) {
			nombre.setText(categoriaSeleccionada.getNombre());
			nombre.setDisable(false);
			tabla.setDisable(true);
			botoneraPrincipal.setDisable(true);
			botoneraPrincipal.setVisible(false);
			botoneraEditar.setDisable(false);
			botoneraEditar.setVisible(true);	
		}					
	}
	
	@FXML
	private void btnConfirmarEdicion() {
		String nombreC = nombre.getText();
		if(!nombreC.isBlank()) {
			
    		nombreC = nombreC.toLowerCase();
    		nombreC = nombreC.substring(0, 1).toUpperCase() + nombreC.substring(1);
    		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Confirmar edición de proveedor");
    		alert.setHeaderText("¿Desea confirmar los siguientes datos?");
    		alert.setContentText("Nombre categoría: "+nombreC+".\n");
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){   
    			if(GestorCategoria.get().editarCategoria(categoriaSeleccionada)) {
    				categoriaSeleccionada.setNombre(nombreC);
    				alert = new Alert(AlertType.INFORMATION);    				
    				stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("La categoría '"+nombreC+"' fue correctamente actualizado.");
                    alert.showAndWait();  
                    
                    tabla.getItems().set(tabla.getSelectionModel().getSelectedIndex(), categoriaSeleccionada);
                    tabla.setDisable(false);
            		nombre.setDisable(true);
            		botoneraPrincipal.setDisable(false);
            		botoneraPrincipal.setVisible(true);
            		botoneraEditar.setDisable(true);
            		botoneraEditar.setVisible(false);
    			}
    			
    		}    
		}
		else {
			String cadena = "";
			//TODO ZZZ cambiar color al equivocarse       
			if(categoriaSeleccionada == null) {
				cadena = "Debe seleccionar una categoría a editar.";
			}
			else {
				cadena = "La categoría debe tener un nombre.";
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
	private void btnAnadir() {
		nombre.clear();
		nombre.setDisable(false);
		tabla.setDisable(true);
		botoneraPrincipal.setDisable(true);
		botoneraPrincipal.setVisible(false);
		botoneraAnadir.setDisable(false);
		botoneraAnadir.setVisible(true);	
	}
	
	@FXML
	private void btnConfirmarAdicion() {
		String nombreC = nombre.getText();
		if(!nombreC.isBlank()) {
			
    		nombreC = nombreC.toLowerCase();
    		nombreC = nombreC.substring(0, 1).toUpperCase() + nombreC.substring(1);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Confirmar adición de categoría");
    		alert.setHeaderText("¿Desea confirmar los siguientes datos?");
    		alert.setContentText("Nombre categoría: "+nombreC+".\n"); 		
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){   
    			DTOCategoria dto = new DTOCategoria();
    			dto.setNombre(nombreC);
    			
    			if(GestorCategoria.get().agregarCategoría(dto)) {
    				alert = new Alert(AlertType.INFORMATION);
    				stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("La categoría '"+nombreC+"' fue correctamente añadida.");
                    alert.showAndWait();  
                    
                    tabla.getItems().add(dto);
                    
            		nombre.setDisable(true);
            		tabla.setDisable(false);
            		botoneraPrincipal.setDisable(false);
            		botoneraPrincipal.setVisible(true);
            		botoneraAnadir.setDisable(true);
            		botoneraAnadir.setVisible(false);
    			}
    		} 
		}
		else {
			//TODO ZZZ cambiar color al equivocarse       
			Alert alert = new Alert(AlertType.ERROR);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Aviso");
    		alert.setHeaderText(null);
    		alert.setContentText("La categoría debe tener un nombre.");
    		alert.showAndWait();
		}
	}
	
	@FXML
	private void btnCancelar() {
		nombre.setDisable(true);
		tabla.setDisable(false);
		botoneraPrincipal.setDisable(false);
		botoneraPrincipal.setVisible(true);
		botoneraAnadir.setDisable(true);
		botoneraAnadir.setVisible(false);	
	}	
	
	@FXML
	private void seleccionarCategoria() {
		categoriaSeleccionada = tabla.getSelectionModel().getSelectedItem();
		if(categoriaSeleccionada != null) {
			btnEditar.setDisable(false);
			nombre.setText(categoriaSeleccionada.getNombre());
		}		
	}
	
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	sceneAnterior = null;
    	tituloAnterior = null;
    	instance = null;
	}
    
    @FXML
    private void onKeyPressed(KeyEvent event) {
    	//TODO CU15 manejar bien los cancelar
        App.onKeyPressed(event);
    }
}
