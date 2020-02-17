package dto;

import java.util.ArrayList;
import java.util.List;

public class DTOCU03 {
	
	private List<DTOProductoInicial> lista;
	
	private Integer idTipoProducto;
	private String nombreCategoria;
	private String nombreTipoProducto;
	
	private Float p100;	
	private Float p250;
	private Float p500;
	private Float p1000;
	private Float p2000;	
	private Float pUnidad;
	
	private Float p100Actual;	
	private Float p250Actual;		
	private Float p500Actual;		
	private Float p1000Actual;
	private Float p2000Actual;
	private Float pUnidadActual;
	
	private Float p100Anterior;	
	private Float p250Anterior;	
	private Float p500Anterior;		
	private Float p1000Anterior;
	private Float p2000Anterior;
	private Float pUnidadAnterior;
		
	private Boolean rd100;		
	private Boolean rd250;	
	private Boolean rd500;	
	private Boolean rd1000;	
	private Boolean rd2000;	
	private Boolean rdUnidad;
	
	private Boolean checkP100;	
	private Boolean checkP250;	
	private Boolean checkP500;	
	private Boolean checkP1000;	
	private Boolean checkP2000;	
	private Boolean checkPUnidad;
	
	public DTOCU03(Integer idTipoProducto, String categoria, String tipoProducto) {
		this.idTipoProducto = idTipoProducto;
		this.nombreCategoria = categoria;
		this.nombreTipoProducto = tipoProducto;
		lista = new ArrayList<DTOProductoInicial>();
		p100 = 0f;	
		p250 = 0f;
		p500 = 0f;
		p1000 = 0f;
		p2000 = 0f;	
		pUnidad = 0f;
		p100Actual = 0f;
		p250Actual = 0f;		
		p500Actual = 0f;		
		p1000Actual = 0f;
		p2000Actual = 0f;
		pUnidadActual = 0f;
		p100Anterior = 0f;	
		p250Anterior = 0f;	
		p500Anterior = 0f;		
		p1000Anterior = 0f;
		p2000Anterior = 0f;
		pUnidadAnterior = 0f;
		rd100 = true;		
		rd250 = true;		
		rd500 = true;		
		rd1000 = true;		
		rd2000 = true;		
		rdUnidad = true;	
		checkP100 = false;		
		checkP250 = false;		
		checkP500 = false;	
		checkP1000 = false;		
		checkP2000 = false;		
		checkPUnidad = false;		
	}
	
	public List<DTOProductoInicial> getLista() { return lista; }
	public Integer getIdTipoProducto() { return idTipoProducto; }
	public Float getP100() { return p100; }
	public Float getP250() { return p250; }
	public Float getP500() { return p500; }
	public Float getP1000() { return p1000; }
	public Float getP2000() { return p2000; }
	public Float getPUnidad() { return pUnidad; }
	public Float getP100Actual() { return p100Actual; }
	public Float getP250Actual() { return p250Actual; }
	public Float getP500Actual() { return p500Actual; }
	public Float getP1000Actual() { return p1000Actual; }
	public Float getP2000Actual() { return p2000Actual; }
	public Float getpUnidadActual() { return pUnidadActual; }
	public Float getP100Anterior() { return p100Anterior; }
	public Float getP250Anterior() { return p250Anterior; }
	public Float getP500Anterior() { return p500Anterior; }
	public Float getP1000Anterior() { return p1000Anterior; }
	public Float getP2000Anterior() { return p2000Anterior; }
	public Float getpUnidadAnterior() { return pUnidadAnterior; }
	public Boolean getRd100() { return rd100; }
	public Boolean getRd250() { return rd250; }
	public Boolean getRd500() { return rd500; }
	public Boolean getRd1000() { return rd1000; }
	public Boolean getRd2000() { return rd2000; }
	public Boolean getRdUnidad() { return rdUnidad; }
	public Boolean getCheckP100() { return checkP100; }
	public Boolean getCheckP250() { return checkP250; }
	public Boolean getCheckP500() { return checkP500; }
	public Boolean getCheckP1000() { return checkP1000; }
	public Boolean getCheckP2000() { return checkP2000; }
	public Boolean getCheckPUnidad() { return checkPUnidad; }
	
	public void setLista(List<DTOProductoInicial> lista) { this.lista = lista; }
	public void setP100(Float p100) { this.p100 = p100; }
	public void setP250(Float p250) { this.p250 = p250; }
	public void setP500(Float p500) { this.p500 = p500; } 
	public void setP1000(Float p1000) { this.p1000 = p1000; }
	public void setP2000(Float p2000) { this.p2000 = p2000; }
	public void setpUnidad(Float pUnidad) { this.pUnidad = pUnidad; }
	public void setP100Actual(Float p100Actual) { this.p100Actual = p100Actual; }
	public void setP250Actual(Float p250Actual) { this.p250Actual = p250Actual; }
	public void setP500Actual(Float p500Actual) { this.p500Actual = p500Actual; }
	public void setP1000Actual(Float p1000Actual) { this.p1000Actual = p1000Actual; }
	public void setP2000Actual(Float p2000Actual) { this.p2000Actual = p2000Actual; }
	public void setPUnidadActual(Float pUnidadActual) { this.pUnidadActual = pUnidadActual; }
	public void setP100Anterior(Float p100Anterior) { this.p100Anterior = p100Anterior; }
	public void setP250Anterior(Float p250Anterior) { this.p250Anterior = p250Anterior; }
	public void setP500Anterior(Float p500Anterior) { this.p500Anterior = p500Anterior; }
	public void setP1000Anterior(Float p1000Anterior) { this.p1000Anterior = p1000Anterior; }
	public void setP2000Anterior(Float p2000Anterior) { this.p2000Anterior = p2000Anterior; }
	public void setpUnidadAnterior(Float pUnidadAnterior) { this.pUnidadAnterior = pUnidadAnterior; }
	public void setRd100(Boolean rd100) { this.rd100 = rd100; }
	public void setRd250(Boolean rd250) { this.rd250 = rd250; 	}
	public void setRd500(Boolean rd500) { this.rd500 = rd500; }
	public void setRd1000(Boolean rd1000) { this.rd1000 = rd1000; }
	public void setRd2000(Boolean rd2000) { this.rd2000 = rd2000; }
	public void setRdUnidad(Boolean rdUnidad) { this.rdUnidad = rdUnidad; }
	public void setCheckP100(Boolean checkP100) { this.checkP100 = checkP100; }
	public void setCheckP250(Boolean checkP250) { this.checkP250 = checkP250; }
	public void setCheckP500(Boolean checkP500) { this.checkP500 = checkP500; }
	public void setCheckP1000(Boolean checkP1000) { this.checkP1000 = checkP1000; }
	public void setCheckP2000(Boolean checkP2000) { this.checkP2000 = checkP2000; }
	public void setCheckPUnidad(Boolean checkPUnidad) { this.checkPUnidad = checkPUnidad; }
	public void setIdTipoProducto(Integer idTipoProducto) { this.idTipoProducto = idTipoProducto; }

	@Override
	public String toString() {
		return "Categoría: "+nombreCategoria+". Producto: "+nombreTipoProducto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoProducto == null) ? 0 : idTipoProducto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOCU03 other = (DTOCU03) obj;
		if (idTipoProducto == null) {
			if (other.idTipoProducto != null)
				return false;
		} else if (!idTipoProducto.equals(other.idTipoProducto))
			return false;
		return true;
	}
	
}
