package Test;

import static org.junit.Assert.*;

import java.sql.Date;


import org.junit.Test;

public class ServicioTest {
	protected Servicio servicio;
	protected Date fechaInicio = null;
	protected Animal animal = null;
	

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
