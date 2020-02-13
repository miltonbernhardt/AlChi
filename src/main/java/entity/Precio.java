package entity;

import java.time.LocalDate;
import enums.TipoPaquete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "precio")
public class Precio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_precio")
	@SequenceGenerator(name="id_precio", sequenceName = "id_precio_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false, name = "tipo_venta")
	@Enumerated(EnumType.STRING)
	private TipoPaquete tipoVenta;
	
	@Column(nullable = false)
	private Float valor;
	
	@Column(nullable = false)
	private LocalDate fecha;
	
	public Precio() {}
	
	public Integer getId() { return id; }
	public TipoPaquete getTipoVenta() { return tipoVenta; }
	public Float getValor() { return valor; }
	public LocalDate getFecha() { return fecha; }
	
	public void setId(Integer id) { this.id = id; }
	public void setTipoVenta(TipoPaquete tipoVenta) { this.tipoVenta = tipoVenta; }
	public void setValor(Float valor) { this.valor = valor; }
	public void setFecha(LocalDate fecha) { this.fecha = fecha; }		
}
