package entity;

import java.time.LocalDate;
import java.util.List;


public class BitacoraSalida {

	private List<ProductoEmpaquetado> productos;
	private List<Combo> combos;
	
	private Integer id;
	private LocalDate fecha;

	
	public List<ProductoEmpaquetado> getProductos() {
		return productos;
	}

	public List<Combo> getCombos() {
		return combos;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setProductos(List<ProductoEmpaquetado> productos) {
		this.productos = productos;
	}

	public void setCombos(List<Combo> combos) {
		this.combos = combos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
		
}
