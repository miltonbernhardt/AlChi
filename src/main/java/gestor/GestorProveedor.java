package gestor;

import java.util.List;

import app.PanelAlerta;
import database.DAOEntity;
import dto.DTOProveedor;
import entity.Proveedor;

public class GestorProveedor {

	private static GestorProveedor instance = null;
	
    private GestorProveedor() { }

    public static GestorProveedor get() {
        if (instance == null){
        	instance = new GestorProveedor();
        }    
        return instance;
    }

	@SuppressWarnings("unchecked")
	public List<DTOProveedor> getProveedores() {
		String consulta = "SELECT new dto.DTOProveedor(p.id, p.nombre, p.numeroTelefono) "
				+ "FROM Proveedor p ORDER BY p.nombre ASC"; 		
	
		return (List<DTOProveedor>) DAOEntity.get().getResultList(consulta, DTOProveedor.class);
	}

	public Boolean agregarProveedor(DTOProveedor dto) {
		try {
			Proveedor p = new Proveedor();		
			p.setNombre(dto.getNombre());
			p.setNumeroTelefono(dto.getNumeroTelefono());	
			Boolean validez = DAOEntity.get().save(p);
			dto.setId(p.getId());
			
			return validez;
			
		}catch(Exception e) {
			PanelAlerta.getExcepcion(e, "Ocurrió un error inesperado.");      	
			return false;
		}
		
	}
  
	public Boolean editarProveedor(DTOProveedor dto) {
		Proveedor p = (Proveedor) DAOEntity.get().get(Proveedor.class, dto.getId());
		p.setNombre(dto.getNombre());
		p.setNumeroTelefono(dto.getNumeroTelefono());			
		return DAOEntity.get().update(p);
	}
	
}
