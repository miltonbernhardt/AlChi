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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
	private static CU01Controller instance = null;
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
    public CU01Controller() { }

    public static CU01Controller get() {
        if (instance == null){ instance = new CU01Controller(); }    
        return instance;
    }
	
	public void setView() {
		sceneAnterior = App.getSceneAnterior();
		tituloAnterior = App.getTituloAnterior();
		App.setRoot("CU01View", "AlChi: Registrar nuevo producto");
	}
	
	@FXML
	private Button btnQuitarImagen;
	
	@FXML
	private ComboBox<DTOCategoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextArea descripcion;
	
	@FXML
	private ImageView imagen;
	
	private URI imagenPath;	
     
    @FXML
    private void initialize(){
    	setCombo();
    }
     
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorCategoria.get().getDTOCategorias());
    	categoria.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void btnConfirmar() {
    	Boolean seleccionCombo = true, completoNombre = true, completoDescripcion = true;
    	String cadenaError = "Debe determinar los siguientes campos del producto:\n";
    	Integer nroCampo = 1;
    	
    	/**
    	 * TODO CU01 cambiar color al equivocarse
    	 */
    	if(categoria.getValue().getId() == null) {
    		cadenaError += nroCampo.toString()+") Categoría.\n";
    		nroCampo++;
    		seleccionCombo = false;
    	}

    	if(nombre.getText().isBlank()) {
    		cadenaError += nroCampo.toString()+") Nombre.\n";
    		nroCampo++;
    		completoNombre = false;
    	}
    	
    	if(descripcion.getText().isBlank()) {
    		completoDescripcion = false;
    	}    
    	
    	if(seleccionCombo && completoNombre) {
    		String nombreProducto = nombre.getText().toLowerCase(), 
    			   descripcionProducto = descripcion.getText().toLowerCase();
    		
    		nombreProducto = nombreProducto.substring(0, 1).toUpperCase() + nombreProducto.substring(1);
    		
    		if(completoDescripcion) {
        		descripcionProducto = descripcionProducto.substring(0, 1).toUpperCase() + descripcionProducto.substring(1);
    		}
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);    
        	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Confirmar producto nuevo");
    		alert.setHeaderText("¿Desea confirmar los siguientes datos ingresados?");
    		alert.setContentText("Categoría: "+categoria.getValue().toString()+"\n"
    				+ "Nombre del producto: "+nombreProducto+"\n"
    				+ "Descripción: "+descripcionProducto);

    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			DTOTipoProductoCU05 dto = new DTOTipoProductoCU05();
    			dto.setIdCategoria(categoria.getValue().getId());
    			dto.setNombreTipoProducto(nombreProducto);
    			dto.setDescripcion(descripcionProducto);
    			
    			if(imagenPath == null) {
    				dto.setDirectorioImagen("");
    			}
    			else {
    				dto.setDirectorioImagen(imagenPath.toString());
    			}
    			
    			if(GestorProductos.get().agregarTipoProducto(dto)) {
        			alert = new Alert(AlertType.INFORMATION);
        			stage = (Stage) alert.getDialogPane().getScene().getWindow();
                	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("El producto '"+nombreProducto+"' fue correctamente guardado.");
                    alert.showAndWait();  
                    
                    volver();
    			}
    		}    		
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    		alert.setTitle("Aviso");
    		alert.setHeaderText(null);
    		alert.setContentText(cadenaError);
    		alert.showAndWait();
    	}
    }
    
    @FXML
    private void btnAgregarImagen(ActionEvent actionEvent) throws java.io.IOException {
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
    
    @FXML
    private void btnQuitarImagen() {
    	imagen.setImage(null);
    	imagenPath = null;
    	btnQuitarImagen.setDisable(true);
    }
    
    @FXML
    private void volver() {
    	App.setRoot(sceneAnterior, tituloAnterior); 
    	instance = null;
	}
}
