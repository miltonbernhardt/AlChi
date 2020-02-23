package dto;

import java.time.LocalDate;

import enums.TipoPaquete;

public class DTOCU10Empaquetado {
	
	private DTOCU10FormaVenta dtoFormaVenta;
	private DTOCU10ProductoInicial dtoProductoInicial;
	private DTOCU10TipoProducto dtoTipoProducto;
	
	private Integer idTipoProducto;
	private Integer idProductoInicial;
	
	private String nombreTipoProducto;
	private String nombreProveedor;
	private String codigoBarra;
	private LocalDate vencimiento;
	private TipoPaquete tipoPaquete;
	private Integer cantidadPaquetes;
	
	public DTOCU10Empaquetado() { }
	
	public DTOCU10FormaVenta getDtoFormaVenta() { return dtoFormaVenta; }
	public DTOCU10ProductoInicial getDtoProductoInicial() { return dtoProductoInicial; }
	public DTOCU10TipoProducto getDtoTipoProducto() { return dtoTipoProducto; }
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public String getNombreProveedor() { return nombreProveedor; }
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getVencimiento() { return vencimiento; }
	public TipoPaquete getTipoPaqueteE() { return tipoPaquete; }
	public Integer getCantidadPaquetes() { return cantidadPaquetes; }
	
	public String getTipoPaquete() { return tipoPaquete.toString(); }
	
	public void setDtoFormaVenta(DTOCU10FormaVenta dtoFormaVenta) { this.dtoFormaVenta = dtoFormaVenta; }
	public void setDtoProductoInicial(DTOCU10ProductoInicial dtoProductoInicial) { this.dtoProductoInicial = dtoProductoInicial; }
	public void setDtoTipoProducto(DTOCU10TipoProducto dtoTipoProducto) { this.dtoTipoProducto = dtoTipoProducto; }
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setNombreTipoProducto(String nombreTipoProducto) { this.nombreTipoProducto = nombreTipoProducto; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setTipoPaquete(TipoPaquete tipoPaquete) { this.tipoPaquete = tipoPaquete; }
	public void setCantidadPaquetes(Integer cantidadPaquetes) { this.cantidadPaquetes = cantidadPaquetes; }

}
