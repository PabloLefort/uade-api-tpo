package view;

import java.util.Collection;

import negocio.Factura;
import negocio.Reclamo;

public class ClienteView {
	private String nombre;
	private String domicilio;
	private String email;
	private String telefono;
	private int dni;
	private Collection<Factura> facturas;
	private Collection<Reclamo> reclamos;
	
	public ClienteView(String nombre, String domicilio, String telefono, String email, int dni) {
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.email = email;
		this.dni = dni;
		this.telefono = telefono;
	}
	
	public Collection<Factura> getFacturas() {
		return this.facturas;
	}
	
	public Collection<Reclamo> getReclamos() {
		return this.reclamos;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getDomicilio() {
		return this.domicilio;
	}
	
	public int getDni() {
		return this.dni;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
