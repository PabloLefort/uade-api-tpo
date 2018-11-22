package pantallas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GenerarReclamo extends BaseReclamo {


	private Dimension productoDimension = new Dimension(420, 390);
	
	
	public GenerarReclamo()
	{
		setTitle("Generar Reclamo");

		setSize(productoDimension);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panelReclamos = new JPanel();
		panelReclamos.setBounds(35, 179, 509, 260);
		getContentPane().add(panelReclamos);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    		String reclamoSelected = comboBox.getSelectedItem().toString();
	    		
	    		switch(reclamoSelected)
	    		{
	    		case "PRODUCTO":
	    			ReclamoProducto reclProd = new ReclamoProducto();
	    			
	    			panelReclamos.add(reclProd);
	    			break;
	    		}
	    	}
		});
	}
}
