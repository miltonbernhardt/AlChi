package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "combo")
public class Combo {
		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_combo", foreignKey=@ForeignKey(name = "fk_combo"))
	@OrderBy("id asc")
	private List<Empaquetado> productos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bitacora_salida")
	private BitacoraSalida bitacora;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_combo")
	@SequenceGenerator(name="id_combo", sequenceName = "id_combo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;

	@Column(nullable = false)
	private Float precio;	
	
	public Combo() {}

	public List<Empaquetado> getProductos() { return productos; }
	public BitacoraSalida getBitacora() { return bitacora; }
	public Integer getId() { return id; }
	public Float getPrecio() { return precio; }

	public void setProductos(List<Empaquetado> productos) { this.productos = productos; }
	public void setBitacora(BitacoraSalida bitacora) { this.bitacora = bitacora; }
	public void setId(Integer id) { this.id = id; }
	public void setPrecio(Float precio) { this.precio = precio; }	
}
