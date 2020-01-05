package entity;

import java.util.List;

@SuppressWarnings("unused")
public class Producto {
	
	private TipoProducto tipoProducto;
	private List<BitacoraEntrada> bitacorasEntrada;
	//manyToOne
	private Proveedor proveedor;

	private Integer id;
	private Float precioComprado;
	private Float cantidadNoVendida;
	private Float cantidadVendida;
	private Boolean disponible;

}
