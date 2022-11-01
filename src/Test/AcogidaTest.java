package Test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AcogidaTest {
	
	protected Acogida acogida;
	protected Date fechaInicio= null;
	protected Animal animal= null;
	protected Date fechaEntrega= null;
	
	
	@Before
	public void setUp() throws Exception {
		acogida = new Acogida(fechaInicio, animal, fechaEntrega);
	}

	@After
	public void tearDown() throws Exception {
		acogida = null;
	}

	@Test
	public void testHashCode() {
		Acogida ac = new Acogida(null, null, null);
		Assert.assertTrue(ac.hashCode() == acogida.hashCode());
	}

	@Test
	public void testToString() {
		String toString = "Acogida [fechaEntrega=" + fechaEntrega + "]";
		assertEquals(toString,acogida.toString());
	}

	@Test
	public void testEqualsObject() {
		Acogida ac2 = new Acogida(null,null , null);
		Assert.assertTrue(ac2.equals(acogida) && acogida.equals(ac2));
	}

	@Test
	public void testAcogida() {
			assertNotNull(acogida);
			assertEquals(fechaEntrega, acogida.getFechaEntrega());
			assertEquals(fechaInicio, acogida.getFechaInicio());
			assertEquals(animal,acogida.getAnimal());
		
	}

	@Test
	public void testGetFechaEntrega() {
		assertEquals(fechaEntrega, acogida.getFechaEntrega());
	}

	@Test
	public void testSetFechaEntrega() {
		Date x = null;
		acogida.setFechaEntrega(x);
		assertEquals(x,acogida.getFechaEntrega());
		
	}

	@Test
	public void testServicio() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFechaInicio() {
		assertEquals(fechaInicio, acogida.getFechaInicio());
	}

	@Test
	public void testSetFechaInicio() {
		Date y = null;
		acogida.setFechaInicio(y);
		assertEquals(y,acogida.getFechaInicio());
	}

	@Test
	public void testGetAnimal() {
		assertEquals(animal,acogida.getAnimal());
	}

	@Test
	public void testSetAnimal() {
		Animal an = new Animal(0, null, null, null, null);
		acogida.setAnimal(an);
		assertEquals(an,acogida.getAnimal());
	}

}
