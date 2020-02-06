package entity;

import java.time.LocalDate;
import enums.TipoPaquete;

public class Precio {
	
	private TipoPaquete tipoVenta;
	private Float valor;//valor = 0.0f cuando no tiene ese tipo de venta, o se elimina el tipoventa
	private LocalDate fecha;
	
	
	public TipoPaquete getTipoVenta() {
		return tipoVenta;
	}
	
	public Float getValor() {
		return valor;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void setTipoVenta(TipoPaquete tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
	
	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}	

}
