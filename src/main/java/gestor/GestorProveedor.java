package gestor;

import java.util.List;

import app.ExceptionPane;
import database.DAOEntity;
import dto.DTOProveedorCU07;
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
	public List<DTOProveedorCU07> getProveedores() {
		String consulta = "SELECT new dto.DTOProveedorCU07(p.id, p.nombre, p.numeroTelefono) "
				+ "FROM Proveedor p ORDER BY p.nombre ASC"; 		
	
		return (List<DTOProveedorCU07>) DAOEntity.get().getResultList(consulta, new DTOProveedorCU07());
	}

	public Boolean agregarProveedor(DTOProveedorCU07 dto) {
		try {
			Proveedor p = new Proveedor();		
			p.setNombre(dto.getNombre());
			p.setNumeroTelefono(dto.getNumeroTelefono());	
			Boolean validez = DAOEntity.get().save(p);
			dto.setId(p.getId());
			
			return validez;
			
		}catch(Exception e) {
			new ExceptionPane(e, "Ocurrió un error inesperado.");      	
			return false;
		}
		
	}
  
	public Boolean editarProveedor(DTOProveedorCU07 dto) {
		Proveedor p = (Proveedor) DAOEntity.get().get(dto.getId(), new Proveedor());
		p.setNombre(dto.getNombre());
		p.setNumeroTelefono(dto.getNumeroTelefono());			
		return DAOEntity.get().update(p);
	}
	
}
