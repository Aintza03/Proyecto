package bbdd;

import java.util.ArrayList;
import java.util.List;

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
		gestorBD.update(usuarios,resultado.get(1),clientes);
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
		
		Usuario usuario = new Usuario(1111, "nombre");
		usuario.setUsuario("Bruce A");
		usuario.setContraseña(1234);
		usuarios.add(usuario);
		
		usuario = new Usuario(1111, "nombre");
		usuario.setUsuario("Bruce B");
		usuario.setContraseña(1234);
		usuarios.add(usuario);
		return usuarios;
	}
	
	public static List<Cliente> initClientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente cliente = new Cliente(null, null, 1111, "nombre", null, null, false);
		cliente.setDni("Bruce Banner");
		cliente.setDireccion("a");
		cliente.setTelefono(90909090);
		cliente.setNombre("Bruce Banner");
		cliente.setAnimalesAdoptados(null);
		cliente.setAnimalesAcogidos(null);
		cliente.setPermiso(true);
		
		return clientes;
	}
	
	public static List<Animal> initAnimales() {
		List<Animal> animales = new ArrayList<>();
		
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


	


