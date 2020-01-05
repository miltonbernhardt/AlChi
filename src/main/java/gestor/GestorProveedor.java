package gestor;

public class GestorProveedor {

	private static GestorProveedor instance = null;
	
    private GestorProveedor() { }

    public static GestorProveedor get() {
        if (instance == null){
        	instance = new GestorProveedor();
        }    
        return instance;
    }
  
}
