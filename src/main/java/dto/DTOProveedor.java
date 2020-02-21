package dto;

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
}
