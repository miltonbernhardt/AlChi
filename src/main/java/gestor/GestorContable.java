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
    	//fijarse si el paquete es compuesto o no 
    	//TODO CU11 implementar
    }
    
    public void calcularPerdidas() {
    	//fijarse si el paquete es compuesto o no 
    	//TODO CU11 implementar
    }
}
