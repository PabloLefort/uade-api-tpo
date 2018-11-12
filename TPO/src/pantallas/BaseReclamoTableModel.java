package pantallas;

import javax.swing.table.AbstractTableModel;

public class BaseReclamoTableModel extends AbstractTableModel {
	private String[] columnNames;
    private Object[][] data;
    
    public BaseReclamoTableModel() {
		this.columnNames = new String[] {"Id", "Descripcion", "Fecha", "Cliente Id"};
	}

	@Override
	public int getRowCount() {
		if(this.data != null) {
			return this.data.length;
		} else {
			return 0;
		}
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int index) {
	    return this.columnNames[index];
	}
	
	@Override
    public boolean isCellEditable(int row, int col) {
         return false;
    }
	
	public String[] getColumnNames() {
		return this.columnNames;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}
	
	public void setNewColumn(String name) {
		String[] aux = new String[this.columnNames.length + 1];
		for (int i = 0; i < this.columnNames.length; i++) {
			aux[i] = this.columnNames[i];
		}
		aux[this.columnNames.length] = name;
		this.columnNames = aux;
	}

}
