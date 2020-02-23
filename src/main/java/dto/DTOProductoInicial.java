package dto;

import java.time.LocalDate;

public class DTOProductoInicial {
	private String nombreCategoria;

	private String nombreTipoProducto;	
	private String nombreProveedor;
	private Float precioComprado;	
	private Float cantidadNoVendida;
	private LocalDate vencimiento;
	private String codigoBarra;
	
	private Float precio100;	
	private Float precio250;	
	private Float precio500;	
	private Float precio1000;	
	private Float precio2000;	
	private Float precioUnidad;	

	private DTOProveedor proveedor;
	private DTOTipoProductoCU02 tipoProducto;

	public DTOProductoInicial() {
		precio100 = 0f;	
		precio250 = 0f;	
		precio500 = 0f;	
		precio1000 = 0f;	
		precio2000 = 0f;	
		precioUnidad = 0f;	
	}
	
	public DTOProductoInicial(DTOProductoInicial dto) {
		nombreTipoProducto = dto.getNombreTipoProducto();
		nombreProveedor = dto.getNombreProveedor();	
		precioComprado = dto.getPrecioCompradoF();	
		cantidadNoVendida = dto.getCantidadNoVendidaF();	
		vencimiento = dto.getVencimiento();	
		codigoBarra = dto.getCodigoBarra();	
		precio100 = dto.getPrecio100();	
		precio250 = dto.getPrecio250();	
		precio500 = dto.getPrecio500();	
		precio1000 = dto.getPrecio1000();	
		precio2000 = dto.getPrecio2000();		
		precioUnidad = dto.getPrecioUnidad();	
		proveedor = dto.getProveedor();
		tipoProducto = dto.getTipoProducto();
	}

	public String getNombreCategoria() { return nombreCategoria; }
	public String getNombreTipoProducto() { return nombreTipoProducto; }
	public String getNombreProveedor() { return nombreProveedor; }
	public LocalDate getVencimiento() { return vencimiento; }
	public String getCodigoBarra() { return codigoBarra; }
	public Float getPrecioCompradoF() { return precioComprado; }
	public Float getCantidadNoVendidaF() { return cantidadNoVendida; }
	public Float getPrecio100() { return precio100; }
	public Float getPrecio250() { return precio250; }
	public Float getPrecio500() { return precio500; }
	public Float getPrecio1000() { return precio1000; }
	public Float getPrecio2000() { 	return precio2000; }
	public Float getPrecioUnidad() { return precioUnidad; }
	public DTOProveedor getProveedor() { return proveedor; }
	public DTOTipoProductoCU02 getTipoProducto() { return tipoProducto; }	
	public String getPrecioComprado() { return precioComprado.toString()+" $"; }
	public String getCantidadNoVendida() { return cantidadNoVendida.toString(); }

	public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
	public void setPrecioComprado(Float precioComprado) { this.precioComprado = precioComprado; }
	public void setCantidadNoVendida(Float cantidadNoVendida) { this.cantidadNoVendida = cantidadNoVendida; }
	public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
	public void setCodigoBarra(String codigoBarra) { this.codigoBarra = codigoBarra; }
	public void setPrecio100(Float precio100) { this.precio100 = precio100; }
	public void setPrecio250(Float precio250) { this.precio250 = precio250; }
	public void setPrecio500(Float precio500) { this.precio500 = precio500; }
	public void setPrecio1000(Float precio1000) { this.precio1000 = precio1000; }
	public void setPrecio2000(Float precio2000) { this.precio2000 = precio2000; }
	public void setPrecioUnidad(Float precioUnidad) { this.precioUnidad = precioUnidad; }	
	
	public void setProveedor(DTOProveedor proveedor) {
		this.proveedor = proveedor;
		this.nombreProveedor = proveedor.getNombre();
	}

	public void setTipoProducto(DTOTipoProductoCU02 tipoProducto) {
		this.tipoProducto = tipoProducto;
		this.nombreTipoProducto = tipoProducto.getNombreTipoProducto();
		this.precio100 = tipoProducto.getPrecio100F();
		this.precio250 = tipoProducto.getPrecio250F();
		this.precio500 = tipoProducto.getPrecio500F();
		this.precio1000 = tipoProducto.getPrecio1000F();
		this.precio2000 = tipoProducto.getPrecio2000F();
		this.precioUnidad = tipoProducto.getPrecioUnidadF();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoProducto == null) ? 0 : tipoProducto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;		
		
		DTOProductoInicial other = (DTOProductoInicial) obj;
		if (tipoProducto.getIdProducto() == null) {
			if (other.tipoProducto.getIdProducto() != null)
				return false;
		} 
		else 
			if (!tipoProducto.getIdProducto().equals(other.tipoProducto.getIdProducto()))
				return false;
		return true;
	}	
	
}
