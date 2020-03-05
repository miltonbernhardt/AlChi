package dto;

import java.util.Objects;

public class DTOTipoProducto {
	private Integer id;
	private String nombre;
	
	public DTOTipoProducto() {  }
	
	public DTOTipoProducto(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Integer getId() { return id; }
	public String getNombre() { return nombre; }
	
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOTipoProducto other = (DTOTipoProducto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}	
}
