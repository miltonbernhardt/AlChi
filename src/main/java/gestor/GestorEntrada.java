package gestor;

public class GestorEntrada {	
	
	private static GestorEntrada instance = null;
	
    private GestorEntrada() { }

    public static GestorEntrada get() {
        if (instance == null){
        	instance = new GestorEntrada();
        }    
        return instance;
    }

}
