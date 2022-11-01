package Test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdopcionTest {
	
	protected Adopcion adopcion;
	protected String contrato;
	protected Date fechaInicio;
	protected Animal animal;
	@Before
	public void setUp() throws Exception {
		adopcion = new Adopcion(fechaInicio, animal);
	}

	@After
	public void tearDown() throws Exception {
		adopcion = null;
	}

	@Test
	public void testHashCode() {
		Animal an = new Animal(0, null, null, null, null);
		Adopcion ad = new Adopcion(null, an);
		Assert.assertTrue(ad.hashCode() == adopcion.hashCode());
	}

	@Test
	public void testGetFechaInicio() {
		assertEquals(fechaInicio, adopcion.getFechaInicio());
	}

	@Test
	public void testSetFechaInicio() {
		Date y = null;
		adopcion.setFechaInicio(y);
		assertEquals(y,adopcion.getFechaInicio());
	}

	@Test
	public void testGetAnimal() {
		assertEquals(animal,adopcion.getAnimal());
	}

	@Test
	public void testSetAnimal() {
		Animal an = new Animal(0, null, null, null, null);
		adopcion.setAnimal(an);
		assertEquals(an,adopcion.getAnimal());
	}

	@Test
	public void testToString() {
		String toString = "Adopcion [contrato=" + contrato + "]";
		assertEquals(toString,adopcion.toString());
	}

	@Test
	public void testEqualsObject() {
		Adopcion ad2 = new Adopcion(null,null);
		Assert.assertTrue(ad2.equals(adopcion) && adopcion.equals(ad2));
	}

	@Test
	public void testAdopcion() {
			assertNotNull(adopcion);
			assertEquals(fechaInicio, adopcion.getFechaInicio());
			assertEquals(animal,adopcion.getAnimal());
			assertEquals(contrato,adopcion.getContrato());
			}

	@Test
	public void testGetContrato() {
		assertEquals(contrato,adopcion.getContrato());
	}

	@Test
	public void testSetContrato() {
		String k = "i";
		adopcion.setContrato(k);
		assertEquals(k,adopcion.getContrato());
	}

