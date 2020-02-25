package dto;

import java.time.LocalDate;
import enums.TipoPaquete;

public class DTOCU06 {

	private Integer idEmpaquetado;
	private Integer idTipoProducto;
	private String nombreProducto;	
	private Integer idProductoInicial;
	private TipoPaquete formaVenta;
	private Float precioVenta;
	private String nombreProveedor;
	private String codigoBarra;
	private LocalDate vencimiento;
	private Integer cantidad;
	
	public DTOCU06(Integer idEmpaquetado, Integer idTipoProducto, String nombreProducto, Integer idProductoInicial, TipoPaquete formaVenta, String nombreProveedor,
			String codigoBarra, LocalDate vencimiento) {
		super();
		this.idEmpaquetado = idEmpaquetado;
		this.idTipoProducto = idTipoProducto;
		this.nombreProducto = nombreProducto;
		this.idProductoInicial = idProductoInicial;
		this.formaVenta = formaVenta;
		this.nombreProveedor = nombreProveedor;
		this.codigoBarra = codigoBarra;
		this.vencimiento = vencimiento;
	}
	
	public Integer getIdEmpaquetado() { return idEmpaquetado; }
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public String getNombreProducto() { return nombreProducto; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public TipoPaquete getFormaVenta() { return formaVenta; }
	public Float getPrecioVentaF() { return precioVenta; }
	public String getNombreProveedor() { return nombreProveedor; }
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getVencimiento() { return vencimiento; }
	public Integer getCantidad() { return cantidad; }
	
	public String getPrecioVenta() {
		return precioVenta.toString()+" $";
	}

	public void setIdEmpaquetado(Integer idEmpaquetado) { this.idEmpaquetado = idEmpaquetado; }
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }
	public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }	
	public void setIdProductoInicial(Integer idProductoInicial) { this.idProductoInicial = idProductoInicial; }
	public void setFormaVenta(TipoPaquete formaVenta) { this.formaVenta = formaVenta; }
	public void setPrecioVenta(Float precioVenta) { this.precioVenta = precioVenta; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra;}
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
}
