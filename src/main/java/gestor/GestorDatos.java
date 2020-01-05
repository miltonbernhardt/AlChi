package gestor;

public class GestorDatos {
	
	private static GestorDatos instance = null;
	
    private GestorDatos() { }

    public static GestorDatos get() {
        if (instance == null){
        	instance = new GestorDatos();
        }    
        return instance;
    }
    
}
