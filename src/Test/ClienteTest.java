package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import General.Animal;
import General.Cliente;

public class ClienteTest {
	protected Cliente cliente;
	protected Cliente cliente2;
	protected String dni="dth";
	protected String direccion="dtj";
	protected int telefono=603441074;
	protected String nombre = "eth" ;
	protected ArrayList<Animal> animalesAdoptados=new ArrayList<Animal>();
	protected ArrayList<Animal> animalesAcogidos=new ArrayList<Animal>();
	protected boolean permiso=true;
	@Before
	public void setUp() throws Exception {
		cliente = new Cliente(dni,direccion,telefono, nombre);
		cliente2 = new Cliente(dni,direccion,telefono, nombre,animalesAdoptados, animalesAcogidos, permiso);
	}

	@After
	public void tearDown() throws Exception {
		cliente=null;
		cliente2=null;
	}

	@Test
	public void testHashCode() {
		Cliente cl2 = new Cliente("dth","dtj",603441074,"eth");
		Assert.assertTrue(cl2.hashCode() == cliente.hashCode());
	}

	@Test
	public void testIsPermiso() {
		assertEquals(true, cliente.isPermiso());
	}

	@Test
	public void testSetPermiso() {
		cliente.setPermiso(true);
		assertTrue(cliente.isPermiso());
	}

	@Test
	public void testGetDni() {
		assertEquals("dth", cliente.getDni());
	}

	@Test
	public void testSetDni() {
		cliente.setDni("gfgfjk");
		assertEquals("gfgfjk",cliente.getDni());
	}

	@Test
	public void testGetDireccion() {
		assertEquals("dtj", cliente.getDireccion());
	}

	@Test
	public void testSetDireccion() {
		cliente.setDireccion("gfgfjk");
		assertEquals("gfgfjk",cliente.getDireccion());
	}

	@Test
	public void testGetTelefono() {
		assertEquals(603441074, cliente.getTelefono());
	}

	@Test
	public void testSetTelefono() {
		cliente.setTelefono(346);
		assertEquals(346,cliente.getTelefono());
	}

	@Test
	public void testGetNombre() {
		assertEquals("eth", cliente.getNombre());
	}

	@Test
	public void testSetNombre() {
		cliente.setNombre("gfgfjk");
		assertEquals("gfgfjk",cliente.getNombre());
	}

	@Test
	public void testGetAnimalesAdoptados() {
		assertEquals(new ArrayList<Animal>() ,cliente.getAnimalesAdoptados());
	}

	@Test
	public void testSetAnimalesAdoptados() {
		Animal a = new Animal(telefono, direccion, direccion, direccion, null);
		ArrayList<Animal> Lista = new ArrayList<Animal>();
		Lista.add(a);
		cliente.setAnimalesAdoptados(Lista);
		assertEquals(Lista ,cliente.getAnimalesAdoptados());
	}

	@Test
	public void testGetAnimalesAcogidos() {
		assertEquals(new ArrayList<Animal>(),cliente.getAnimalesAcogidos());
	}

	@Test
	public void testSetAnimalesAcogidos() {
		Animal a = new Animal(telefono, direccion, direccion, direccion, null);
		ArrayList<Animal> Lista = new ArrayList<Animal>();
		Lista.add(a);
		cliente.setAnimalesAcogidos(Lista);
		assertEquals(Lista,cliente.getAnimalesAcogidos());
	}

	@Test
	public void testToString() {
		String s =cliente.toString();
		assertEquals(s,cliente.toString());
	}

	@Test
	public void testClienteStringStringIntStringArrayListOfAnimalArrayListOfAnimalBoolean() {
		//cliente2
		assertNotNull(cliente2);
		assertEquals(dni,cliente2.getDni());
		assertEquals(direccion,cliente2.getDireccion());
		assertEquals(telefono,cliente2.getTelefono());
		assertEquals(nombre,cliente2.getNombre());
		assertEquals(animalesAdoptados,cliente2.getAnimalesAdoptados());
		assertEquals(animalesAcogidos,cliente2.getAnimalesAcogidos());
		assertEquals(permiso,cliente2.isPermiso());
	}

	@Test
	public void testClienteStringStringIntString() {
		assertNotNull(cliente);
		assertEquals(dni,cliente.getDni());
		assertEquals(direccion,cliente.getDireccion());
		assertEquals(telefono,cliente.getTelefono());
		assertEquals(nombre,cliente.getNombre());
		assertEquals(new ArrayList<Animal>(),cliente.getAnimalesAdoptados());
		assertEquals(new ArrayList<Animal>(),cliente.getAnimalesAcogidos());
		assertEquals(true,cliente.isPermiso());
	}

	@Test
	public void testEqualsObject() {
		Cliente c = new Cliente("dth","dtj",603441074,"eth");
		Assert.assertTrue(c.equals(cliente) && cliente.equals(c));
	}

}
