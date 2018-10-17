package controlador;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import negocio.Cliente;
import negocio.Factura;
import negocio.ItemFactura;
import negocio.Producto;
import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;
import negocio.Reporte;
import negocio.Usuario;
import negocio.TiposReclamo;

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
		//this.productos.add(prod);
		/*
		 * Agregar a DAO productos
		 * */
		return prod;
	}
	
	private Producto BuscarProducto(int codProducto) {
		/*
		 * Consultar a dao y retornar null en caso de exepcion
		 * */
		return null;
	}
	
	public void BajaProducto(int codProducto) {
		/*
		 * Si existe producto DAO eliminar producto
		 * */
	}
	
	public void ModificacionProducto(int codProducto, float precio, String nombre, String descripcion) {
		Producto prod = this.BuscarProducto(codProducto);
		if(prod != null) {
			prod.setDescripcion(descripcion);
			prod.setNombre(nombre);
			prod.setPrecio(precio);
		}
	}

	
	// ABM Reclamo
	public Reclamo CrearReclamo(Date fechaReclamo, int nroReclamo, String descripcion, Cliente cliente, String tipoReclamo) {
		Reclamo reclamo = null;
		if(tipoReclamo == TiposReclamo.CANTIDADES.name()) {
			reclamo = new ReclamoCantidades(fechaReclamo, nroReclamo, descripcion, cliente);
		} else if (tipoReclamo == TiposReclamo.FACTURACION.name()) {
			reclamo = new ReclamoFacturacion(fechaReclamo, nroReclamo, descripcion, cliente);
		} else if (tipoReclamo == TiposReclamo.FALTANTES.name()) {
			reclamo = new ReclamoFaltantes(fechaReclamo, nroReclamo, descripcion, cliente);
		} else if (tipoReclamo == TiposReclamo.PRODUCTO.name()) {
			reclamo = new ReclamoProducto(fechaReclamo, nroReclamo, descripcion, cliente);
		}
		if(reclamo != null) {
			/*
			 * DAO agregar reclamo
			 * */
			//this.reclamos.add(reclamo);
		}
		return reclamo;
	}
}
