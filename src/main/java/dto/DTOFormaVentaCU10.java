package dto;

import enums.TipoPaquete;

public class DTOFormaVentaCU10 {	
	private TipoPaquete tipoPaquete;
	private Float precioVenta;
	
	public DTOFormaVentaCU10(TipoPaquete tipoPaquete, Float precioVenta) {
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
