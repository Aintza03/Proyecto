package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

import javax.swing.*;

import bbdd.GestorBD;

public class VentanaEditarCliente extends JFrame{
	
	protected JLabel DNI;
	protected JLabel DNI2;
	protected JLabel TELEFONO;
	protected JLabel DIRECCION;
	
	protected JTextField dni;
	protected JTextField dni2;
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
		
		DNI = new JLabel(p.getProperty("dni") + ": ");
		TELEFONO = new JLabel(p.getProperty("tel") + " ");
		DIRECCION = new JLabel(p.getProperty("dir") + " ");
		Error = new JLabel("");
		
		dni = new JTextField("");
		telefono = new JTextField("");
		direccion = new JTextField("");
		registrarCliente = new JButton(p.getProperty("editCli"));
		
		arriba.add(DNI);
		arriba.add(dni);
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
		DNI2 = new JLabel(p.getProperty("dni"));
		Error2 = new JLabel("");
		JScrollPane b = new JScrollPane(Error2);
		
		dni2 = new JTextField();
		eliminarCliente = new JButton(p.getProperty("Elimi"));
		cp2.add(DNI2);
		cp2.add(dni2);
		cp2.add(b);
		cp2.add(eliminarCliente);	
		pestaña.addTab(p.getProperty("editCli"), cp1);
		pestaña.addTab(p.getProperty("elimCli"), cp2);
		cp.add(pestaña);
		
		eliminarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean apto = VentanaIntroducirCliente.DNIAPTO(dni2.getText());
				boolean ocurre = v.borrarDatosCliente(dni2.getText());
				if (ocurre && apto) {
					Error2.setText("Se ha borrado el Cliente");
				}else if (!apto){
					Error2.setText("El DNI introducido no es apto");
				} else if (!ocurre) {
					Error2.setText("No se ha encontrado el Cliente");
				}
			}
		});
		
		registrarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
				String a = telefono.getText();
				int b = Integer.parseInt(a);
				if(dni.getText().length() == 9) {
					boolean cliente = VentanaIntroducirCliente.DNIAPTO(dni.getText());
					if (cliente) {
					if(telefono.getText().length() == 9) {
						if(direccion.getText().length() != 0) {
							boolean ocurre = v.actualizarClienteYaExistente(dni.getText(), b, direccion.getText());
							if (ocurre) {
								Error.setText("Se ha actualizado el cliente");
							} else {
								Error.setText("El cliente no se ha actualizado.");
							}
						}else {
							Error.setText("No se admite esa dirección.");
						}
					}else {
						Error.setText("No se admite ese número de telefono.");
					}
					} else {
						Error.setText("El DNI introducido no es valido.");
					}
				}else{
					Error.setText("El DNI introducido no es valido.");
				}
					
				} catch (Exception e2) {
					// TODO: handle exception
					Error.setText("No se admite ese número de telefono.");
				}
				
				
			}
		});
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (pestaña.getSelectedIndex() == 0) {
						registrarCliente.doClick();
					}else {
						eliminarCliente.doClick();
					}
				}
			}
		};
		this.dni.addKeyListener(keyListener);
		this.pestaña.addKeyListener(keyListener);
		this.dni2.addKeyListener(keyListener);
		this.telefono.addKeyListener(keyListener);
		this.direccion.addKeyListener(keyListener);
		
		Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int b = 0;
				boolean cambio = true;
				while(isVisible()) {
					if(!(b==255) && cambio) {
						b++;
					}else if(b == 255) {
						b--;
						cambio = false;
					}else if(b == 0) {
						b++;
						cambio = true;
					}else {
						b--;
					}
					Color color = new Color(0,0,b);
					Error.setForeground(color);
					Error2.setForeground(color);
					DNI.setForeground(color);
					DNI2.setForeground(color);
					TELEFONO.setForeground(color);
					DIRECCION.setForeground(color);
					try {
						Thread.sleep(25);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
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
		this.setTitle(p.getProperty("Editar/EliminarClientes"));
		this.setLocationRelativeTo(null);
		hilo.start();
	}
	
	
}
