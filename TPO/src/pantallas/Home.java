package pantallas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import controlador.SistemaAdministracionReclamos;
import negocio.Cliente;
import negocio.ItemReclamoCantidad;
import negocio.Producto;
import negocio.ReclamoCantidades;

public class Home extends JFrame {
	private JTable tablaCantidades;
	private JTable tablaCompuestos;
	private JTable tablaFacturacion;
	private JTable tablaFaltantes;
	private JTable tablaProducto;
	private JTable tablaZona;
	
	public static void main(String[] args) {
		Home h = new Home();
		Cliente cliente_test = new Cliente("Pepe Pompin",
				"Avenida La Plata 945", "9999-9999", "test@gmail.com", 39000123); 
		Producto producto_test = new Producto(1, (float) 22.50, "producto 1",
				"test de alta de producto");
		Date now = new Date(7, 7, 1990);
		ItemReclamoCantidad item = new ItemReclamoCantidad(producto_test, 10);
		ReclamoCantidades reclamo = new ReclamoCantidades(now, 1, "asdasd", cliente_test, item);
		List<ReclamoCantidades> reclamos = new ArrayList<ReclamoCantidades>();
		reclamos.add(reclamo);
		h.setReclamosCantidades(reclamos);
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
}
