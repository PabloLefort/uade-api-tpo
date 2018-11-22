package pantallas;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

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
	
	public static final String ALTA = "Alta";
	public static final String BAJA = "Baja";
	public static final String MODIFICAR = "Modificación";

	private SistemaAdministracionReclamos sistema;

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
		sistema = new SistemaAdministracionReclamos();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.setTitle("Sistema de Reclamos");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		menuBar.add(mnReclamos);
		
		JMenuItem jMGenerarReclamo = new JMenuItem("Generar");
		mnReclamos.add(jMGenerarReclamo);
		
		jMGenerarReclamo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		GenerarReclamo pd = new GenerarReclamo();
	    	}
	    });
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenuItem jMAltaProducto = new JMenuItem("Alta");
		mnProductos.add(jMAltaProducto);
		
		JMenuItem jMBajaProducto = new JMenuItem("Baja");
		mnProductos.add(jMBajaProducto);
		
		JMenuItem jMModificacionProducto = new JMenuItem("Modificacion");
		mnProductos.add(jMModificacionProducto);	
		
		jMAltaProducto.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ProductoABM pd = new ProductoABM(sistema, ALTA);
	    	}
	    });
		
		jMBajaProducto.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ProductoABM pd = new ProductoABM(sistema, BAJA);
	    	}
	    });
		
		jMModificacionProducto.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ProductoABM pd = new ProductoABM(sistema, MODIFICAR);
	    	}
	    });
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem jMAltaCliente = new JMenuItem("Alta");
		mnClientes.add(jMAltaCliente);
		
		JMenuItem jMBajaCliente = new JMenuItem("Baja");
		mnClientes.add(jMBajaCliente);
		
		JMenuItem jMModificacionCliente = new JMenuItem("Modificacion");
		mnClientes.add(jMModificacionCliente);	
		
		jMAltaCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ClienteABM cl = new ClienteABM(sistema, ALTA);
	    	}
	    });
		
		jMBajaCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ClienteABM cl = new ClienteABM(sistema, BAJA);
	    	}
	    });
		
		jMModificacionCliente.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ClienteABM cl = new ClienteABM(sistema, MODIFICAR);
	    	}
	    });
		
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
		this.setBounds(0,0,screenSize.width, screenSize.height);
	}
	
	public void setReclamosCantidades(Collection<ReclamoCantidades> reclamos) {
		ReclamoCantidadesTableModel model = new ReclamoCantidadesTableModel(reclamos);
		this.tablaCantidades.setModel(model);
		for (int i = 0; i < model.getColumnNames().length; i++) {
			this.tablaCantidades.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(model.getColumnNames()[i]);
		}
	}
}
