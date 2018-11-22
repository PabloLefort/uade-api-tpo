package pantallas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import controlador.SistemaAdministracionReclamos;
import negocio.Reclamo;

@SuppressWarnings("serial")
public class ReclamoABM extends JFrame {

	private JTextField jtBusqueda;
	private JButton jbBuscar;
	
	public ReclamoABM(SistemaAdministracionReclamos sistema, String operacion) {
		String title = operacion != null ? operacion + " de " : "";
		String reclamo = "Reclamo";

		setTitle(title + reclamo);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(700,600);
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		int nx = 45;
		int ny = 25;
		
		jtBusqueda = new JTextField("");
		jtBusqueda.setBounds(nx + 20, 12, 250, 30);
		jbBuscar = new JButton();
		jbBuscar.setToolTipText("Buscar");
		jbBuscar.setBounds(nx + 272, 12, 30, 30);
		

		getContentPane().add(jtBusqueda);
		getContentPane().add(jbBuscar);
		
		jbBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
			}
		});
		
		setVisible(true);
	}
}
