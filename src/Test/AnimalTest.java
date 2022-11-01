package General;
import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {

	protected Animal animal;
	protected int id = 1;
	protected String raza= "p";
	protected String especial = "a";
	protected String tipo = "n";
	protected Date fechaNac = null;
	@Before
	public void setUp() throws Exception {
		animal = new Animal(id, raza,especial, tipo, fechaNac);
	}

	@After
	public void tearDown() throws Exception {
		animal = null;
	}

	@Test
	public void testAnimal() {
		assertNotNull(animal);
		assertEquals(id, animal.getId());
		assertEquals(raza,animal.getRaza());
		assertEquals(especial, animal.getEspecial());
		assertEquals(tipo,animal.getTipo());
		assertEquals(fechaNac, animal.getFechaNac());	
	}

	@Test
	public void testGetRaza() {
		assertEquals(raza,animal.getRaza());
	}

	@Test
	public void testSetRaza() {
		String c = "i";
		animal.setRaza(c);
		assertEquals(c,animal.getRaza());
	}

	@Test
	public void testGetEspecial() {
		assertEquals(especial, animal.getEspecial());
	}

	@Test
	public void testSetEspecial() {
		String b = "o";
		animal.setEspecial(b);
		assertEquals(b,animal.getEspecial());
	}

	@Test
	public void testGetTipo() {
		assertEquals(tipo,animal.getTipo());
	}

	@Test
	public void testSetTipo() {
		String v = "j";
		animal.setTipo(v);
		assertEquals(v,animal.getTipo());
		
	}

	@Test
	public void testGetFechaNac() {
		assertEquals(fechaNac, animal.getFechaNac());
	}

	@Test
	public void testSetFechaNac() {
		Date i = null;
		animal.setFechaNac(i);
		assertEquals(i,animal.getFechaNac());
	}

	@Test
	public void testToString() {
		String toString = tipo + " " + raza +  ", nacido en " + fechaNac + "??"+ especial;
		assertEquals(toString,animal.toString());
	}

	@Test
	public void testHashCode() {
		Animal an1 = new Animal(1, "p", "u", "i", null);
		Assert.assertTrue(an1.hashCode() == animal.hashCode());
	}

}
