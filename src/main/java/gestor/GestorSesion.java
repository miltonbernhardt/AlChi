package gestor;

public class GestorSesion {

	private static GestorSesion instance = null;
	
    private GestorSesion() { }

    public static GestorSesion get() {
        if (instance == null){
        	instance = new GestorSesion();
        }    
        return instance;
    }
  
}
