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
		this.setTitle(idioma.getProperty("introducir"));
		this.setLocationRelativeTo(null);
		Buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String res = encontrarCliente(DNI.getText(), gestorV, idioma);
			if (res.equals("Se ha encontrado el cliente")) {
			v3 = new VentanaAcoger(gestorV, idioma, DNI.getText());
			setVisible(false);
			VentanaInstruc.Ventana = 3;
			} else if (res.equals("El cliente no existe")) {
			v2 = new VentanaIntroducirCliente(gestorV,idioma);
			setVisible(false);
			VentanaInstruc.Ventana = 2;
			} else {
			ErrorCliente.setText(res);
			}
			}
			});
			Thread hilo = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int b = 0;
					boolean cambio = true;
					while(isVisible()) {
						if(!(b == 255) && cambio) {
							b++;
						}else if(b == 255) {
							b--;
							cambio = false;
						}else if (b==0) {
							b++;
							cambio = true;
						}else {
							b--;
						}
						Color color = new Color(0,0,b);
						ErrorCliente.setForeground(color);
						dni.setForeground(color);
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			hilo.start();
			
			KeyListener keyListener = new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						Buscar.doClick();
					}
					if (e.isAltDown()) {
						gestorV.actualizarCliente(DNI.getText(), 1);
					}
				}
			};
			this.Buscar.addKeyListener(keyListener);
			this.DNI.addKeyListener(keyListener);
			this.addWindowListener(new WindowAdapter() {
				
				
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					System.exit(0);
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
				if (DNIA.length() == 9) {
			resultado = "El cliente no existe";
			} else {
				resultado = idioma.getProperty("mes9");
			}
			}
			}
			return resultado;
			}
			}
