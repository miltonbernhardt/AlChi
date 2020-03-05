package dto;

import java.time.LocalDate;
import java.util.Objects;

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
	public Boolean getDadoBaja() { return dadoBaja; }
	public Boolean getSecundario() { return secundario; }
	
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
	public void setDadoBaja(Boolean dadoBaja) { this.dadoBaja = dadoBaja; }
	public void setSecundario(Boolean secundario) { this.secundario = secundario; }

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarra, idProductoInicial, idProductoSecundario, idTipoProducto, nombreProveedor,
				nombreTipoProducto, vencimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOEmpaquetadoCU10 other = (DTOEmpaquetadoCU10) obj;
		return Objects.equals(codigoBarra, other.codigoBarra)
				&& Objects.equals(idProductoInicial, other.idProductoInicial)
				&& Objects.equals(idProductoSecundario, other.idProductoSecundario)
				&& Objects.equals(idTipoProducto, other.idTipoProducto)
				&& Objects.equals(nombreProveedor, other.nombreProveedor)
				&& Objects.equals(nombreTipoProducto, other.nombreTipoProducto)
				&& Objects.equals(vencimiento, other.vencimiento);
	}
}
