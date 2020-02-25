package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import database.HibernateUtil;

public class App extends Application {
	
    private static Scene scene;
    private static Stage stage;
    private static FXMLLoader fxmlLoader;
    
	private static Parent sceneAnterior = null;
	private static String tituloAnterior = null;
	
	private static Parent sceneAnterior2 = null;
	private static String tituloAnterior2 = null;
    
    public static void main(String[] args) {
        launch();
    } 

    @Override
    @SuppressWarnings("exports")
    public void start(Stage primaryStage) throws IOException {
    	HibernateUtil.apagarLog(true);
    	HibernateUtil.getSessionFactory();    	    	
    	scene = new Scene(loadFXML("CU06View"));
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

	static void setViewAnterior() {
		sceneAnterior = stage.getScene().getRoot();
		tituloAnterior = stage.getTitle();
	}
	
	static void getViewAnterior() {
		try {
			scene.setRoot(sceneAnterior);
		    scene.getRoot().requestFocus();
		    stage.setTitle(tituloAnterior);
	    }catch(Exception e) {
	    	 scene.setRoot(loadFXML("menu"));
	         scene.getRoot().requestFocus();
	         stage.setTitle("AlChi: Menú");
	    }
	    sceneAnterior = null;
	    tituloAnterior = null;
	}
	
	static void setViewAnterior2() {
		sceneAnterior2 = stage.getScene().getRoot();
		tituloAnterior2 = stage.getTitle();
	}
	
	static void getViewAnterior2() {
		try {
			scene.setRoot(sceneAnterior2);
		    scene.getRoot().requestFocus();
		    stage.setTitle(tituloAnterior2);
	    }catch(Exception e) {
	    	 scene.setRoot(loadFXML("menu"));
	         scene.getRoot().requestFocus();
	         stage.setTitle("AlChi: Menú");
	    }
	    sceneAnterior2 = null;
	    tituloAnterior2 = null;
	}

    static Object getControllerActual() {
    	return fxmlLoader.getController();
    }
	
	static void setError(Control nodo) {
		nodo.getStylesheets().clear();
		nodo.getStylesheets().add("app/error.css");
	}
	
	static void setValido(Control nodo) {
		nodo.getStylesheets().clear();
		nodo.getStylesheets().add("app/styles.css");
	}
}