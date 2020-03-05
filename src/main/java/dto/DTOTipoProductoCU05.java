package dto;

import java.util.Objects;

public class DTOTipoProductoCU05 {
	private Integer idCategoria;
	private String nombreCategoria;
	private Integer idProducto;
	private String nombreTipoProducto;
	private String descripcion;
	private String directorioImagen;

	public DTOTipoProductoCU05() {	}
	
	public DTOTipoProductoCU05(Integer idCategoria, String nombreCategoria, Integer idProducto,
			String nombreTipoProducto, String descripcion, String directorioImagen) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.descripcion = descripcion;
		this.directorioImagen = directorioImagen;
	}

	public Integer getIdCategoria() { return idCategoria; }
	public String getNombreCategoria() { return nombreCategoria; }
	public Integer getIdProducto() { return idProducto; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public String getDescripcion() { return descripcion; }
	public String getDirectorioImagen() { return directorioImagen; }

	public void setIdCategoria(Integer idCategoria) { 	this.idCategoria = idCategoria; }
	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
	public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
	public void setNombreTipoProducto(String nombreTipoProducto) { this.nombreTipoProducto = nombreTipoProducto; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public void setDirectorioImagen(String directorioImagen) { this.directorioImagen = directorioImagen; }
	
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, directorioImagen, idCategoria, idProducto, nombreCategoria,
				nombreTipoProducto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOTipoProductoCU05 other = (DTOTipoProductoCU05) obj;
		return Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(directorioImagen, other.directorioImagen)
				&& Objects.equals(idCategoria, other.idCategoria) && Objects.equals(idProducto, other.idProducto)
				&& Objects.equals(nombreCategoria, other.nombreCategoria)
				&& Objects.equals(nombreTipoProducto, other.nombreTipoProducto);
	}
}
