package controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import negocio.Cliente;
import negocio.Factura;
import negocio.ItemFactura;
import negocio.Producto;
import negocio.Reclamo;
import negocio.Reporte;
import negocio.Usuario;

public class SistemaAdministracionReclamos {
	private String nombreEmpresa;
	private Collection<Cliente> clientes;
	private Collection<Reclamo> reclamos;
	private Collection<Factura> facturas;
	private Collection<Usuario> usuarios;
	private Collection<Reporte> reportes;
	private ArrayList<Producto> productos;
	
	public SistemaAdministracionReclamos(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	public Cliente AltaCliente(String nombre, String domicilio, String telefono, String email, int dni) {
		Cliente cliente = new Cliente(nombre, domicilio, telefono, email, dni);
		return cliente;
	}
	
	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}
	
	public Usuario buscarUsuario(String nombre) {
		
		for (Usuario usr : usuarios) {
			if (usr.getNombre().equals(nombre))
				return usr;
		}
		
		return null;
	}
	
	public Cliente buscarCliente(int dni) {
		
		for (Cliente cli : clientes) {
			if (cli.getDni() == dni)
				return cli;
		}
		
		return null;
	}
	
	// ABM PRODUCTOS
	public Producto AltaProducto(int codProducto, float precio, String nombre, String descripcion) {
		Producto prod = new Producto(codProducto, precio, nombre, descripcion);
		this.productos.add(prod);
		return prod;
	}
	
	private Producto BuscarProducto(int codProducto) {
		for (int i = 0; i < this.productos.size(); i++) {
			if(this.productos.get(i).codProducto == codProducto) {
				return this.productos.get(i);
			}
		}
		return null;
	}
	
	public void BajaProducto(int codProducto) {
		if(this.BuscarProducto(codProducto) != null) {
			for (int i = 0; i < this.productos.size(); i++) {
				if(this.productos.get(i).codProducto == codProducto) {
					this.productos.remove(i);
				}
			}
		}
	}
	
	public void ModificacionProducto(int codProducto, float precio, String nombre, String descripcion) {
		Producto prod = this.BuscarProducto(codProducto);
		if(prod != null) {
			prod.setDescripcion(descripcion);
			prod.setNombre(nombre);
			prod.setPrecio(precio);
		}
	}
}
