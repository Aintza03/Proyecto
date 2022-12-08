package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

import javax.swing.*;
import bbdd.GestorBD;

public class VentanaCliente extends JFrame{
	protected JTextField DNI;
	protected JLabel ErrorCliente;
	protected JButton Buscar;
	protected JLabel dni;
	//para concatenar las ventanas
	protected VentanaAcoger v3;
	protected VentanaIntroducirCliente v2;
	public VentanaCliente(Properties idioma, GestorBD gestorV) {
		Container cp = this.getContentPane();
		DNI = new JTextField();
		ErrorCliente = new JLabel("");
		Buscar = new JButton("Buscar");
		dni = new JLabel("DNI");//constante en todos los idiomas
		
		JScrollPane textoError = new JScrollPane(ErrorCliente);
		JPanel arriba = new JPanel();
		JPanel abajo = new JPanel();
		
		cp.setLayout(new GridLayout(2,2));
		
		cp.add(dni);
		cp.add(DNI);
		cp.add(textoError);
		cp.add(Buscar);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,100);
		this.setTitle("Introducir Cliente");
		this.setLocationRelativeTo(null);
	}
	}
