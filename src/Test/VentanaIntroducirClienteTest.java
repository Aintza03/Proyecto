package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Ventanas.VentanaIntroducirCliente;
import Ventanas.VentanaPrincipal;
import bbdd.GestorBD;

public class VentanaIntroducirClienteTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		VentanaPrincipal.iniciarlogger();
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void testDNIAPTO() {
		
		String dni= "11111111";
		String dni2= "2";
		String dni3= "99999A999";
		
		assertTrue(VentanaIntroducirCliente.DNIAPTO(dni));
		assertFalse(VentanaIntroducirCliente.DNIAPTO(dni2));
		assertFalse(VentanaIntroducirCliente.DNIAPTO(dni3));
		
	}

}
