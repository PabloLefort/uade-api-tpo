package pantallas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import negocio.TiposReclamo;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

public abstract class BaseReclamo extends JFrame {
	private JTextField fechaFextField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	
	public BaseReclamo(){
		this.getContentPane().setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(6, 40, 36, 16);
		this.getContentPane().add(lblFecha);
		
		fechaFextField = new JTextField();
		fechaFextField.setBounds(93, 35, 130, 26);
		this.getContentPane().add(fechaFextField);
		fechaFextField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(6, 74, 75, 16);
		this.getContentPane().add(lblDescripcion);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 69, 175, 26);
		this.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblIdCliente = new JLabel("Id Cliente");
		lblIdCliente.setBounds(6, 108, 61, 16);
		this.getContentPane().add(lblIdCliente);
		
		textField_2 = new JTextField();
		textField_2.setBounds(93, 103, 130, 26);
		this.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(TiposReclamo.values()));
		comboBox.setBounds(93, 141, 175, 27);
		this.getContentPane().add(comboBox);
		this.comboBox = comboBox;

		
		JLabel lblReclamoTipo = new JLabel("Reclamo Tipo");
		lblReclamoTipo.setBounds(6, 145, 85, 16);
		this.getContentPane().add(lblReclamoTipo);
	};
	
	public void setFecha(String fecha) {
		this.fechaFextField.setText(fecha);
	}
}
