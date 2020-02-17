package gestor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import dto.DTOCU03;
import dto.DTOProductoInicial;

public class GestorEntrada {	
	
	private static GestorEntrada instance = null;
    private GestorEntrada() { }
    public static GestorEntrada get() {
        if (instance == null){
        	instance = new GestorEntrada();
        }    
        return instance;
    }
    
    public List<DTOCU03> getProductosActualizar(List<DTOProductoInicial> productosInicial){
    	List<DTOCU03> listaFinal = new ArrayList<DTOCU03> ();
    	
    	List<DTOProductoInicial> listaInicial = new ArrayList<DTOProductoInicial> ();
    	
    	Iterator<DTOProductoInicial> iterator1 = new HashSet<DTOProductoInicial>(productosInicial).iterator();
    	
    	while(iterator1.hasNext()) {
    		DTOProductoInicial dtoProdInicial = iterator1.next();
    		
    		DTOCU03 dtoCu03 = new DTOCU03(dtoProdInicial.getTipoProducto().getIdProducto(), dtoProdInicial.getNombreCategoria(), dtoProdInicial.getNombreTipoProducto());
    		
        	Iterator<DTOProductoInicial> iterator2 = listaInicial.iterator();
        	while(iterator2.hasNext()) {
        		DTOProductoInicial dtoProdInicial2 = iterator2.next();
        		if(dtoProdInicial2.getIdProductoInicial().equals(dtoProdInicial.getIdProductoInicial())) {
        			dtoCu03.getLista().add(dtoProdInicial2);
        			listaInicial.remove(dtoProdInicial2);
        		}
        	}
        	listaFinal.add(dtoCu03);
    	}
    	
    	return listaFinal;
    }

}
