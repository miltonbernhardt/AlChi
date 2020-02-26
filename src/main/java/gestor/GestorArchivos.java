package gestor;

public class GestorArchivos {
	private static GestorArchivos instance = null;
	
    private GestorArchivos() { }

    public static GestorArchivos get() {
        if (instance == null){
        	instance = new GestorArchivos();
        }    
        return instance;
    }

}
