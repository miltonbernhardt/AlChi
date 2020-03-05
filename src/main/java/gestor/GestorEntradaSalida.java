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
import entity.Combo;
import entity.Empaquetado;
import entity.Precio;
import entity.ProductoInicial;
import entity.Proveedor;
import entity.TipoProducto;
import enums.TipoPaquete;

public class GestorEntradaSalida {	

	private static GestorEntradaSalida instance = null;
    private GestorEntradaSalida() { }
    public static GestorEntradaSalida get() {
        if (instance == null){
        	instance = new GestorEntradaSalida();
        }    
        return instance;
    }
    
	public Boolean registrarIngreso(List<DTOCU03> items) {		
		
		Iterator<DTOCU03> itemsIterator = items.iterator();		
		while(itemsIterator.hasNext()) {
			Boolean ventaUnidades = false;
			
			DTOCU03 dtoCu03 = itemsIterator.next();
			
			TipoProducto tipoProducto = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dtoCu03.getIdTipoProducto());
			List<ProductoInicial> listaProductos = new ArrayList<ProductoInicial>();
			
			Iterator<Precio> preciosIterator = tipoProducto.getPrecios().iterator();
			while(preciosIterator.hasNext()) {
				Precio p = preciosIterator.next();
				TipoPaquete tipoVenta = p.getTipoVenta();
				switch(tipoVenta) {
					case G100:
						p.setValor(dtoCu03.getP100FinalF());
					break;
					
					case G250:
						p.setValor(dtoCu03.getP250FinalF());
					break;
					
					case G500:
						p.setValor(dtoCu03.getP500FinalF());
					break;
					
					case G1000:
						p.setValor(dtoCu03.getP1000FinalF());
					break;
					
					case G2000:
						p.setValor(dtoCu03.getP2000FinalF());
					break;
					
					case UNIDAD:
						if(dtoCu03.getPUnidadFinalF()>0) {
							ventaUnidades = true;
						}
						p.setValor(dtoCu03.getPUnidadFinalF());
					break;
				}
			}		
			
			Iterator<DTOProductoInicial> productos = dtoCu03.getLista().iterator();
			while(productos.hasNext()) {
				
				DTOProductoInicial dtoProdInicial = productos.next();
				
				ProductoInicial pro = new ProductoInicial();				
				pro.setPrecioComprado(dtoProdInicial.getPrecioCompradoF());
				pro.setCantidadNoVendida(dtoProdInicial.getCantidadNoVendidaF());
				pro.setVencimiento(dtoProdInicial.getVencimiento());
				pro.setCodigoBarra(dtoProdInicial.getCodigoBarra());
				pro.setDisponible(true);
				pro.setFechaEntrada(LocalDate.now());
				
				Proveedor proveedor = (Proveedor) DAOEntity.get().get(Proveedor.class, dtoProdInicial.getProveedor().getId());
				pro.setProveedor(proveedor);				
							
				listaProductos.add(pro);
				pro.setTipoProducto(tipoProducto);			
				
				if( !DAOEntity.get().save(pro))
					return false;
				
				if(ventaUnidades) {
					for(int i = 0; i<dtoProdInicial.getCantidadNoVendidaF(); i++) {
						Empaquetado p = new Empaquetado();
						
						p.setProductoInicial(pro);
						p.setTipoVenta(TipoPaquete.UNIDAD);
						p.setVendido(false);
						p.setDadoBaja(false);
						p.setCantidadProductoPrincipal(1f);
						
						if(! DAOEntity.get().save(p))
							return false;
					}
					
					ProductoInicial pro2 = (ProductoInicial) DAOEntity.get().get(ProductoInicial.class, pro.getId());

					pro2.setDisponible(false);	
					
					if(! DAOEntity.get().update(pro2))
						return false;
				}
				

			}		
			
			if(!tipoProducto.getEnVenta())
				tipoProducto.setEnVenta(true);
			
			if( !DAOEntity.get().update(tipoProducto))
				return false;
		}		
		
		return true;
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
		LocalDate hoy = LocalDate.now();

		Iterator<DTOCU06> ite = items.iterator();
		while(ite.hasNext()) {
			DTOCU06 dto = ite.next();			
			if(dto.getIdTipoProducto()==null) {
				Combo combo = new Combo();
				combo.setPrecio(dto.getPrecioVentaF());
				
				List<Empaquetado> productosCombo = new ArrayList<Empaquetado>();
				Iterator<DTOCU06> iteCombo = dto.getListaCombo().iterator();
				while(iteCombo.hasNext()) {
					DTOCU06 dto2 = iteCombo.next();
					Empaquetado e = (Empaquetado) DAOEntity.get().get(Empaquetado.class, dto2.getIdEmpaquetado());	
					productosCombo.add(e);
					e.setFechaSalida(hoy);	
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
				e.setFechaSalida(hoy);
				if(!DAOEntity.get().update(e))
					return false;
			}
			
			if(!GestorProductos.get().cantProductosTipoProducto(dto.getIdTipoProducto()))
				return false;
			
		}
		

		return true;
	}
	
	public Boolean darBajaProductoInicial(Integer idProductoInicial, Integer idTipoProducto) {
		ProductoInicial p = (ProductoInicial) DAOEntity.get().get(ProductoInicial.class, idProductoInicial);
		p.setDisponible(false);
		if(!DAOEntity.get().update(p)) 
			return false;
		
		if(!GestorProductos.get().cantProductosTipoProducto(idTipoProducto))
			return false;
		
		return true;
	}

	public Boolean darBajaProductoEmpaquetado(Integer idEmpaquetado, Integer idTipoProducto) {
		Empaquetado producto = (Empaquetado) DAOEntity.get().get(Empaquetado.class, idEmpaquetado);
		producto.setDadoBaja(true);
		
		if( !DAOEntity.get().update(producto) )
			return false;
		
		if(!GestorProductos.get().cantProductosTipoProducto(idTipoProducto))
			return false;
		
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public Boolean dejarVender(Integer idTipoProducto) {
		TipoProducto tipoProducto = (TipoProducto) DAOEntity.get().get(TipoProducto.class, idTipoProducto);
		
		String consulta1 = "select p from ProductoInicial p where p.tipoProducto="+tipoProducto.getId();
		
		List<ProductoInicial> listaProductosIniciales = (List<ProductoInicial>) DAOEntity.get().getResultList(consulta1, ProductoInicial.class);
		
		Iterator<ProductoInicial> iterator1 = listaProductosIniciales.iterator();
		while(iterator1.hasNext()) {
			ProductoInicial prodInicial = iterator1.next();
			
			String consulta2 = "select e from Empaquetado e where e.productoInicial="+prodInicial.getId();
			
			List<Empaquetado> listaEmpaquetados = (List<Empaquetado>) DAOEntity.get().getResultList(consulta2, Empaquetado.class);
			
			Iterator<Empaquetado> iterator2 = listaEmpaquetados.iterator();
			while(iterator2.hasNext()) {
				Empaquetado empaquetado = iterator2.next();
				empaquetado.setDadoBaja(true);
				if(!DAOEntity.get().update(empaquetado))
					return false;
			}
			
			prodInicial.setDisponible(false);
			if(!DAOEntity.get().update(prodInicial))
				return false;
		}
		
		
		tipoProducto.setEnVenta(false);
		if(!DAOEntity.get().update(tipoProducto))
			return false;
		return true;
	}
	
	public Boolean darAltaProductoInicial(Integer idProductoInicial, Integer idTipoProducto) {
		ProductoInicial p = (ProductoInicial) DAOEntity.get().get(ProductoInicial.class, idProductoInicial);
		p.setDisponible(true);
		if(!DAOEntity.get().update(p)) 
			return false;
		
		TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, idTipoProducto);
		t.setEnVenta(true);		
		
		if(!DAOEntity.get().update(t))
			return false;
		
		return true;
	}
	
	public Boolean darAltaProductoEmpaquetado(Integer idProductoEmpaquetado, Integer idTipoProducto) {
		Empaquetado e = (Empaquetado) DAOEntity.get().get(Empaquetado.class, idProductoEmpaquetado);
		e.setDadoBaja(false);
		if(!DAOEntity.get().update(e)) 
			return false;
		
		TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, idTipoProducto);
		t.setEnVenta(true);		
		
		if(!DAOEntity.get().update(t))
			return false;
		
		return true;
	}
}
