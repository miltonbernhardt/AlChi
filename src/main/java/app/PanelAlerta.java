package app;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class PanelAlerta {

	public static void getExcepcion(Exception excepcion, String tipoError){
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Excepción!");
    	alert.setHeaderText(null);
    	alert.setContentText(tipoError);
    	App.setStyle(alert.getDialogPane());
    	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
    	
    	String exceptionText = excepcion.toString();

    	Label label = new Label("La excepción fue:");

    	TextArea textArea = new TextArea(exceptionText);
    	textArea.setEditable(false);
    	textArea.setWrapText(true);

    	textArea.setMaxWidth(Double.MAX_VALUE);
    	textArea.setMaxHeight(Double.MAX_VALUE);
    	GridPane.setVgrow(textArea, Priority.ALWAYS);
    	GridPane.setHgrow(textArea, Priority.ALWAYS);
    	
    	GridPane expContent = new GridPane();
    	expContent.setMaxWidth(Double.MAX_VALUE);
    	expContent.add(label, 0, 0);
    	expContent.add(textArea, 0, 1);
    	alert.getDialogPane().setExpandableContent(expContent);
    	alert.showAndWait();       
	}
	
	public static void getError(String titulo, String header, String cadena) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(cadena);
		App.setStyle(alert.getDialogPane());
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
		alert.showAndWait();
	}
	
	@SuppressWarnings("exports")
	public static Optional<ButtonType> getConfirmation(String titulo, String header, String cadena) {
		Alert alert = new Alert(AlertType.CONFIRMATION);   
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(cadena);
		App.setStyle(alert.getDialogPane());
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
    	return alert.showAndWait();
	}
	
	public static void getInformation(String titulo, String header, String cadena) {
		Alert alert = new Alert(AlertType.INFORMATION);	
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(cadena);
		App.setStyle(alert.getDialogPane());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
            }
        });
        alert.showAndWait();  
	}
}
