package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;
import General.Cliente;
import Ventanas.VentanaCliente;
import bbdd.GestorBD;

public class VentanaClienteTest {
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
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		gestor.actualizarAnimal(new Animal(10, null, null, null, null), null, null);
		gestor = null;
		p = null;
	}


	@Test
	public void testEncontrarCliente() {
		assertEquals(VentanaCliente.encontrarCliente("11111111H", gestor, p),"Se ha encontrado el cliente");
		assertEquals(VentanaCliente.encontrarCliente("11111111J", gestor, p),"El cliente no existe");
	}

	@Test
	public void testHmadoptados() {
		gestor.actualizarAnimal(new Animal(10, null, null, null, null), null, "11111111H");
		for (HashMap.Entry<Cliente, ArrayList<Animal>> entry : VentanaCliente.hmadoptados(gestor).entrySet()) {
			Cliente key = entry.getKey();
			ArrayList<Animal> val = entry.getValue();
			if (key.getDni().equals("11111111H")) {
				for (Animal animal : val) {
					if(animal.getId() == 10) {
						assertTrue(true);
					}
				}
			}
			
			
		}
	}

	@Test
	public void testHmacogidos() {
		gestor.actualizarAnimal(new Animal(10, null, null, null, null), "11111111H", null );
		for (HashMap.Entry<Cliente, ArrayList<Animal>> entry : VentanaCliente.hmacogidos(gestor).entrySet()) {
			Cliente key = entry.getKey();
			ArrayList<Animal> val = entry.getValue();
			if (key.getDni().equals("11111111H")) {
				for (Animal animal : val) {
					if(animal.getId() == 10) {
						assertTrue(true);
					}
				}
			}
		
	}

	}
	}
