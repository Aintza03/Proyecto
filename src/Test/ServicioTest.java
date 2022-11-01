package Test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import General.Adopcion;
import General.Animal;
import General.Servicio;

public class ServicioTest {
	protected Adopcion servicio;
	protected Date fechaInicio = null;
	protected Animal animal = null;
	@Before
	public void setUp() throws Exception {
		servicio = new Adopcion(fechaInicio, animal);
	}

	@After
	public void tearDown() throws Exception {
		servicio = null;
	}

	@Test
	public void testServicio() {
		assertNotNull(servicio);
		assertEquals(fechaInicio, servicio.getFechaInicio());
		assertEquals(animal,servicio.getAnimal());
	}

	@Test
	public void testGetFechaInicio() {
		assertEquals(fechaInicio, servicio.getFechaInicio());
	}

	@Test
	public void testSetFechaInicio() {
		Date o = null;
		servicio.setFechaInicio(o);
		assertEquals(o,servicio.getFechaInicio());
	}

	@Test
	public void testGetAnimal() {
		assertEquals(animal,servicio.getAnimal());
	}

	@Test
	public void testSetAnimal() {
		Animal an = new Animal(0, null, null, null, null);
		servicio.setAnimal(an);
		assertEquals(an,servicio.getAnimal());
	}

	

}
