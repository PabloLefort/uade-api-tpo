package negocio;

public class Producto {

	public int codProducto;
	public Float precio;
	public String nombre;
	public String descripcion;
	
	public Producto(int codProducto, Float precio, String nombre, String descripcion) {
		super();
		this.codProducto = codProducto;
		this.precio = precio;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
}
