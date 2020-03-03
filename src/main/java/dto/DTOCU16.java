package dto;

import java.time.LocalDate;

import app.App;
import enums.TipoPaquete;

public class DTOCU16 {
	
	private Integer idTipoProducto;
	private Integer idProductoInicial;
	private Integer idProductoEmpaquetado;
	
	private String nombreCategoria;
	private String nombreProducto;
	private TipoPaquete formaVenta;
	private String codigoBarra;
	private String nombreProveedor;
	
	private Boolean dadoBaja;
	private Boolean vendido;

	private LocalDate fechaVenta;
	private LocalDate vencimiento;
	private Float precioVenta;
	
	public DTOCU16(Integer idTipoProducto, Integer idProducto, Integer idProductoEmpaquetado, String nombreProducto, LocalDate vencimiento, String codigoBarra, String nombreCategoria,
			String nombreProveedor, TipoPaquete formaVenta, LocalDate fechaVenta, Float precioVenta, Boolean vendido, Boolean dadoBaja) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.idProductoInicial = idProducto;
		this.idProductoEmpaquetado = idProductoEmpaquetado;
		this.nombreProducto = nombreProducto;
		this.codigoBarra = codigoBarra;
		this.nombreCategoria = nombreCategoria;
		this.nombreProveedor = nombreProveedor;
		this.formaVenta = formaVenta;
		this.fechaVenta = fechaVenta;
		this.precioVenta = precioVenta;
		this.vendido = vendido;
		this.dadoBaja = dadoBaja;
		this.setVencimiento(vencimiento);
	}
	
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public Integer getIdProductoEmpaquetado() { return idProductoEmpaquetado; }
	public String getNombreProducto() { return nombreProducto; }
	public TipoPaquete getFormaVentaE() { return formaVenta; }
	public String getCodigoBarra() { return codigoBarra; }
	public String getNombreCategoria() { return nombreCategoria; }
	public String getNombreProveedor() { return nombreProveedor; }
	public LocalDate getFechaVentaL() { return fechaVenta; }
	public Float getPrecioVentaF() { return precioVenta; }
	public Boolean getDadoBajaB() { return dadoBaja; }
	public Boolean getVendidoB() { return vendido; }
	public LocalDate getVencimiento() { return vencimiento; }
	
	public String getFormaVenta() {
		if(formaVenta!=null)
			return formaVenta.toString();
		else
			return "-";
	}
	
	public String getFechaVenta() {
		if(fechaVenta != null)
			return fechaVenta.toString();
		else {
			return "-";
		}
	}
	
	public String getPrecioVenta() {
		if(precioVenta != null)
			return App.floatSinCero(precioVenta)+" $";
		else {
			if(fechaVenta != null) {
				return "Parte del combo";
			}
			else
				return "-";
		}
	}	
	
	public String getDadoBaja() { 
		if(dadoBaja)
			return "Si";
		else
			return "No";
	}
	public String getVendido() {
		if(vendido)
			return "Si";
		else
			return "No";
	}
	
	
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setIdProductoEmpaquetado(Integer idProductoEmpaquetado) { this.idProductoEmpaquetado = idProductoEmpaquetado; }
	public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
	public void setFormaVenta(TipoPaquete formaVenta) { this.formaVenta = formaVenta; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setFechaVenta(LocalDate fechaVenta) { this.fechaVenta = fechaVenta; }
	public void setPrecioVenta(Float precioVenta) { this.precioVenta = precioVenta; }		
	public void setDadoBaja(Boolean dadoBaja) { this.dadoBaja = dadoBaja; }
	public void setVendido(Boolean vendido) { this.vendido = vendido; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
}
