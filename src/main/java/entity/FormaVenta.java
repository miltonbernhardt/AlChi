package entity;

import enums.TipoVenta;

@SuppressWarnings("unused")
public class FormaVenta {
	
	//joinToOne
	private Producto producto;
	private BitacoraSalida bitacora;
	
	private TipoVenta tipoVenta;

}
