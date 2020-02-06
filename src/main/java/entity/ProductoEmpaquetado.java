package entity;

import enums.TipoPaquete;

public class ProductoEmpaquetado {
	
	private ProductoTotal productoTotal;
	
	private Integer id;
	private TipoPaquete tipoVenta;
	private Boolean vendido;
	
	
	public ProductoTotal getProductoTotal() {
		return productoTotal;
	}
	
	public Integer getId() {
		return id;
	}
	
	public TipoPaquete getTipoVenta() {
		return tipoVenta;
	}
	
	public Boolean getVendido() {
		return vendido;
	}
	
	public void setProductoTotal(ProductoTotal productoTotal) {
		this.productoTotal = productoTotal;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setTipoVenta(TipoPaquete tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
	
	public void setVendido(Boolean vendido) {
		this.vendido = vendido;
	}
	
}
