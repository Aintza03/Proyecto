package Ventanas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class VentanaPrincipal extends JFrame{
		protected JButton continuar;
		protected JTextField usuario;
		protected JTextField contraseña;
		protected JLabel Error;
		//para crear el menu(idioma por efecto al iniciar español)
		protected JMenu idioma;
		protected JMenuItem español;
		protected JMenuItem euskera;
		protected JMenuBar barra;
	public VentanaPrincipal() {
		//Inicializar y declarar el cp
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(3,3));
		//Inicializar los componentes previamente declarados
		usuario = new JTextField();
		contraseña = new JTextField();
		Error = new JLabel("");
		continuar = new JButton("Continuar ->");
		//Inicializar el menu
		barra = new JMenuBar();//se inicializa la barra
		this.setJMenuBar(barra);//se escoge la barra inicializada
		idioma = new JMenu("Idioma");//se escoge la pestaña
		barra.add(idioma);
		español = new JMenuItem("Español");//se crean las opciones
		euskera = new JMenuItem("Euskera");
		idioma.add(español);
		idioma.add(euskera);
		
		//para añadir los componentes previamente inicializados y declarados
		cp.add(new JLabel("Usuario:"));
		cp.add(usuario);
		cp.add(new JLabel("Contraseña:"));
		cp.add(contraseña);
		JScrollPane a = new JScrollPane(Error); //como error sera modificado y puede ser muy grande se le añade un scroll
		cp.add(a);
		cp.add(continuar);
		//Action Listener del boton
		continuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				}
		});
		//Action Listener de la opcion de español
		español.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//Action Listener de la opcion de euskera
		euskera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//se escoge el modo de cierre de la ventana, su apariencia y nombre
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,150);
		this.setName("Inicio de Sesión");
		this.setLocationRelativeTo(null);
	}
	public void leerUsuarios() {
		
	}
	public static void main(String[] args) {//temporalmente localizado aqui para hacer pruebas
		VentanaPrincipal v = new VentanaPrincipal();
	}

}
