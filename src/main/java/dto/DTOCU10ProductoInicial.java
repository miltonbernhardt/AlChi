package dto;

import java.time.LocalDate;

import enums.TipoPaquete;

@SuppressWarnings("unused")
public class DTOCU10ProductoInicial {
	
	private Integer idProductoInicial;
	private Float cantidadNoVendida;
	private String codigoBarra;
	private LocalDate vencimiento;
	private String proveedor;
	
	public DTOCU10ProductoInicial(Integer idProductoInicial, Float cantidadNoVendida, String codigoBarra,
			LocalDate vencimiento, String proveedor) {
		super();
		this.idProductoInicial = idProductoInicial;
		this.cantidadNoVendida = cantidadNoVendida;
		this.codigoBarra = codigoBarra;
		this.vencimiento = vencimiento;
		this.proveedor = proveedor;
	}
	
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public Float getCantidadNoVendida() { return cantidadNoVendida; }
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getVencimiento() { return vencimiento; }
	public String getProveedor() { return proveedor; }
	
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = cantidadNoVendida; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setProveedor(String proveedor) { this.proveedor = proveedor; }
	
	@Override
	public String toString() {		
		return "Proveedor: "+proveedor+".  Código de barra: "+codigoBarra+".  Vencimiento: "+vencimiento+".  Cantidad restante: "+cantidadNoVendida.toString()+" Kg";
	}	
}
