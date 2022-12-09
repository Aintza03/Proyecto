package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;

import General.Cliente;
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
		Buscar = new JButton(idioma.get("buscar").toString());
		dni = new JLabel(idioma.get("dni").toString());
		
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
		Buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String res = encontrarCliente(DNI.getText(), gestorV, idioma);
			if (res.equals("Se ha encontrado el cliente")) {
			v3 = new VentanaAcoger(gestorV, idioma, DNI.getText());
			setVisible(false);
			} else if (res.equals("El cliente no existe")) {
			v2 = new VentanaIntroducirCliente(gestorV,idioma);
			setVisible(false);
			} else {
			ErrorCliente.setText(res);
			}
			}
			});
			}
			public String encontrarCliente(String DNIA, GestorBD gestorV, Properties idioma) {
			String resultado = "";
			ArrayList<Cliente> clientes = (ArrayList<Cliente>) gestorV.obtenerDatosCliente();
			for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(DNIA)) {
			if (cliente.isPermiso()) {
			resultado = "Se ha encontrado el cliente";
			} else {
			resultado = idioma.get("mes3").toString();
			}
			break;
			} else {
			resultado = "El cliente no existe";
			}
			}
			return resultado;
			}
			}
