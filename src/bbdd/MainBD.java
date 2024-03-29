package bbdd;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import General.Animal;
import General.Cliente;
import General.Usuario;
import Ventanas.VentanaPrincipal;


public class MainBD {

	public static void main(String[] args) {
		VentanaPrincipal.iniciarlogger();
		GestorBD gestorBD = new GestorBD();
		 gestorBD.borrarBBDD();
		//CREATE DATABASE: Se crea la BBDD
		gestorBD.crearBBDD();
		//INSERT: Insertar datos en la BBDD		
		List<Usuario> usuarios = Inits.initUsuarios();
		gestorBD.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		List<Cliente> clientes = Inits.initClientes();
		gestorBD.insertarDatosCliente(clientes.toArray(new Cliente[clientes.size()]));
		List<Animal> animales = Inits.initAnimales();
		gestorBD.insertarDatosAnimal(animales.toArray(new Animal[animales.size()]));

		//SELECT: Se obtienen datos de la BBDD
		usuarios = gestorBD.obtenerDatosUsuario();
		Inits.printUsuarios(usuarios);
		clientes = (ArrayList<Cliente>) gestorBD.obtenerDatosCliente();
		Inits.printClientes(clientes);
		for (Cliente c : clientes) {
			System.out.println(c.isPermiso());
		}
		ArrayList<Cliente> listac = (ArrayList<Cliente>) clientes;
		List<ArrayList> resultado = gestorBD.obtenerDatosAnimal((listac));
		Inits.printAnimales(resultado);
		//UPDATE: Se actualizan los datos
		
		Animal animal = new Animal(1, "Gato", null , null , null);
		gestorBD.actualizarAnimal(animal, "11111111H", null);
		gestorBD.actualizarCliente("22222222J", 1);
		usuarios = gestorBD.obtenerDatosUsuario();
		Inits.printUsuarios(usuarios);
		clientes = (ArrayList<Cliente>) gestorBD.obtenerDatosCliente();
		Inits.printClientes(clientes);
		for (Cliente c : clientes) {
			System.out.println(c.isPermiso());
		}
		ArrayList<Cliente> listaA = (ArrayList<Cliente>) clientes;
		List<ArrayList> resultadoB = gestorBD.obtenerDatosAnimal((listac));
		Inits.printAnimales(resultado);
		//DELETE: Se borra usuario concreto de la BD
	    gestorBD.borrarDatosUsuario("Aintzane");
		System.out.println(gestorBD.obtenerDatosUsuario());
		gestorBD.borrarDatosCliente("11111111H");
		System.out.println(gestorBD.obtenerDatosCliente());
		gestorBD.borrarDatosAnimal(1);
		System.out.println(gestorBD.obtenerDatosAnimal(listac)); 
		gestorBD.actualizarClienteYaExistente("22222222J", 99999999, "Jamaica");
		System.out.println(gestorBD.obtenerDatosCliente());
		//DROP DATABASE: Se borra la BBDD (No vamos a usarlo en el proyecto)
		 gestorBD.borrarBBDD();
	}
}
	

	


