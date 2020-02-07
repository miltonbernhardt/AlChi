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
 * Controller para la view de "Actualización de características de tipos de productos"
 */
public class CU05Controller {
	
	@FXML
	private ComboBox<Categoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextArea descripcion;
	
	@FXML
	private ImageView imagen;
	
	public CU05Controller(){ }
     
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
    private void btnActualizar(){
    	Boolean seleccionCombo = true, completoNombre = true, completoDescripcion = true, subioImagen = true;
    	
    	if(categoria.getValue() == null) {
    		seleccionCombo = false;
    	}
    	
    	/**
    	 * TODO CU05 ignorar espacios al princio y al nombre convertir todo a minus y luego la primera letra en mayus
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
    	 * TODO CU05 guardar imagen en TipoProducto
    	 */
    	
    	if(seleccionCombo && completoNombre && completoDescripcion && subioImagen) {
    		//TODO CU01 buscar tipos alertas
    		 Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Confirmar producto nuevo");
             alert.setHeaderText("¿Desea confirmar los datos ingresados?");
             alert.showAndWait();
    	}
    	else {
    		//TODO CU05 crear notifiacion error
    	}
    }
    
    @FXML
    private void btnCambiarImagen(ActionEvent actionEvent) throws java.io.IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Cambiar imágen");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif")); 
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
                String imagepath = file.toURI().toURL().toString();
                Image image = new Image(imagepath);
                imagen.setImage(image);
        }
    }
	
	@FXML
	public void btnBuscarTipoProducto() throws IOException{
		 App.setRoot("CU02View");    	
    }

	
}
