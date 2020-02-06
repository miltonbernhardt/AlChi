package entity;

import java.util.List;

public class Combo {
	
	private List<ProductoEmpaquetado> productos;
	private BitacoraSalida bitacora;

	private Integer id;
	private Float precio;	
	

	public List<ProductoEmpaquetado> getProductos() {
		return productos;
	}

	public BitacoraSalida getBitacora() {
		return bitacora;
	}

	public Integer getId() {
		return id;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setProductos(List<ProductoEmpaquetado> productos) {
		this.productos = productos;
	}

	public void setBitacora(BitacoraSalida bitacora) {
		this.bitacora = bitacora;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	
}
