package bbdd;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import General.Animal;
import General.Cliente;
import General.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GestorBD {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
	public GestorBD() {		
		try {
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
		
	public void crearBBDDUsuario() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		//Al abrir la conexiÃ³n, si no existÃ­a el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "CREATE TABLE IF NOT EXISTS USUARIOS (\n"
	                   + " USUARIO TEXT NOT NULL PRIMARY KEY,\n"
	                   + " CONTRASEÑA INTEGER NOT NULL\n"
	                   + ");";
	   
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Usuarios");
	        }
	       
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void crearBBDDCliente() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		//Al abrir la conexiÃ³n, si no existÃ­a el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "CREATE TABLE IF NOT EXISTS CLIENTE(\n"
	        		+ "DNI TEXT NOT NULL PRIMARY KEY,\n"
	        		+ "PERMISO INTEGER DEFAULT 1,\n"
	        		+ "TEL INTEGER,\n"
	        		+ "DIR TEXT,\n"
	        		+ "NOMBRE TEXT,\n" + ");";
	   
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Cliente");
	        }
	       
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void crearBBDDAnimal() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		//Al abrir la conexiÃ³n, si no existÃ­a el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "CREATE TABLE IF NOT EXISTS ANIMAL(\n"
	        		+"ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	        		+"TIPO TEXT,\n"
	        		+"FECHA_NAC TEXT,\n"
	        		+"ESPECIAL TEXT,\n"
	        		+"RAZA TEXT,\n"
	        		+"DNIC_AC TEXT DEFAULT noAcogido,\n"
	        		+"DNIC_AD TEXT DEFAULT noAdoptado,\n"
	        		+ "FOREIGN KEY (DNIC_AC) REFERENCES CLIENTE(DNI) ON DELETE CASCADE,\n"
	        		+ "FOREIGN KEY (DNIC_AD) REFERENCES CLIENTE(DNI) ON DELETE CASCADE\n" + ");";

	   
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Animales");
	        }
	       
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDDUsuario() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS USUARIO";
			
	        //Se ejecuta la sentencia de creaciÃ³n de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla usuario");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			     Statement stmt = con.createStatement()) {
				
		        String sql = "DROP TABLE IF EXISTS CLIENTE";
				
		        //Se ejecuta la sentencia de creaciÃ³n de la tabla Estudiantes
		        if (!stmt.execute(sql)) {
		        	System.out.println("- Se ha borrado la tabla cliente");
		        }
			} catch (Exception ex) {
				System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();			
			}
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			     Statement stmt = con.createStatement()) {
				
		        String sql = "DROP TABLE IF EXISTS ANIMAL";
				
		        //Se ejecuta la sentencia de creaciÃ³n de la tabla Estudiantes
		        if (!stmt.execute(sql)) {
		        	System.out.println("- Se ha borrado la tabla animal");
		        }
			} catch (Exception ex) {
				System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();			
			}
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosUsuario(Usuario... Usuarios) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO USUARIO (USUARIO, CONTRASEÑA) VALUES ('%s', '%s');";
			
			System.out.println("- Insertando usuarios...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Usuario c : Usuarios) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getUsuario(), c.getContraseña() ))) {					
					System.out.println(String.format(" - Usuario insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el usuario: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	public void insertarDatosCliente(Cliente... clientes) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CLIENTE (DNI,PERMISO,TEL,DIR,NOMBRE) VALUES ('%s', '%d','%d','%s','%s');";
			
			System.out.println("- Insertando Clientes...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Cliente c : clientes) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getDni(),c.isPermiso(),c.getTelefono(), c.getDireccion(),c.getNombre()))) {					
					System.out.println(String.format(" - Cliente insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el cliente: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	public void insertarDatosAnimal(Animal... animales) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO ANIMAL (TIPO,FECHA_NAC,ESPECIAL,RAZA,) VALUES ( '%s','%s','%s','%s');";
			
			System.out.println("- Insertando Animales...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Animal c : animales) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getTipo(),c.getFechaNac(),c.getEspecial(), c.getRaza()))) {					
					System.out.println(String.format(" - Animal insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el animal: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	public List<Usuario> obtenerDatosUsuario() {
		List<Usuario> usuarios = new ArrayList<>();
		
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM USUARIO WHERE ID >= 0";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Usuario usuario;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				usuario = new Usuario(0, sql);
				
				usuario.setUsuario(rs.getString("USUARIO"));
				usuario.setContraseña(rs.getInt("CONTRASEÑA"));
				
				//Se inserta cada nuevo cliente en la lista de clientes
				usuarios.add(usuario);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d usuario...", usuarios.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return usuarios;
	}
	public List<Cliente> obtenerDatosCliente() {
		List<Cliente> clientes = new ArrayList<>();
		
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CLIENTE WHERE";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Cliente cliente;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				cliente = new Cliente(rs.getString("DNI"),rs.getString("DIR"),rs.getInt("TEL"),rs.getString("NOMBRE"),new ArrayList<Animal>(),new ArrayList<Animal>(),true);
				if (rs.getInt("PERMISO") == 1) {
					cliente.setPermiso(true);
				}else {
				cliente.setPermiso(false);
				}
				
				//Se inserta cada nuevo cliente en la lista de clientes
				clientes.add(cliente);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d cliente...", clientes.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return clientes;
	}
	
	public List<ArrayList> obtenerDatosAnimal(ArrayList<Cliente> clientes) {
		//El valor 0 de lista sera animales y el valor 1 clientes 
		List<ArrayList> lista = new ArrayList<ArrayList>() ;
		ArrayList<Animal> animales = new ArrayList<>();
		
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM ANIMAL WHERE";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Animal animal;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				animal = new Animal(rs.getInt("ID"),rs.getString("RAZA"),rs.getString("ESPECIAL"),rs.getString("TIPO"),rs.getDate("FECHA_NAC"));
				if (!rs.getString("DNI_AC").equals("noAcogido")) {
					for (Cliente cliente : clientes) {
						if(cliente.getDni().equals(rs.getString("DNI_AC"))) {
							cliente.getAnimalesAcogidos().add(animal);
							break;
						}
					}
				}
					
				if (!rs.getString("DNI_AD").equals("noAdoptado")) {
					for (Cliente cliente : clientes) {
						if(cliente.getDni().equals(rs.getString("DNI_AD"))) {
							cliente.getAnimalesAdoptados().add(animal);
							break;
						}
					}
				}
				
				//Se inserta cada nuevo cliente en la lista de clientes
				animales.add(animal);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d cliente...", animales.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		lista.add(animales);
		lista.add(clientes);
		return lista;
	}
	
	
	
	public void borrarDatos() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM CLIENTE;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d clientes", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	public void update(List<Usuario> usuarios, List<Animal> animales , List<Cliente> clientes) {
		List<Usuario> usuarioBBDD;
		List<ArrayList> animalesBBDD;
		ArrayList<Cliente> clienteBBDD;
		clienteBBDD = (ArrayList<Cliente>) obtenerDatosCliente();
		usuarioBBDD = obtenerDatosUsuario();
		animalesBBDD =obtenerDatosAnimal(clienteBBDD);
		for (Usuario usuario : usuarios) {
			if (!(usuarioBBDD.contains(usuario))) {
				insertarDatosUsuario(usuario);
			}
		}
		
		for (Cliente cliente : clientes) {
			if (!(clienteBBDD.contains(cliente))) {
				insertarDatosCliente(cliente);
			}
		}
	
			for (Animal animal : animales) {
			if (!(animalesBBDD.contains(animal))) {
				insertarDatosAnimal(animal);
			}
		}}
		
		
		
		
		
}

