package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import database.HibernateUtil;

public class App extends Application {
	
    private static Scene scene;
    private static Stage stage;

    @Override
    @SuppressWarnings("exports")
    public void start(Stage primaryStage) throws IOException {
    	HibernateUtil.apagarLog(true);
    	HibernateUtil.getSessionFactory();    	    	
    	scene = new Scene(loadFXML("menu"));
    	
    	primaryStage.setOnCloseRequest(e->{
        	Platform.exit();
        	System.exit(0);        	
        });    	
    	primaryStage.getIcons().add(new Image("app/icon/logoAlChi.png"));
    	primaryStage.setTitle("AlChi: Menú");
    	primaryStage.setMinHeight(500);
    	primaryStage.setMinWidth(900);
        primaryStage.setMaxHeight(1080);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    	stage = primaryStage;  
    }

    static void setRoot(String fxml, String titulo) {
        scene.setRoot(loadFXML(fxml));
        stage.setTitle(titulo);
    }
    
    static void setRoot(Parent p, String titulo) {
        scene.setRoot(p);
        stage.setTitle(titulo);
    }

    static Parent getSceneAnterior() {
	   return stage.getScene().getRoot();
    }
   
    static String getTituloAnterior() {
	   return stage.getTitle();
    }
    
    static void setObjectScene(Object o) {
    	stage.setUserData(o);
    }
    
    static Object getObjectScene() {
	   return stage.getUserData();
    }
    
    private static Parent loadFXML(String fxml)  {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        try {
        	Parent p = fxmlLoader.load();
			return p;
		} catch (IOException | RuntimeException  e) {
			new ExceptionPane(e, "Ocurrió un error al cargar la view \""+fxml+"\"."); 
			return null;
		}
    }

    
	/* TODO APP poner iconos a las alertas
	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	stage.getIcons().add(new Image(this.getClass().getResource("app/icon/logo.png").toString()));
	*/   		

    public static void main(String[] args) {
        launch();
    } 
}