package dto;

import java.time.LocalDate;

public class DTOCU08 {

	private Integer idProducto;
	private String nombreProducto;
	private String codigoBarra;
	private String nombreCategoria;
	private String nombreProveedor;
	private LocalDate fechaIngreso;
	private LocalDate fechaVencimiento;
	private Float precioCompra;
	private Boolean disponible;
	
	public DTOCU08(Integer idProducto, String nombreProducto, String codigoBarra, String nombreCategoria,
			String nombreProveedor, LocalDate fechaIngreso, LocalDate fechaVencimiento, Float precioCompra, Boolean disponible) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.codigoBarra = codigoBarra;
		this.nombreCategoria = nombreCategoria;
		this.nombreProveedor = nombreProveedor;
		this.fechaIngreso = fechaIngreso;
		this.fechaVencimiento = fechaVencimiento;
		this.precioCompra = precioCompra;
		this.disponible = disponible;
	}
	
	public Integer getIdProducto() { return idProducto; }
	public String getNombreProducto() { return nombreProducto; }
	public String getCodigoBarra() { return codigoBarra; }
	public String getNombreCategoria() { return nombreCategoria; }
	public String getNombreProveedor() { return nombreProveedor; }
	public LocalDate getFechaIngreso() { return fechaIngreso; }
	public LocalDate getFechaVencimiento() { return fechaVencimiento; }
	public Float getPrecioCompraF() { return precioCompra; }
	public Boolean getDisponibleB() { return disponible; }
	
	public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
	public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
	public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
	public void setPrecioCompra(Float precioCompra) { this.precioCompra = precioCompra; }	
	public void setDisponible(Boolean disponible) { this.disponible = disponible; }	
	
	public String getPrecioCompra() {
		return precioCompra.toString()+" $"; 
	}	
	
	public String getDisponible() {
		if(disponible)
			return "Si";
		else
			return "No";
	}	
}
