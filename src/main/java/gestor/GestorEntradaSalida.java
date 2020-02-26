package gestor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import database.DAOEntity;
import dto.DTOCU03;
import dto.DTOCU06;
import dto.DTOProductoInicial;
import entity.BitacoraSalida;
import entity.Combo;
import entity.Empaquetado;

public class GestorEntradaSalida {	
	
	private static GestorEntradaSalida instance = null;
    private GestorEntradaSalida() { }
    public static GestorEntradaSalida get() {
        if (instance == null){
        	instance = new GestorEntradaSalida();
        }    
        return instance;
    }
    
    public List<DTOCU03> getProductosActualizar(List<DTOProductoInicial> productosInicial){
    	List<DTOCU03> listaFinal = new ArrayList<DTOCU03> ();    
    	Iterator<DTOProductoInicial> iterator1 = new HashSet<DTOProductoInicial>(productosInicial).iterator();
    	
    	while(iterator1.hasNext()) {
    		DTOProductoInicial dtoProdInicial = iterator1.next();
    		
    		DTOCU03 dtoCu03 = new DTOCU03(dtoProdInicial);
        	Iterator<DTOProductoInicial> iterator2 = productosInicial.iterator();
        	while(iterator2.hasNext()) {
        		DTOProductoInicial dtoProdInicial2 = iterator2.next();
        		if(dtoProdInicial.getTipoProducto().getIdProducto().equals(dtoProdInicial2.getTipoProducto().getIdProducto())) {
        			dtoCu03.getLista().add(dtoProdInicial2);
        		}
        	}
        	listaFinal.add(dtoCu03);
    	}    	
    	return listaFinal;
    }
    
	public boolean registrarSalida(List<DTOCU06> items) {
		BitacoraSalida bitacora = new BitacoraSalida();
		bitacora.setFecha(LocalDate.now());
		if(!DAOEntity.get().save(bitacora))
			return false;

		Iterator<DTOCU06> ite = items.iterator();
		while(ite.hasNext()) {
			DTOCU06 dto = ite.next();
			//TODO Gestor-Salida fijarse si es empaquetado conjunto
			
			if(dto.getIdTipoProducto()==null) {
				Combo combo = new Combo();
				combo.setPrecio(dto.getPrecioVentaF());
				combo.setBitacora(bitacora);				
				List<Empaquetado> productosCombo = new ArrayList<Empaquetado>();
				Iterator<DTOCU06> iteCombo = dto.getListaCombo().iterator();
				while(iteCombo.hasNext()) {
					DTOCU06 dto2 = iteCombo.next();
					Empaquetado e = (Empaquetado) DAOEntity.get().get(Empaquetado.class, dto2.getIdEmpaquetado());	
					productosCombo.add(e);
					e.setVendido(true);
					if(!DAOEntity.get().update(e))
						return false;
				}				
				combo.setProductos(productosCombo);				
				if(!DAOEntity.get().save(combo))
					return false;
			}
			else {
				Empaquetado e = (Empaquetado) DAOEntity.get().get(Empaquetado.class, dto.getIdEmpaquetado());	
				e.setVendido(true);
				e.setPrecio(dto.getPrecioVentaF());
				if(!DAOEntity.get().update(e))
					return false;
			}
		}
		
		return true;
	}

}
