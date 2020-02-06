package entity;

import java.time.LocalDate;

public class BitacoraEntrada {

	private Integer id;
	private LocalDate fecha;
	
	public Integer getId() {
		return id;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
		
}
