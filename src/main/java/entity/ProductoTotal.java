package entity;

import java.time.LocalDate;
import java.util.List;

public class ProductoTotal {
	
	private TipoProducto tipoProducto;	
	private List<BitacoraEntrada> bitacorasEntrada;	
	private List<ProductoEmpaquetado> productoEmpaquetado;
	
	//manyToOne
	private Proveedor proveedor;
	private Integer id;	
	private Float precioComprado;	
	private Float cantidadNoVendida;	
	private Boolean disponible;	
	private LocalDate vencimiento;
	private String codigoBarra;

	
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public List<BitacoraEntrada> getBitacorasEntrada() {
		return bitacorasEntrada;
	}

	public List<ProductoEmpaquetado> getProductoEmpaquetado() {
		return productoEmpaquetado;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public Integer getId() {
		return id;
	}

	public Float getPrecioComprado() {
		return precioComprado;
	}

	public Float getCantidadNoVendida() {
		return cantidadNoVendida;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public void setBitacorasEntrada(List<BitacoraEntrada> bitacorasEntrada) {
		this.bitacorasEntrada = bitacorasEntrada;
	}

	public void setProductoEmpaquetado(List<ProductoEmpaquetado> productoEmpaquetado) {
		this.productoEmpaquetado = productoEmpaquetado;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrecioComprado(Float precioComprado) {
		this.precioComprado = precioComprado;
	}

	public void setCantidadNoVendida(Float cantidadNoVendida) {
		this.cantidadNoVendida = cantidadNoVendida;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
}
