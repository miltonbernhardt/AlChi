package dto;

public class DTOTipoProductoCU02 {	
	private String nombreCategoria;
	private Integer idProducto;
	private String nombreTipoProducto;
	private Boolean enVenta;
	private String directorioImagen;
	private Float precio100;
	private Float precio250;
	private Float precio500;
	private Float precio1000;
	private Float precio2000;
	private Float precioUnidad;
	
	public DTOTipoProductoCU02() {}

	public DTOTipoProductoCU02(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}	
	
	public DTOTipoProductoCU02(Integer idProducto, String nombreTipoProducto) {
		super();
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
	}
	
	public DTOTipoProductoCU02(Integer idProducto, String nombreTipoProducto, Boolean enVenta) {
		super();
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.enVenta = enVenta;
	}

	public DTOTipoProductoCU02(String nombreCategoria, Integer idProducto, String nombreTipoProducto, Boolean enVenta) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.enVenta = enVenta;
	}
	
	public DTOTipoProductoCU02(String nombreCategoria, Integer idProducto, String nombreTipoProducto, Boolean enVenta, String directorioImagen) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.enVenta = enVenta;
		this.directorioImagen = directorioImagen;
	}

	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; 	}
	public void setNombreTipoProducto(String nombreTipoProducto) { this.nombreTipoProducto = nombreTipoProducto; }
	public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
	public void setEnVenta(Boolean enVenta) { 	this.enVenta = enVenta; }
	public void setDirectorioImagen(String directorioImagen) { this.directorioImagen = directorioImagen; }
	public void setPrecio100(Float precio100) { this.precio100 = precio100; }
	public void setPrecio250(Float precio250) { this.precio250 = precio250; }
	public void setPrecio500(Float precio500) { this.precio500 = precio500; }
	public void setPrecio1000(Float precio1000) { this.precio1000 = precio1000; 	}
	public void setPrecio2000(Float precio2000) { this.precio2000 = precio2000; 	}
	public void setPrecioUnidad(Float precioUnidad) { this.precioUnidad = precioUnidad; }

	public String getNombreCategoria() { return nombreCategoria; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public Integer getIdProducto() { return idProducto; }
	
	public String getEnVenta() { 
		String s = "NO";
		if(enVenta) {
			s= "SI";
		}
		return s; 	
	}
	
	public String getDirectorioImagen() {
		if(directorioImagen == null)
			return "No";
		else {
			if(directorioImagen.isBlank()) 
				return "No";
			else 
				return "Si";
		}
	}
	
	public String getPrecio100() { return precio100.toString()+" $";  }
	public String getPrecio250() { return precio250.toString()+" $";  }
	public String getPrecio500() {  return precio500.toString()+" $";  }
	public String getPrecio1000() {  return precio1000.toString()+" $";  }
	public String getPrecio2000() {  return precio2000.toString()+" $";  }
	public String getPrecioUnidad() {  return precioUnidad.toString()+" $";  }

	@Override
	public String toString() {
		return nombreTipoProducto;
	}	
}
