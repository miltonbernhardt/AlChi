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
import app.ExceptionPane;
import database.DAOEntity;
import dto.DTOCategoria;
import dto.DTOTipoProductoCU02;
import dto.DTOTipoProductoCU05;
import entity.Categoria;
import entity.Precio;
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
    
	@SuppressWarnings("unchecked")
	public List<DTOCategoria> getDTOCategorias() {	
		String consulta = "SELECT new dto.DTOCategoria(c.id, c.nombre) FROM Categoria c ORDER BY c.nombre ASC";
		
		return (List<DTOCategoria>) DAOEntity.get().getResultList(consulta, new DTOCategoria());
	}

	public Boolean agregarTipoProducto(Integer idCategoria, String nombreProducto, String descripcionProducto,
			URI imagenPath) { 
		Categoria cat = new Categoria();
		cat.setId(idCategoria);
		
		TipoProducto t = new TipoProducto();
		t.setCategoria(cat);
		t.setNombre(nombreProducto);
		t.setDescripcion(descripcionProducto);
		t.setEnVenta(true);
		
		if(!(imagenPath == null)) {
			Path imagenOriginal = Paths.get(imagenPath);
			Path directorioImagenes = Paths.get("D:/AlChi/Imágenes");
			Path pathFinal = null;
			String extension = "";
			
			try {				
				Files.createDirectories(directorioImagenes);
				extension = imagenPath.toURL().toString().substring(imagenPath.toURL().toString().lastIndexOf('.'));				
				pathFinal = directorioImagenes.resolve(nombreProducto+extension);				
				Files.copy(imagenOriginal, pathFinal, StandardCopyOption.REPLACE_EXISTING);
				
				t.setDirectorioImagen(pathFinal.toUri().toString());
			} catch (IOException e) {				
				new ExceptionPane(e, "Error al copiar la imágen.");				
			}
		}
		else {
			t.setDirectorioImagen("");
		}
		
		List<Precio> precios = new ArrayList<Precio> ();		
		for(int i = 0; i<TipoPaquete.values().length; i++) {
			Precio p = new Precio();
			p.setTipoProducto(t);
			p.setFecha(LocalDate.now());
			p.setTipoVenta(TipoPaquete.values()[i]);
			p.setValor(0f);
			precios.add(p);
		}
		t.setPrecios(precios);
		
		return DAOEntity.get().save(t);
	}
	
	public Boolean updateTipoProducto(Integer idCategoria, Integer idProducto, String nombreProducto, String descripcionProducto,
			URI imagenPath) {
		TipoProducto t = (TipoProducto) DAOEntity.get().get(idProducto, new TipoProducto());
		Categoria cat = (Categoria) DAOEntity.get().get(idCategoria, new Categoria());
		t.setCategoria(cat);
		t.setNombre(nombreProducto);
		t.setDescripcion(descripcionProducto);
		
		if(!(imagenPath == null)) {
			Path imagenOriginal = Paths.get(imagenPath);
			Path directorioImagenes = Paths.get("D:/AlChi/Imágenes");
			Path pathFinal = null;
			//TODO reemplzar user.home por el de la imagenes en todos
			String extension = "";
			
			try {				
				Files.createDirectories(directorioImagenes);
				extension = imagenPath.toURL().toString().substring(imagenPath.toURL().toString().lastIndexOf('.'));				
				pathFinal = directorioImagenes.resolve(nombreProducto+extension);				
				Files.copy(imagenOriginal, pathFinal, StandardCopyOption.REPLACE_EXISTING);
				
				t.setDirectorioImagen(pathFinal.toUri().toString());
			} catch (IOException e) {				
				new ExceptionPane(e, "Error al copiar la imágen.");				
			}
		}
		else {
			if(!t.getDirectorioImagen().isEmpty()) {
				new File(URI.create(t.getDirectorioImagen())).delete();
			}			
			t.setDirectorioImagen("");
		}
		
		return DAOEntity.get().update(t);
	}

	public DTOTipoProductoCU05 getTipoProducto() {	
		String consulta = "SELECT new dto.DTOTipoProductoCU05(c.id, c.nombre, t.id, t.nombre, t.descripcion, t.directorioImagen) "
    			+ "FROM Categoria c, TipoProducto t "
    			+ "WHERE c.id=t.categoria and t.id="+103;
		
		return  (DTOTipoProductoCU05) DAOEntity.get().getSingleResult(consulta, new DTOTipoProductoCU05());
	}

	@SuppressWarnings("unchecked")
	public List<DTOTipoProductoCU02> buscarTiposProductos(Integer idCategoria, String nombreProducto, Boolean vende) {
		String consulta = "SELECT new dto.DTOTipoProductoCU02(c.nombre, t.id, t.nombre, t.enVenta) "
				+ "FROM Categoria c, TipoProducto t WHERE c.id=t.categoria ";
		
		// Float precio100,	Float precio250, Float precio500, Float precio1000, Float precio2000, Float precioUnidad
		if(idCategoria != null) {
			consulta = consulta + " AND c.id="+idCategoria;
		}
		
		if(nombreProducto != null) {
			consulta = consulta + " AND t.nombre LIKE '%"+nombreProducto+"%'";
		}
		
		if(vende != null) {
			if(vende) {
				consulta = consulta + " AND t.enVenta=true";
			}
			else {
				consulta = consulta + " AND t.enVenta=false";
			}			
		}

		consulta = consulta + " ORDER BY t.nombre ASC"; 
		
		List<DTOTipoProductoCU02> lista = (List<DTOTipoProductoCU02>) DAOEntity.get().getResultList(consulta, new DTOTipoProductoCU02());
		
		Iterator<DTOTipoProductoCU02> listaIterator = lista.iterator();
		
		while(listaIterator.hasNext()) {
			DTOTipoProductoCU02 dto = listaIterator.next();
			
			TipoProducto t = (TipoProducto) DAOEntity.get().get(dto.getIdProducto(), new TipoProducto());
			
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
}
