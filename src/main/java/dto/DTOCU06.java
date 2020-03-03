package dto;

import java.time.LocalDate;
import java.util.List;

import app.App;
import enums.TipoPaquete;

public class DTOCU06 {
	private List<DTOCU06> listaCombo;

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
	
	public DTOCU06(DTOEmpaquetadoCU10 dto, Integer id) {
		this.listaCombo = null;
		setCodigoBarra(dto.getCodigoBarra());
		setFormaVenta(dto.getTipoPaqueteE());
		setIdEmpaquetado(id);
		setIdProductoInicial(dto.getIdProductoInicial());
		setIdTipoProducto(dto.getIdTipoProducto());
		setNombreProducto(dto.getNombreTipoProducto());
		setNombreProveedor(dto.getNombreProveedor());
		setPrecioVenta(dto.getDtoFormaVenta().getPrecioVenta());
		setVencimiento(dto.getVencimiento());		
	}
	
	public DTOCU06(Integer idEmpaquetado, Integer idTipoProducto, String nombreProducto, Integer idProductoInicial, TipoPaquete formaVenta, String nombreProveedor,
			String codigoBarra, LocalDate vencimiento) {
		super();
		this.listaCombo = null;
		this.idEmpaquetado = idEmpaquetado;
		this.idTipoProducto = idTipoProducto;
		this.nombreProducto = nombreProducto;
		this.idProductoInicial = idProductoInicial;
		this.formaVenta = formaVenta;
		this.nombreProveedor = nombreProveedor;
		this.codigoBarra = codigoBarra;
		this.vencimiento = vencimiento;
	}
	
	public DTOCU06(List<DTOCU06> listaCombo, Float precio) {
		this.setListaCombo(listaCombo);
		this.idProductoInicial = null;
		this.idTipoProducto = null;
		this.nombreProducto = "Combo de "+listaCombo.size()+" productos.";
		this.codigoBarra = "-";
		this.vencimiento = null;
		this.nombreProveedor = "-";
		this.precioVenta = precio;
		this.formaVenta = null;
	}

	public List<DTOCU06> getListaCombo() { return listaCombo; }
	public Integer getIdEmpaquetado() { return idEmpaquetado; }
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public String getNombreProducto() { return nombreProducto; }
	public Integer getIdProductoInicial() { return idProductoInicial; }
	public TipoPaquete getFormaVentaE() { return formaVenta; }
	public Float getPrecioVentaF() { return precioVenta; }
	public String getNombreProveedor() { return nombreProveedor; }
	public String getCodigoBarra() { return codigoBarra; }
	public LocalDate getVencimientoL() { return vencimiento; }
	public Integer getCantidad() { return cantidad; }
	
	public String getPrecioVenta() {
		return App.floatSinCero(precioVenta)+" $";
	}
	
	public String getVencimiento() {
		if(vencimiento == null) {
			return "-";
		}
		else {
			return vencimiento.toString();
		}
	}
	
	public String getFormaVenta() {
		if(formaVenta == null) {
			return "Combo";
		}
		else {
			return formaVenta.toString(); 
		}		
	}

	public void setListaCombo(List<DTOCU06> listaCombo) { this.listaCombo = listaCombo; }
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
