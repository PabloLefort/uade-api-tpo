package controlador;

import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import dao.ClienteDAO;
import dao.LoginDAO;
import dao.ProductoDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import negocio.Cliente;
import negocio.Factura;
import negocio.Producto;
import negocio.Reclamo;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.Reporte;
import negocio.Rol;
import negocio.TiposReclamo;
import negocio.Usuario;
import view.ClienteView;

public class SistemaAdministracionReclamos {

	private ArrayList clientes = new ArrayList();
	private Collection<Reclamo> reclamos;
	private Collection<Factura> facturas;
	private Collection<Usuario> usuarios;
	private Collection<Reporte> reportes;
	private ArrayList<Producto> productos;

	public SistemaAdministracionReclamos() {

	}

	public void Start() {
		System.out.println("skljhfasldkfjhalsdkfjhaldkj ");
		Cliente cliente_test = this.AltaCliente("Pepe Pompin",
				"Avenida La Plata 945", "9999-9999", "test@gmail.com", 39000123); 
		Producto producto_test = this.AltaProducto(1, (float) 22.50, "producto 1",
				"test de alta de producto");
		this.ModificacionProducto(1, (float) 30.70, "producto test", "nueva descripcion");
		this.BajaProducto(1);
		Date date = new Date(2018, 10, 20);
		Reclamo reclamos_test = this.CrearReclamo(date, 2, "test_reclamo_cantidades", cliente_test, "cantidades");
		this.CrearReclamo(date, 3, "test_reclamo_facturacion", cliente_test, "facturacion");
		this.CrearReclamo(date, 4, "test_reclamo_faltantes", cliente_test, "faltantes");
		this.CrearReclamo(date, 5, "test_reclamo_productos", cliente_test, "productos");
		//this.clientes = (Collection<Cliente>) new List();
		this.clientes.add(cliente_test);
	}

	public Cliente AltaCliente(String nombre, String domicilio, String telefono, String email, int dni) {
		try {
			Cliente cliente = new Cliente(nombre, domicilio, telefono, email, dni);
			//ClienteDAO.getInstancia().save(cliente);
			this.clientes.add(cliente);
			return cliente;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


	public ClienteView buscarCliente(int dni) throws ConexionException, AccesoException, ClienteException {
		Cliente cliente = new ClienteDAO().getInstancia().getById(dni);
		return cliente.toView();
	}
	
	public ClienteView buscarClienteByDni(int dni) throws ConexionException, AccesoException, ClienteException {
		//ClienteView cliente = new ClienteDAO().getInstancia().getById(dni).toView();
		for (Iterator iterator = this.clientes.iterator(); iterator.hasNext();) {
			Cliente aux = (Cliente) iterator.next();
			if(aux.getDni() == dni) {
				return aux.toView();
			}
		}
		//return cliente;
		return null;
	}
	
	public void ModificacionCliente(int cliente_dni, String nombre, String domicilio, String telefono, String email) throws ConexionException, AccesoException, ClienteException {
		/*Cliente cliente = new ClienteDAO().getInstancia().getById(cliente_dni);
		if (cliente != null) {
			ClienteDAO clienteDao = new ClienteDAO();
			cliente.setEmail(email);
			cliente.setNombre(nombre);
			cliente.setTelefono(telefono);
			clienteDao.update(cliente);
		}*/
		for (Iterator iterator = this.clientes.iterator(); iterator.hasNext();) {
			Cliente aux = (Cliente) iterator.next();
			if(aux.getDni() == cliente_dni) {
				aux.setNombre(nombre);
				aux.setDomicilio(domicilio);
				aux.setEmail(email);
				aux.setTelefono(telefono);
			}
		}
	}
	
	public void BajaCliente(int cliente_dni) throws ConexionException, AccesoException, ClienteException {
		/*ClienteDAO clienteDao = new ClienteDAO();
		Cliente cliente = clienteDao.getInstancia().getById(cliente_dni);
		clienteDao.delete(cliente);*/
		for (int i = 0; i < this.clientes.size(); i++) {
			Cliente c = (Cliente) this.clientes.get(i);
			if(c.getDni() == cliente_dni) {
				this.clientes.remove(i);
			}
		}
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
    	
        //si el formulario falla	
        if (email.isEmpty() || password.isEmpty()) {	
             System.out.println("Error: Complete los datos solicitados");    	
        }	
        else {	
                //si el formulario pasa la validacion	
             Usuario user = null;	
            UsuarioDAO UsuDAO = new UsuarioDAO();	
            LoginDAO login = null;	
            	
            try {	
                	
                user = login.autenticarUsuario(email, password);	
                	
                return user;                              	
            } catch (Exception e) {	
                	
                System.out.println(e.getMessage());                	
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

	public Producto BuscarProducto(int codProducto) {
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
		if (prod != null) {
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
		if (prod != null) {
			ProductoDAO prodDao = new ProductoDAO();
			try {
				prodDao.modificarProducto(codProducto, precio, nombre, descripcion);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// ABM Reclamo
	public Reclamo CrearReclamo(Date fechaReclamo, int nroReclamo, String descripcion, Cliente cliente,
			String tipoReclamo) {
		Reclamo reclamo = null;
		if (tipoReclamo == TiposReclamo.CANTIDADES.name()) {
			// reclamo = new ReclamoCantidades(fechaReclamo, nroReclamo, descripcion,
			// cliente);
		} else if (tipoReclamo == TiposReclamo.FACTURACION.name()) {
			reclamo = new ReclamoFacturacion(fechaReclamo, nroReclamo, descripcion, cliente);
		} else if (tipoReclamo == TiposReclamo.FALTANTES.name()) {
			reclamo = new ReclamoFaltantes(fechaReclamo, nroReclamo, descripcion, cliente);
		} else if (tipoReclamo == TiposReclamo.PRODUCTO.name()) {
			// reclamo = new ReclamoProducto(fechaReclamo, nroReclamo, descripcion,
			// cliente);
		}
		if (reclamo != null) {
			/*
			 * DAO agregar reclamo
			 */
			// this.reclamos.add(reclamo);
		}
		return reclamo;
	}

	// PROCESAR RECLAMO
	public void ProcesarReclamo(int reclamoID) {

	}
}
