package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_proveedor")
	@SequenceGenerator(name="id_proveedor", sequenceName = "id_proveedor_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false, name = "id_proveedor")
	private Integer id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column
	private String numeroTelefono;
	
	public Proveedor() { }
	
	public Integer getId() { return id; }	
	public String getNombre() { return nombre; }
	public String getNumeroTelefono() { return numeroTelefono; }
	
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }
}
