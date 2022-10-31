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
		
	public void crearBBDD() {
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
	
	public void crearBBDD1() {
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
	
	public void borrarBBDD() {
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
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatos0(Usuario... Usuarios) {
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
	public void insertarDatos1(Cliente... clientes) {
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
	public List<Usuario> obtenerDatosUs() {
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
	public List<Cliente> obtenerDatosCl() {
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
				cliente = new Cliente(rs.getString("DNI"),rs.getString("DIR"),rs.getInt("TEL"),rs.getString("NOMBRE"),new ArrayList<Animal>(),new ArrayList<Animal>(),rs.getInt("PERMISO"),);
				
				
				
				//Se inserta cada nuevo cliente en la lista de clientes
				clientes.add(cliente);
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
	
	public void actualizarPassword(Usuario usuario, String newPassword) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE CLIENTE SET PASSWORD = '%s' WHERE ID = %d;";
			
			int result = stmt.executeUpdate(String.format(sql, newPassword, usuario.getUsuario()));
			
			System.out.println(String.format("- Se ha actulizado %d clientes", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
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
	
}
