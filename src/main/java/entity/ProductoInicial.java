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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "producto_inicial")
public class ProductoInicial {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_producto", nullable = false)
	private TipoProducto tipoProducto;		
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto_inicial", foreignKey=@ForeignKey(name = "fk_producto_inicial"))
	@OrderBy("id asc")
	private List<Empaquetado> empaquetado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", foreignKey=@ForeignKey(name = "fk_proveedor"), nullable = false)
	private Proveedor proveedor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_producto_inicial")
	@SequenceGenerator(name="id", sequenceName = "id_producto_inicial_seq", initialValue = 100, allocationSize = 1)
	private Integer id;	
	
	@Column(nullable = false, name = "precio_comprado")
	private Float precioComprado;	
	
	@Column(name = "cantidad_no_vendida")
	private Float cantidadNoVendida;
	
	@Column(nullable = false)
	private Boolean disponible;	
	
	@Column(nullable = false)
	private LocalDate vencimiento;
	
	@Column(name = "codigo_barra")
	private String codigoBarra;
	
	@Column(nullable = false, name = "fecha_entrada")
	private LocalDate fechaEntrada;

	public ProductoInicial() {}
	
	public TipoProducto getTipoProducto() { return tipoProducto; }
	public List<Empaquetado> getEmpaquetado() { return empaquetado; }
	public Proveedor getProveedor() { return proveedor; }
	public Integer getId() { return id; }
	public Float getPrecioComprado() { return precioComprado; }
	public Float getCantidadNoVendida() { return cantidadNoVendida; }
	public Boolean getDisponible() { return disponible; }
	public LocalDate getVencimiento() { return vencimiento;	}
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getFechaEntrada() { return fechaEntrada; }
	
	public void setTipoProducto(TipoProducto tipoProducto) { this.tipoProducto = tipoProducto; }
	public void setEmpaquetado(List<Empaquetado> productoEmpaquetado) { this.empaquetado = productoEmpaquetado; }
	public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
	public void setId(Integer id) { this.id = id; }
	public void setPrecioComprado(Float precioComprado) { this.precioComprado = precioComprado; }
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = cantidadNoVendida; }
	public void setDisponible(Boolean disponible) { this.disponible = disponible; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }
}
