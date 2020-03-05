package dto;

import java.time.LocalDate;
import java.util.Objects;

import app.App;

public class DTOCU08 {

	private Integer idTipoProducto;
	private Integer idProducto;
	private String nombreProducto;
	private String codigoBarra;
	private String nombreCategoria;
	private String nombreProveedor;
	private LocalDate fechaIngreso;
	private LocalDate fechaVencimiento;
	private Float precioCompra;
	private Float cantidadNoVendida;
	private Boolean disponible;
	private Float precioUnidad;
	
	private String directorioImagen;
	
	public DTOCU08(Integer idTipoProducto, Integer idProducto, String nombreProducto, String codigoBarra, String nombreCategoria,
			String nombreProveedor, LocalDate fechaIngreso, LocalDate fechaVencimiento, Float precioCompra, Float cantidadNoVendida, Boolean disponible, String directorioImagen) {
		super();
		this.idTipoProducto = idTipoProducto;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.codigoBarra = codigoBarra;
		this.nombreCategoria = nombreCategoria;
		this.nombreProveedor = nombreProveedor;
		this.fechaIngreso = fechaIngreso;
		this.fechaVencimiento = fechaVencimiento;
		this.precioCompra = precioCompra;
		this.setCantidadNoVendida(cantidadNoVendida);
		this.disponible = disponible;
		this.directorioImagen = directorioImagen;
	}
	
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public Integer getIdProducto() { return idProducto; }
	public String getNombreProducto() { return nombreProducto; }
	public String getCodigoBarra() { return codigoBarra; }
	public String getNombreCategoria() { return nombreCategoria; }
	public String getNombreProveedor() { return nombreProveedor; }
	public LocalDate getFechaIngreso() { return fechaIngreso; }
	public LocalDate getFechaVencimiento() { return fechaVencimiento; }
	public Float getPrecioCompraF() { return precioCompra; }
	public Float getCantidadNoVendidaF() { return cantidadNoVendida; }
	public Boolean getDisponibleB() { return disponible; }
	public Float getPrecioUnidad() { return precioUnidad; }
	public String getDirectorioImagen() { return directorioImagen; }
	
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }
	public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
	public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
	public void setNombreProveedor(String nombreProveedor) { this.nombreProveedor = nombreProveedor; }
	public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
	public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
	public void setPrecioCompra(Float precioCompra) { this.precioCompra = precioCompra; }	
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = cantidadNoVendida; }	
	public void setDisponible(Boolean disponible) { this.disponible = disponible; }	
	public void setPrecioUnidad(Float precioUnidad) { this.precioUnidad = precioUnidad; }
	public void setDirectorioImagen(String directorioImagen) { this.directorioImagen = directorioImagen; }
	
	public String getPrecioCompra() {
		return App.floatSinCero(precioCompra)+" $"; 
	}	
	
	public String getCantidadNoVendida() {	
		
		if(precioUnidad!= null && precioUnidad>0f) {
			return App.floatSinCero(cantidadNoVendida)+" unidades";
		}
		else {
			return App.floatSinCero(cantidadNoVendida)+" Kg";
		}
	}
	
	public String getDisponible() {
		if(disponible)
			return "Si";
		else
			return "No";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarra, idProducto, idTipoProducto, nombreCategoria, nombreProducto, nombreProveedor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOCU08 other = (DTOCU08) obj;
		return Objects.equals(codigoBarra, other.codigoBarra) && Objects.equals(idProducto, other.idProducto)
				&& Objects.equals(idTipoProducto, other.idTipoProducto)
				&& Objects.equals(nombreCategoria, other.nombreCategoria)
				&& Objects.equals(nombreProducto, other.nombreProducto)
				&& Objects.equals(nombreProveedor, other.nombreProveedor);
	}
}
