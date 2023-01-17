package Ventanas;

import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

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
	public VentanaIntroducirCliente(String dnis,GestorBD v,Properties idioma) {
		Container cp = this.getContentPane();
		DNI = new JLabel(idioma.get("dni1").toString());
		NOMBRE = new JLabel(idioma.get("nombre").toString());
		TELEFONO = new JLabel(idioma.get("tel").toString());
		DIRECCION = new JLabel(idioma.get("dir").toString());
		Error = new JLabel("");
		
		dni = new JTextField(dnis);
		dni.setEditable(false);
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
		this.setTitle(idioma.getProperty("nuevo"));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("images/image.jpg").getImage());
		
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
		VentanaPrincipal.logger.log(Level.WARNING, "El nombre no es correcto en VentanaIntroducirCliente");
		}
		} else {
		Error.setText(idioma.get("mes5").toString());
		VentanaPrincipal.logger.log(Level.WARNING,"Direccion incorrecta en VentanaIntroducirCliente");
		}
		} else {
		Error.setText(idioma.get("mes6").toString());
		VentanaPrincipal.logger.log(Level.WARNING,"El numero es incorrecto en VentanaIntroducirCliente");
		}
		if (dniC != null && direccionC != null && telefonoC != 0 && nombreC != null) {
		//si el if se cumple significara que se ha podido crear el cliente
		cliente = new Cliente(dniC,direccionC,telefonoC,nombreC);
		//Error.setText("Cliente Registrado");
		v.insertarDatosCliente(cliente);
		v3 = new VentanaAcoger(v , idioma, dni.getText());
		VentanaPrincipal.logger.log(Level.INFO," Se ha insertado el cliente.Se ha abierto la ventana acoger desde VentanaIntroducirCliente");

		}else {
			VentanaPrincipal.logger.log(Level.WARNING,"El cliente no ha sido insertado");
		}

		} catch (Exception e2) {
		// TODO: handle exception
		Error.setText(idioma.get("mes8").toString());
		VentanaPrincipal.logger.log(Level.WARNING , "El telefono introducido tiene letras");
		}
		}
		});
		Thread hilo1 = new Thread(new Runnable() {
			
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
					DNI.setForeground(color);
					NOMBRE.setForeground(color);
					TELEFONO.setForeground(color);
					DIRECCION.setForeground(color);
					Error.setForeground(color);
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						VentanaPrincipal.logger.log(Level.WARNING , "El hilo de la ventana introducir cliente no se ha detenido como debia con sleep");
					}
				}
			}
		});
		hilo1.start();
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				registrarCliente.doClick();
				}
			}
			};
		this.dni.addKeyListener(keyListener);
		this.nombre.addKeyListener(keyListener);
		this.telefono.addKeyListener(keyListener);
		this.direccion.addKeyListener(keyListener);
		this.registrarCliente.addKeyListener(keyListener);
		this.addWindowListener(new WindowAdapter() {
			
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.finLogger();
				System.exit(0);
			}
		});
		}
		
		public static boolean DNIAPTO(String dni) {
		//Comprueba que el dni empieza por 8 digitos y 1 letra
		if(dni.length() == 8) {
			try {
			int a = Integer.parseInt(dni);
			return true;
			} catch(Exception e) {
				VentanaPrincipal.logger.log(Level.WARNING, "El dni introducido no se puede usar");
				return false;
			}
		}else {
			VentanaPrincipal.logger.log(Level.WARNING,"El dni introducido no se puede usar");
			return false;
		}
		}
	}