package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;
import General.Cliente;
import General.Contrato;
import Ventanas.VentanaPrincipal;

public class ContratoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		VentanaPrincipal.iniciarlogger();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testObtenerPlantilla() {
		String path = "Contrato de adopcion\r\n\r\nEl cliente llamado %s con el dni %s se compromete a adoptar el %s con el id %s y la raza %s.\r\nAl firmar este contrato el animal queda a cargo del cliente.\r\n\r\nFirma\r\n\r\n\r\n";
		assertEquals(Contrato.obtenerPlantilla("Plantillas/plantillaespanol.txt"), path);
		String path2 = "Adopzio-kontratua\r\n\r\n%s izeneko bezeroak, %s NAN-arekin, konpromisoa hartzen du %s bat,  %s IDarekin eta %s arrazaduna adoptatzeko.\r\nSinatzean kontratu hau bezeroaren menpe geratzen da.\r\n\r\nSinadura\r\n\r\n\r\n";
		assertEquals(Contrato.obtenerPlantilla("Plantillas/plantillaeuskera.txt"), path2);
	}

	@Test
	public void testGuardarContrato() {
		Cliente cl = new Cliente("20986738Q", "Munguia", 897678765 ,"Aitor");
		Animal a = new Animal(98, "Siames", "nada", "gato", "20/09/2017");
		assertTrue(Contrato.guardarContrato(String.format(Contrato.obtenerPlantilla("Plantillas/plantillaespanol.txt"), cl.getNombre(), cl.getDni(), a.getTipo(), a.getId(), a.getRaza()), 98));
		assertTrue(Contrato.guardarContrato(String.format(Contrato.obtenerPlantilla("Plantillas/plantillaeuskera.txt"), cl.getNombre(), cl.getDni(), a.getTipo(), "97" , a.getRaza()), 97));
		Contrato.borrarContrato(98);
		Contrato.borrarContrato(97);
	}

	@Test
	public void testBorrarContrato() {
		Cliente cl = new Cliente("20986738Q", "Munguia", 897678765 ,"Aitor");
		Animal a = new Animal(98, "Siames", "nada", "gato", "20/09/2017");
		Contrato.guardarContrato(String.format(Contrato.obtenerPlantilla("Plantillas/plantillaespanol.txt"), cl.getNombre(), cl.getDni(), a.getTipo(), a.getId(), a.getRaza()), 98);
		Contrato.guardarContrato(String.format(Contrato.obtenerPlantilla("Plantillas/plantillaeuskera.txt"), cl.getNombre(), cl.getDni(), a.getTipo(), "97" , a.getRaza()), 97);
		assertTrue(Contrato.borrarContrato(98));
		assertTrue(Contrato.borrarContrato(97));
	}

}
