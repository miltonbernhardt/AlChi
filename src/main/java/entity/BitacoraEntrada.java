package entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bitacora_entrada")
public class BitacoraEntrada {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_bitacora_entrada")
	@SequenceGenerator(name="id_bitacora_entrada", sequenceName = "id_bitacora_entrada_seq", initialValue = 100, allocationSize = 1)
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = false, name = "fecha")
	private LocalDate fecha;
	
	public BitacoraEntrada() { }
	
	public Integer getId() { return id; }	
	public LocalDate getFecha() { return fecha; }	
	
	public void setId(Integer id) { this.id = id; }	
	public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
