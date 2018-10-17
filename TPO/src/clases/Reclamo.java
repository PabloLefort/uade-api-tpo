package clases;

import java.sql.Date;

public abstract class Reclamo {
	private Date fecha;
	private int nroReclamo;
	private String descripcion;
	private String estado;
	
	public Reclamo(Date fecha, int nroReclamo, String descripcion, String estado) {
		super();
		this.fecha = fecha;
		this.nroReclamo = nroReclamo;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public void cerrarReclamo() {
		this.estado = "pendiente";
	};
	
	public void procesar(TratamientoStrategy strategia) {
		strategia.ProcesarReclamo();
	}

}
