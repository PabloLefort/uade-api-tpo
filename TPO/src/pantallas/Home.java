package pantallas;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Home extends JFrame {
	private JTable tablaCantidades;
	private JTable tablaCompuestos;
	private JTable tablaFacturacion;
	private JTable tablaFaltantes;
	private JTable tablaProducto;
	private JTable tablaZona;
	
	public static void main(String[] args) {
		Home h = new Home();
	}
	
	public Home() {
		this.setTitle("Sistema de Reclamos");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		menuBar.add(mnReclamos);
		
		JMenuItem btnGenerar = new JMenuItem("Generar");
		mnReclamos.add(btnGenerar);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(100, 68, 478, 253);
		getContentPane().add(tabbedPane);
		
		tablaCantidades = new JTable();
		tabbedPane.addTab("Cantidad", null, tablaCantidades, null);
		
		tablaFacturacion = new JTable();
		tabbedPane.addTab("Facturacion", null, tablaFacturacion, null);
		
		tablaFaltantes = new JTable();
		tabbedPane.addTab("Faltantes", null, tablaFaltantes, null);
		this.setVisible(true);
		this.setSize(700, 500);
	}
}
