package controlador;

import java.util.Collection;

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
	private Collection<Producto> productos;
	
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
	public Producto AltaProducto(int codProducto, Float precio, String nombre, String descripcion) {
		Producto prod = new Producto(codProducto, precio, nombre, descripcion);
		this.productos.add(prod);
		return prod;
	}
	
	public void BajaProducto(int codProducto) {
		for (int i = 0; i < this.productos.size(); i++) {
			//if(this.productos[i])
		}
	}
}
