package dto;

import enums.TipoPaquete;

public class DTOCU10FormaVenta {	
	private TipoPaquete tipoPaquete;
	private Float precioVenta;
	
	public DTOCU10FormaVenta(TipoPaquete tipoPaquete, Float precioVenta) {
		super();
		this.tipoPaquete = tipoPaquete;
		this.precioVenta = precioVenta;
	}
	
	public Float getPrecioVenta() { return precioVenta; }
	public TipoPaquete getTipoPaquete() { return tipoPaquete; }
	
	public void setPrecioVenta(Float precioVenta) { this.precioVenta = precioVenta; }
	public void setTipoPaquete(TipoPaquete tipoPaquete) { this.tipoPaquete = tipoPaquete; }

	@Override
	public String toString() {
		return tipoPaquete.toString();
	}
}
