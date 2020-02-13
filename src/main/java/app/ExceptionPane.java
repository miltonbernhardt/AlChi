package app;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ExceptionPane {

	public ExceptionPane(Exception excepcion, String tipoError){
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Excepción!");
    	alert.setHeaderText(null);
    	alert.setContentText(tipoError);
    	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    	stage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	
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
}
