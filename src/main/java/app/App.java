package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import database.HibernateUtil;

public class App extends Application {
	
    private static Scene scene;

    @Override
    @SuppressWarnings("exports")
    public void start(Stage stage) throws IOException {
    	HibernateUtil.apagarLog(true);
    	HibernateUtil.getSessionFactory();
    	
    	scene = new Scene(loadFXML("menu"));
    	
    	stage.setOnCloseRequest(e->{
        	
        	Platform.exit();
        	
        	System.exit(0);
        	
        });

        stage.setTitle("AlChi");
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
    
    static void volver() throws IOException {
    	setRoot("menu");
    }

    public static void main(String[] args) {
        launch();
    }
    
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    



}