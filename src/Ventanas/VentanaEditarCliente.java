package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import bbdd.GestorBD;

public class VentanaEditarCliente extends JFrame{
	
	protected JLabel DNI;
	protected JLabel DNI2;
	protected JLabel dniletra;
	protected JLabel dniletra2;
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
		abajo.setLayout(new GridLayout(2,2));
		
		DNI = new JLabel(p.getProperty("dni") + ": ");
		TELEFONO = new JLabel(p.getProperty("tel") + " ");
		DIRECCION = new JLabel(p.getProperty("dir") + " ");
		Error = new JLabel("");
		JScrollPane c = new JScrollPane(Error);
		dni = new JTextField("");
		dniletra = new JLabel("");
		telefono = new JTextField("");
		direccion = new JTextField("");
		registrarCliente = new JButton(p.getProperty("editCli"));
		
		arriba.add(DNI);
		arriba.add(dni);
		arriba.add(TELEFONO);
		arriba.add(telefono);
		arriba.add(DIRECCION);
		arriba.add(direccion);
		abajo.add(dniletra);
		abajo.add(new JLabel(""));
		abajo.add(c);
		abajo.add(registrarCliente);
		cp1.add(arriba);
		cp1.add(abajo);
		
		JPanel cp2 = new JPanel();
		cp2.setLayout(new GridLayout(3,2));
		DNI2 = new JLabel(p.getProperty("dni") + ":");
		Error2 = new JLabel("");
		JScrollPane b = new JScrollPane(Error2);
		
		dni2 = new JTextField();
		dniletra2 = new JLabel("");
		eliminarCliente = new JButton(p.getProperty("Elimi"));
		cp2.add(DNI2);
		cp2.add(dni2);
		cp2.add(dniletra2);
		cp2.add(new JLabel(""));
		cp2.add(b);
		cp2.add(eliminarCliente);	
		pestaña.addTab(p.getProperty("editCli"), cp1);
		pestaña.addTab(p.getProperty("elimCli"), cp2);
		cp.add(pestaña);
		dni.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				dniletra.setText(dni.getText());
				if (VentanaIntroducirCliente.DNIAPTO(dni.getText())) {
					int a = Integer.parseInt(dni.getText());
					int b = a%23;
					String alfabeto = "TRWAGMYFPDXBNJZSQVHLCKE";
					dniletra.setText(dni.getText() + alfabeto.charAt(b));
					Error.setText("");
				}else {
					Error.setText(p.getProperty("error17"));
					VentanaPrincipal.logger.warning("El dni no es correcto en VentanaEditarCliente");
				}
			}
		});
		dni2.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				dniletra2.setText(dni2.getText());
				if (VentanaIntroducirCliente.DNIAPTO(dni2.getText())) {
					int a = Integer.parseInt(dni2.getText());
					int b = a%23;
					String alfabeto = "TRWAGMYFPDXBNJZSQVHLCKE";
					dniletra2.setText(dni2.getText() + alfabeto.charAt(b));
					Error2.setText("");
				} else {
					Error2.setText(p.getProperty("error17"));
					VentanaPrincipal.logger.warning("El dni no es correcto en VentanaEditarCliente");
				}
			}
		});
		eliminarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean ocurre = v.borrarDatosCliente(dniletra2.getText());
				if (ocurre) {
					Error2.setText(p.getProperty("error16"));
					VentanaPrincipal.logger.info("Se ha borrado el cliente en VentanaEditarCliente");
				} else {
					Error2.setText(p.getProperty("error18"));
					VentanaPrincipal.logger.log(Level.WARNING,"El cliente no ha sido encontrado");
				}
			}
		});
		
		registrarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				String a = telefono.getText();
				int b = Integer.parseInt(a);
					if(telefono.getText().length() == 9) {
						if(direccion.getText().length() != 0) {
							boolean ocurre = v.actualizarClienteYaExistente(dniletra.getText(), b, direccion.getText());
							if (ocurre) {
								Error.setText(p.getProperty("error19"));
								VentanaPrincipal.logger.log(Level.INFO,"Cliente registrado en VentanaEditarCliente");
							} else {
								Error.setText(p.getProperty("error20"));
								VentanaPrincipal.logger.log(Level.WARNING,"El cliente no se ha actualizado en VentanaEditarCliente");
							}
						}else {
							Error.setText(p.getProperty("error21"));
							VentanaPrincipal.logger.log(Level.WARNING,"No se admite esa direccion en VentanaEditarCliente");
						}
					}else {
						Error.setText(p.getProperty("error22"));
						VentanaPrincipal.logger.log(Level.WARNING, "No se admite el numero de telefono en VentanaEditarCliente");
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					Error.setText(p.getProperty("error22"));
					VentanaPrincipal.logger.log(Level.WARNING, "No se admite el numero de telefono en VentanaEditarCliente");
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
					dniletra.setForeground(color);
					dniletra2.setForeground(color);
					try {
						Thread.sleep(25);
					}catch (InterruptedException e) {
						VentanaPrincipal.logger.log(Level.WARNING , "El hilo de ventana editar cliente no ha ejecutado la instruccion sleep correctamente");
					}
				}
			}
		});
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.finLogger();
				System.exit(0);
			}
			
			
		});
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500,150);
		this.setTitle(p.getProperty("Editar/EliminarClientes"));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("images/image.jpg").getImage());
		hilo.start();
	}
	
	
}
