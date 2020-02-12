package app;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Optional;
import dto.DTOCategoria;
import gestor.Directorio;
import gestor.GestorProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.stage.StageStyle;

/**
 * Controller para la view de "Registro de un nuevo tipo de producto"
 */
public class CU01Controller {
	
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
	
	public CU01Controller(){ }
     
    @FXML
    private void initialize(){
    	setCombo();
    }
     
    @FXML
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorProductos.get().getDTOCategorias());
    	categoria.getSelectionModel().selectFirst();
    }
    
    @FXML
    private void btnConfirmar() throws IOException{
    	Boolean seleccionCombo = true, completoNombre = true, completoDescripcion = true;
    	String cadenaError = "Debe determinar los siguientes campos del producto:\n";
    	Integer nroCampo = 1;
    	
    	/**
    	 * TODO cambiar color al equivocarse
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
    		
    		/* TODO CU01 poner iconos a las alertas
    		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    		stage.getIcons().add(new Image(this.getClass().getResource("app/icon/logo.png").toString()));
    		*/   		
    		
    		alert.initStyle(StageStyle.UTILITY);
    		alert.setTitle("Confirmar producto nuevo");
    		alert.setHeaderText("¿Desea confirmar los siguientes datos ingresados?");
    		alert.setContentText("Categoría: "+categoria.getValue().toString()+"\n"
    				+ "Nombre del producto: "+nombreProducto+"\n"
    				+ "Descripción: "+descripcionProducto);
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){    			
    			if(GestorProductos.get().agregarTipoProducto(categoria.getValue().getId(), nombreProducto, descripcionProducto, imagenPath)) {
        			alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("'"+nombreProducto+"' correctamente guardado.");
                    alert.showAndWait();  
                    
                    App.volver();
    			}   			
    		}    		
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
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
    private void btnVolver() throws IOException {
    	App.volver();
	}
}
