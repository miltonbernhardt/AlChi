package entity;

import enums.TipoPaquete;
import java.time.LocalDate;
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

@Entity
@Table
public class Empaquetado {	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto_inicial", nullable = false)
	private ProductoInicial productoInicial;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_producto_inicial_opcional", foreignKey=@ForeignKey(name = "fk_producto_inicial_opcional"))
	private ProductoInicial productoInicialOpcional;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_empaquetado")
	@SequenceGenerator(name="id", sequenceName = "id_empaquetado_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPaquete tipoVenta;
	
	@Column(nullable = false)
	private Boolean vendido;
	
	@Column(nullable = false, name = "dado_baja")
	private Boolean dadoBaja;
	
	@Column
	private Float precio;
	
	@Column(nullable = false, name = "cantidad_producto_principal")
	private Float cantidadProductoPrincipal;

	@Column(name = "fecha_salida")
	private LocalDate fechaSalida;

	public Empaquetado() {}
	
	public ProductoInicial getProductoInicial() { return productoInicial; }	
	public ProductoInicial getProductoInicialOpcional() { return productoInicialOpcional; }	
	public Integer getId() { return id; }	
	public TipoPaquete getTipoVenta() { return tipoVenta; }	
	public Boolean getVendido() { return vendido; }
	public Boolean getDadoBaja() { return dadoBaja; }
	public Float getPrecio() { return precio; }
	public Float getCantidadProductoPrincipal() { return cantidadProductoPrincipal; }
	public LocalDate getFechaSalida() { return fechaSalida; }
	
	public void setProductoInicial(ProductoInicial productoInicial) { this.productoInicial = productoInicial; }	
	public void setProductoInicialOpcional(ProductoInicial productoInicialOpcional) { this.productoInicialOpcional = productoInicialOpcional; }	
	public void setId(Integer id) { this.id = id; }	
	public void setTipoVenta(TipoPaquete tipoVenta) { this.tipoVenta = tipoVenta; }	
	public void setVendido(Boolean vendido) { this.vendido = vendido; }	
	public void setDadoBaja(Boolean dadoBaja) { this.dadoBaja = dadoBaja; }
	public void setPrecio(Float precio) { this.precio = precio; }
	public void setCantidadProductoPrincipal(Float cantidadProductoPrincipal) { this.cantidadProductoPrincipal = cantidadProductoPrincipal; }
	public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
}
