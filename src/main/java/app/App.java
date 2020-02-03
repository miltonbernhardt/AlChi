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
        scene = new Scene(loadFXML("menu"));
      //  scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("menu");
        stage.setMinHeight(500);
        stage.setMinWidth(900);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setMaximized(true);
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
    }

}