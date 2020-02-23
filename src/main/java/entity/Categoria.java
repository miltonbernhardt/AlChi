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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Categoria {		
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", foreignKey=@ForeignKey(name = "fk_categoria"))
	@OrderBy("id asc")
	private List<TipoProducto> productos;
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_categoria")
	@SequenceGenerator(name="id_categoria", sequenceName = "id_categoria_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
		
	@Column(nullable = false)
	private String nombre;

	public List<TipoProducto> getProductos() { return productos; }
	public Integer getId() { return id; }
	public String getNombre() { return nombre; }

	public void setProductos(List<TipoProducto> productos) { this.productos = productos; }
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
}

