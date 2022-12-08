package Ventanas;

import java.util.Properties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bbdd.GestorBD;

public class VentanaIntroducirCliente extends JFrame {
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
	protected VentanaAcoger v3;
	public VentanaIntroducirCliente(GestorBD v,Properties idioma) {
		Container cp = this.getContentPane();
		DNI = new JLabel("DNI:");
		NOMBRE = new JLabel("Nombre:");
		TELEFONO = new JLabel("Telefono:");
		DIRECCION = new JLabel("Direccion:");
		Error = new JLabel("");
		
		dni = new JTextField("");
		nombre = new JTextField("");
		telefono = new JTextField("");
		direccion = new JTextField("");
		registrarCliente = new JButton("Registrar Cliente");
		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(2,4));
		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(1,2));
		JScrollPane error = new JScrollPane(Error);
		arriba.add(DNI);
		arriba.add(dni);
		arriba.add(NOMBRE);
		arriba.add(nombre);
		arriba.add(TELEFONO);
		arriba.add(telefono);
		arriba.add(DIRECCION);
		arriba.add(direccion);
		
		
		abajo.add(error);
		abajo.add(registrarCliente);
		
		cp.setLayout(new GridLayout(2,1));
		cp.add(arriba);
		cp.add(abajo);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,100);
		this.setTitle("Introducir nuevo cliente");
		this.setLocationRelativeTo(null);
}
	}