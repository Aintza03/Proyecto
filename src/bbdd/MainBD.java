package bbdd;

import java.util.ArrayList;
import java.util.List;

import General.Usuario;


public class MainBD {

	public static void main(String[] args) {
		GestorBD gestorBD = new GestorBD();		
		
		//CREATE DATABASE: Se crea la BBDD
		gestorBD.crearBBDD();
		
		//INSERT: Insertar datos en la BBDD		
		List<Usuario> usuarios = initUsuarios();
		gestorBD.insertarDatos(usuarios.toArray(new Usuario[usuarios.size()]));
		
		//SELECT: Se obtienen datos de la BBDD
		usuarios = gestorBD.obtenerDatos();
		printUsuarios(usuarios);
		
		//UPDATE: Se actualiza la password de un cliente
		//String newPassword = "hWaPvd6R28%1";
		//gestorBD.actualizarPassword(clientes.get(0), newPassword);

		//SELECT: Se obtienen datos de la BBDD
		usuarios = gestorBD.obtenerDatos();
		printUsuarios(usuarios);

		//DELETE: Se borran datos de la BBDD
		gestorBD.borrarDatos();
		
		//DROP DATABASE: Se borra la BBDD
		gestorBD.borrarBBDD();
	}
	
	private static void printUsuarios(List<Usuario> usuarios) {
		if (!usuarios.isEmpty()) {		
			for(Usuario usuario : usuarios) {
				System.out.println(String.format(" - %s", usuario.toString()));
			}
		}		
	}
	
	public static List<Usuario> initUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		
		Usuario usuario = new Usuario();
		usuario.setUsuario("Bruce Banner");
		usuario.setContraseña(1234);
		usuarios.add(usuario);
		
		return usuarios;
	}

}

