package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import enums.TipoPaquete;

@Entity
@Table(name = "empaquetado_conjunto")
public class EmpaquetadoConjunto {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto_inicial1", foreignKey=@ForeignKey(name = "fk_producto_inicial1"), nullable = false)
	private ProductoInicial productoInicial1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto_inicial2", foreignKey=@ForeignKey(name = "fk_producto_inicial2"), nullable = false)
	private ProductoInicial productoInicial2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_empaquetado_conjunto")
	@SequenceGenerator(name="id_empaquetado_conjunto", sequenceName = "id_empaquetado_conjunto_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPaquete tipoVenta;
	
	@Column(nullable = false)
	private Boolean vendido;
	
	public EmpaquetadoConjunto() {}
	
	public ProductoInicial getProductoInicial1() { return productoInicial1; }
	public ProductoInicial getProductoInicial2() { return productoInicial2; }	
	public Integer getId() { return id; }	
	public TipoPaquete getTipoVenta() { return tipoVenta; }	
	public Boolean getVendido() { return vendido; }
	
	public void setProductoInicial1(ProductoInicial productoTotal1) { this.productoInicial1 = productoTotal1; }
	public void setProductoInicial2(ProductoInicial productoTotal2) { this.productoInicial2 = productoTotal2; }	
	public void setId(Integer id) { this.id = id; }	
	public void setTipoVenta(TipoPaquete tipoVenta) { this.tipoVenta = tipoVenta; }	
	public void setVendido(Boolean vendido) { this.vendido = vendido; }	

}
