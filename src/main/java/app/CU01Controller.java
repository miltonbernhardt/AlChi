package app;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Optional;
import dto.DTOCategoria;
import dto.DTOTipoProductoCU05;
import gestor.Directorio;
import gestor.GestorCategoria;
import gestor.GestorProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller para la view de "Registro de un nuevo tipo de producto"
 */
public class CU01Controller {
	private CU03Controller01 controllerCu03 = null;
	public CU03Controller01 getControllerCu03() { return controllerCu03; }
	public void setControllerCu03(CU03Controller01 controllerCu03) { this.controllerCu03 = controllerCu03; }
	
	private static CU01Controller instance = null;
	
	public static CU01Controller get() {
        if (instance == null){
        	App.setViewAnterior();	
        	instance = (CU01Controller) App.setRoot("CU01View", "Registrar nuevo producto"); 
        }    
        return instance;
    }
		
	@FXML private Button btnQuitarImagen;	
	@FXML private ComboBox<DTOCategoria> categoria;	
	@FXML private TextField nombre;	
	@FXML private TextArea descripcion;	
	@FXML private ImageView imagen;	
	private URI imagenPath;	
     
    public CU01Controller() {}
	
    @FXML private void initialize(){
    	setCombo();
    }
     
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorCategoria.get().getCategorias());
    	categoria.getSelectionModel().selectFirst();
    }
    
    @FXML private void btnConfirmar() {
    	Boolean seleccionCombo = true, completoNombre = true, completoDescripcion = true;
    	String cadenaError = "Debe determinar los siguientes campos del producto:\n";
    	Integer nroCampo = 1;

    	if(categoria.getValue().getId() == null) {
    		cadenaError += nroCampo.toString()+") Categoría.\n";
    		nroCampo++;
    		seleccionCombo = false;
    		App.setError(categoria);
    	}

    	if(nombre.getText().isBlank()) {
    		cadenaError += nroCampo.toString()+") Nombre.\n";
    		nroCampo++;
    		completoNombre = false;
    		App.setError(nombre);
    	}
    	
    	if(descripcion.getText().isBlank()) {
    		completoDescripcion = false;
    	}    
    	
    	if(seleccionCombo && completoNombre) {
    		String nombreProducto = nombre.getText().toLowerCase(), 
    			   descripcionProducto = descripcion.getText().toLowerCase();
    		
    		nombreProducto = nombreProducto.substring(0, 1).toUpperCase() + nombreProducto.substring(1);
    		
    		String cadena = "Categoría: "+categoria.getValue().toString()+"\nNombre del producto: "+nombreProducto;
    		
    		if(completoDescripcion) {
        		descripcionProducto = descripcionProducto.substring(0, 1).toUpperCase() + descripcionProducto.substring(1);
        		cadena = cadena+"\nDescripción: "+descripcionProducto;
    		}
    		
    		
    		
    		Optional<ButtonType> result = PanelAlerta.getConfirmation("Confirmar producto nuevo", "¿Desea confirmar los siguientes datos ingresados?", cadena); 
    				
    		if (result.get() == ButtonType.OK){
    			DTOTipoProductoCU05 dto = new DTOTipoProductoCU05();
    			dto.setIdCategoria(categoria.getValue().getId());
    			dto.setNombreTipoProducto(nombreProducto);
    			dto.setDescripcion(descripcionProducto);
    			
    			if(imagenPath == null)
    				dto.setDirectorioImagen("");
    			else
    				dto.setDirectorioImagen(imagenPath.toString());

    			if(GestorProductos.get().agregarTipoProducto(dto)) {
    				if(controllerCu03 != null) {
    					controllerCu03.setearTipoProducto(GestorProductos.get().getTipoProductoCU02(dto.getIdProducto()));
    					volver();
    				}
    				else {
    					Optional<ButtonType> result2 = PanelAlerta.getConfirmation("Confirmación", "El producto '"+nombreProducto+"' fue correctamente guardado.",
        						"¿Desea registrar un nuevo producto?");
        				
        				if (result2.get() == ButtonType.OK){
        					categoria.getSelectionModel().selectFirst();
        					nombre.setText("");
        					descripcion.setText("");
        					btnQuitarImagen();
        				}
        				else {
        					volver();
        				}
    				}                    
    			}
    		}    		
    	}
    	else {
    		PanelAlerta.getError("Aviso", null, cadenaError);
    	}
    }
    
    @FXML private void btnAgregarImagen(ActionEvent actionEvent) throws java.io.IOException {
    	FileChooser chooser = new FileChooser();
        chooser.setTitle("Subir imágen");
        chooser.setInitialDirectory(new File(Directorio.get().getDirectorio()));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = chooser.showOpenDialog(new Stage());        
        if(file != null) {        	
        	imagenPath = file.toURI();
            Image image = new Image(imagenPath.toURL().toString());
            imagen.setImage(image);
            btnQuitarImagen.setDisable(false);
            Directorio.get().setDirectorio(Paths.get(imagenPath).toString().substring(0,Paths.get(imagenPath).toString().lastIndexOf("\\")));	
        }
    }
    
    @FXML private void btnQuitarImagen() {
    	imagen.setImage(null);
    	imagenPath = null;
    	btnQuitarImagen.setDisable(true);
    }
    
    @FXML private void volver() {
    	App.getViewAnterior();
    	instance = null;
	}
    
    @FXML private void setCategoriaValida() {
    	if(categoria.getValue().getId() != null) {
    		App.setValido(categoria);
    	}
    }
    
    @FXML private void setNombreValido() {
    	App.setValido(nombre);
    }
}
