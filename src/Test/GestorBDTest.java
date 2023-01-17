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
import bbdd.GestorBD;
import bbdd.Inits;

public class GestorBDTest {
	protected static GestorBD gestor;
	protected static ArrayList<Cliente> listaC;
	protected static ArrayList<Usuario> listaU;
	protected static ArrayList<Animal> listaA;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
		gestor.insertarDatosUsuario(listaU.toArray(new Usuario[listaU.size()]));
		gestor.insertarDatosCliente(listaC.toArray(new Cliente[listaC.size()]));
		gestor.insertarDatosAnimal(listaA.toArray(new Animal[listaA.size()]));
	}

	@Test
	public void testBorrarBBDD() {
		assertTrue(gestor.borrarBBDD());
		gestor.crearBBDD();
		gestor.insertarDatosUsuario(listaU.toArray(new Usuario[listaU.size()]));
		gestor.insertarDatosCliente(listaC.toArray(new Cliente[listaC.size()]));
		gestor.insertarDatosAnimal(listaA.toArray(new Animal[listaA.size()]));
	}

	@Test
	public void testInsertarDatosUsuario() {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		assertTrue(gestor.insertarDatosUsuario(listaU.toArray(new Usuario[listaU.size()])));
		gestor.insertarDatosCliente(listaC.toArray(new Cliente[listaC.size()]));
		gestor.insertarDatosAnimal(listaA.toArray(new Animal[listaA.size()]));
	}

	@Test
	public void testInsertarDatosCliente() {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		gestor.insertarDatosUsuario(listaU.toArray(new Usuario[listaU.size()]));
		assertTrue(gestor.insertarDatosCliente(listaC.toArray(new Cliente[listaC.size()])));
		gestor.insertarDatosAnimal(listaA.toArray(new Animal[listaA.size()]));
	}

	@Test
	public void testInsertarDatosAnimal() {
		gestor.borrarBBDD();
		gestor.crearBBDD();
		gestor.insertarDatosUsuario(listaU.toArray(new Usuario[listaU.size()]));
		gestor.insertarDatosCliente(listaC.toArray(new Cliente[listaC.size()]));
		assertTrue(gestor.insertarDatosAnimal(listaA.toArray(new Animal[listaA.size()])));
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
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarAnimal() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testActualizarClienteYaExistente() {
		fail("Not yet implemented");
	}

}
