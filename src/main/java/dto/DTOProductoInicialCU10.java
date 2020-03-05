package dto;

import java.time.LocalDate;
import java.util.Objects;

import app.App;

public class DTOProductoInicialCU10 {
	
	private Integer idTipoProducto;
	private Integer idProductoInicial;
	private Float cantidadNoVendida;
	private String codigoBarra;
	private LocalDate vencimiento;
	private String proveedor;
	private Boolean disponible;
	
	public DTOProductoInicialCU10(Integer idTipoProducto, Integer idProductoInicial, Float cantidadNoVendida, String codigoBarra,
			LocalDate vencimiento, String proveedor) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.idProductoInicial = idProductoInicial;
		this.cantidadNoVendida = cantidadNoVendida;
		this.codigoBarra = codigoBarra;
		this.vencimiento = vencimiento;
		this.proveedor = proveedor;
		this.setDisponible(true);
	}
	
	public DTOProductoInicialCU10(DTOCU08 dto) {
		this.idTipoProducto = dto.getIdTipoProducto();
		this.idProductoInicial = dto.getIdProducto();
		this.cantidadNoVendida = dto.getCantidadNoVendidaF();
		this.codigoBarra = dto.getCodigoBarra();
		this.vencimiento = dto.getFechaVencimiento();
		this.proveedor = dto.getNombreProveedor();
	}
	
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public Float getCantidadNoVendida() { return cantidadNoVendida; }
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getVencimiento() { return vencimiento; }
	public String getProveedor() { return proveedor; }
	public Boolean getDisponible() { return disponible; }
	
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }	
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = App.redondear(cantidadNoVendida); }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setProveedor(String proveedor) { this.proveedor = proveedor; }
	public void setDisponible(Boolean disponible) { this.disponible = disponible; }
	
	@Override
	public String toString() {		
		return "Proveedor: "+proveedor+".  Código de barra: "+codigoBarra+".  Vencimiento: "+vencimiento+".  Cantidad restante: "+cantidadNoVendida.toString()+" Kg";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarra, idProductoInicial, idTipoProducto, proveedor, vencimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOProductoInicialCU10 other = (DTOProductoInicialCU10) obj;
		return Objects.equals(codigoBarra, other.codigoBarra)
				&& Objects.equals(idProductoInicial, other.idProductoInicial)
				&& Objects.equals(idTipoProducto, other.idTipoProducto) && Objects.equals(proveedor, other.proveedor)
				&& Objects.equals(vencimiento, other.vencimiento);
	}
}
