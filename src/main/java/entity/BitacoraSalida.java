package entity;

import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "bitacora_salida")
public class BitacoraSalida {

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bitacora_salida", foreignKey=@ForeignKey(name = "fk_bitacora_salida"))
	@IndexColumn(name = "idx")
	private List<ProductoEmpaquetado> productos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bitacora_salida", foreignKey=@ForeignKey(name = "fk_bitacora_salida"))
	@IndexColumn(name = "idx")
	private List<Combo> combos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_combo")
	@SequenceGenerator(name="id_combo", sequenceName = "id_combo_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	private LocalDate fecha;

	public BitacoraSalida() {}
	
	public List<ProductoEmpaquetado> getProductos() { return productos; }
	public List<Combo> getCombos() { return combos; }
	public Integer getId() { return id; }
	public LocalDate getFecha() { return fecha; }
	
	public void setProductos(List<ProductoEmpaquetado> productos) { this.productos = productos; }
	public void setCombos(List<Combo> combos) { this.combos = combos; }
	public void setId(Integer id) { this.id = id; }
	public void setFecha(LocalDate fecha) { this.fecha = fecha; }		
}
