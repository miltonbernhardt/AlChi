package app;

import java.io.File;
import java.io.IOException;

import enums.Categoria;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
	
	@FXML
	private ComboBox<Categoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextArea descripcion;
	
	@FXML
	private ImageView imagen;
	
	public CU01Controller(){ }
     
    @FXML
    private void initialize(){
    	setCombo();
    }
     
    @FXML
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().addAll(Categoria.values());
    }
    
    @FXML
    private void btnConfirmar(){
    	Boolean seleccionCombo = true, completoNombre = true, completoDescripcion = true, subioImagen = true;
    	
    	if(categoria.getValue() == null) {
    		seleccionCombo = false;
    	}
    	
    	/**
    	 * TODO CU01 ignorar espacios al princio y al nombre convertir todo a minus y luego la primera letra en mayus
    	 */

    	if(nombre.getText().isBlank()) {
    		completoNombre = false;
    	}
    	
    	if(descripcion.getText().isBlank()) {
    		completoNombre = false;
    	}
    	
    	
    	if(imagen.getImage() == null) {
    		subioImagen = false;
    	}
    	
    	/*
    	 * TODO CU01 guardar direccion directorio en tipoproducto y tratar de copiar la imagen en el directorio del proyecto
    	 */
    	
    	if(seleccionCombo && completoNombre && completoDescripcion && subioImagen) {
    		//TODO CU01 buscar tipos alertas
    		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Confirmar producto nuevo");
             alert.setHeaderText("¿Desea confirmar los datos ingresados?");
             alert.showAndWait();
    	}
    	else {
    		//TODO CU01 crear notifiacion error
    	}
    }
    
    @FXML
    private void btnAgregarImagen(ActionEvent actionEvent) throws java.io.IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Subir imágen");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif")); 
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
                String imagepath = file.toURI().toURL().toString();
                Image image = new Image(imagepath);
                imagen.setImage(image);
        }
       /*
       	TODO CU01 alerta
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Select a File");
        	//alert.setContentText("You didn't select a file!");
            alert.showAndWait();
        }
    	*/
    }
    
    @FXML
    private void btnVolver() throws IOException {
    	App.volver();
	}
}
