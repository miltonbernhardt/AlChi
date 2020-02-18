package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import database.HibernateUtil;

public class App extends Application {
	
    private static Scene scene;
    private static Stage stage;
    private static FXMLLoader fxmlLoader;
    
    public static void main(String[] args) {
        launch();
    } 

    @Override
    @SuppressWarnings("exports")
    public void start(Stage primaryStage) throws IOException {
    	HibernateUtil.apagarLog(true);
    	HibernateUtil.getSessionFactory();    	    	
    	scene = new Scene(loadFXML("CU03View01"));
    	primaryStage.setOnCloseRequest(e->{
        	Platform.exit();
        	System.exit(0);    
        	HibernateUtil.closeBaseDatos();
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
    
    private static Parent loadFXML(String fxml)  {
        fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        try {
        	Parent p = fxmlLoader.load();
			return p;
		} catch (IOException | RuntimeException  e) {
			PanelAlerta.getExcepcion(e, "Ocurrió un error al cargar la view \""+fxml+"\"."); 
			return null;
		}
    }  

    static Object setRoot(String fxml, String titulo) {
        scene.setRoot(loadFXML(fxml));
        scene.getRoot().requestFocus();
        stage.setTitle(titulo);
        return fxmlLoader.getController();
    }
    
    static void setRoot(Parent p, String titulo) {
        scene.setRoot(p);
        scene.getRoot().requestFocus();
        stage.setTitle(titulo);
    }

    static Parent getSceneAnterior() {
	   return stage.getScene().getRoot();
    }
   
    static String getTituloAnterior() {
	   return stage.getTitle();
    } 		

    static Object getControllerActual() {
    	return fxmlLoader.getController();
    }

	static void onKeyPressed(KeyEvent event) {
    	//TODO APP manejar bien esto
        if (event.getCode() == KeyCode.ESCAPE) {
        	
        } else if (event.getCode() == KeyCode.ENTER) {
            Node focusOwner = stage.getScene().getFocusOwner();
            if (focusOwner instanceof Button) {
                ((Button) (focusOwner)).fire();
            }
        }
    }
}