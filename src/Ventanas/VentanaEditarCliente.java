package Ventanas;

import java.awt.Container;
import java.awt.GridLayout;
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
	protected JLabel NOMBRE;
	protected JLabel TELEFONO;
	protected JLabel DIRECCION;
	
	protected JTextField dni;
	protected JTextField nombre;
	protected JTextField telefono;
	protected JTextField direccion;
	
	protected JLabel Error;
	protected JButton registrarCliente;
	
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
		
		DNI = new JLabel("Usuario: ");
		NOMBRE = new JLabel("Contraseña: ");
		TELEFONO = new JLabel("Admin: ");
		DIRECCION = new JLabel("Admin: ");
		Error = new JLabel("");
		
		dni = new JTextField("");
		nombre = new JPasswordField("");
		telefono = new JTextField("");
		direccion = new JTextField("");
		registrarCliente = new JButton("Añadir Usuario");
		
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
		Usuario2 = new JLabel("Usuario: ");
		Error2 = new JLabel("");
		JScrollPane b = new JScrollPane(Error2);
		
		borrado = new JTextField();
		eliminar = new JButton("Eliminar");
		cp2.add(Usuario2);
		cp2.add(borrado);
		cp2.add(b);
		cp2.add(eliminar);	
		pestaña.addTab("Añadir Usuarios", cp1);
		pestaña.addTab("Eliminar Usuarios", cp2);
		cp.add(pestaña);
	}
}
