package Ventanas;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import bbdd.GestorBD;

public class VentanaEditarCliente extends JFrame{
	
	protected JLabel DNI;
	protected JLabel DNI2;
	protected JLabel NOMBRE;
	protected JLabel TELEFONO;
	protected JLabel DIRECCION;
	
	protected JTextField dni;
	protected JTextField dni2;
	protected JTextField nombre;
	protected JTextField telefono;
	protected JTextField direccion;
	
	protected JLabel Error;
	protected JLabel Error2;
	protected JButton registrarCliente;
	protected JButton eliminarCliente;
	
	protected JTabbedPane pestaña;
	
	public VentanaEditarCliente(GestorBD v, Properties p) {
		Container cp = this.getContentPane();
		pestaña = new JTabbedPane();
		JPanel cp1 = new JPanel();
		JScrollPane a = new JScrollPane(Error);
		cp1.setLayout(new GridLayout(2,1));
		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(1,6));
		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(1,2));
		
		DNI = new JLabel("DNI: ");
		NOMBRE = new JLabel("Nombre: ");
		TELEFONO = new JLabel("Telefono: ");
		DIRECCION = new JLabel("Direccion: ");
		Error = new JLabel("");
		
		dni = new JTextField("");
		nombre = new JPasswordField("");
		telefono = new JTextField("");
		direccion = new JTextField("");
		registrarCliente = new JButton("Editar Cliente");
		
		arriba.add(DNI);
		arriba.add(dni);
		arriba.add(NOMBRE);
		arriba.add(nombre);
		arriba.add(TELEFONO);
		arriba.add(telefono);
		arriba.add(DIRECCION);
		arriba.add(direccion);
		abajo.add(Error);
		abajo.add(registrarCliente);
		cp1.add(arriba);
		cp1.add(abajo);
		
		JPanel cp2 = new JPanel();
		cp2.setLayout(new GridLayout(2,2));
		DNI2 = new JLabel("DNI: ");
		Error2 = new JLabel("");
		JScrollPane b = new JScrollPane(Error2);
		
		dni2 = new JTextField();
		eliminarCliente = new JButton("Eliminar");
		cp2.add(DNI2);
		cp2.add(dni2);
		cp2.add(b);
		cp2.add(eliminarCliente);	
		pestaña.addTab("Editar Cliente", cp1);
		pestaña.addTab("Eliminar Cliente", cp2);
		cp.add(pestaña);
		
		eliminarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean ocurre = v.borrarDatosCliente(dni2.getText());
				if (ocurre) {
					Error2.setText("Se ha borrado el Cliente");
				} else {
					Error2.setText("No se ha encontrado el Cliente");
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
			
		});
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500,150);
		this.setTitle("Editar/Eliminar Clientes");
		this.setLocationRelativeTo(null);
		
	}
}
