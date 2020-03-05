package dto;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(precioVenta, tipoPaquete);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOFormaVentaCU10 other = (DTOFormaVentaCU10) obj;
		return Objects.equals(precioVenta, other.precioVenta) && tipoPaquete == other.tipoPaquete;
	}
}
