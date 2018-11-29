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

import com.sun.javafx.binding.StringFormatter;

import controlador.SistemaAdministracionReclamos;
import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import negocio.Cliente;
import view.ClienteView;

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
	private JLabel lblDniCliente;
	
	
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
		jtBusqueda.setBounds(146, 11, 144, 30);
		jbBuscar = new JButton();
		jbBuscar.setText("Buscar");
		jbBuscar.setBounds(295, 12, 91, 30);
		jbBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				ClienteView cl;
				int dni = Integer.parseInt(jtBusqueda.getText());
				try {
					cl = sistema.buscarClienteByDni(dni);
				} catch (ConexionException | AccesoException | ClienteException e) {
					e.printStackTrace();
					cl = null;
				}
				
				if (cl != null)
				{

					if (operacion.equalsIgnoreCase(Home.MODIFICAR))
					{
						lNombre.setVisible(true);
						tNombre.setEditable(true);
						tNombre.setEnabled(true);
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
						
						tDni.setValue(cl.getDni());
						tDni.setVisible(false);
						tDni.setEnabled(false);
						tDni.setEditable(false);
						lDni.setVisible(false);

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
						
						lEmail.setVisible(true);
						tEmail.setEditable(false);
						tEmail.setEnabled(false);
						tEmail.setVisible(true);
						tEmail.setText(cl.getEmail());
						
						lDomicilio.setVisible(true);
						tDomicilio.setEditable(false);
						tDomicilio.setEnabled(false);
						tDomicilio.setVisible(true);
						tDomicilio.setText(cl.getDomicilio());
						
						tTelefono.setVisible(true);
						tTelefono.setEditable(false);
						tTelefono.setEnabled(false);
						lTelefono.setVisible(true);
						tTelefono.setText(cl.getTelefono());
						
						tDni.setValue(cl.getDni());
						tDni.setVisible(true);
						tDni.setEnabled(false);
						tDni.setEditable(true);
						lDni.setVisible(true);

					}

				} else
				{
					JOptionPane.showMessageDialog(null,
							"El Cliente no existe", "Busqueda",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		
		lblDniCliente = new JLabel("Dni cliente:");
		lblDniCliente.setBounds(44, 18, 72, 16);
		getContentPane().add(lblDniCliente);

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
		lEmail.setBounds(45, 87, 60, 30);
		tEmail = new JFormattedTextField();
		tEmail.setText("");
		tEmail.setBounds(147, 87, 200, 30);
		getContentPane().add(lEmail);
		getContentPane().add(tEmail);

		lDomicilio = new JLabel("Domicilio");
		lDomicilio.setBounds(45, 171, 100, 30);
		tDomicilio = new JTextField();
		tDomicilio.setText("");
		tDomicilio.setBounds(145, 171, 202, 30);
		getContentPane().add(lDomicilio);
		getContentPane().add(tDomicilio);
		
		lTelefono = new JLabel("Telefono");
		lTelefono.setBounds(45, 129, 100, 30);
		tTelefono = new JTextField();
		tTelefono.setText("");
		tTelefono.setBounds(145, 129, 202, 30);
		getContentPane().add(lTelefono);
		getContentPane().add(tTelefono);
		
		lDni = new JLabel("Dni");
		lDni.setBounds(45, 213, 60, 30);
		tDni = new JFormattedTextField();
		tDni.setText("");
		tDni.setBounds(145, 213, 202, 30);
		getContentPane().add(lDni);
		getContentPane().add(tDni);

		jbCancelar = new JButton("Cancelar");
		jbCancelar.setBounds(167, 305, 89, 40);
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
		jbModificar.setBounds(268, 305, 118, 40);
		jbModificar.setToolTipText("Modificar Cliente");
		jbModificar.setVisible(false);
		jbModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String nombre = tNombre.getText();
				String domicilio = tDomicilio.getText();
				String telefono = tTelefono.getText();
				String email = tEmail.getText();
				int dni = Integer.parseInt(tDni.getText());

				try {
					sistema.ModificacionCliente(dni, nombre, domicilio, telefono, email);
					JOptionPane.showMessageDialog(null,
							"El Cliente se modifico correctamente",
							"Modificar Cliente",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (ConexionException | AccesoException | ClienteException e) { // move this to sistema
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"El Cliente no pudo modificarse",
							"Modificar Cliente",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});

		getContentPane().add(jbModificar);

		jbEliminar = new JButton("Eliminar");
		jbEliminar.setBounds(268, 305, 118, 40);
		jbEliminar.setToolTipText("Eliminar Cliente");
		jbEliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				String msj = "Seguro de eliminar el Cliente seleccionado?";
				String titulo = "Eliminar Cliente";
				int reply = JOptionPane.showConfirmDialog(null, msj, titulo,
						JOptionPane.YES_NO_OPTION);

				if (reply == JOptionPane.YES_OPTION)
				{
					try {
						sistema.BajaCliente(Integer.parseInt(jtBusqueda.getText()));
						JOptionPane
						.showMessageDialog(null,
								"El Cliente se elimino correctamente",
								"Baja de Cliente",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (NumberFormatException | ConexionException | AccesoException | ClienteException e) {
						e.printStackTrace();
						JOptionPane
						.showMessageDialog(null,
								"El Cliente no se pudo eliminar",
								"Baja de Cliente",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				}

			}
		});
		getContentPane().add(jbEliminar);

		jbGuardarYCerrar = new JButton();
		jbGuardarYCerrar.setBounds(268, 305, 118, 40);
		jbGuardarYCerrar.setText("Guardar");
		jbGuardarYCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				String nombre = tNombre.getText();
				String domicilio = tDomicilio.getText();
				String telefono = tTelefono.getText();
				String email = tEmail.getText();
				int dni = Integer.parseInt(tDni.getText());

				System.out.println(sistema.AltaCliente(nombre, domicilio, telefono, email, dni));
				
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
		jtBusqueda.setVisible(mostrar);
		lblDniCliente.setVisible(mostrar);
		tNombre.setVisible(!mostrar);
		lNombre.setVisible(!mostrar);
		tDomicilio.setVisible(!mostrar);
		lDomicilio.setVisible(!mostrar);
		tEmail.setVisible(!mostrar);
		lEmail.setVisible(!mostrar);
		tDni.setVisible(!mostrar);
		lDni.setVisible(!mostrar);
		tTelefono.setVisible(!mostrar);
		lTelefono.setVisible(!mostrar);
		jbGuardarYCerrar.setVisible(!mostrar);
		jbEliminar.setVisible(!mostrar);
	}

	public void setUIAlta(boolean mostrar)
	{
		jtBusqueda.setVisible(!mostrar);
		jbBuscar.setVisible(!mostrar);
		tNombre.setVisible(mostrar);
		tDni.setVisible(mostrar);
		lDni.setVisible(mostrar);
		tTelefono.setVisible(mostrar);
		lTelefono.setVisible(mostrar);
		tDomicilio.setVisible(mostrar);
		lNombre.setVisible(mostrar);
		lblDniCliente.setVisible(!mostrar);
		jbGuardarYCerrar.setVisible(mostrar);
		jbEliminar.setVisible(false);
	}
}
