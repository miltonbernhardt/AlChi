package app;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import dto.DTOCategoria;
import gestor.GestorCategoria;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

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
	
	@FXML private HBox botoneraPrincipal; 	
	@FXML private HBox botoneraAnadir; 	
	@FXML private HBox botoneraEditar; 	
	@FXML private Button btnEditar;	
	@FXML private TextField nombre;	
	@FXML private TableView<DTOCategoria> tabla;	
	@FXML private TableColumn<DTOCategoria, String> columnaCategoria;

    public CU15Controller() { }
	
    @FXML private void initialize(){
    	iniciarTabla();
    	cargarTabla();
    }
    
    private void iniciarTabla() {
    	tabla.setPlaceholder(new Label("No hay categorías que mostrar."));
    	columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }
      
    private void cargarTabla() {	
    	List<DTOCategoria> lista = GestorCategoria.get().getCategorias();
    	tabla.getItems().clear();    	
    	Iterator<DTOCategoria> iteratorCategorias = lista.iterator();    	
    	while(iteratorCategorias.hasNext()) {
    		tabla.getItems().add(iteratorCategorias.next());	
    	}
    }
    
	@FXML private void btnEditar() {
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
	
	@FXML private void btnConfirmarEdicion() {
		String nombreC = nombre.getText();
		if(!nombreC.isBlank()) {
			
    		nombreC = nombreC.toLowerCase();
    		nombreC = nombreC.substring(0, 1).toUpperCase() + nombreC.substring(1);
    		
    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar edición de proveedor", "¿Desea confirmar los siguientes datos?", "Nombre categoría: "+nombreC+".\n");
    		if (result.get() == ButtonType.OK){   
    			if(GestorCategoria.get().editarCategoria(categoriaSeleccionada)) {
    				categoriaSeleccionada.setNombre(nombreC);
    				
    				PanelAlerta.getInformation("Confirmación", null, "La categoría '"+nombreC+"' fue correctamente actualizado.");
                    
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
			if(categoriaSeleccionada == null) {
				cadena = "Debe seleccionar una categoría a editar.";
			}
			else {
				App.setError(nombre); 
				cadena = "La categoría debe tener un nombre.";
			}			
			PanelAlerta.getError("Aviso", null, cadena);
		}
	}
	
	@FXML private void btnAnadir() {
		nombre.clear();
		nombre.setDisable(false);
		tabla.setDisable(true);
		botoneraPrincipal.setDisable(true);
		botoneraPrincipal.setVisible(false);
		botoneraAnadir.setDisable(false);
		botoneraAnadir.setVisible(true);	
	}
	
	@FXML private void btnConfirmarAdicion() {
		String nombreC = nombre.getText();
		if(!nombreC.isBlank()) {
			
    		nombreC = nombreC.toLowerCase();
    		nombreC = nombreC.substring(0, 1).toUpperCase() + nombreC.substring(1);
			
    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar adición de categoría", "¿Desea confirmar los siguientes datos?", "Nombre categoría: "+nombreC+".\n");
    		if (result.get() == ButtonType.OK){   
    			DTOCategoria dto = new DTOCategoria();
    			dto.setNombre(nombreC);
    			
    			if(GestorCategoria.get().agregarCategoría(dto)) {
    				PanelAlerta.getInformation("Confirmación", null, "La categoría '"+nombreC+"' fue correctamente añadida.");
                    
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
			App.setError(nombre); 
			PanelAlerta.getError("Aviso", null, "La categoría debe tener un nombre.");
		}
	}
	
	@FXML private void btnCancelar() {
		nombre.setDisable(true);
		tabla.setDisable(false);
		botoneraPrincipal.setDisable(false);
		botoneraPrincipal.setVisible(true);
		botoneraAnadir.setDisable(true);
		botoneraAnadir.setVisible(false);	
		botoneraEditar.setDisable(true);
		botoneraEditar.setVisible(false);	
	}	
	
	@FXML private void seleccionarCategoria() {
		categoriaSeleccionada = tabla.getSelectionModel().getSelectedItem();
		if(categoriaSeleccionada != null) {
			btnEditar.setDisable(false);
			nombre.setText(categoriaSeleccionada.getNombre());
		}		
	}
	
    @FXML private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	sceneAnterior = null;
    	tituloAnterior = null;
    	instance = null;
	}
    
    @FXML private void setValido() {
    	App.setValido(nombre);
    }
}
