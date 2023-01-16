package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;
import General.Cliente;
import Ventanas.VentanaAcoger;
import bbdd.GestorBD;

public class VentanaAcogerTest {
	protected static GestorBD gestor;
	protected static Properties p;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gestor = new GestorBD();
		try (FileReader reader = new FileReader("config/castellano.properties")){
			p = new Properties();
			p.load(reader);
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	HashMap<String, ArrayList<Animal>> mapa = new HashMap<String, ArrayList<Animal>>();
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestor = null;
		p = null;
	}

	@Test
	public void testRecorrer() {
		
		ArrayList<Cliente> listaCl = new ArrayList<Cliente>();
		ArrayList<Animal> lista = new ArrayList<Animal>();
		ArrayList<Animal> listab = new ArrayList<Animal>();
		
		Animal a = new Animal(1, "Siames", "nada", "gato","2020/01/03");
		Animal b = new Animal(2, "Negro", "nada", "gato","2020/06/03");
		Animal c = new Animal(3, "Dalmata", "nada", "perro","2019/10/20");
		
		lista.add(a);
		listab.add(b);
		
		Cliente cl1 = new Cliente("2098", "dir", 686, "Alfredo", lista, new ArrayList<Animal>(), true);
		Cliente cl2 = new Cliente("1232", "dir34", 686, "Rodolfo", new ArrayList<Animal>(), listab, true);
		
		listaCl.add(cl1);
		listaCl.add(cl2);
		
		assertFalse(VentanaAcoger.recorrer(listaCl).get("Acogidos").contains(c));
		assertFalse(VentanaAcoger.recorrer(listaCl).get("Adoptados").contains(c));
		assertTrue(VentanaAcoger.recorrer(listaCl).get("Acogidos").get(0).equals(b));
		assertTrue(VentanaAcoger.recorrer(listaCl).get("Adoptados").get(0).equals(a));

	}

	@Test
	public void testRecorrerdos() {
		
		ArrayList<Cliente> listaCl = new ArrayList<Cliente>();
		ArrayList<Animal> lista = new ArrayList<Animal>();
		ArrayList<Animal> listab = new ArrayList<Animal>();
		
		Animal a = new Animal(1, "Siames", "nada", "gato","2020/01/03");
		Animal b = new Animal(2, "Negro", "nada", "gato","2020/06/03");
		Animal c = new Animal(3, "Dalmata", "nada", "perro","2019/10/20");
		
		lista.add(a);
		listab.add(b);
		
		Cliente cl1 = new Cliente("2098", "dir", 686, "Alfredo", lista, new ArrayList<Animal>(), true);
		Cliente cl2 = new Cliente("1232", "dir34", 686, "Rodolfo", new ArrayList<Animal>(), listab, true);
		
		listaCl.add(cl1);
		listaCl.add(cl2);
		
		ArrayList<Animal> lista2 = new ArrayList<Animal>();
		
		lista2.add(a);
		lista2.add(b);
		lista2.add(c);
		
		assertEquals(VentanaAcoger.recorrerdos(VentanaAcoger.recorrer(listaCl), lista2).size(), 1);
		assertEquals(VentanaAcoger.recorrerdos(VentanaAcoger.recorrer(listaCl), lista2).get(0), c);
		
	}

}
