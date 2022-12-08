package bbdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import General.Animal;
import General.Cliente;
import General.Usuario;

public class Inits {
	public static void printClientes(List<Cliente> clientes) {
		// TODO Auto-generated method stub
		if (!clientes.isEmpty()) {		
			for(Cliente cliente : clientes) {
				System.out.println(String.format(" - %s", cliente.toString()));
			}
		}	
	}

	public static void printUsuarios(List<Usuario> usuarios) {
		if (!usuarios.isEmpty()) {		
			for(Usuario usuario : usuarios) {
				System.out.println(String.format(" - %s", usuario.toString()));
			}
		}		
	}
	
	public static List<Usuario> initUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		File fichero = new File("data/usuarios.csv");
		try {
			Scanner sc = new Scanner(fichero);
			sc.nextLine();
			while(sc.hasNextLine()) {
				Usuario usuario = new Usuario(0,null);
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				usuario.setContraseña(Integer.parseInt(campos[0]));
				usuario.setUsuario(campos[1]);
				usuarios.add(usuario);
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Usuario usuario = new Usuario(1111, "nombre");
		//usuario.setUsuario("Bruce A");
		//usuario.setContraseña(1234);
		//usuarios.add(usuario);
		
		//usuario = new Usuario(1111, "nombre");
		//usuario.setUsuario("Bruce B");
		//usuario.setContraseña(1234);
		//usuarios.add(usuario);
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Cliente cliente = new Cliente(null, null, 1111, "nombre", null, null, false);
		//cliente.setDni("Bruce Banner");
		//cliente.setDireccion("a");
		//cliente.setTelefono(90909090);
		//cliente.setNombre("Bruce Banner");
		//cliente.setAnimalesAdoptados(null);
		//cliente.setAnimalesAcogidos(null);
		//cliente.setPermiso(true);
		
		return clientes;
	}
	
	public static List<Animal> initAnimales() {
		List<Animal> animales = new ArrayList<>();
		File fichero = new File("data/animales.csv");
		try {
			Scanner sc = new Scanner(fichero);
			sc.nextLine();
			while(sc.hasNextLine()) {
				Animal animal = new Animal(0,null,null,null,new Date());
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				animal.setId(Integer.parseInt(campos[0]));
				animal.setTipo(campos[1]);
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					animal.setFechaNac(formato.parse(campos[2]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				animal.setRaza(campos[3]);
				animal.setEspecial(campos[4]);
				animales.add(animal);
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Animal animal = new Animal(1111, "nombre", null, null, null);
		//animal.setId(2002);
		//animal.setRaza("Siames");
		//animal.setEspecial(null);
		//animal.setTipo("gato");
		//animal.setFechaNac(null);
		//animales.add(animal);
		
		return animales; 
	}
	public static void printAnimales(List<ArrayList> lista) {
		if (!lista.isEmpty()) {	
			ArrayList<Animal> objeto = lista.get(0);
			System.out.println(objeto);
			for(Animal animal : objeto) {
				System.out.println(String.format(" - %s", animal.toString()));
			}
		}	
		}
			
	}


