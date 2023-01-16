package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bbdd.GestorBD;

public class VentanaIntroducirClienteTest {
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
		gestor = null;
		p = null;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDNIAPTO() {
		fail("Not yet implemented");
	}

}
