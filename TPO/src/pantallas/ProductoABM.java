package pantallas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controlador.SistemaAdministracionReclamos;
import negocio.Producto;

@SuppressWarnings("serial")
public class ProductoABM extends JFrame {
	private JLabel lNombre;
	private JTextField tNombre;	
	private JLabel lDescripcion;
	private JTextField tDescripcion;	
	private JLabel lCodigo;
	private JFormattedTextField tCodigo;
	private JLabel lPrecio;
	private JFormattedTextField tPrecio;
	private JTextField jtBusqueda;
	private JButton jbBuscar;	
	private JButton jbGuardarYCerrar;
	private JButton jbModificar;
	private JButton jbCancelar;
	private JButton jbEliminar;
	
	
	private Dimension productoDimension = new Dimension(420, 390);
	
	@SuppressWarnings("deprecation")
	public ProductoABM(final SistemaAdministracionReclamos sistema, final String operacion)
	{
		super();

		String title = operacion != null ? operacion + " de " : "";
		String producto = "Producto";

		setTitle(title + producto);
		setSize(productoDimension);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		int nx = 45;
		int ny = 25;
		// LABELS DE LOS DIFERENTES COMPONENTES
		jtBusqueda = new JTextField("");
		jtBusqueda.setBounds(nx + 20, 12, 250, 30);
		jbBuscar = new JButton();
		jbBuscar.setToolTipText("Buscar");
		jbBuscar.setBounds(nx + 272, 12, 30, 30);
		jbBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Producto pd = sistema.BuscarProducto(Integer.parseInt(jtBusqueda.getText()));

				if (pd != null)
				{

					if (operacion.equalsIgnoreCase(Home.MODIFICAR))
					{
						lNombre.setVisible(true);
						tNombre.setEditable(false);
						tNombre.setEnabled(false);
						tNombre.setVisible(true);
						tNombre.setText(pd.getNombre());

						tCodigo.setValue(pd.getCodigo());
						tCodigo.setVisible(true);
						lCodigo.setVisible(true);

						lDescripcion.setVisible(true);
						tDescripcion.setVisible(true);
						tDescripcion.setText(pd.getDescripcion());

						tPrecio.setValue(pd.getPrecio());
						tPrecio.setVisible(true);
						lPrecio.setVisible(true);

						jbModificar.setVisible(true);
					}

					if (operacion.equalsIgnoreCase(Home.BAJA))
					{
						jbGuardarYCerrar.setVisible(false);
						jbEliminar.setVisible(true);
						
						lNombre.setVisible(true);
						tNombre.setEditable(false);
						tNombre.setEnabled(false);
						tNombre.setVisible(true);
						tNombre.setText(pd.getNombre());
						
						tCodigo.setValue(pd.getCodigo());
						tCodigo.setVisible(true);
						tCodigo.setEnabled(false);
						tCodigo.setEditable(true);
						lCodigo.setVisible(true);

					}

				} else
				{
					JOptionPane.showMessageDialog(null,
							"El producto no existe", "Búsqueda",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		getContentPane().add(jtBusqueda);
		getContentPane().add(jbBuscar);

		lNombre = new JLabel("Nombre");
		lNombre.setBounds(nx, ny + 20, 60, 30);
		tNombre = new JTextField();
		tNombre.setText("");
		tNombre.setBounds(nx + 100, ny + 20, 200, 30);
		getContentPane().add(lNombre);
		getContentPane().add(tNombre);

		lCodigo = new JLabel("Código");
		lCodigo.setBounds(nx, ny + 50, 60, 30);
		tCodigo = new JFormattedTextField(NumberFormat.getIntegerInstance());
		tCodigo.setValue(new Integer(0));
		tCodigo.setBounds(nx + 100, ny + 50, 200, 30);
		getContentPane().add(lCodigo);
		getContentPane().add(tCodigo);

		lDescripcion = new JLabel("Descripcion");
		lDescripcion.setBounds(nx, ny + 110, 100, 30);
		tDescripcion = new JTextField();
		tDescripcion.setText("");
		tDescripcion.setBounds(nx + 100, ny + 110, 80, 30);
		getContentPane().add(lDescripcion);
		getContentPane().add(tDescripcion);

		lPrecio = new JLabel("Precio");
		lPrecio.setBounds(nx, ny + 140, 150, 30);
		tPrecio = new JFormattedTextField(NumberFormat.getNumberInstance());
		tPrecio.setValue(new Integer(0));
		tPrecio.setBounds(nx + 100, ny + 140, 80, 30);

		getContentPane().add(tPrecio);
		getContentPane().add(lPrecio);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(nx, ny + 280, 70, 40);
		jbCancelar.setToolTipText("Cancelar");
		getContentPane().add(jbCancelar);
		jbCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				dispose();
			}
		});

		jbModificar = new JButton("Modificar");
		jbModificar.setBounds(nx + 250, ny + 280, 70, 40);
		jbModificar.setToolTipText("Modificar producto");
		jbModificar.setVisible(false);
		jbModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String nombre = tNombre.getText();
				String descripcion = tDescripcion.getText();
				int codigo = ((Number) tCodigo.getValue()).intValue();
				int precio = ((Number) tPrecio.getValue()).intValue();

				sistema.ModificacionProducto(codigo, precio, nombre, descripcion);

				JOptionPane.showMessageDialog(null,
						"El producto se modifico correctamente",
						"Modificar Producto",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		getContentPane().add(jbModificar);

		jbEliminar = new JButton("Eliminar");
		jbEliminar.setBounds(nx + 250, ny + 280, 50, 40);
		jbEliminar.setToolTipText("Eliminar Producto");
		jbEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String msj = "¿Estás seguro que desea eliminar el producto seleccionado?";
				String titulo = "Eliminar Producto";
				int reply = JOptionPane.showConfirmDialog(null, msj, titulo,
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION)
				{
					sistema.BajaProducto(Integer.parseInt(jtBusqueda.getText()));
			
					JOptionPane
							.showMessageDialog(null,
									"El Producto se eliminó correctamente",
									"Baja de Producto",
									JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}

			}
		});
		getContentPane().add(jbEliminar);

		jbGuardarYCerrar = new JButton();
		jbGuardarYCerrar.setBounds(nx + 250, ny + 280, 50, 40);
		jbGuardarYCerrar.setToolTipText("Guardar y Cerrar");
		jbGuardarYCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				String nombre = tNombre.getText();
				String descripcion = tDescripcion.getText();
				int codigo = ((Number) tCodigo.getValue()).intValue();
				float precio = ((Number) tPrecio.getValue()).floatValue();

				sistema.AltaProducto(codigo, precio, nombre, descripcion);
				
				JOptionPane
						.showMessageDialog(null,
								"El producto se creo correctamente",
								"Alta de Producto",
								JOptionPane.INFORMATION_MESSAGE);
				
				dispose();
			}
		});
		getContentPane().add(jbGuardarYCerrar);

		// MUESTRO CAMPOS SEGUN LA OPCION DE AMB
		switch (operacion)
		{
		case Home.ALTA:
			setUIAlta(true);
			break;
		case Home.BAJA:
			setUIBusqueda(true);
			break;
		case Home.MODIFICAR:
			setUIBusqueda(true);
			break;
		}
		setVisible(true);
	}

	public void setUIBusqueda(boolean mostrar)
	{
		tNombre.setVisible(!mostrar);
		tCodigo.setVisible(!mostrar);
		lNombre.setVisible(!mostrar);
		lCodigo.setVisible(!mostrar);
		jbGuardarYCerrar.setVisible(!mostrar);
		jbEliminar.setVisible(!mostrar);
	}

	public void setUIAlta(boolean mostrar)
	{
		jtBusqueda.setVisible(!mostrar);
		jbBuscar.setVisible(!mostrar);
		tNombre.setVisible(mostrar);
		tCodigo.setVisible(mostrar);
		lNombre.setVisible(mostrar);
		lCodigo.setVisible(mostrar);
		jbGuardarYCerrar.setVisible(mostrar);
		jbEliminar.setVisible(false);
	}

}
