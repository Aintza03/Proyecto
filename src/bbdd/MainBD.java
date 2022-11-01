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
		
		Usuario usuario1 = new Usuario(1111, "nombre");
		usuario.setUsuario("Bruce Banner");
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


	
}

