package pantallas;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import negocio.ReclamoCantidades;

public class ReclamoCantidadesTableModel extends BaseReclamoTableModel {
	
	public ReclamoCantidadesTableModel(Collection<ReclamoCantidades> reclamos) {
		super();
		this.setNewColumn("Producto Id");
		this.setNewColumn("Cantidad");
		Object[][] data = new Object[reclamos.size()][6];
		for (Iterator iterator = reclamos.iterator(); iterator.hasNext();) {
			ReclamoCantidades reclamo = (ReclamoCantidades) iterator.next();
			data[this.getRowCount()][0] = reclamo.getId();
			data[this.getRowCount()][1] = reclamo.getDescripcion();
			data[this.getRowCount()][2] = reclamo.getFecha().toString();
			data[this.getRowCount()][3] = reclamo.getCliente().getId();
			data[this.getRowCount()][4] = reclamo.getItemReclamoCantidad().getProducto().getId();
			data[this.getRowCount()][5] = reclamo.getItemReclamoCantidad().getCantidad();
		}
		this.setData(data);
	}
}
