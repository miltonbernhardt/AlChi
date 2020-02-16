package dto;

import java.time.LocalDate;

public class DTOProductoInicial {

	private Integer idTipoProducto;	
	private String nombreTipoProducto;	
	private Integer idProveedor;
	private String nombreProveedor;
	private Integer idProductoInicial;
	private Integer nombreProductoInicial;
	private Float precioComprado;	
	private Float cantidadNoVendida;
	private LocalDate vencimiento;
	private String codigoBarra;
	
	private Float precio100;	
	private Float precio250;	
	private Float precio500;	
	private Float precio1000;	
	private Float precio2000;	
	private Float precioUnidad;		

	public DTOProductoInicial() {
		precio100 = 0f;	
		precio250 = 0f;	
		precio500 = 0f;	
		precio1000 = 0f;	
		precio2000 = 0f;	
		precioUnidad = 0f;	
	}

	public Integer getIdTipoProducto() { return idTipoProducto; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public Integer getIdProveedor() { return idProveedor; }
	public String getNombreProveedor() { return nombreProveedor; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public Integer getNombreProductoInicial() { return nombreProductoInicial; }
	public LocalDate getVencimiento() { return vencimiento; }
	public String getCodigoBarra() { return codigoBarra; }
	public Float getPrecioCompradoF() { return precioComprado; }
	public Float getCantidadNoVendidaF() { return cantidadNoVendida; }
	public Float getPrecio100F() { return precio100; }
	public Float getPrecio250F() { return precio250; }
	public Float getPrecio500F() { return precio500; }
	public Float getPrecio1000F() { return precio1000; }
	public Float getPrecio2000F() { 	return precio2000; }
	public Float getPrecioUnidadF() { return precioUnidad; }
	
	public String getPrecioComprado() { return precioComprado.toString()+" $"; }
	public String getCantidadNoVendida() { return cantidadNoVendida.toString()+" $"; }
	public String getPrecio100() { return precio100.toString()+" $"; }
	public String getPrecio250() { return precio250.toString()+" $"; }
	public String getPrecio500() { return precio500.toString()+" $"; }
	public String getPrecio1000() { return precio1000.toString()+" $"; }
	public String getPrecio2000() { 	return precio2000.toString()+" $"; }
	public String getPrecioUnidad() { return precioUnidad.toString()+" $"; }
	
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }
	public void setNombreTipoProducto(String nombreTipoProducto) { this.nombreTipoProducto = nombreTipoProducto; }
	public void setIdProveedor(Integer idProveedor) { this.idProveedor = idProveedor; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setNombreProductoInicial(Integer nombreProductoInicial) { this.nombreProductoInicial = nombreProductoInicial; }
	public void setPrecioComprado(Float precioComprado) { this.precioComprado = precioComprado; }
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = cantidadNoVendida; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setPrecio100(Float precio100) { this.precio100 = precio100; }
	public void setPrecio250(Float precio250) { this.precio250 = precio250; }
	public void setPrecio500(Float precio500) { this.precio500 = precio500; }
	public void setPrecio1000(Float precio1000) { this.precio1000 = precio1000; }
	public void setPrecio2000(Float precio2000) { this.precio2000 = precio2000; }
	public void setPrecioUnidad(Float precioUnidad) { this.precioUnidad = precioUnidad; }	
}
