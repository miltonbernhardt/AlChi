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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_bitacora_entrada", foreignKey=@ForeignKey(name = "fk_bitacora_entrada"), nullable = false)
	private BitacoraEntrada bitacoraEntrada;	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empaquetado", foreignKey=@ForeignKey(name = "fk_empaquetado"))
	@OrderBy("id asc")
	private List<Empaquetado> empaquetado;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_producto_inicial")
	private EmpaquetadoConjunto empaquetadoConjunto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", foreignKey=@ForeignKey(name = "fk_proveedor"), nullable = false)
	private Proveedor proveedor;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_producto_total")
	@SequenceGenerator(name="id_producto_total", sequenceName = "id_producto_total_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
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

	public ProductoInicial() {}
	
	public TipoProducto getTipoProducto() { return tipoProducto; }
	public BitacoraEntrada getBitacoraEntrada() { return bitacoraEntrada; }
	public List<Empaquetado> getEmpaquetado() { return empaquetado; }
	public EmpaquetadoConjunto getEmpaquetadoConjunto() { return empaquetadoConjunto; }
	public Proveedor getProveedor() { return proveedor; }
	public Integer getId() { return id; }
	public Float getPrecioComprado() { return precioComprado; }
	public Float getCantidadNoVendida() { return cantidadNoVendida; }
	public Boolean getDisponible() { return disponible; }
	public LocalDate getVencimiento() { return vencimiento;	}
	public String getCodigoBarra() { return codigoBarra; }
	
	public void setTipoProducto(TipoProducto tipoProducto) { this.tipoProducto = tipoProducto; }
	public void setBitacoraEntrada(BitacoraEntrada bitacorasEntrada) { this.bitacoraEntrada = bitacorasEntrada; }
	public void setEmpaquetado(List<Empaquetado> productoEmpaquetado) { this.empaquetado = productoEmpaquetado; }
	public void setEmpaquetadoConjunto(EmpaquetadoConjunto empaquetadoConjunto) { this.empaquetadoConjunto = empaquetadoConjunto; }
	public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
	public void setId(Integer id) { this.id = id; }
	public void setPrecioComprado(Float precioComprado) { this.precioComprado = precioComprado; }
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = cantidadNoVendida; }
	public void setDisponible(Boolean disponible) { this.disponible = disponible; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
}
