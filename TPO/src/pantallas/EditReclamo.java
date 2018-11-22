package pantallas;

import javax.swing.JButton;

import negocio.EstadosReclamo;
import negocio.Reclamo;

public class EditReclamo extends BaseReclamo {
	private Reclamo rec;
	
	public EditReclamo() {
		setSize(400,400);
		setLocation(600,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public EditReclamo(Reclamo rec) {
		super();
		this.setTitle("Reclamo " + rec.getId());
		this.rec = rec;
		
		this.setFecha(rec.getFecha().toString());
		
		JButton btnNewButton_1 = new JButton("Actualizar");
		btnNewButton_1.setBounds(327, 243, 117, 29);
		this.getContentPane().add(btnNewButton_1);
	}

	public void Actualizar() {
		
	}
}
