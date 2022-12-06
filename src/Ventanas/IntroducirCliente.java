package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JButton;

public class IntroducirCliente extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;

	
	

	
	public IntroducirCliente() {
		getContentPane().setLayout(new MigLayout("", "[][][][][grow][][grow]", "[][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("DNI:");
		getContentPane().add(lblNewLabel, "cell 3 1,alignx trailing");
		
		textField = new JTextField();
		getContentPane().add(textField, "flowx,cell 4 1,alignx left");
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		getContentPane().add(lblNewLabel_2, "cell 5 1,alignx trailing");
		
		textField_2 = new JTextField();
		getContentPane().add(textField_2, "cell 6 1,alignx right");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono:");
		getContentPane().add(lblNewLabel_1, "cell 3 3,alignx trailing");
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 4 3,alignx left");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Direccion:");
		getContentPane().add(lblNewLabel_3, "cell 5 3,alignx trailing");
		
		textField_3 = new JTextField();
		getContentPane().add(textField_3, "cell 6 3,alignx right");
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("");
		getContentPane().add(lblNewLabel_4, "cell 4 7");
		
		JButton btnNewButton = new JButton("Registrar");
		getContentPane().add(btnNewButton, "cell 6 7");
		

			
		

	}
	

}
