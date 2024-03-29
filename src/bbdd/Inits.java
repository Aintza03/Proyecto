package bbdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import General.Animal;
import General.Cliente;
import General.Usuario;
import Ventanas.VentanaPrincipal;

public class Inits {
	public static int printClientes(List<Cliente> clientes) {
		// TODO Auto-generated method stub
		int a = 0;
		if (!clientes.isEmpty()) {		
			for(Cliente cliente : clientes) {
				System.out.println(String.format(" - %s", cliente.toString()));
				a++;
			}
		}	
		return a;
	}

	public static int printUsuarios(List<Usuario> usuarios) {
		int a = 0;
		if (!usuarios.isEmpty()) {		
			for(Usuario usuario : usuarios) {
				System.out.println(String.format(" - %s", usuario.toString()));
				a++;
			}
		}	
		return a;
	}
	
	public static List<Usuario> initUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		File fichero = new File("data/usuarios.csv");
		try {
			Scanner sc = new Scanner(fichero);
			sc.nextLine();
			while(sc.hasNextLine()) {
				Usuario usuario = new Usuario(0,null,false);
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				usuario.setContraseña(Integer.parseInt(campos[0]));
				usuario.setUsuario(campos[1]);
				if (campos[2].equals("true")) {
				usuario.setAdmin(true);
				}else {
					usuario.setAdmin(false);
				}
				usuarios.add(usuario);
				
			}
			sc.close();
			VentanaPrincipal.logger.log(Level.FINE, "Usuarios inicializados");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			VentanaPrincipal.logger.log(Level.WARNING , "No se ha encontrado el fichero",e);
		}
		return usuarios;
	}
	
	public static List<Cliente> initClientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		File fichero = new File("data/clientes.csv");
		try {
			Scanner sc = new Scanner(fichero);
			sc.nextLine();
			while(sc.hasNextLine()) {
				Cliente cliente = new Cliente(null,null,0,null);
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				cliente.setDni(campos[0]);
				cliente.setDireccion(campos[1]);
				cliente.setTelefono(Integer.parseInt(campos[2]));
				cliente.setNombre(campos[3]);
				if (campos[4].equals("false")) {
					cliente.setPermiso(false);
				}
				
				clientes.add(cliente);
				
			}
			sc.close();
			VentanaPrincipal.logger.log(Level.FINE,"Clientes inicializados");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			VentanaPrincipal.logger.log(Level.WARNING , "No se ha encontrado el fichero",e);
		}
		return clientes;
	}
	
	public static List<Animal> initAnimales() {
		List<Animal> animales = new ArrayList<>();
		File fichero = new File("data/animales.csv");
		try {
			Scanner sc = new Scanner(fichero);
			sc.nextLine();
			while(sc.hasNextLine()) {
				Animal animal = new Animal(0,null,null,null,null);
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				animal.setId(Integer.parseInt(campos[0]));
				animal.setTipo(campos[1]);
				animal.setFechaNac(campos[2]);	
				animal.setRaza(campos[3]);
				animal.setEspecial(campos[4]);
				animales.add(animal);
				
			}
			sc.close();
			VentanaPrincipal.logger.log(Level.FINE,"Animales inicializados");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			VentanaPrincipal.logger.log(Level.WARNING , "No se ha encontrado el fichero",e);
		}
		
		return animales; 
	}
	public static int printAnimales(List<ArrayList> lista) {
		int a = 0;
		if (!lista.isEmpty()) {	
			ArrayList<Animal> objeto = lista.get(0);
			System.out.println(objeto);
			for(Animal animal : objeto) {
				System.out.println(String.format(" - %s", animal.toString()));
				a++;
			}
		}
		return a;
		}
	}


