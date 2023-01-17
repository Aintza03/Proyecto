package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;
import General.Cliente;
import General.Usuario;
import bbdd.Inits;

public class InitsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPrintClientes() {
		assertEquals(Inits.printClientes(Inits.initClientes()), 4);
	}

	@Test
	public void testPrintUsuarios() {
		assertEquals(Inits.printUsuarios(Inits.initUsuarios()), 4);
	}

	@Test
	public void testInitUsuarios() {
		ArrayList<Usuario> lista = (ArrayList<Usuario>) Inits.initUsuarios();
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
	public void testInitClientes() {
		ArrayList<Cliente> lista = (ArrayList<Cliente>) Inits.initClientes();
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
	public void testInitAnimales() {
		ArrayList<Animal> lista = (ArrayList<Animal>) Inits.initAnimales();
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
	public void testPrintAnimales() {
		ArrayList<ArrayList> lista = new ArrayList<ArrayList>();
		lista.add((ArrayList) Inits.initAnimales());
		assertEquals(Inits.printAnimales(lista), 59);;
	}

}
