package gestor;

public class GestorProductos {

	private static GestorProductos instance = null;
	
    private GestorProductos() { }

    public static GestorProductos get() {
        if (instance == null){
        	instance = new GestorProductos();
        }    
        return instance;
    }
  
}
