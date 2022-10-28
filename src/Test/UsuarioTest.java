package Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import General.Usuario;

public class UsuarioTest {
	protected Usuario usuario;
	protected int contraseña = 5;
	protected String us = "a";
	@Before
	public void setUp() throws Exception {
		usuario = new Usuario(contraseña,us);
	}

	@After
	public void tearDown() throws Exception {
		usuario = null;
	}

	@Test
	public void testHashCode() {
		Usuario us2 = new Usuario(5,"a");
		Assert.assertTrue(us2.hashCode() == usuario.hashCode());
	}

	@Test
	public void testUsuario() {
		assertNotNull(usuario);
		assertEquals(us, usuario.getUsuario());
		assertEquals(contraseña,usuario.getContraseña());
	}

	@Test
	public void testEqualsObject() {
		Usuario us2 = new Usuario(5,"a");
		Assert.assertTrue(us2.equals(usuario) && usuario.equals(us2));
		}

	@Test
	public void testGetContraseña() {
		assertEquals(contraseña,usuario.getContraseña());
	}

	@Test
	public void testSetContraseña() {
		int c = 10;
		usuario.setContraseña(c);
		assertEquals(c,usuario.getContraseña());
	}

	@Test
	public void testGetUsuario() {
		assertEquals(us,usuario.getUsuario());
	}

	@Test
	public void testSetUsuario() {
		String a = "hola";
		usuario.setUsuario(a);
		assertEquals(a,usuario.getUsuario());
	}

	@Test
	public void testToString() {
		String toString = "Usuario: " + us;
		assertEquals(toString,usuario.toString());
	}

}
