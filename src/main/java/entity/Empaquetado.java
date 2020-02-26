package entity;

import enums.TipoPaquete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	@JoinColumn(name = "id_producto_inicial")
	private ProductoInicial productoInicial;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	@SequenceGenerator(name="id", sequenceName = "id_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPaquete tipoVenta;
	
	@Column(nullable = false)
	private Boolean vendido;
	
	@Column
	private Float precio;

	public Empaquetado() {}
	
	public ProductoInicial getProductoInicial() { return productoInicial; }	
	public Integer getId() { return id; }	
	public TipoPaquete getTipoVenta() { return tipoVenta; }	
	public Boolean getVendido() { return vendido; }
	public Float getPrecio() { return precio; }
	
	public void setProductoInicial(ProductoInicial productoTotal) { this.productoInicial = productoTotal; }	
	public void setId(Integer id) { this.id = id; }	
	public void setTipoVenta(TipoPaquete tipoVenta) { this.tipoVenta = tipoVenta; }	
	public void setVendido(Boolean vendido) { this.vendido = vendido; }	
	public void setPrecio(Float precio) { this.precio = precio; }
}
