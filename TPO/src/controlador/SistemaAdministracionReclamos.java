package controlador;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import dao.ProductoDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.RolException;
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
import negocio.Rol;
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
	
	public SistemaAdministracionReclamos() {
		
	}
	
	public void Start(void) {
		Cliente cliente_test = this.AltaCliente("Pepe Pompin", "Avenida La Plata 945","9999-9999", "test@gmail.com", ?);
		Producto producto_test = this.AltaProducto(?, 22.50, "producto 1", "test de alta de producto");
		controlador.ModificacionProducto(?, 30.70, "producto test", "nueva descripcion");
		controlador.BajaProducto(?); 
		Reclamo reclamos_test = this.CrearReclamo(20/10/2018, ?, "test_reclamo_cantidades", cliente_test, "cantidades");
		Reclamo reclamos_test = this.CrearReclamo(20/10/2018, ?, "test_reclamo_facturacion", cliente_test, "facturacion");
		Reclamo reclamos_test = this.CrearReclamo(20/10/2018, ?, "test_reclamo_faltantes", cliente_test, "faltantes");
		Reclamo reclamos_test = this.CrearReclamo(20/10/2018, ?, "test_reclamo_productos", cliente_test, "productos");
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
	
	
	// ABM Usuario
	public Usuario AltaUsuario(String nombre, String email, String password, int idRol) {
		UsuarioDAO usuDAO = new UsuarioDAO();
		RolDAO rolDAO = new RolDAO();
		Rol rol = null;
		try {
			rol = rolDAO.buscarRolPorId(idRol);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Usuario usuario = new Usuario(nombre, email, password, rol);
		try {
			usuDAO.insertarUsuario(nombre, email, password, idRol);
			return usuario;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Usuario BuscarUsuario(String email) {
		UsuarioDAO UsuDAO = new UsuarioDAO();
    	try {
    		return UsuDAO.buscarUsuarioPorMail(email);    		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	// LOGIN
	public Usuario Login(String email, String password){
		
		Usuario user;

		//si el formulario falla
        if (email.isEmpty() || password.isEmpty()) 
        {
        	System.out.println("Error: Complete los datos solicitados");	
        } 
        else 
        {
            //si el formulario pasa la validación
        	UsuarioDAO UsuDAO = new UsuarioDAO();
        	
        	try {
				
        		user = UsuDAO.buscarUsuarioPorMail(email);
        		
        		if (user.getPassword().equals(password)){
					return user;
				}else{
					
					System.out.println("Login incorrecto");					
				}        		
        		
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null;
			}
        	
        }		
		
		return null;
		
	}

	
	// ABM PRODUCTOS
	public Producto AltaProducto(int codProducto, float precio, String nombre, String descripcion) {
		Producto prod = new Producto(codProducto, precio, nombre, descripcion);
		ProductoDAO prodDao = new ProductoDAO();
		try {
			prodDao.insertarProducto(codProducto, precio, nombre, descripcion);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return prod;
	}
	
	private Producto BuscarProducto(int codProducto) {
		try {
			ProductoDAO prodDao = new ProductoDAO();
			return prodDao.obtenerProductoPorId(codProducto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void BajaProducto(int codProducto) {
		Producto prod = this.BuscarProducto(codProducto);
		if(prod != null) {
			ProductoDAO prodDao = new ProductoDAO();
			try {
				prodDao.eliminarProductoPorId(codProducto);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void ModificacionProducto(int codProducto, float precio, String nombre, String descripcion) {
		Producto prod = this.BuscarProducto(codProducto);
		if(prod != null) {
			ProductoDAO prodDao = new ProductoDAO();
			try {
				prodDao.modificarProducto(codProducto, precio, nombre, descripcion);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
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
	
	
	// PROCESAR RECLAMO
	public void ProcesarReclamo(int reclamoID) {
		ReclamoDAO recDAO = new ReclamoDAO();
		Reclamo rec = null;
		
		rec = recDAO.buscarReclamoPorId(reclamoID);
		if(rec.getClass().getName() == ReclamoProducto.class.getName()) {
			
		}
	}
}
