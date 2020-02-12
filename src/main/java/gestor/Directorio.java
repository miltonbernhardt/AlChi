package gestor;

public class Directorio {
	private static Directorio instance = null;
	private static String directorioImagenes = System.getProperty("user.home");
	
    private Directorio() { }

    public static Directorio get() {
        if (instance == null){
        	instance = new Directorio();
        }    
        return instance;
    }
	public String getDirectorio() { return directorioImagenes; }
	public void setDirectorio(String directorio) { Directorio.directorioImagenes = directorio; }
}
