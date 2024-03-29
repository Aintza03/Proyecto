package Ventanas;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.awt.event.*;
import javax.swing.*;

import General.Animal;
import General.Cliente;
import General.Usuario;
import bbdd.GestorBD;
import bbdd.Inits;
public class VentanaPrincipal extends JFrame{
		public static Logger logger = Logger.getLogger(VentanaPrincipal.class.getName()) ;
		protected JButton continuar;
		protected JTextField usuario;
		protected JPasswordField contraseña; 
		protected JLabel Error;
		protected JLabel usuarioIdioma;
		protected JLabel contraseñaIdioma;
		//para crear el menu(idioma por efecto al iniciar español)
		protected JMenu idioma;
		protected JMenuItem español;
		protected JMenuItem euskera;
		protected JMenuBar barra;
		protected int contra;
		protected String u;
		//variables parte de la BD
		protected GestorBD gestorV;
		//variables parte del Properties;
		protected Properties i;
		//para la ventanaCliente
		protected VentanaCliente v2;
		public static void finLogger() {
			logger.log(Level.INFO, "Fin del programa");
		}
	public VentanaPrincipal() {
		//Inicializar y declarar el cp
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(3,3));
		//Inicializar los componentes previamente declarados
		usuario = new JTextField();
		contraseña = new JPasswordField();
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
		usuarioIdioma = new JLabel("Usuario:");
		cp.add(usuarioIdioma);
		cp.add(usuario);
		contraseñaIdioma = new JLabel("Contraseña:");
		cp.add(contraseñaIdioma);
		cp.add(contraseña);
		JScrollPane a = new JScrollPane(Error); //como error sera modificado y puede ser muy grande se le añade un scroll
		cp.add(a);
		cp.add(continuar);
		//Action Listener del boton
		continuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				char[] contraseñas = contraseña.getPassword();
				String stringC = "";
				for (char c : contraseñas) {
					stringC = stringC + c;
				}
				try {
					u = usuario.getText();
					contra = Integer.parseInt(stringC);
					ArrayList verificacion = verificarUsuario(u,contra,gestorV,i);
					if (verificacion.get(0).equals("Usuario encontrado")) {
						v2 = new VentanaCliente(i,gestorV,((Usuario) verificacion.get(1)).isAdmin());
						setVisible(false);
						logger.log(Level.INFO,"Usuario encontrado en VentanaPrincipal. Se abre la ventana Cliente");
					} else {
						Error.setText((String) verificacion.get(0));
						logger.log(Level.INFO,"El usuario no ha sido encontrado en VentanaPrincipal");
					}
					
				} catch (Exception e1) {
					Error.setText(i.getProperty("errorTres"));
					logger.log(Level.WARNING,"No se han insertado numeros en VentanaPrincipal");
				}
				
				}
		});
		//Action Listener de la opcion de español
		español.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try (FileReader reader = new FileReader("config/castellano.properties")){
					i = new Properties();
					i.load(reader);
					usuarioIdioma.setText(i.get("usuario").toString()+ ":");
					contraseñaIdioma.setText(i.get("contrasena").toString() + ":");
					continuar.setText(i.get("continuar").toString() + " ->");
					setTitle(i.get("inicio").toString());
					idioma.setText(i.get("idioma").toString());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					logger.log(Level.WARNING, "No se puede encontrar el fichero config/castellano.properties en VentanaPrincipal");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					logger.log(Level.WARNING, "No se puede leer el fichero castellano.properties en VentanaPrincipal");
				}
				
			}
		});
		//Action Listener de la opcion de euskera
		euskera.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try (FileReader reader = new FileReader("config/euskera.properties")){
					i = new Properties();
					i.load(reader);
					usuarioIdioma.setText(i.get("usuario").toString()+ ":");
					contraseñaIdioma.setText(i.get("contrasena").toString() + ":");
					continuar.setText(i.get("continuar").toString() + " ->");
					setTitle(i.get("inicio").toString());
					idioma.setText(i.get("idioma").toString());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					logger.log(Level.WARNING, "No se puede encontrar el fichero config/euskera.properties en VentanaPrincipal");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					logger.log(Level.WARNING, "No se puede leer el fichero euskera.properties en VentanaPrincipal");
				}
			}
		});
		//El idioma por defecto es español
		español.doClick();
		
		//se escoge el modo de cierre de la ventana, su apariencia y nombre
				this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				this.setVisible(true);
				this.setSize(300,150);
				this.setTitle("Inicio de sesion");
				this.setLocationRelativeTo(null);
				this.setIconImage(new ImageIcon("images/image.jpg").getImage());
		Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
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
					usuarioIdioma.setForeground(color);
					contraseñaIdioma.setForeground(color);
					try {
						Thread.sleep(25);
					}catch (InterruptedException e) {
						logger.log(Level.WARNING, "El hilo de la ventana principal no ha podido ejecutar el comando sleep");
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
					continuar.doClick();
				}
			}
		};
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				finLogger();
				System.exit(0);
			}
		});
		this.continuar.addKeyListener(keyListener);
		this.usuario.addKeyListener(keyListener);
		this.contraseña.addKeyListener(keyListener);
		this.español.addKeyListener(keyListener);
		this.euskera.addKeyListener(keyListener);
	}
	public static ArrayList verificarUsuario(String u, int contraseña, GestorBD gestorV,Properties i) {
		ArrayList<Usuario> usuario = (ArrayList<Usuario>) gestorV.obtenerDatosUsuario();
		//ArrayList<Usuario> usuario = new ArrayList();
		ArrayList resultados = new ArrayList();
		String resultado = "";
		Usuario coincide = new Usuario(0,"",false);
		for (Usuario usuario2 : usuario) {
			if (usuario2.getUsuario().equals(u)) {
				if (usuario2.getContraseña() == contraseña) {
					resultado = "Usuario encontrado";
					coincide = usuario2;
				} else {
					resultado = i.getProperty("errorUno");
					}
				break;
				} else {
					resultado = i.getProperty("errorDos");
				}
			
		} 
		resultados.add(resultado);
		resultados.add(coincide);
		return resultados;
	}
	public static void iniciarlogger() {
		try {
			FileInputStream fis = new FileInputStream("config/logger.properties");
			LogManager.getLogManager().readConfiguration(fis);
			}catch (Exception e) {
				logger.log(Level.SEVERE,"No se pudo leer el fichero");
			}
	}
	public static void main(String[] args) {//temporalmente localizado aqui para hacer pruebas
		iniciarlogger();
		logger.log(Level.INFO, "Inicio del programa");
		VentanaPrincipal v = new VentanaPrincipal();
		
		v.gestorV = new GestorBD();
		//v.gestorV.borrarBBDD(); //para comprobaciones de la BD
		//CREATE DATABASE: Se crea la BBDD
		v.gestorV.crearBBDD();
		//INSERT: Insertar datos en la BBDD	inicial	
		List<Usuario> usuarios = Inits.initUsuarios();
		//Si la BD ya tenia los datos iniciales insertados la siguiente linea fallara por su primary key al igual que al insertar clientes, no obstante, habra que detener el proceso de añadir datos a la tabla de animales para evitar duplicados
		if(v.gestorV.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]))) {
			logger.log(Level.INFO,"Inicializado el programa con datos por defecto");
			List<Cliente> clientes = Inits.initClientes();
			v.gestorV.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
			List<Animal> animales = Inits.initAnimales();
			v.gestorV.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));
			
		}else {
			logger.info("Inicializado el programa con datos desde la BD");
		}
}}
