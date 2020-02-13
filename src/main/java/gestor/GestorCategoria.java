package gestor;

import java.util.List;
import database.DAOEntity;
import dto.DTOCategoria;

public class GestorCategoria {

	private static GestorCategoria instance = null;
	
    private GestorCategoria() { }

    public static GestorCategoria get() {
        if (instance == null){
        	instance = new GestorCategoria();
        }    
        return instance;
    }

	@SuppressWarnings("unchecked")
	public List<DTOCategoria> getDTOCategorias() {	
		String consulta = "SELECT new dto.DTOCategoria(c.id, c.nombre) FROM Categoria c ORDER BY c.nombre ASC";
		
		return (List<DTOCategoria>) DAOEntity.get().getResultList(consulta, new DTOCategoria());
	}
	
}
