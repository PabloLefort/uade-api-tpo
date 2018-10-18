package negocio;

public class Producto {

	public int codProducto;
	public float precio;
	public String nombre;
	public String descripcion;
	
	public Producto(int codProducto, float precio, String nombre, String descripcion) {
		super();
		this.codProducto = codProducto;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCodigo() {
		return this.codProducto;
	}
	
	
}
