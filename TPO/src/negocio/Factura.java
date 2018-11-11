package negocio;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;

public class Factura {
	private int nroFactura;
	private Date fechaEmision;
	
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
}
