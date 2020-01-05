package gestor;

public class GestorSalida {

	private static GestorSalida instance = null;
	
    private GestorSalida() { }

    public static GestorSalida get() {
        if (instance == null){
        	instance = new GestorSalida();
        }    
        return instance;
    }
    
}
