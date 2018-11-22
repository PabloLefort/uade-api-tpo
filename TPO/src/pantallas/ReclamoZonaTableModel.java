package pantallas;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import negocio.ReclamoZona;

public class ReclamoZonaTableModel extends BaseReclamoTableModel {
	
	public ReclamoZonaTableModel(Collection<ReclamoZona> reclamos) {
		super();
		this.setNewColumn("Zona");
		Object[][] data = new Object[reclamos.size()][6];
		for (Iterator iterator = reclamos.iterator(); iterator.hasNext();) {
			ReclamoZona reclamo = (ReclamoZona) iterator.next();
			data[this.getRowCount()][0] = reclamo.getId();
			data[this.getRowCount()][1] = reclamo.getDescripcion();
			data[this.getRowCount()][2] = reclamo.getFecha().toString();
			data[this.getRowCount()][3] = reclamo.getCliente().getId();
			data[this.getRowCount()][4] = reclamo.getZona().getDescripcion();
		}
		this.setData(data);
	}
}
