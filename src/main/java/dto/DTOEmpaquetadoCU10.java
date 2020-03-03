package dto;

import java.time.LocalDate;

import app.App;
import enums.TipoPaquete;

public class DTOEmpaquetadoCU10 {
	
	private DTOFormaVentaCU10 dtoFormaVenta;
	private DTOProductoInicialCU10 dtoProductoInicial;
	private DTOTipoProductoCU10 dtoTipoProducto;
	
	private DTOProductoInicialCU10 dtoProductoInicialSecundario;
	private Float cantPrimario;
	
	private Integer idTipoProducto;
	private Integer idProductoInicial;
	private Integer idProductoSecundario;
	
	private String nombreTipoProducto;
	private String nombreProveedor;
	private String codigoBarra;
	private LocalDate vencimiento;
	private TipoPaquete tipoPaquete;
	private Integer cantidadPaquetes;
	
	private Boolean secundario = false;
	private Boolean dadoBaja = false;
	
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
	public DTOProductoInicialCU10 getDtoProductoInicialSecundario() { return dtoProductoInicialSecundario; }
	public Float getCantPrimario() { return cantPrimario; }
	public Integer getIdProductoSecundario() { return idProductoSecundario; }
	
	
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
	public void setDtoProductoInicialSecundario(DTOProductoInicialCU10 dtoProductoInicialSecundario) { this.dtoProductoInicialSecundario = dtoProductoInicialSecundario; }
	public void setCantPrimario(Float cantPrimario) { this.cantPrimario = App.redondear(cantPrimario); }	public void setIdProductoSecundario(Integer idProductoSecundario) { this.idProductoSecundario = idProductoSecundario; }

	public Boolean getDadoBaja() {
		return dadoBaja;
	}

	public void setDadoBaja(Boolean dadoBaja) {
		this.dadoBaja = dadoBaja;
	}

	public Boolean getSecundario() {
		return secundario;
	}

	public void setSecundario(Boolean secundario) {
		this.secundario = secundario;
	}
}
