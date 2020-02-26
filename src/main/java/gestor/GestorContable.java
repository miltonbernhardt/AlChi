package gestor;

public class GestorContable {
	private static GestorContable instance = null;
	
    private GestorContable() { }

    public static GestorContable get() {
        if (instance == null){
        	instance = new GestorContable();
        }    
        return instance;
    }
    
    public void calcularGanancias() {
    	//TODO CU12 implementar
    }
    
    public void calcularPerdidas() {
    	//TODO CU13 implementar
    }
}
