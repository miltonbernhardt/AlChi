package dto;

public class DTOCategoria {
	private Integer id;
	private String nombre;
	
	public DTOCategoria() {  }
	
	public DTOCategoria(Integer id, String nombre) {
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
}
