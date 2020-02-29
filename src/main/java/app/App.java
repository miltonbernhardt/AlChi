package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import database.HibernateUtil;

public class App extends Application {
	//TODO APP corregir los float con cero, que aparezcan sin cero
	//TODO APP ver si no cambiar el metodo de busqueda por nombre, si hacer por escribir o con combos
	
    private static Scene scene;
    private static Stage stage;
    private static FXMLLoader fxmlLoader;
	
	private static List<Parent> scenesAnteriores = new ArrayList<Parent>();
	private static List<String> titulosAnteriores = new ArrayList<String>();
    
    public static void main(String[] args) {
        launch();
    } 

    @Override
    @SuppressWarnings("exports")
    public void start(Stage primaryStage) throws IOException {
    	HibernateUtil.apagarLog(true);
    	HibernateUtil.getSessionFactory();    	    	
    	scene = new Scene(loadFXML("CU16View"));
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
		scenesAnteriores.add(stage.getScene().getRoot());
		titulosAnteriores.add(stage.getTitle());
	}
	
	static void getViewAnterior() {
		Integer index = scenesAnteriores.size()-1;		
		Parent p = null;
		String t = null;		
		try {
			p = scenesAnteriores.get(index);
			t = titulosAnteriores.get(index);
			scene.setRoot(p);
		    scene.getRoot().requestFocus();
		    stage.setTitle(t);
	    }catch(Exception e) {
	    	 scene.setRoot(loadFXML("menu"));
	         scene.getRoot().requestFocus();
	         stage.setTitle("AlChi: Menú");
	    }
		scenesAnteriores.remove(p);
		titulosAnteriores.remove(t);
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

	public static void setStyle(@SuppressWarnings("exports") DialogPane dialogPane) {
		dialogPane.getStylesheets().clear();
		dialogPane.getStylesheets().add("app/styles.css");		
	}
}