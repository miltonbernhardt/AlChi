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
import dto.DTOCU16;
import dto.DTOFormaVentaCU10;
import dto.DTOEmpaquetadoCU10;
import dto.DTOProductoInicialCU10;
import dto.DTOTipoProductoCU10;
import dto.DTOTipoProducto;
import dto.DTOTipoProductoCU02;
import dto.DTOTipoProductoCU05;
import entity.Categoria;
import entity.Precio;
import entity.Empaquetado;
import entity.ProductoInicial;
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
	
	public Boolean actualizarPrecios(DTOCU03 dto) {		
		TipoProducto tipoProducto = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdTipoProducto());
		
		Iterator<Precio> preciosIterator = tipoProducto.getPrecios().iterator();
		while(preciosIterator.hasNext()) {
			Precio p = preciosIterator.next();
			TipoPaquete tipoVenta = p.getTipoVenta();
			switch(tipoVenta) {
				case G100:
					p.setValor(dto.getP100FinalF());
				break;
				
				case G250:
					p.setValor(dto.getP250FinalF());
				break;
				
				case G500:
					p.setValor(dto.getP500FinalF());
				break;
				
				case G1000:
					p.setValor(dto.getP1000FinalF());
				break;
				
				case G2000:
					p.setValor(dto.getP2000FinalF());
				break;
				
				case UNIDAD:
					p.setValor(dto.getPUnidadFinalF());
				break;
			}
		}		
		
		if( !DAOEntity.get().update(tipoProducto))
			return false;			
		
		return true;
	}
	
	public Boolean registrarEmpaquetamiemto(List<DTOEmpaquetadoCU10> items, List<DTOCU06> itemsParaCu6) {
		Iterator<DTOEmpaquetadoCU10> iterator = items.iterator();
		Boolean cu06 = false;
		if(itemsParaCu6 != null) {
			cu06 = true;
		}

		while(iterator.hasNext()){
			DTOEmpaquetadoCU10 dto = iterator.next();	
			
			ProductoInicial prodInicial = (ProductoInicial) DAOEntity.get().get(ProductoInicial.class, dto.getIdProductoInicial());		
			
			ProductoInicial prodSecundario = null;
			
			Float cantNoVendida = prodInicial.getCantidadNoVendida();	

			if(dto.getDadoBaja()) {	
				prodInicial.setDisponible(false);
			}
			else {				
				if(dto.getSecundario()) {							
					cantNoVendida = cantNoVendida - dto.getCantPrimario(); 					
					prodSecundario = (ProductoInicial) DAOEntity.get().get(ProductoInicial.class, dto.getIdProductoSecundario());
					Float cantNoVendida2 = prodSecundario.getCantidadNoVendida();			
					cantNoVendida2 = cantNoVendida2 - dto.getTipoPaqueteE().getCantidad() + dto.getCantPrimario(); 
					prodSecundario.setCantidadNoVendida(cantNoVendida2);				
					if(cantNoVendida2<=0) {
						prodSecundario.setDisponible(false);
					}
				}
				else {
					cantNoVendida = cantNoVendida - (dto.getCantidadPaquetesF() * dto.getTipoPaqueteE().getCantidad()); 
				}
				
				prodInicial.setCantidadNoVendida(cantNoVendida);		
				if(cantNoVendida<=0) {
					prodInicial.setDisponible(false);
				}
				
				for(int i = 0; i<dto.getCantidadPaquetesF(); i++) {
					Empaquetado p = new Empaquetado();
					
					p.setProductoInicial(prodInicial);
					if(prodSecundario != null) {
						p.setProductoInicialOpcional(prodSecundario);
					}
					p.setTipoVenta(dto.getTipoPaqueteE());
					p.setVendido(false);
					p.setDadoBaja(false);
					p.setCantidadProductoPrincipal(dto.getTipoPaqueteE().getCantidad());
					
					if(! DAOEntity.get().save(p))
						return false;
					
					if(cu06) {
						DTOCU06 dto6 = new DTOCU06(dto, p.getId());
						itemsParaCu6.add(dto6);
					}
				}
				if(!GestorProductos.get().cantProductosTipoProducto(dto.getDtoTipoProducto().getId()))
					return false;
				
			}
			
			if(! DAOEntity.get().update(prodInicial))
				return false;	
			
			if(prodSecundario != null) {
				if(! DAOEntity.get().update(prodSecundario))
					return false;
			}
		}		
		return true;
	}
	
	public DTOCU06 getDTOCU06(Integer idEmpaquetado) {
		String consulta = "SELECT new dto.DTOCU06(e.id, t.id, t.nombre, p.id, e.tipoVenta, pro.nombre, p.codigoBarra, p.vencimiento) "
				+ "FROM TipoProducto t, ProductoInicial p, Proveedor pro, Empaquetado e "
				+ "WHERE t.id=p.tipoProducto AND p.proveedor=pro.id AND p.id=e.productoInicial "
				+ "AND e.vendido = false AND e.dadoBaja=false AND e.id="+idEmpaquetado+" ORDER BY t.nombre ASC"; 
		
		DTOCU06 dto = (DTOCU06) DAOEntity.get().getSingleResult(consulta, DTOCU06.class);

		TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdTipoProducto());
		
		Iterator<Precio> precios = t.getPrecios().iterator();
		TipoPaquete tipoVenta = dto.getFormaVentaE();
		while(precios.hasNext()) {
			Precio p = precios.next();
			
			if(p.getTipoVenta().equals(tipoVenta)) {
				dto.setPrecioVenta(p.getValor());
				break;
			}
		}	

		return dto;
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
	
	public DTOTipoProductoCU05 getTipoProducto(Integer idTipoProducto) {	
		String consulta = "SELECT new dto.DTOTipoProductoCU05(c.id, c.nombre, t.id, t.nombre, t.descripcion, t.directorioImagen) "
    			+ "FROM Categoria c, TipoProducto t "
    			+ "WHERE c.id=t.categoria and t.id="+idTipoProducto;
		
		return  (DTOTipoProductoCU05) DAOEntity.get().getSingleResult(consulta, DTOTipoProductoCU05.class);
	}

	@SuppressWarnings("unchecked")
	public List<DTOCU06> getPaquetes() {
		String consulta = "SELECT new dto.DTOCU06(e.id, t.id, t.nombre, p.id, e.tipoVenta, pro.nombre, p.codigoBarra, p.vencimiento) "
				+ "FROM TipoProducto t, ProductoInicial p, Proveedor pro, Empaquetado e "
				+ "WHERE t.id=p.tipoProducto AND p.proveedor=pro.id AND p.id=e.productoInicial "
				+ "AND e.vendido = false AND e.dadoBaja=false ORDER BY t.nombre ASC"; 
		
		List<DTOCU06> lista = (List<DTOCU06>) DAOEntity.get().getResultList(consulta, DTOCU06.class);
		
		Iterator<DTOCU06> ite = lista.iterator();
		
		while(ite.hasNext()) {
			DTOCU06 dto = ite.next();
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdTipoProducto());
			
			Iterator<Precio> precios = t.getPrecios().iterator();
			TipoPaquete tipoVenta = dto.getFormaVentaE();
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
	
	@SuppressWarnings("unchecked")
	public List<DTOCU08> buscarProductosIniciales(Integer idCategoria, Integer idProveedor, String textCodigoBarra,
			String nombreProducto, LocalDate fechaIngAntes, LocalDate fechaIngDespues, Boolean disponible) {
		
		String consultaFalse="", consultaTrue = "SELECT new dto.DTOCU08(t.id, p.id, t.nombre, p.codigoBarra, c.nombre, pro.nombre, p.fechaEntrada, p.vencimiento, p.precioComprado, p.cantidadNoVendida, p.disponible, t.directorioImagen) "
				+ "FROM Categoria c, TipoProducto t, ProductoInicial p, Proveedor pro "
				+ "WHERE c.id=t.categoria AND t.id=p.tipoProducto AND pro.id=p.proveedor ";

		if(idCategoria != null)
			consultaTrue = consultaTrue + " AND c.id="+idCategoria;		
		if(idProveedor != null)
			consultaTrue = consultaTrue + " AND pro.id="+idProveedor;		
		if(textCodigoBarra != null)
			consultaTrue = consultaTrue + " AND p.codigoBarra LIKE '%"+textCodigoBarra+"%'";		
		if(nombreProducto != null)
			consultaTrue = consultaTrue + " AND t.nombre LIKE '%"+nombreProducto+"%'";
		
		if(fechaIngAntes != null) {
			if(fechaIngDespues != null) {				
				if(fechaIngAntes.isAfter(fechaIngDespues))
					consultaTrue = consultaTrue + " AND t.fechaEntrada BETWEEN '"+fechaIngDespues+"' AND '"+fechaIngAntes+"'";
				else
					consultaTrue = consultaTrue + " AND t.fechaEntrada BETWEEN '"+fechaIngAntes+"' AND '"+fechaIngDespues+"'";
			}
			else
				consultaTrue = consultaTrue + " AND '"+fechaIngAntes+"'>=t.fechaEntrada";	
		}
		else {
			if(fechaIngDespues != null)
				consultaTrue = consultaTrue + " AND t.fechaEntrada>='"+fechaIngDespues+"'";	
		}
		
		if(disponible != null)
			consultaTrue = consultaTrue + " AND p.disponible="+disponible;
		else {
			consultaFalse = consultaTrue + " AND p.disponible=false ORDER BY t.nombre ASC";
			consultaTrue = consultaTrue + " AND p.disponible=true";
		}

		consultaTrue = consultaTrue + " ORDER BY t.nombre ASC"; 
		
		List<DTOCU08> lista = (List<DTOCU08>) DAOEntity.get().getResultList(consultaTrue, DTOCU08.class);
		
		if(!consultaFalse.isBlank()) {
			lista.addAll((List<DTOCU08>) DAOEntity.get().getResultList(consultaFalse, DTOCU08.class));
		}		
		
		Iterator<DTOCU08> ite = lista.iterator();
		while(ite.hasNext()) {
			DTOCU08 dto = ite.next();
			
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, dto.getIdTipoProducto());
			
			List<Precio> precios = t.getPrecios();
			Iterator<Precio> preciosIterator = precios.iterator();
				
			while(preciosIterator.hasNext()) {
				Precio p = preciosIterator.next();
				TipoPaquete tipoVenta = p.getTipoVenta();
				switch(tipoVenta) {
					case G100:
					case G250:
					case G500:
					case G1000:
					case G2000:
					break;
						
					case UNIDAD:
						dto.setPrecioUnidad(p.getValor());
					break;
				}
			}			
			
		}
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<DTOTipoProducto> getTiposProducto(Integer idCategoria) {
		String consulta = "SELECT new dto.DTOTipoProducto(t.id, t.nombre) FROM TipoProducto t WHERE t.categoria="+idCategoria+" ORDER BY t.nombre ASC"; 
			
		return (List<DTOTipoProducto>) DAOEntity.get().getResultList(consulta, DTOTipoProducto.class);
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
	
	@SuppressWarnings("unchecked")
	public List<DTOTipoProductoCU10> getTiposProducto() {
		String consulta = "SELECT new dto.DTOTipoProductoCU10(t.id, t.nombre) FROM TipoProducto t WHERE t.enVenta=true ORDER BY t.nombre ASC"; 
		
		
		List<DTOTipoProductoCU10> listaProductos =  (List<DTOTipoProductoCU10>) DAOEntity.get().getResultList(consulta, DTOTipoProductoCU10.class);
		Iterator<DTOTipoProductoCU10> iteratorProductos = listaProductos.iterator();
		List<DTOTipoProductoCU10> listaProductosFinal = new ArrayList<DTOTipoProductoCU10>();

		while(iteratorProductos.hasNext()) {
			DTOTipoProductoCU10 dto = iteratorProductos.next();
			String consulta2 = "select count(distinct p.id) from tipo_producto t, producto_inicial p, precio pre "
					+ "where t.id="+dto.getId()+" and p.id_tipo_producto=t.id and p.disponible=true and t.id=pre.id_tipo_producto and not pre.tipo_venta='UNIDAD'";
			

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
						
					break;
				}
			}
			
			if(DAOEntity.get().getCantidad(consulta2)>=1) {
				consulta = "SELECT new dto.DTOProductoInicialCU10(t.id, p.id, p.cantidadNoVendida, p.codigoBarra, p.vencimiento, pro.nombre) "
						+ "FROM ProductoInicial p, Proveedor pro, TipoProducto t "
						+ "WHERE t.id=p.tipoProducto AND pro.id=p.proveedor AND p.disponible=true AND p.tipoProducto="+dto.getId();
				
				dto.setProductosIniciales((List<DTOProductoInicialCU10>) DAOEntity.get().getResultList(consulta, DTOProductoInicialCU10.class));
					
				listaProductosFinal.add(dto);						
			}
		}
		return listaProductosFinal;
	}

	@SuppressWarnings("unchecked")
	public List<DTOCU16> buscarProductosEmpaquetados(Integer idCategoria, String nombreProducto, String textFormaVenta, String textCodigoBarra, Integer idProveedor,
			Boolean dadoBaja, Boolean vendido, LocalDate fechaVenAntes, LocalDate fechaVenDespues) {
		String consulta = "SELECT new dto.DTOCU16(t.id, p.id, e.id, t.nombre, p.vencimiento, p.codigoBarra, c.nombre, pro.nombre, e.tipoVenta, e.fechaSalida, e.precio, e.vendido, e.dadoBaja) "
				+ "FROM Categoria c, TipoProducto t, ProductoInicial p, Proveedor pro, Empaquetado e "
				+ "WHERE c.id=t.categoria AND t.id=p.tipoProducto AND pro.id=p.proveedor AND e.productoInicial=p.id ",
			   consulta2 = "", consulta3 = "";

		
		if(idCategoria != null) {
			consulta = consulta + " AND c.id="+idCategoria;
		}
		
		if(nombreProducto != null)
			consulta = consulta + " AND t.nombre LIKE '%"+nombreProducto+"%'";
		
		if(idProveedor != null)
			consulta = consulta + " AND pro.id="+idProveedor;
		
		if(!textFormaVenta.isBlank())
			consulta = consulta + " AND e.tipoVenta='"+TipoPaquete.valueOf(textFormaVenta).name()+"'";
		
		if(!textCodigoBarra.isBlank())
			consulta = consulta + " AND p.codigoBarra LIKE '%"+textCodigoBarra+"%'";
			
		
		if(fechaVenAntes != null) {
			if(fechaVenDespues != null) {				
				if(fechaVenAntes.isAfter(fechaVenDespues))
					consulta = consulta + " AND e.fechaSalida BETWEEN '"+fechaVenDespues+"' AND '"+fechaVenAntes+"'";
				else
					consulta = consulta + " AND e.fechaSalida BETWEEN '"+fechaVenAntes+"' AND '"+fechaVenDespues+"'";
			}
			else
				consulta = consulta + " AND '"+fechaVenAntes+"'>=e.fechaSalida";	
		}
		else {
			if(fechaVenDespues != null)
				consulta = consulta + " AND e.fechaSalida>='"+fechaVenDespues+"'";	
		}
		
		if(dadoBaja != null) {			
			if(dadoBaja == false) {
				consulta = consulta + " AND e.dadoBaja=false";
				if(vendido != null) {
					if(vendido) {
						consulta = consulta + " AND e.vendido=true";
					}
					else {
						consulta = consulta + " AND e.vendido=false";
					}
				}
				else {
					consulta2 = consulta + " AND e.vendido=false";
					consulta = consulta + " AND e.vendido=true";
				}
			}
			else {
				consulta = consulta + " AND e.dadoBaja=true";
			}
		}
		else {
			consulta2 = consulta + " AND e.dadoBaja=true";
			consulta = consulta + " AND e.dadoBaja=false";
			
			if(vendido != null) {
				if(vendido) {
					consulta = consulta + " AND e.vendido=true";
				}
				else {
					consulta = consulta + " AND e.vendido=false";
				}
			}
			else {
				consulta3 = consulta + " AND e.vendido=false";
				consulta = consulta + " AND e.vendido=true";
			}
		}
		

		consulta = consulta + " ORDER BY t.nombre ASC"; 
		
		List<DTOCU16> lista = (List<DTOCU16>) DAOEntity.get().getResultList(consulta, DTOCU16.class);
		
		if(!consulta2.isBlank()) {
			consulta2 = consulta2 + " ORDER BY t.nombre ASC"; 
			lista.addAll((List<DTOCU16>) DAOEntity.get().getResultList(consulta2, DTOCU16.class));
		}
		
		if(!consulta3.isBlank()) {
			consulta3 = consulta3 + " ORDER BY t.nombre ASC"; 
			lista.addAll((List<DTOCU16>) DAOEntity.get().getResultList(consulta3, DTOCU16.class));
		}
		
		return lista;
	}
	
	public Boolean cantProductosTipoProducto(Integer idTipoProducto) {
		String consulta = "select count(p.id) from tipo_producto t, producto_inicial p where t.id="+idTipoProducto+" and p.id_tipo_producto=t.id and p.disponible=true";
		
		String consulta2 = "select count(e.id) from tipo_producto t, producto_inicial p, empaquetado e where t.id="+idTipoProducto
				+ " and p.id_tipo_producto=t.id and e.id_producto_inicial=p.id and e.dado_baja=false and e.vendido=false";

		Integer cantProductosIniciales = DAOEntity.get().getCantidad(consulta);
		Integer cantEmpaquetados = DAOEntity.get().getCantidad(consulta2);
		
		if(cantEmpaquetados<1 && cantProductosIniciales<1) {
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, idTipoProducto);
			t.setEnVenta(false);
			if(!DAOEntity.get().update(t))
				return false;
		}
		else {
			TipoProducto t = (TipoProducto) DAOEntity.get().get(TipoProducto.class, idTipoProducto);
			t.setEnVenta(true);
			if(!DAOEntity.get().update(t))
				return false;
		}
		
		return true;
	}
}
