package dto;

import java.time.LocalDate;

import enums.TipoPaquete;

public class DTOEmpaquetadoCU10 {
	
	private DTOFormaVentaCU10 dtoFormaVenta;
	private DTOProductoInicialCU10 dtoProductoInicial;
	private DTOTipoProductoCU10 dtoTipoProducto;
	
	private Integer idTipoProducto;
	private Integer idProductoInicial;
	
	private String nombreTipoProducto;
	private String nombreProveedor;
	private String codigoBarra;
	private LocalDate vencimiento;
	private TipoPaquete tipoPaquete;
	private Integer cantidadPaquetes;
	
	public DTOEmpaquetadoCU10() { }
	
	public DTOFormaVentaCU10 getDtoFormaVenta() { return dtoFormaVenta; }
	public DTOProductoInicialCU10 getDtoProductoInicial() { return dtoProductoInicial; }
	public DTOTipoProductoCU10 getDtoTipoProducto() { return dtoTipoProducto; }
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public String getNombreProveedor() { return nombreProveedor; }
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getVencimiento() { return vencimiento; }
	public TipoPaquete getTipoPaqueteE() { return tipoPaquete; }
	public Integer getCantidadPaquetesF() { return cantidadPaquetes; }
	
	
	public String getTipoPaquete() { 
		if(tipoPaquete!=null)
			return tipoPaquete.toString(); 
		else
			return "Dado de baja";
	}
	
	public String getCantidadPaquetes() {
		if(cantidadPaquetes <= 0) {
			return "-";
		}
		else {
			return cantidadPaquetes.toString();
		} 
	}
	
	public void setDtoFormaVenta(DTOFormaVentaCU10 dtoFormaVenta) { this.dtoFormaVenta = dtoFormaVenta; }
	public void setDtoProductoInicial(DTOProductoInicialCU10 dtoProductoInicial) { this.dtoProductoInicial = dtoProductoInicial; }
	public void setDtoTipoProducto(DTOTipoProductoCU10 dtoTipoProducto) { this.dtoTipoProducto = dtoTipoProducto; }
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setNombreTipoProducto(String nombreTipoProducto) { this.nombreTipoProducto = nombreTipoProducto; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setTipoPaquete(TipoPaquete tipoPaquete) { this.tipoPaquete = tipoPaquete; }
	public void setCantidadPaquetes(Integer cantidadPaquetes) { this.cantidadPaquetes = cantidadPaquetes; }

}
