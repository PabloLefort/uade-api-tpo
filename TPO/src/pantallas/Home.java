package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import negocio.ReclamoCantidades;
import negocio.ReclamoZona;

public class Home extends JFrame {
	private JTable tablaCantidades;
	private JTable tablaCompuestos;
	private JTable tablaFacturacion;
	private JTable tablaFaltantes;
	private JTable tablaProducto;
	private JTable tablaZona;
	
	public Home() {
		this.setTitle("Sistema de Reclamos");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		menuBar.add(mnReclamos);
		
		JMenuItem btnGenerar = new JMenuItem("Generar");
		mnReclamos.add(btnGenerar);
		ActionListener btnGenerarListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		btnGenerar.addActionListener(btnGenerarListener);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(100, 68, 478, 253);
		getContentPane().add(tabbedPane);
		
		tablaCantidades = new JTable();
		JScrollPane scrollCantidades = new JScrollPane(tablaCantidades);
		scrollCantidades.add(tablaCantidades.getTableHeader());
	    tabbedPane.addTab("Cantidades", scrollCantidades);
		
		tablaFacturacion = new JTable();
		JScrollPane scrollFacturacion = new JScrollPane(tablaFacturacion);
		scrollFacturacion.add(tablaFacturacion.getTableHeader());
	    tabbedPane.addTab("Facturacion", scrollFacturacion);
		
		tablaFaltantes = new JTable();
		JScrollPane scrollFaltantes = new JScrollPane(tablaFaltantes);
		scrollFaltantes.add(tablaFaltantes.getTableHeader());
	    tabbedPane.addTab("Faltantes", scrollFaltantes);
		
		tablaProducto = new JTable();
		JScrollPane scrollProducto = new JScrollPane(tablaProducto);
		scrollProducto.add(tablaProducto.getTableHeader());
	    tabbedPane.addTab("Producto", scrollProducto);
		
		tablaZona = new JTable();
		JScrollPane scrollZona = new JScrollPane(tablaZona);
		scrollZona.add(tablaZona.getTableHeader());
	    tabbedPane.addTab("Zona", scrollZona);
		
		
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setSize(700, 500);
	}
	
	public void setReclamosCantidades(Collection<ReclamoCantidades> reclamos) {
		ReclamoCantidadesTableModel model = new ReclamoCantidadesTableModel(reclamos);
		this.tablaCantidades.setModel(model);
		for (int i = 0; i < model.getColumnNames().length; i++) {
			this.tablaCantidades.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(model.getColumnNames()[i]);
		}
	}
	
	public void setReclamosZona(Collection<ReclamoZona> reclamos) {
		ReclamoZonaTableModel model = new ReclamoZonaTableModel(reclamos);
		this.tablaZona.setModel(model);
		for (int i = 0; i < model.getColumnNames().length; i++) {
			this.tablaZona.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(model.getColumnNames()[i]);
		}
	}
}
