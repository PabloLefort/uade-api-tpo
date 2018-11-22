package pantallas;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ReclamoProducto extends JInternalFrame {

	private JLabel lNombre;
	private JTextField tNombre;	
	
	public ReclamoProducto() {
		setSize(600,400);
		setVisible(true);
		int nx = 45;
		int ny = 25;
		
		lNombre = new JLabel("Nombre");
		lNombre.setBounds(nx, ny + 20, 60, 30);
		tNombre = new JTextField();
		tNombre.setText("");
		tNombre.setBounds(nx + 100, ny + 20, 200, 30);
		getContentPane().add(lNombre);
		getContentPane().add(tNombre);
		
		getContentPane().setLayout(null);
		this.setVisible(true);
		this.setBounds(0,0,200, 100);
	}
}
