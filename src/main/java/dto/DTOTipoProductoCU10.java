package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DTOTipoProductoCU10 {
	private Integer id;
	private String nombre;
	
	private Boolean conStock;
	
	private List<DTOProductoInicialCU10> productosIniciales;
	private List<DTOFormaVentaCU10> formasVenta;
	
	public DTOTipoProductoCU10() {  }
	
	public DTOTipoProductoCU10(Integer id, String nombre) {
		productosIniciales = new ArrayList<DTOProductoInicialCU10>();
		formasVenta = new ArrayList<DTOFormaVentaCU10>();
		this.id = id;
		this.nombre = nombre;
		this.setConStock(true);
	}

	public Integer getId() { return id; }
	public String getNombre() { return nombre; }
	public List<DTOProductoInicialCU10> getProductosIniciales() { 
		return productosIniciales;
	}
	public List<DTOFormaVentaCU10> getFormasVenta() { return formasVenta; }
	public Boolean getConStock() { return conStock; }
	
	public void setId(Integer id) { this.id = id; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setProductosIniciales(List<DTOProductoInicialCU10> productosIniciales) { this.productosIniciales = productosIniciales; }
	public void setFormasVenta(List<DTOFormaVentaCU10> formasVenta) { this.formasVenta = formasVenta; }	
	public void setConStock(Boolean conStock) { this.conStock = conStock; }	
	
	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOTipoProductoCU10 other = (DTOTipoProductoCU10) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
}
