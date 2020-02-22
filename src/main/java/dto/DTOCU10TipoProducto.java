package dto;

import java.util.ArrayList;
import java.util.List;

public class DTOCU10TipoProducto {
	private Integer id;
	private String nombre;
	
	private List<DTOCU10ProductoInicial> productosIniciales;
	private List<DTOCU10FormaVenta> formasVenta;
	
	public DTOCU10TipoProducto() {  }
	
	public DTOCU10TipoProducto(Integer id, String nombre) {
		productosIniciales = new ArrayList<DTOCU10ProductoInicial>();
		formasVenta = new ArrayList<DTOCU10FormaVenta>();
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() { return id; }
	public String getNombre() { return nombre; }
	public List<DTOCU10ProductoInicial> getProductosIniciales() { return productosIniciales; }
	public List<DTOCU10FormaVenta> getFormasVenta() { return formasVenta; }
	
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setProductosIniciales(List<DTOCU10ProductoInicial> productosIniciales) { this.productosIniciales = productosIniciales; }
	public void setFormasVenta(List<DTOCU10FormaVenta> formasVenta) { this.formasVenta = formasVenta; }	
	
	@Override
	public String toString() {
		return nombre;
	}	
}
