package Ventanas;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bbdd.GestorBD;
public class VentanaUsuarios extends JFrame {
	protected JLabel Usuario;
	protected JLabel Usuario2;
	protected JLabel Contraseña;
	protected JLabel Admin;
	protected JLabel Error;
	protected JLabel Error2;
	protected JTextField usuario;
	protected JPasswordField contraseña;
	protected JTextField admin;
	protected JButton continuar;
	protected JTextField borrado;
	protected JButton eliminar;
	
	protected JTabbedPane pestaña;
	public VentanaUsuarios(GestorBD v,Properties p) {
		Container cp = this.getContentPane();
		pestaña = new JTabbedPane();
		JPanel cp1 = new JPanel();
		JScrollPane a = new JScrollPane(Error);
		cp1.setLayout(new GridLayout(2,1));
		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(1,6));
		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(1,2));
		
		Usuario = new JLabel(p.getProperty("usuario") + ":");
		Contraseña = new JLabel(p.getProperty("contrasena") + ":");
		Admin = new JLabel("Admin: ");
		Error = new JLabel("");
		
		usuario = new JTextField("");
		contraseña = new JPasswordField("");
		admin = new JTextField("");
		continuar = new JButton(p.getProperty("AnaUsu"));
		
		arriba.add(Usuario);
		arriba.add(usuario);
		arriba.add(Contraseña);
		arriba.add(contraseña);
		arriba.add(Admin);
		arriba.add(admin);
		abajo.add(Error);
		abajo.add(continuar);
		cp1.add(arriba);
		cp1.add(abajo);
		
		JPanel cp2 = new JPanel();
		cp2.setLayout(new GridLayout(2,2));
		Usuario2 = new JLabel(p.getProperty("usuario") + ": ");
		Error2 = new JLabel("");
		JScrollPane b = new JScrollPane(Error2);
		
		borrado = new JTextField();
		eliminar = new JButton(p.getProperty("Elimi"));
		cp2.add(Usuario2);
		cp2.add(borrado);
		cp2.add(b);
		cp2.add(eliminar);	
		pestaña.addTab(p.getProperty("AnaUsu"), cp1);
		pestaña.addTab(p.getProperty("elimUsu"), cp2);
		cp.add(pestaña);
		continuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!(usuario.getText().isEmpty()) && !(contraseña.getPassword().length != 4) && !(admin.getText().isEmpty())) {
					char[] contraseñas = contraseña.getPassword();
					String stringC = "";
					for (char c : contraseñas) {
						stringC = stringC + c;
					}
					try {
						String u = usuario.getText();
						Integer contra = Integer.parseInt(stringC);
						boolean ad = false;
						boolean guardable = false;
						if (admin.getText().equals("Si")) {
							ad = true;
							guardable = true;
						}else if (admin.getText().equals("No")) {
							ad = false;
							guardable = true;
						}else {
							Error.setText("El campo administrador debe contener o Si o No");
						}
						if (guardable) {
							boolean e3 = v.insertarDatosUsuario(new General.Usuario(contra,u,ad));
							if (!e3) {
								Error.setText("La contraseña o el Usuario no son validos");
							}
						}
					} catch (Exception e2) {
						// TODO: handle exception
						System.err.println("La contraseña tiene letras");
						Error.setText("La contraseña no debe tener letras");
					}
				} else {
					Error.setText("Ninguno de los campos puede estar vacio o la contraseña no tiene 4 digitos.");
				}
			}
		});
		eliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean ocurre = v.borrarDatosUsuario(borrado.getText());
				if (ocurre) {
					Error2.setText("Se ha borrado el Usuario");
				} else {
					Error2.setText("No se ha encontrado el Usuario");
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
					if (!(b==255) && cambio) {
						b++;
					}else if (b ==255){
						b--;
						cambio = false;
					}else if (b==0) {
						b++;
						cambio = true;
					}else {
						b--;
					}
					Color color = new Color(0,0,b);
					Error.setForeground(color);
					Usuario.setForeground(color);
					Usuario2.setForeground(color);
					Contraseña.setForeground(color);
					Admin.setForeground(color);
					Error.setForeground(color);
					Error2.setForeground(color);
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
		
		pestaña.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(pestaña.getSelectedIndex() == 0) {
					setSize(500,150);
				}else if (pestaña.getSelectedIndex() == 1) {
					setSize(300,150);
				
			}
			}
		});
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (pestaña.getSelectedIndex() == 0) {
						continuar.doClick();
					}else {
						eliminar.doClick();
					}
				}
			}
		};
		this.usuario.addKeyListener(keyListener);
		this.pestaña.addKeyListener(keyListener);
		this.contraseña.addKeyListener(keyListener);
		this.admin.addKeyListener(keyListener);
		this.borrado.addKeyListener(keyListener);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(500,150);
		this.setTitle(p.getProperty("Anadir/EliminarUsuarios"));
		this.setLocationRelativeTo(null);
		hilo.start();
		
	}
}
