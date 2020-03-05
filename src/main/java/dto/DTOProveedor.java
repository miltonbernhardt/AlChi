package dto;

import java.util.Objects;

public class DTOProveedor {

	private Integer id;
	private String nombre;
	private String numeroTelefono;
	
	public DTOProveedor() { }	
	
	public DTOProveedor(Integer id, String nombre, String numeroTelefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.numeroTelefono = numeroTelefono;
	}
	
	public DTOProveedor(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() { return id; }	
	public String getNombre() { return nombre; }
	public String getNumeroTelefono() { return numeroTelefono; }
	
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }
	
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, numeroTelefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOProveedor other = (DTOProveedor) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(numeroTelefono, other.numeroTelefono);
	}	
}
