package Test;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Cliente;
import Ventanas.VentanaAdopcion;
import bbdd.GestorBD;

public class VentanaAdopcionTest {
	protected static GestorBD gestor;
	protected static Properties p;
	protected String dni2="00000000A";
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
		gestor.borrarDatosCliente("01234567A");
		gestor = null;
		p = null;
	}


	@Test
	public void testCargarCliente() {
		Cliente c =new Cliente("01234567A", "Gernika", 012, "Haizea");
		gestor.insertarDatosCliente(c);
		assertNotNull(VentanaAdopcion.cargarCliente(c.getDni(), gestor));
		assertNull(VentanaAdopcion.cargarCliente(dni2, gestor));
		
	}

}
