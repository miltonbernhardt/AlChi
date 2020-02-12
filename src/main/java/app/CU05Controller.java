package app;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Optional;
import dto.DTOCategoria;
import dto.DTOTipoProductoCU05;
import gestor.Directorio;
import gestor.GestorProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controller para la view de "Actualización de características de tipos de productos"
 */
public class CU05Controller {
	
	private DTOTipoProductoCU05 dto = null;
	
	@FXML
	private Button btnQuitarImagen;
	
	@FXML
	private Button btnCambiarImagen;
	
	@FXML	
	private Button btnActualizar;
	
	@FXML
	private ComboBox<DTOCategoria> categoria;
	
	@FXML
	private TextField nombre;
	
	@FXML
	private TextArea descripcion;
	
	@FXML
	private ImageView imagen;
	
	private URI imagenPath = null;
	
	public CU05Controller(){ }
     
    @FXML
    private void initialize(){
    	setCombo();
    }

    @FXML
    private void setCombo(){
    	categoria.getItems().clear();
    	categoria.getItems().add(new DTOCategoria(null, "Seleccionar categoría"));
    	categoria.getItems().addAll(GestorProductos.get().getDTOCategorias());    	
    }
    
    
	@FXML
	public void btnBuscarTipoProducto() throws IOException{
	  	/*
    	 //TODO CU02 crear buscar tipo producto
    	 App.setRoot("CU02View");
    	*/
		
		categoria.setDisable(false);
		nombre.setDisable(false);
		descripcion.setDisable(false);
		btnCambiarImagen.setDisable(false);
		btnActualizar.setDisable(false);
		
		dto = GestorProductos.get().getTipoProducto();
		categoria.getSelectionModel().clearSelection();
		categoria.getSelectionModel().select(new DTOCategoria(dto.getIdCategoria(), dto.getNombreCategoria()));
    	
    	nombre.setText(dto.getNombreTipoProducto());
    	descripcion.setText(dto.getDescripcion());
    	if(!dto.getDirectorioImagen().isEmpty()) {
    		imagenPath = URI.create(dto.getDirectorioImagen());
    		btnQuitarImagen.setDisable(false);
    		Image image = new Image(imagenPath.toURL().toString());
    		imagen.setImage(image);
    	}
    }
    
    @FXML
    private void btnActualizar() throws IOException{
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
    		alert.initStyle(StageStyle.UTILITY);
    		alert.setTitle("Confirmar producto nuevo");
    		alert.setHeaderText("¿Desea confirmar los siguientes datos ingresados?");
    		alert.setContentText("Categoría: "+categoria.getValue().toString()+"\n"
    				+ "Nombre del producto: "+nombreProducto+"\n"
    				+ "Descripción: "+descripcionProducto);
    		
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){    			
    			if(GestorProductos.get().updateTipoProducto(categoria.getValue().getId(), dto.getIdProducto(), nombreProducto, descripcionProducto, imagenPath)) {
        			alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText(null);
                    alert.setContentText("'"+nombreProducto+"' correctamente actualizado.");
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
    private void btnCambiarImagen(ActionEvent actionEvent) throws java.io.IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Subir imágen");
        chooser.setInitialDirectory(new File(Directorio.get().getDirectorio()));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif", ".*jpeg")); 
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
