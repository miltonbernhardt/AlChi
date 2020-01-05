package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    @SuppressWarnings("exports")
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("CU03View01"));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("CU03");
     
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        //System.out.println(javafx.scene.text.Font.getFamilies());
    }

}