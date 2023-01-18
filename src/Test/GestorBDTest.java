package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;
import General.Cliente;
import General.Usuario;
import Ventanas.VentanaPrincipal;
import bbdd.GestorBD;
import bbdd.Inits;

public class GestorBDTest {
	protected static GestorBD gestor;
	protected static ArrayList<Cliente> listaC;
	protected static ArrayList<Usuario> listaU;
	protected static ArrayList<Animal> listaA;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		VentanaPrincipal.iniciarlogger();
		gestor = new GestorBD();
		listaC = (ArrayList<Cliente>) gestor.obtenerDatosCliente();
		listaU = (ArrayList<Usuario>) gestor.obtenerDatosUsuario();
		listaA = gestor.obtenerDatosAnimal(listaC).get(0);
		gestor.borrarBBDD();
		gestor.crearBBDD();
		List<Usuario> usuarios = Inits.initUsuarios();
		gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = Inits.initClientes();
		gestor.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = Inits.initAnimales();
		gestor.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		Cliente[] c = listaC.toArray(new Cliente[listaC.size()]);
		Usuario[] u = listaU.toArray(new Usuario[listaU.size()]);
		Animal[] a = listaA.toArray(new Animal[listaA.size()]);
		gestor.insertarDatosUsuario(u);
		gestor.insertarDatosCliente(c);
		gestor.insertarDatosAnimal(a);
		gestor = null;
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrearBBDD() {
		gestor.borrarBBDD();
		assertTrue(gestor.crearBBDD());
		assertTrue(gestor.crearBBDD());
		List<Usuario> usuarios = Inits.initUsuarios();
		gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = Inits.initClientes();
		gestor.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = Inits.initAnimales();
		gestor.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));

	}

	@Test
	public void testBorrarBBDD() {
		assertTrue(gestor.borrarBBDD());
		gestor.crearBBDD();
		List<Usuario> usuarios = Inits.initUsuarios();
		gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = Inits.initClientes();
		gestor.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = Inits.initAnimales();
		gestor.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));
	}

	@Test
	public void testInsertarDatosUsuario() {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		List<Usuario> usuarios = Inits.initUsuarios();
		assertTrue(gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()])));
		List<Cliente> clientes = Inits.initClientes();
		gestor.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = Inits.initAnimales();
		gestor.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));
	}

	@Test
	public void testInsertarDatosCliente() {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		List<Usuario> usuarios = Inits.initUsuarios();
		gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = Inits.initClientes();
		assertTrue(gestor.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()])));
		List<Animal> animales = Inits.initAnimales();
		gestor.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));
	}

	@Test
	public void testInsertarDatosAnimal() {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		List<Usuario> usuarios = Inits.initUsuarios();
		gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = Inits.initClientes();
		gestor.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = Inits.initAnimales();
		assertTrue(gestor.insertarDatosAnimal(animales.toArray(new Animal[animales.size()])));
	}

	@Test
	public void testObtenerDatosUsuario() {
		ArrayList<Usuario> lista = (ArrayList<Usuario>) gestor.obtenerDatosUsuario();
		for (Usuario usuario : lista) {
			if(usuario.getUsuario().equals("Aintzane") && !(usuario.getContraseña() == 1234 && usuario.isAdmin() == true)) {
				fail();
			} 
			if(usuario.getUsuario().equals("Irea") && !(usuario.getContraseña() == 5678 && usuario.isAdmin() == false)) {
				fail();
			}
		}
	}

	@Test
	public void testObtenerDatosCliente() {
		ArrayList<Cliente> lista = (ArrayList<Cliente>) gestor.obtenerDatosCliente();
		for (Cliente cliente : lista) {
			if(cliente.getDni().equals("11111111H") && !(cliente.getDireccion().equals("Amorebieta") && cliente.getTelefono() == 623435689 && cliente.getNombre().equals("Aritz") && cliente.isPermiso() == true)) {
				fail();
			} 
			if(cliente.getDni().equals("22222222J") && !(cliente.getDireccion().equals("Bilbao") && cliente.getTelefono() == 778129034 && cliente.getNombre().equals("Amaia") && cliente.isPermiso() == false)) {
				fail();
			}
		}
	}

	@Test
	public void testObtenerDatosAnimal() {
		ArrayList<Animal> lista = (ArrayList<Animal>) gestor.obtenerDatosAnimal((ArrayList<Cliente>) gestor.obtenerDatosCliente()).get(0);
		for (Animal animal : lista) {
			if(animal.getId() == 1 && !(animal.getTipo().equals("Gato") && animal.getFechaNac().equals("20/01/2018") && animal.getRaza().equals("Blanco") && animal.getEspecial().equals("nada"))) {
				fail();
			}
			if(animal.getId() == 59 && !(animal.getTipo().equals("Perro") && animal.getFechaNac().equals("19/03/2021") && animal.getRaza().equals("Border Collie") && animal.getEspecial().equals("nada"))) {
				fail();
			}
		}
	}

	@Test
	public void testBorrarDatosUsuario() {
		ArrayList<Usuario> lista = (ArrayList<Usuario>) gestor.obtenerDatosUsuario();
		Usuario u = null;
		for (Usuario usuario : lista) {
			if(usuario.getUsuario().equals("Aintzane")) {
				u = usuario;
				break;
			}
		}
		assertTrue(gestor.borrarDatosUsuario("Aintzane"));
		gestor.insertarDatosUsuario(u);
	}

	@Test
	public void testBorrarDatosCliente() {
		ArrayList<Cliente> lista = (ArrayList<Cliente>) gestor.obtenerDatosCliente();
		Cliente c = null;
		for (Cliente cliente : lista) {
			if(cliente.getDni().equals("11111111H")) {
				c = cliente;
				break;
			}
		}
		assertTrue(gestor.borrarDatosCliente("11111111H"));
		gestor.insertarDatosCliente(c);
	}
	@Test
	public void testBorrarDatosAnimal() {
		ArrayList<Animal> lista = (ArrayList<Animal>) gestor.obtenerDatosAnimal((ArrayList<Cliente>) gestor.obtenerDatosCliente()).get(0);
		Animal c = null;
		for (Animal animal : lista) {
			if(animal.getId() == 15) {
				c = animal;
				break;
			}
		}
		assertTrue(gestor.borrarDatosAnimal(15));
		gestor.insertarDatosAnimal(c);
	}

	@Test
	public void testActualizarAnimal() {
		ArrayList<Animal> lista = (ArrayList<Animal>) gestor.obtenerDatosAnimal((ArrayList<Cliente>) gestor.obtenerDatosCliente()).get(0);
		Animal c = null;
		for (Animal animal : lista) {
			if(animal.getId() == 20) {
				c = animal;
				break;
			}
		}
		assertTrue(gestor.actualizarAnimal(c, "11111111H", null));
		assertTrue(gestor.actualizarAnimal(c,null , "11111111H"));
		assertTrue(gestor.actualizarAnimal(c,null , null));
	}

	@Test
	public void testActualizarCliente() {
		ArrayList<Cliente> lista = (ArrayList<Cliente>) gestor.obtenerDatosCliente();
		Cliente c = null;
		for (Cliente cliente : lista) {
			if(cliente.getDni().equals("22222222J")) {
				c = cliente;
				break;
			}
		}
		assertTrue(gestor.actualizarCliente("22222222J", 1));
		assertTrue(gestor.actualizarCliente("22222222J", 0));
	}

	@Test
	public void testActualizarClienteYaExistente() {
		ArrayList<Cliente> lista = (ArrayList<Cliente>) gestor.obtenerDatosCliente();
		Cliente c = null;
		for (Cliente cliente : lista) {
			if(cliente.getDni().equals("33333333P")) {
				c = cliente;
				break;
			}
		}
		assertTrue(gestor.actualizarClienteYaExistente(c.getDni(),123456789, "Bermeo"));
		assertTrue(gestor.actualizarClienteYaExistente(c.getDni(),c.getTelefono(),c.getDireccion()));
	}

}
