package negocio;

public class ItemFactura {
	private Producto prod;
	private int cantidad;
	private int precio;
	
	public ItemFactura(Producto prod, int cantidad, int precio) {
		this.prod = prod;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public int calcularPrecio() {
		return precio * cantidad;
	}
	
	public Producto getProducto() {
		return this.prod;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public int getPrecio() {
		return this.precio;
	}
}
