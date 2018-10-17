package negocio;

import java.sql.Date;
import java.util.Collection;

public abstract class Reclamo {
	private Date fecha;
	private int nroReclamo;
	private String descripcion;
	private EstadosReclamo estado;
	private Cliente cliente;
	private Collection<IObserver> observadores;
	
	public Reclamo(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super();
		this.fecha = fecha;
		this.nroReclamo = nroReclamo;
		this.descripcion = descripcion;
		this.cliente = cliente;
		this.estado = EstadosReclamo.ABIERTO;
	}

	public void cerrarReclamo() {
		this.estado = EstadosReclamo.CERRADO;
	};
	
	public void addCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	
	public int getNroReclamo() {
		return this.nroReclamo;
	}
	
	public EstadosReclamo getEstado() {
		return this.estado;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public void attach(IObserver tablero){
		observadores.add(tablero);
	}
	
	public void remove(IObserver tablero) {
		for (IObserver obs : observadores) {
			if(obs.equals(tablero))
				observadores.remove(tablero);
		}
	}
	
	public void procesar(TratamientoStrategy estrategia) {
		estrategia.ProcesarReclamo();
	}
}
