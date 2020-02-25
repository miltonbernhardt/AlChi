package gestor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import app.PanelAlerta;
import database.DAOEntity;
import dto.DTOCU03;
import dto.DTOCU06;
import dto.DTOCU08;
import dto.DTOFormaVentaCU10;
import dto.DTOEmpaquetadoCU10;
import dto.DTOProductoInicialCU10;
import dto.DTOTipoProductoCU10;
import dto.DTOProductoInicial;
import dto.DTOTipoProducto;
import dto.DTOTipoProductoCU02;
import dto.DTOTipoProductoCU05;
import entity.BitacoraEntrada;
import entity.Categoria;
import entity.Precio;
import entity.Empaquetado;
import entity.ProductoInicial;
import entity.Proveedor;
import entity.TipoProducto;
import enums.TipoPaquete;

public class GestorProductos {

	private static GestorProductos instance = null;
	
    private GestorProductos() { }

    public static GestorProductos get() {
        if (instance == null){
        	instance = new GestorProductos();
        }    
        return instance;
    }

	public Boolean agregarTipoProducto(DTOTipoProductoCU05 dto) { 
		try {		
			Categoria cat = new Categoria();
			cat.setId(dto.getIdCategoria());
			
			TipoProducto t = new TipoProducto();
			t.setCategoria(cat);
			
			t.setNombre(dto.getNombreTipoProducto());
			t.setDescripcion(dto.getDescripcion());
			
			if(!dto.getDirectorioImagen().isEmpty()) {
				URI imagenPath = URI.create(dto.getDirectorioImagen());
				Path imagenOriginal = Paths.get(imagenPath);
				Path directorioImagenes = Paths.get("D:/AlChi/Imágenes");
				Path pathFinal = null;
				String extension = "";
				
				try {				
					Files.createDirectories(directorioImagenes);
					extension = imagenPath.toURL().toString().substring(imagenPath.toURL().toString().lastIndexOf('.'));				
					pathFinal = directorioImagenes.resolve(dto.getNombreTipoProducto()+extension);				
					Files.copy(imagenOriginal, pathFinal, StandardCopyOption.REPLACE_EXISTING);
					
					t.setDirectorioImagen(pathFinal.toUri().toString());
				} catch (IOException e) {				
					PanelAlerta.getExcepcion(e, "Error al copiar la imágen.");				
				}
			}
			else {
				t.setDirectorioImagen("");
			}
			
			List<Precio> precios = new ArrayList<Precio> ();		
			for(int i = 0; i<TipoPaquete.values().length; i++) {
				Precio p = new Precio();
				p.setFecha(LocalDate.now());
				p.setTipoVenta(TipoPaquete.values()[i]);
				p.setValor(0f);
				precios.add(p);
			}
			t.setPrecios(precios);
			t.setEnVenta(false);
			
			Boolean bool = DAOEntity.get().save(t);
			dto.setIdProducto(t.getId());
			return bool;
		}catch(Exception e) {
			PanelAlerta.getExcepcion(e, "Ocurrió un error inesperado.");      	
			return false;
		}
	}
	
	public Boolean updateTipoProducto(DTOTipoProductoCU05 dto) {
		try {
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdProducto());
			Categoria cat = (Categoria) DAOEntity.get().get(Categoria.class, dto.getIdCategoria());
			t.setCategoria(cat);
			t.setNombre(dto.getNombreTipoProducto());
			t.setDescripcion(dto.getDescripcion());			
			
			
			if(!dto.getDirectorioImagen().isEmpty()) {
				URI imagenPath = URI.create(dto.getDirectorioImagen());
				Path imagenOriginal = Paths.get(imagenPath);
				Path directorioImagenes = Paths.get("D:/AlChi/Imágenes");
				Path pathFinal = null;

				String extension = "";
				
				try {				
					Files.createDirectories(directorioImagenes);
					extension = imagenPath.toURL().toString().substring(imagenPath.toURL().toString().lastIndexOf('.'));				
					pathFinal = directorioImagenes.resolve(dto.getNombreTipoProducto()+extension);				
					Files.copy(imagenOriginal, pathFinal, StandardCopyOption.REPLACE_EXISTING);
					
					t.setDirectorioImagen(pathFinal.toUri().toString());
				} catch (IOException e) {				
					PanelAlerta.getExcepcion(e, "Error al copiar la imágen.");				
				}
			}
			else {
				if(!t.getDirectorioImagen().isEmpty()) {
					new File(URI.create(t.getDirectorioImagen())).delete();
				}			
				t.setDirectorioImagen("");
			}
			
			return DAOEntity.get().update(t);
		}catch(Exception e) {
			PanelAlerta.getExcepcion(e, "Ocurrió un error inesperado.");      	
			return false;
		}
	}

	public DTOTipoProductoCU05 getTipoProducto(Integer idTipoProducto) {	
		String consulta = "SELECT new dto.DTOTipoProductoCU05(c.id, c.nombre, t.id, t.nombre, t.descripcion, t.directorioImagen) "
    			+ "FROM Categoria c, TipoProducto t "
    			+ "WHERE c.id=t.categoria and t.id="+idTipoProducto;
		
		return  (DTOTipoProductoCU05) DAOEntity.get().getSingleResult(consulta, DTOTipoProductoCU05.class);
	}
	
	public DTOTipoProductoCU02 getTipoProductoCU02(Integer idTipoProducto) {
		String consulta = "SELECT new dto.DTOTipoProductoCU02(c.nombre, t.id, t.nombre, t.enVenta)"
				+ "FROM Categoria c, TipoProducto t WHERE c.id=t.categoria AND t.id="+idTipoProducto; 
		
		DTOTipoProductoCU02 tipoProduco = (DTOTipoProductoCU02) DAOEntity.get().getSingleResult(consulta, DTOTipoProductoCU02.class);
		TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, tipoProduco.getIdProducto());
			
		List<Precio> precios = t.getPrecios();
		Iterator<Precio> preciosIterator = precios.iterator();
			
		while(preciosIterator.hasNext()) {
			Precio p = preciosIterator.next();
			TipoPaquete tipoVenta = p.getTipoVenta();
			switch(tipoVenta) {
				case G100:
					tipoProduco.setPrecio100(p.getValor());
				break;
					
				case G250:
					tipoProduco.setPrecio250(p.getValor());
				break;
					
				case G500:
					tipoProduco.setPrecio500(p.getValor());
				break;
					
				case G1000:
					tipoProduco.setPrecio1000(p.getValor());
				break;
					
				case G2000:
						tipoProduco.setPrecio2000(p.getValor());
				break;
					
				case UNIDAD:
					tipoProduco.setPrecioUnidad(p.getValor());
				break;
			}
		}		
		
		return tipoProduco;
	}

	@SuppressWarnings("unchecked")
	public List<DTOTipoProductoCU02> buscarTiposProductos(Integer idCategoria, String nombreProducto, Boolean vende) {
		String consulta = "SELECT new dto.DTOTipoProductoCU02(c.nombre, t.id, t.nombre, t.enVenta, t.directorioImagen) "
				+ "FROM Categoria c, TipoProducto t WHERE c.id=t.categoria ";

		if(idCategoria != null)
			consulta = consulta + " AND c.id="+idCategoria;		
		if(nombreProducto != null)
			consulta = consulta + " AND t.nombre LIKE '%"+nombreProducto+"%'";
		
		if(vende != null)
			consulta = consulta + " AND t.enVenta="+vende;	

		consulta = consulta + " ORDER BY t.nombre ASC"; 
		
		List<DTOTipoProductoCU02> lista = (List<DTOTipoProductoCU02>) DAOEntity.get().getResultList(consulta, DTOTipoProductoCU02.class);
		
		Iterator<DTOTipoProductoCU02> listaIterator = lista.iterator();
		
		while(listaIterator.hasNext()) {
			DTOTipoProductoCU02 dto = listaIterator.next();
			
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdProducto());
			
			List<Precio> precios = t.getPrecios();
			Iterator<Precio> preciosIterator = precios.iterator();
			
			while(preciosIterator.hasNext()) {
				Precio p = preciosIterator.next();
				TipoPaquete tipoVenta = p.getTipoVenta();
				switch(tipoVenta) {
					case G100:
						dto.setPrecio100(p.getValor());
					break;
					
					case G250:
						dto.setPrecio250(p.getValor());
					break;
					
					case G500:
						dto.setPrecio500(p.getValor());
					break;
					
					case G1000:
						dto.setPrecio1000(p.getValor());
					break;
					
					case G2000:
						dto.setPrecio2000(p.getValor());
					break;
					
					case UNIDAD:
						dto.setPrecioUnidad(p.getValor());
					break;
				}
			}		
		}
		
		return lista;
	}

	public Boolean registrarIngreso(List<DTOCU03> items) {		
		BitacoraEntrada bitacora = new BitacoraEntrada();
		bitacora.setFecha(LocalDate.now());
		if( !DAOEntity.get().save(bitacora))
			return false;
		
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
				
				Proveedor proveedor = (Proveedor) DAOEntity.get().get(Proveedor.class, dtoProdInicial.getProveedor().getId());
				pro.setProveedor(proveedor);				
				
				pro.setBitacoraEntrada(bitacora);					
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
						
						if(! DAOEntity.get().save(p))
							return false;
					}
				}
			}		
			
			if(!tipoProducto.getEnVenta())
				tipoProducto.setEnVenta(true);
			
			if( !DAOEntity.get().update(tipoProducto))
				return false;
		}		
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<DTOCU08> buscarProductosIniciales(Integer idCategoria, Integer idProveedor, String textCodigoBarra,
			Integer idProducto, LocalDate fechaIngAntes, LocalDate fechaIngDespues, Boolean disponible) {
		
		String consulta = "SELECT new dto.DTOCU08(p.id, t.nombre, p.codigoBarra, c.nombre, pro.nombre, b.fecha, p.vencimiento, p.precioComprado, p.disponible) "
				+ "FROM Categoria c, TipoProducto t, ProductoInicial p, Proveedor pro, BitacoraEntrada b "
				+ "WHERE c.id=t.categoria AND t.id=p.tipoProducto AND pro.id=p.proveedor AND b.id=p.bitacoraEntrada";

		if(idCategoria != null)
			consulta = consulta + " AND c.id="+idCategoria;		
		if(idProveedor != null)
			consulta = consulta + " AND pro.id="+idProveedor;		
		if(textCodigoBarra != null)
			consulta = consulta + " AND p.codigoBarra LIKE '%"+textCodigoBarra+"%'";		
		if(idProducto != null)
			consulta = consulta + " AND t.id="+idProducto;		
		
		if(fechaIngAntes != null) {
			if(fechaIngDespues != null) {				
				if(fechaIngAntes.isAfter(fechaIngDespues))
					consulta = consulta + " AND b.fecha BETWEEN '"+fechaIngDespues+"' AND '"+fechaIngAntes+"'";
				else
					consulta = consulta + " AND b.fecha BETWEEN '"+fechaIngAntes+"' AND '"+fechaIngDespues+"'";
			}
			else
				consulta = consulta + " AND '"+fechaIngAntes+"'>=b.fecha";	
		}
		else {
			if(fechaIngDespues != null)
				consulta = consulta + " AND b.fecha>='"+fechaIngDespues+"'";	
		}
		
		if(disponible != null)
			consulta = consulta + " AND p.disponible="+disponible;

		consulta = consulta + " ORDER BY t.nombre ASC"; 
		
		return (List<DTOCU08>) DAOEntity.get().getResultList(consulta, DTOCU08.class);
	}

	@SuppressWarnings("unchecked")
	public List<DTOTipoProducto> getTiposProducto(Integer idCategoria) {
		String consulta = "SELECT new dto.DTOTipoProducto(t.id, t.nombre) FROM TipoProducto t WHERE t.categoria="+idCategoria+" ORDER BY t.nombre ASC"; 
			
		return (List<DTOTipoProducto>) DAOEntity.get().getResultList(consulta, DTOTipoProducto.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<DTOTipoProductoCU10> getTiposProducto() {
		String consulta = "SELECT new dto.DTOTipoProductoCU10(t.id, t.nombre) FROM TipoProducto t WHERE t.enVenta=true ORDER BY t.nombre ASC"; 
			
		List<DTOTipoProductoCU10> listaProductos =  (List<DTOTipoProductoCU10>) DAOEntity.get().getResultList(consulta, DTOTipoProductoCU10.class);
		Iterator<DTOTipoProductoCU10> iteratorProductos = listaProductos.iterator();
		List<DTOTipoProductoCU10> listaProductosFinal = new ArrayList<DTOTipoProductoCU10>();

		while(iteratorProductos.hasNext()) {
			DTOTipoProductoCU10 dto = iteratorProductos.next();				
			
			Boolean ventaUnidad = false;
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getId());
			List<Precio> precios = t.getPrecios();
			Iterator<Precio> preciosIterator = precios.iterator();			
			while(preciosIterator.hasNext()) {
				Precio p = preciosIterator.next();
				TipoPaquete tipoVenta = p.getTipoVenta();
				Float valor = p.getValor();
				switch(tipoVenta) {
					case G100:
						if(valor != 0)
							dto.getFormasVenta().add(new DTOFormaVentaCU10(tipoVenta, valor));
					break;
					
					case G250:
						if(valor != 0)
							dto.getFormasVenta().add(new DTOFormaVentaCU10(tipoVenta, valor));
					break;
					
					case G500:
						if(valor != 0)
							dto.getFormasVenta().add(new DTOFormaVentaCU10(tipoVenta, valor));
					break;
					
					case G1000:
						if(valor != 0)
							dto.getFormasVenta().add(new DTOFormaVentaCU10(tipoVenta, valor));
					break;
					
					case G2000:
						if(valor != 0)
							dto.getFormasVenta().add(new DTOFormaVentaCU10(tipoVenta, valor));
					break;
					
					case UNIDAD:
						if(valor != 0)
							ventaUnidad = true;
					break;
				}
			}
			
			if( !(ventaUnidad && (dto.getFormasVenta().size() == 0)) ) {
				if(!ventaUnidad) {
					consulta = "SELECT new dto.DTOProductoInicialCU10(p.id, p.cantidadNoVendida, p.codigoBarra, p.vencimiento, pro.nombre ) "
							+ "FROM ProductoInicial p, Proveedor pro "
							+ "WHERE pro.id=p.proveedor AND p.disponible=true AND p.tipoProducto="+dto.getId();
					
					dto.setProductosIniciales((List<DTOProductoInicialCU10>) DAOEntity.get().getResultList(consulta, DTOProductoInicialCU10.class));
						
					listaProductosFinal.add(dto);	
				}
			}
		}
		
		return listaProductosFinal;
	}

	public boolean registrarEmpaquetamiemto(List<DTOEmpaquetadoCU10> items) {
		Iterator<DTOEmpaquetadoCU10> iterator = items.iterator();

		while(iterator.hasNext()){
			DTOEmpaquetadoCU10 dto = iterator.next();
			
			/*if( TODO GESTOR-PRODUCTOS dto no es un descarte o parte de otro) {
				
			}*/
			
			ProductoInicial prodInicial = (ProductoInicial) DAOEntity.get().get(ProductoInicial.class, dto.getIdProductoInicial());
			
			Float cantNoVendida = prodInicial.getCantidadNoVendida();			
			cantNoVendida = cantNoVendida - (dto.getCantidadPaquetes() * dto.getTipoPaqueteE().getCantidad())/1000; 
			prodInicial.setCantidadNoVendida(cantNoVendida);
			
			if(cantNoVendida<=0) {
				prodInicial.setDisponible(false);
			}
			
			for(int i = 0; i<dto.getCantidadPaquetes(); i++) {
				Empaquetado p = new Empaquetado();
				
				p.setProductoInicial(prodInicial);
				p.setTipoVenta(dto.getTipoPaqueteE());
				p.setVendido(false);
				
				if(! DAOEntity.get().save(p))
					return false;
			}

			
			if(! DAOEntity.get().update(prodInicial))
				return false;	
			
			if(cantNoVendida<=0) {
				if(!tipoProductoEnVenta(dto.getDtoTipoProducto().getId()))
					return false;
			}		
		}
		
		return true;
	}
	
	private Boolean tipoProductoEnVenta(Integer id) {
		String cadena = "select count(p.id_tipo_producto)"
				+ "from producto_inicial p "
				+ "where p.id_tipo_producto="+id+" and p.disponible=true "
			    + "group by p.id_tipo_producto";
		Integer cantDisponible = DAOEntity.get().getCantidad(cadena);
		if(cantDisponible == 0) {
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, id);
			t.setEnVenta(false);
			if(! DAOEntity.get().update(t))
				return false;
		}
		
		return true;			
	}


	@SuppressWarnings("unchecked")
	public List<DTOCU06> getPaquetes() {
		String consulta = "SELECT new dto.DTOCU06(e.id, t.id, t.nombre, p.id, e.tipoVenta, pro.nombre, p.codigoBarra, p.vencimiento) "
				+ "FROM TipoProducto t, ProductoInicial p, Proveedor pro, Empaquetado e "
				+ "WHERE t.id=p.tipoProducto AND p.proveedor=pro.id AND p.id=e.productoInicial "
				+ "AND e.vendido = false  ORDER BY t.nombre ASC"; 
		
		List<DTOCU06> lista = (List<DTOCU06>) DAOEntity.get().getResultList(consulta, DTOCU06.class);
		
		Iterator<DTOCU06> ite = lista.iterator();
		
		while(ite.hasNext()) {
			DTOCU06 dto = ite.next();
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdTipoProducto());
			
			Iterator<Precio> precios = t.getPrecios().iterator();
			TipoPaquete tipoVenta = dto.getFormaVenta();
			while(precios.hasNext()) {
				Precio p = precios.next();
				
				if(p.getTipoVenta().equals(tipoVenta)) {
					dto.setPrecioVenta(p.getValor());
					break;
				}
			}
		}
		return lista;
	}
	
}
