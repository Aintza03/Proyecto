package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;

public class AnimalTest {
	protected Animal animal;
	protected int id = 1;
	protected String raza= "p";
	protected String especial = "a";
	protected String tipo = "n";
	protected String fechaNac = null;
	
	@Before
	public void setUp() throws Exception {
		animal = new Animal(id, raza,especial, tipo, fechaNac);
	}

	@After
	public void tearDown() throws Exception {
		animal = null;
	}

	@Test
	public void testHashCode() {
		Animal a = new Animal(1,"p","a","n", null);
		Assert.assertTrue(a.hashCode() == animal.hashCode());
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
	public void testGetId() {
		assertEquals(id,animal.getId());
	}

	@Test
	public void testSetId() {
		int x = 2;
		animal.setId(x);
		assertEquals(x,animal.getId());
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
		String i = null;
		animal.setFechaNac(i);
		assertEquals(i,animal.getFechaNac());
	}

	@Test
	public void testToString() {
		String toString = id + " " + tipo + " " + raza +  ", nacido en " + fechaNac + "??"+ especial;
		assertEquals(toString,animal.toString());
	}

	@Test
	public void testEqualsObject() {
		Animal a = new Animal(1,"p","a","n", null);
		Assert.assertTrue(a.equals(animal) && animal.equals(a));
	}

	@Test
	public void testCompareTo() {
		ArrayList<Animal> a = new ArrayList<Animal>();
		a.add(animal);
		a.add(new Animal(2, "a", especial, tipo, "2010/12/20"));
		Collections.sort(a);
		assertTrue(2 == a.get(0).getId());
		assertTrue(1 == a.get(1).getId());
	}

}
