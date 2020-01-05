package entity;

import java.util.List;
import enums.Categoria;

@SuppressWarnings("unused")
public class TipoProducto {
	
	private Categoria categoria;
	private List<Producto> productos;
	private List<Precio> precios;

	private Integer id;
	private String nombre;
	private String descripcion;
	private Float precioVentaUnKg;
	private Float precioVentaMedioKg;
	private Float precioUnitario;
	
}
