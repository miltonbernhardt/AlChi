package gestor;

import java.util.ArrayList;
import java.util.List;

import app.PanelAlerta;
import database.DAOEntity;
import dto.DTOCategoria;
import entity.Categoria;
import entity.TipoProducto;

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
	public List<DTOCategoria> getCategorias() {	
		String consulta = "SELECT new dto.DTOCategoria(c.id, c.nombre) FROM Categoria c ORDER BY c.nombre ASC";
		
		return (List<DTOCategoria>) DAOEntity.get().getResultList(consulta, new DTOCategoria());
	}

	public boolean agregarCategoría(DTOCategoria dto) {
		try {
			Categoria c = new Categoria();		
			c.setNombre(dto.getNombre());	
			c.setProductos(new ArrayList<TipoProducto>());
			Boolean validez = DAOEntity.get().save(c);
			dto.setId(c.getId());
			
			return validez;
			
		}catch(Exception e) {
			PanelAlerta.getExcepcion(e, "Ocurrió un error inesperado.");      	
			return false;
		}
	}

	public boolean editarCategoria(DTOCategoria dto) {
		Categoria c = (Categoria) DAOEntity.get().get(dto.getId(), new Categoria());
		c.setNombre(dto.getNombre());		
		return DAOEntity.get().update(c);
	}
}
