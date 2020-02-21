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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_producto")
@NamedEntityGraph(name = "graph.TipoProducto.productos", attributeNodes = @NamedAttributeNode("productos"))
public class TipoProducto{
	
	@OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_producto", foreignKey=@ForeignKey(name = "fk_tipo_producto"))
	@OrderBy("id asc")
	private List<ProductoInicial> productos;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipo_producto", foreignKey=@ForeignKey(name = "fk_tipo_producto"))
	@OrderBy("id asc")
	private List<Precio> precios;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_tipo_producto")
	@SequenceGenerator(name="id_tipo_producto", sequenceName = "id_tipo_producto_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	private String nombre;
	
	private String descripcion;	
	
	@Column(nullable = false, name = "en_venta")
	private Boolean enVenta;
	
	@Column(name = "directorio_imagen")
	private String directorioImagen;
	
	public TipoProducto() {}

	public Categoria getCategoria() { return categoria; }
	public List<ProductoInicial> getProductos() { return productos; }
	public List<Precio> getPrecios() { return precios; }
	public Integer getId() { return id; }
	public String getNombre() { return nombre; }
	public String getDescripcion() { return descripcion; }
	public Boolean getEnVenta() { return enVenta; }	
	public String getDirectorioImagen() { return directorioImagen; }

	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	public void setProductos(List<ProductoInicial> productos) { this.productos = productos; }
	public void setPrecios(List<Precio> precios) { this.precios = precios; }
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public void setEnVenta(Boolean enVenta) { this.enVenta = enVenta; }
	public void setDirectorioImagen(String directorioImagen) { this.directorioImagen = directorioImagen; }

	@Override
	public String toString() {
		return nombre;
	}	
}
