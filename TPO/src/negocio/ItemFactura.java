package negocio;

public class ItemFactura {
	private Producto prod;
	private int cantidad;
	private Factura factura;
	
	public ItemFactura(Producto prod, int cantidad, Factura fac) {
		this.prod = prod;
		this.cantidad = cantidad;
		this.factura = fac;
	}
	
	public int calcularPrecio() {
		return (int)prod.getPrecio() * cantidad;
	}
	
	public Producto getProducto() {
		return this.prod;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	
	public Factura getFactura() {
		return this.factura;
	}
}
