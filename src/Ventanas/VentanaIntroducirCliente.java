package Ventanas;

import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;

import General.Cliente;

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
		DNI = new JLabel(idioma.get("dni1").toString());
		NOMBRE = new JLabel(idioma.get("nombre").toString());
		TELEFONO = new JLabel(idioma.get("tel").toString());
		DIRECCION = new JLabel(idioma.get("dir").toString());
		Error = new JLabel("");
		
		dni = new JTextField("");
		nombre = new JTextField("");
		telefono = new JTextField("");
		direccion = new JTextField("");
		registrarCliente = new JButton(idioma.get("regi").toString());
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

	registrarCliente.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String dniC = null;
		int telefonoC = 0;
		String direccionC = null;
		String nombreC = null;
		Cliente cliente = null;
		try {
		if (dni.getText().length() == 9 && DNIAPTO(dni.getText())) {
		dniC = dni.getText();
		if (telefono.getText().length() == 9){
		System.out.println(telefono.getText());
		telefonoC = Integer.parseInt(telefono.getText());
		if (direccion.getText().length() != 0) {
		direccionC = direccion.getText();
		if (nombre.getText().length() != 0) {
		nombreC = nombre.getText();
		} else {
		Error.setText(idioma.get("mes4").toString());
		}
		} else {
		Error.setText(idioma.get("mes5").toString());
		}
		} else {
		Error.setText(idioma.get("mes6").toString());
		}
		} else {
		Error.setText(idioma.get("mes7").toString());
		}
		if (dniC != null && direccionC != null && telefonoC != 0 && nombreC != null) {
		//si el if se cumple significara que se ha podido crear el cliente
		cliente = new Cliente(dniC,direccionC,telefonoC,nombreC);
		//Error.setText("Cliente Registrado");
		v.insertarDatosCliente(cliente);
		v3 = new VentanaAcoger(v , idioma, dni.getText());

		}

		} catch (Exception e2) {
		// TODO: handle exception
		System.err.println("El telefono introducido tiene letras");
		Error.setText(idioma.get("mes8").toString());
		e2.printStackTrace();
		}
		}
		});
		}
		public boolean DNIAPTO(String dni) {
		//Comprueba que el dni empieza por 8 digitos y 1 letra
		String finaliza = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String comienza = "1234567890";
		boolean res = true;
		String letra = "" + dni.charAt(8);
		if (finaliza.contains(letra)) {
		for(int i = 0; i < 8; i++) {
		letra = "" + dni.charAt(i);
		if (!(comienza.contains(letra))){
		res = false;
		break;
		}
		}
		} else {
		res = false;
		}
		return res;
		}
	}