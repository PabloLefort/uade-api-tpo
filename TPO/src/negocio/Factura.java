package negocio;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

public class Factura {
	private int nroFactura;
	private Date fechaEmision;
	private Collection<ItemFactura> items;
	
	public Factura(int nroFactura, Date fecha) {
		this.nroFactura = nroFactura;
		this.fechaEmision = fecha;
	}
	
	public int getNroFactura() {
		return this.nroFactura;
	}
	
	public Date getFechaFactura() {
		return this.fechaEmision;
	}
	
	public void addItemFactura(ItemFactura item) {
		items.add(item);
	}
	
	public int calcularTotal(){
		int total = 0;
		for (Iterator<ItemFactura> i = items.iterator(); i.hasNext();) {
			ItemFactura itemFactura = i.next();
			total += itemFactura.calcularPrecio();
		}
		return total;
	}
}
