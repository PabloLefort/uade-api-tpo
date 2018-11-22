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
import negocio.Cliente;

@SuppressWarnings("serial")
public class ClienteABM extends JFrame {
	private JLabel lNombre;
	private JTextField tNombre;	
	private JLabel lDomicilio;
	private JTextField tDomicilio;	
	private JLabel lEmail;
	private JTextField tEmail;
	private JLabel lTelefono;
	private JTextField tTelefono;
	private JLabel lDni;
	private JFormattedTextField tDni;	
	private JTextField jtBusqueda;
	private JButton jbBuscar;	
	private JButton jbGuardarYCerrar;
	private JButton jbModificar;
	private JButton jbCancelar;
	private JButton jbEliminar;
	
	
	private Dimension clienteDimension = new Dimension(420, 390);
	
	@SuppressWarnings("deprecation")
	public ClienteABM(final SistemaAdministracionReclamos sistema, final String operacion)
	{
		super();

		String title = operacion != null ? operacion + " de " : "";
		String cliente = "Cliente";

		setTitle(title + cliente);
		setSize(clienteDimension);
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
				//Cliente cl = sistema.buscarClienteByDni(Integer.parseInt(jtBusqueda.getText()));
				
				Cliente cl = new Cliente(cliente, cliente, cliente, cliente, ny);
				
				if (cl != null)
				{

					if (operacion.equalsIgnoreCase(Home.MODIFICAR))
					{
						lNombre.setVisible(true);
						tNombre.setEditable(false);
						tNombre.setEnabled(false);
						tNombre.setVisible(true);
						tNombre.setText(cl.getNombre());

						tDomicilio.setText(cl.getDomicilio());
						tDomicilio.setVisible(true);
						lDomicilio.setVisible(true);

						lEmail.setVisible(true);
						tEmail.setVisible(true);
						tEmail.setText(cl.getEmail());

						tTelefono.setText(cl.getTelefono());
						tTelefono.setVisible(true);
						lTelefono.setVisible(true);

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
						tNombre.setText(cl.getNombre());
						
						tDni.setValue(cl.getDni());
						tDni.setVisible(true);
						tDni.setEnabled(false);
						tDni.setEditable(true);
						lDni.setVisible(true);

					}

				} else
				{
					JOptionPane.showMessageDialog(null,
							"El Cliente no existe", "Búsqueda",
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

		lEmail = new JLabel("Email");
		lEmail.setBounds(nx, ny + 50, 60, 30);
		tEmail = new JFormattedTextField(NumberFormat.getIntegerInstance());
		tEmail.setText("");
		tEmail.setBounds(nx + 100, ny + 50, 200, 30);
		getContentPane().add(lEmail);
		getContentPane().add(tEmail);

		lDomicilio = new JLabel("Domicilio");
		lDomicilio.setBounds(nx, ny + 110, 100, 30);
		tDomicilio = new JTextField();
		tDomicilio.setText("");
		tDomicilio.setBounds(nx + 100, ny + 110, 80, 30);
		getContentPane().add(lDomicilio);
		getContentPane().add(tDomicilio);

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
		jbModificar.setToolTipText("Modificar Cliente");
		jbModificar.setVisible(false);
		jbModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				//TODO: Modificar cliente
				String nombre = tNombre.getText();
				String domicilio = tDomicilio.getText();
				
				//sistema.ModificacionCliente(codigo, precio, nombre, descripcion);

				JOptionPane.showMessageDialog(null,
						"El Cliente se modifico correctamente",
						"Modificar Cliente",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		getContentPane().add(jbModificar);

		jbEliminar = new JButton("Eliminar");
		jbEliminar.setBounds(nx + 250, ny + 280, 50, 40);
		jbEliminar.setToolTipText("Eliminar Cliente");
		jbEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String msj = "¿Estás seguro que desea eliminar el Cliente seleccionado?";
				String titulo = "Eliminar Cliente";
				int reply = JOptionPane.showConfirmDialog(null, msj, titulo,
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION)
				{
					//TODO: Baja Cliente
					//sistema.BajaCliente(Integer.parseInt(jtBusqueda.getText()));
			
					JOptionPane
							.showMessageDialog(null,
									"El Cliente se eliminó correctamente",
									"Baja de Cliente",
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
				String domicilio = tDomicilio.getText();
				String telefono = tTelefono.getText();
				String email = tEmail.getText();
				int dni = ((Number) tDni.getValue()).intValue();

				sistema.AltaCliente(nombre, domicilio, telefono, email, dni);
				
				JOptionPane
						.showMessageDialog(null,
								"El Cliente se creo correctamente",
								"Alta de Cliente",
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
		tDomicilio.setVisible(!mostrar);
		tEmail.setVisible(!mostrar);
		jbGuardarYCerrar.setVisible(!mostrar);
		jbEliminar.setVisible(!mostrar);
	}

	public void setUIAlta(boolean mostrar)
	{
		jtBusqueda.setVisible(!mostrar);
		jbBuscar.setVisible(!mostrar);
		tNombre.setVisible(mostrar);
		tDomicilio.setVisible(mostrar);
		lNombre.setVisible(mostrar);
		jbGuardarYCerrar.setVisible(mostrar);
		jbEliminar.setVisible(false);
	}

}
