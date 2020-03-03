package dto;

import app.App;

public class DTOTipoProductoCU02 {	
	private String nombreCategoria;
	private Integer idProducto;
	private String nombreTipoProducto;
	private String directorioImagen;
	private Boolean enVenta;
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

	public DTOTipoProductoCU02(String nombreCategoria, Integer idProducto, String nombreTipoProducto, Boolean enVenta) {
		super();
		this.nombreCategoria = nombreCategoria;
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.enVenta = enVenta;
	}
	
	public DTOTipoProductoCU02(String nombreCategoria, Integer idProducto, String nombreTipoProducto, Boolean enVenta, String directorioImagen) {
		super();
		this.enVenta = enVenta;
		this.nombreCategoria = nombreCategoria;
		this.idProducto = idProducto;
		this.nombreTipoProducto = nombreTipoProducto;
		this.directorioImagen = directorioImagen;
	}

	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; 	}
	public void setNombreTipoProducto(String nombreTipoProducto) { this.nombreTipoProducto = nombreTipoProducto; }
	public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }
	public void setDirectorioImagen(String directorioImagen) { this.directorioImagen = directorioImagen; }
	public void setEnVenta(Boolean enVenta) { this.enVenta = enVenta; }	
	public void setPrecio100(Float precio100) { this.precio100 = precio100; }
	public void setPrecio250(Float precio250) { this.precio250 = precio250; }
	public void setPrecio500(Float precio500) { this.precio500 = precio500; }
	public void setPrecio1000(Float precio1000) { this.precio1000 = precio1000; 	}
	public void setPrecio2000(Float precio2000) { this.precio2000 = precio2000; 	}
	public void setPrecioUnidad(Float precioUnidad) { this.precioUnidad = precioUnidad; }

	public String getNombreCategoria() { return nombreCategoria; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public Integer getIdProducto() { return idProducto; }
	
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
	
	public String getEnVenta() {
		if(enVenta)
			return "Si";
		else
			return "No"; 
	}
	
	public String getPrecio100() {
		if(precio100 <= 0f) {
			return "-";
		}
		else
			return  App.floatSinCero(precio100)+" $";  
	}
	public String getPrecio250() { 
		if(precio250 <= 0f) {
			return "-";
		}
		else
			return App.floatSinCero(precio250)+" $";
	}
	public String getPrecio500() {  
		if(precio500 <= 0f) {
			return "-";
		}
		else
			return App.floatSinCero(precio500)+" $";  
	}
	public String getPrecio1000() {  
		if(precio1000 <= 0f) {
			return "-";
		}
		else
			return App.floatSinCero(precio1000)+" $"; 
	}
	public String getPrecio2000() {  
		if(precio2000 <= 0f) {
			return "-";
		}
		else
			return App.floatSinCero(precio2000)+" $";  
	}
	public String getPrecioUnidad() {  
		if(precioUnidad == 0f) {
			return "-";
		}
		else
			return App.floatSinCero(precioUnidad)+" $"; 
	}
	public Boolean getEnVentaF() { return enVenta; }
	public Float getPrecio100F() { return precio100;  }
	public Float getPrecio250F() { return precio250;  }
	public Float getPrecio500F() {  return precio500;  }
	public Float getPrecio1000F() {  return precio1000;  }
	public Float getPrecio2000F() {  return precio2000;  }
	public Float getPrecioUnidadF() {  return precioUnidad;  }

	@Override
	public String toString() {
		return nombreTipoProducto;
	}	
}
