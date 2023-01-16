package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Ventanas.VentanaAdopcion;
import bbdd.GestorBD;

public class VentanaAdopcionTest {
	protected static GestorBD gestor;
	protected static Properties p;
	protected String dni="11111111H";
	protected String dni2="11111115J";
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
		gestor = null;
		p = null;
	}


	@Test
	public void testCargarCliente() {
		
		assertNotNull(VentanaAdopcion.cargarCliente(dni, gestor));
		assertNull(VentanaAdopcion.cargarCliente(dni2, gestor));
		
	}

}
