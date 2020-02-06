package entity;

import java.util.List;
import enums.Categoria;

public class TipoProducto {
	
	private Categoria categoria;
	private List<ProductoTotal> productos;
	private List<Precio> precios;

	
	private Integer id;
	private String nombre;
	private String descripcion;	
	private Boolean enVenta;
	private String directorioImagen;

	public Categoria getCategoria() {
		return categoria;
	}

	public List<ProductoTotal> getProductos() {
		return productos;
	}

	public List<Precio> getPrecios() {
		return precios;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean getEnVenta() {
		return enVenta;
	}	

	public String getDirectorioImagen() {
		return directorioImagen;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setProductos(List<ProductoTotal> productos) {
		this.productos = productos;
	}

	public void setPrecios(List<Precio> precios) {
		this.precios = precios;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEnVenta(Boolean enVenta) {
		this.enVenta = enVenta;
	}

	public void setDirectorioImagen(String directorioImagen) {
		this.directorioImagen = directorioImagen;
	}
	
	
}
