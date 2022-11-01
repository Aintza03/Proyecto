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


public class MainBD {

	public static void main(String[] args) {
		GestorBD gestorBD = new GestorBD();
		
		//CREATE DATABASE: Se crea la BBDD
		gestorBD.crearBBDDUsuario();
		gestorBD.crearBBDDCliente();
		gestorBD.crearBBDDAnimal();
		
		//INSERT: Insertar datos en la BBDD		
		List<Usuario> usuarios = initUsuarios();
		gestorBD.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = initClientes();
		gestorBD.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = initAnimales();
		gestorBD.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));

		//SELECT: Se obtienen datos de la BBDD
		usuarios = gestorBD.obtenerDatosUsuario();
		printUsuarios(usuarios);
		clientes = (ArrayList<Cliente>) gestorBD.obtenerDatosCliente();
		printClientes(clientes);
		ArrayList<Cliente> listac = (ArrayList<Cliente>) clientes;
		List<ArrayList> resultado = gestorBD.obtenerDatosAnimal((listac));
		printAnimales(resultado);
		//UPDATE: Se actualizan los datos
		Animal animal = new Animal(2002, "nombre", null, null, null);
		gestorBD.update(animal, "090909c", null);
		//DELETE: Se borran datos de la BBDD (No vamos a usar borrarDatos() )
	    gestorBD.borrarDatos();
		
		//DROP DATABASE: Se borra la BBDD (No vamos a usarlo en el proyecto)
		 gestorBD.borrarBBDDUsuario();
	}
	
	private static void printClientes(List<Cliente> clientes) {
		// TODO Auto-generated method stub
		if (!clientes.isEmpty()) {		
			for(Cliente cliente : clientes) {
				System.out.println(String.format(" - %s", cliente.toString()));
			}
		}	
	}

	private static void printUsuarios(List<Usuario> usuarios) {
		if (!usuarios.isEmpty()) {		
			for(Usuario usuario : usuarios) {
				System.out.println(String.format(" - %s", usuario.toString()));
			}
		}		
	}
	
	public static List<Usuario> initUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		File fichero = new File("data/usuarios");
		try {
			Scanner sc = new Scanner(fichero);
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
		
		File fichero = new File("data/clientes");
		try {
			Scanner sc = new Scanner(fichero);
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
		File fichero = new File("data/animales");
		try {
			Scanner sc = new Scanner(fichero);
			while(sc.hasNextLine()) {
				Animal animal = new Animal(0,null,null,null,new Date());
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				animal.setTipo(campos[0]);
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				try {
					animal.setFechaNac(formato.parse(campos[1]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				animal.setRaza(campos[2]);
				animal.setEspecial(campos[3]);
				animales.add(animal);
				
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Animal animal = new Animal(1111, "nombre", null, null, null);
		animal.setId(2002);
		animal.setRaza("Siames");
		animal.setEspecial(null);
		animal.setTipo("gato");
		animal.setFechaNac(null);
		animales.add(animal);
		
		return animales; 
	}
	private static void printAnimales(List<ArrayList> lista) {
		if (!lista.isEmpty()) {	
			ArrayList<Animal> objeto = lista.get(1);
			for(Animal animal : objeto) {
				System.out.println(String.format(" - %s", animal.toString()));
			}
		}	
		}
			
	}


	


