package Ventanas;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.event.*;
import javax.swing.*;

import General.Animal;
import General.Cliente;
import General.Usuario;
import bbdd.GestorBD;
import bbdd.Inits;
public class VentanaPrincipal extends JFrame{
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
					String verificacion = verificarUsuario(u,contra);
					if (verificacion.equals("Usuario encontrado")) {
						Error.setText(verificacion); //Temporal hasta crear la 2ª ventana
						//setVisible(false);
					} else {
						Error.setText(verificacion);
					}
					System.out.println(verificarUsuario(u,contra));
				} catch (Exception e1) {
					Error.setText("La contraseña insertada no es una contraseña valida.");
					System.out.println("No se han insertado numeros");
					e1.printStackTrace();
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
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("No se puede encontrar el fichero config/castellano.properties");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.err.println("No se puede leer el fichero");
					e1.printStackTrace();
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
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					System.out.println("No se puede encontrar el fichero config/euskera.properties");
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.err.println("No se puede leer el fichero");
					e1.printStackTrace();
				}
			}
		});
		//El idioma por defecto es español
		español.doClick();
		//se escoge el modo de cierre de la ventana, su apariencia y nombre
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,150);
		this.setTitle("Inicio de Sesión");
		this.setLocationRelativeTo(null);
	}
	public String verificarUsuario(String u, int contraseña) {
		ArrayList<Usuario> usuario = (ArrayList<Usuario>) gestorV.obtenerDatosUsuario();
		//ArrayList<Usuario> usuario = new ArrayList(); usuario.add(new Usuario(12,"Hola"));
		String resultado = "";
		for (Usuario usuario2 : usuario) {
			if (usuario2.getUsuario().equals(u)) {
				if (usuario2.getContraseña() == contraseña) {
					resultado = "Usuario encontrado";
				} else {
					resultado = "La contraseña no coincide";
				} 
				} else {
					resultado = "El usuario no existe";
			}
		}
		return resultado;
	}
	public static void main(String[] args) {//temporalmente localizado aqui para hacer pruebas
		VentanaPrincipal v = new VentanaPrincipal();
		VentanaAcoger a = new VentanaAcoger();
		v.gestorV = new GestorBD();
		//v.gestorV.borrarBBDDUsuario(); //para comprobaciones de la BD
		//CREATE DATABASE: Se crea la BBDD
		v.gestorV.crearBBDDUsuario();
		
		//INSERT: Insertar datos en la BBDD	inicial	
		List<Usuario> usuarios = Inits.initUsuarios();
		//Si la BD ya tenia los datos iniciales insertados la siguiente linea fallara por su primary key al igual que al insertar clientes, no obstante, habra que detener el proceso de añadir datos a la tabla de animales para evitar duplicados
		if(v.gestorV.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]))) {
			List<Cliente> clientes = Inits.initClientes();
			v.gestorV.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
			List<Animal> animales = Inits.initAnimales();
			v.gestorV.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));
			
		}
		
	}

}
