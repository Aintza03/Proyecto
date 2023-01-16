package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Ventanas.VentanaPrincipal;
import bbdd.GestorBD;

public class VentanaPrincipalTest {
	protected String u;
	protected int contra;
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
	@Test
	public void testVerificarUsuario() {
		gestor.obtenerDatosUsuario();
		u = "Aintzane";
		contra = 1234;
		assertTrue(VentanaPrincipal.verificarUsuario(u, contra, gestor, p).get(0).equals("Usuario encontrado"));
		contra = 2222;
		assertFalse(VentanaPrincipal.verificarUsuario(u, contra, gestor, p).get(0).equals("Usuario encontrado"));
		
	}

}
