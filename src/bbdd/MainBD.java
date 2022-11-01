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
		ArrayList<Cliente> clientes = (ArrayList<Cliente>) initClientes();
		gestorBD.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<ArrayList> animales = initAnimales();
		gestorBD.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));

		//SELECT: Se obtienen datos de la BBDD
		//usuarios = gestorBDUsuario.obtenerDatosUsuario();
		//printUsuarios(usuarios);
		//clientes = (ArrayList<Cliente>) gestorBDCliente.obtenerDatosCliente();
		//printClientes(clientes);
		//animales = gestorBDAnimal.obtenerDatosAnimal(clientes);
		//printAnimales(animales);
		//SELECT: Se obtienen datos de la BBDD
		//usuarios = gestorBDUsuario.obtenerDatosUsuario();
		//printUsuarios(usuarios);

		//DELETE: Se borran datos de la BBDD
		//gestorBDUsuario.borrarDatos();
		
		//DROP DATABASE: Se borra la BBDD
		//gestorBDUsuario.borrarBBDDUsuario();
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
		usuario.setUsuario("Bruce Banner");
		usuario.setContraseña(1234);
		usuarios.add(usuario);
		
		return usuarios;
	}
	private static void printAnimales(List<ArrayList> lista) {
		if (!lista.isEmpty()) {	
			
			for(Object elemento : lista) {
				try {System.out.println(String.format(" - %s", elemento.toString()));
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println(String.format(" - %s", elemento.toString()));
			} {
				
					
				
			}}
		}		
	}
	
	public static List<ArrayList> initAnimales() {
		List<ArrayList> animales = null;
		ArrayList<Animal> animal = new ArrayList<Animal>();
		animal.add(new Animal(0, null, null, null, null));
		animal.add(new Animal(5, null, null, null, null));
		animales.add(animal);
		return animales;
	}
	private static void printClientes(List<Cliente> clientes) {
		if (!clientes.isEmpty()) {		
			for(Cliente cliente : clientes) {
				System.out.println(String.format(" - %s", cliente.toString()));
			}
		}		
	}
	
	public static List<Cliente> initClientes() {
		List<Cliente> clientes = null;
		Cliente cliente = new Cliente(null, null, 0, null); 
		clientes.add(cliente);
		return clientes;
	}

}

