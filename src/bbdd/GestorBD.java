package bbdd;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import General.Animal;
import General.Cliente;
import General.Usuario;
import Ventanas.VentanaPrincipal;

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
			VentanaPrincipal.logger.log(Level.INFO, "Creación del gestor de la BBDD");
		} catch (ClassNotFoundException ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error al cargar el driver de la BBDD: %s",ex.getMessage()));
		}
	}
		
	public boolean crearBBDD() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		//Al abrir la conexiÃ³n, si no existÃ­a el fichero, se crea la base de datos
		boolean funciona = true;
	String sql = "CREATE TABLE IF NOT EXISTS USUARIO (\n"
                + " USUARIO TEXT PRIMARY KEY,\n"
                + " CONTRASEÑA INTEGER UNIQUE,\n"
                + "ADMIN TEXT\n"
                + ");";
     String sql1 = "CREATE TABLE IF NOT EXISTS CLIENTE(\n"
     		+ "DNI TEXT NOT NULL PRIMARY KEY,\n"
     		+ "PERMISO INTEGER DEFAULT 1,\n"
     		+ "TEL INTEGER,\n"
     		+ "DIR TEXT,\n"
     		+ "NOMBRE TEXT\n" + ");";
     String sql2 = "CREATE TABLE IF NOT EXISTS ANIMAL(\n"
     		+"ID INTEGER PRIMARY KEY,\n"
     		+"TIPO TEXT,\n"
     		+"FECHA_NAC TEXT,\n"
     		+"ESPECIAL TEXT,\n"
     		+"RAZA TEXT,\n"
     		+"DNIC_AC TEXT DEFAULT noAcogido,\n"
     		+"DNIC_AD TEXT DEFAULT noAdoptado,\n"
     		+ "FOREIGN KEY (DNIC_AC) REFERENCES CLIENTE(DNI) ON DELETE CASCADE,\n"
     		+ "FOREIGN KEY (DNIC_AD) REFERENCES CLIENTE(DNI) ON DELETE CASCADE\n" + ");";

		try {
			Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     PreparedStatement pstmt = con.prepareStatement(sql);
		     PreparedStatement pstmt1 = con.prepareStatement(sql1);
		     PreparedStatement pstmt2 = con.prepareStatement(sql2);
	        
	        if (!pstmt.execute() && !pstmt1.execute() && !pstmt2.execute()) {
	        	VentanaPrincipal.logger.log(Level.INFO,"- Se han creado las tablas");
	        	funciona = true;
	        }else {
	        	VentanaPrincipal.logger.log(Level.WARNING,"No se han creado las tablas");
	        	funciona = false;
	        }
	       con.close();
	       return funciona;
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error al crear la BBDD: %s",ex.getMessage()));
			return false;
		}
	}
	
	
	public boolean borrarBBDD() {
		//Se abre la conexiÃ³n y se obtiene el Statement
		boolean funciona = true;
		String sql = "DROP TABLE IF EXISTS USUARIO";
		String sql1 = "DROP TABLE IF EXISTS CLIENTE";
		String sql2 = "DROP TABLE IF EXISTS ANIMAL";
		
		try {
		     Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     PreparedStatement pstmt = con.prepareStatement(sql);
		     PreparedStatement pstmt1 = con.prepareStatement(sql1);
		     PreparedStatement pstmt2 = con.prepareStatement(sql2);
		     
	        //Se ejecuta la sentencia de creaciÃ³n de la tabla Estudiantes
	        if (!pstmt.execute() && !pstmt1.execute() && !pstmt2.execute()) {
	        	VentanaPrincipal.logger.log(Level.INFO,"- Se han borrado las tablas");
	        	funciona = true;
	        } else {
	        	VentanaPrincipal.logger.log(Level.WARNING,"No se han borrado las tablas");
	        	funciona = false;
	        }
	        con.close();
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.WARNING , String.format("*Error al borrar la BBDD: %s",ex.getMessage()));
			funciona = false;
		}
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			VentanaPrincipal.logger.log(Level.INFO,"- Se ha borrado el fichero de la BBDD");
			funciona = true;
		} catch (Exception ex) {
			funciona = false;
			VentanaPrincipal.logger.log(Level.WARNING , String.format("*Error al borrar el archivo de la BBDD: %s",ex.getMessage()));					
		}
		return funciona;
	}
	
	public boolean insertarDatosUsuario(Usuario... Usuarios) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO USUARIO (USUARIO, CONTRASEÑA,ADMIN) VALUES ('%s', '%s','%s');";
			
			VentanaPrincipal.logger.log(Level.FINE,"- Insertando usuarios...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Usuario c : Usuarios) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getUsuario(), c.getContraseña(),c.isAdmin() ))) {					
					VentanaPrincipal.logger.log(Level.INFO,String.format(" - Usuario insertado: %s", c.toString()));
				} else {
					VentanaPrincipal.logger.log(Level.WARNING,String.format(" - No se ha insertado el usuario: %s", c.toString()));
				}
			}	
			con.close();
			return true;
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error insertando datos en la BBDD: %s",ex.getMessage()));
			return false;
		}				
	}
	public boolean insertarDatosCliente(Cliente... clientes) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CLIENTE (DNI,PERMISO,TEL,DIR,NOMBRE) VALUES ('%s', '%d','%d','%s','%s');";
			
			VentanaPrincipal.logger.log(Level.FINE,"- Insertando Clientes...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Cliente c : clientes) {
				if(c.isPermiso() == true) {
					if (1 == stmt.executeUpdate(String.format(sql, c.getDni(),1,c.getTelefono(), c.getDireccion(),c.getNombre()))) {					
						VentanaPrincipal.logger.log(Level.INFO,String.format(" - Cliente insertado: %s", c.toString()));
					} else {
						VentanaPrincipal.logger.log(Level.WARNING,String.format(" - No se ha insertado el cliente: %s", c.toString()));
					}
				} else {
					if (1 == stmt.executeUpdate(String.format(sql, c.getDni(),0,c.getTelefono(), c.getDireccion(),c.getNombre()))) {					
						VentanaPrincipal.logger.log(Level.INFO,String.format(" - Cliente insertado: %s", c.toString()));
					} else {
						VentanaPrincipal.logger.log(Level.WARNING,String.format(" - No se ha insertado el cliente: %s", c.toString()));
					}
				}		
				}
			con.close();
			return true;
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error insertando datos en la BBDD: %s",ex.getMessage()));				
			return false;
		}				
	}
	public boolean insertarDatosAnimal(Animal... animales) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO ANIMAL (ID,TIPO,FECHA_NAC,ESPECIAL,RAZA) VALUES ('%d','%s','%s','%s','%s');";
			
			VentanaPrincipal.logger.log(Level.FINE,"- Insertando Animales...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Animal c : animales) {
				if (1 == stmt.executeUpdate(String.format(sql ,c.getId(),c.getTipo(),c.getFechaNac(),c.getEspecial(), c.getRaza()))) {					
					VentanaPrincipal.logger.log(Level.INFO,String.format(" - Animal insertado: %s", c.toString()));
				} else {
					VentanaPrincipal.logger.log(Level.WARNING,String.format(" - No se ha insertado el animal: %s", c.toString()));
				}
			}
			con.close();
			return true;
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error insertando datos en la BBDD: %s",ex.getMessage()));						
			return false;
		}				
	}
	public List<Usuario> obtenerDatosUsuario() {
		List<Usuario> usuarios = new ArrayList<>();
		
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM USUARIO";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Usuario usuario;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				usuario = new Usuario(0, "a",false);
				
				usuario.setUsuario(rs.getString("USUARIO"));
				usuario.setContraseña(rs.getInt("CONTRASEÑA"));
				if (rs.getString("ADMIN").equals("true")) {
				usuario.setAdmin(true);
				} else {
					usuario.setAdmin(false);
				}
				//Se inserta cada nuevo cliente en la lista de clientes
				usuarios.add(usuario);
			}
			
			//Se cierra el ResultSet
			rs.close();
			con.close();
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se han recuperado %d usuario...", usuarios.size()));			
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error obteniendo datos de la BBDD: %s",ex.getMessage()));						
		}		
		
		return usuarios;
	}
	public List<Cliente> obtenerDatosCliente() {
		List<Cliente> clientes = new ArrayList<>();
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CLIENTE";
			
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
			con.close();
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se han recuperado %d cliente...", clientes.size()));			
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error obteniendo datos de la BBDD: %s",ex.getMessage()));				
		}		
		obtenerDatosAnimal((ArrayList<Cliente>) clientes);
		return clientes;
	}
	
	public List<ArrayList> obtenerDatosAnimal(ArrayList<Cliente> clientes) {
		//El valor 0 de lista sera animales y el valor 1 clientes 
		List<ArrayList> lista = new ArrayList<ArrayList>() ;
		ArrayList<Animal> animales = new ArrayList<>();
		
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM ANIMAL";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Animal animal;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				animal = new Animal(rs.getInt("ID"),rs.getString("RAZA"),rs.getString("ESPECIAL"),rs.getString("TIPO"), rs.getString("Fecha_Nac"));
				
				if (!rs.getString("DNIC_AC").equals("noAcogido")) {
					for (Cliente cliente : clientes) {
						if(cliente.getDni().equals(rs.getString("DNIC_AC"))) {
							cliente.getAnimalesAcogidos().add(animal);
							break;
						}
					}
				}
					
				if (!rs.getString("DNIC_AD").equals("noAdoptado")) {
					for (Cliente cliente : clientes) {
						if(cliente.getDni().equals(rs.getString("DNIC_AD"))) {
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
			con.close();
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se han recuperado %d animales...", animales.size()));			
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error obteniendo datos de la BBDD: %s",ex.getMessage()));
								
		}		
		lista.add(animales);
		lista.add(clientes);
		return lista;
	}
	
	
	
	public boolean borrarDatosUsuario(String usuario) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM USUARIO WHERE USUARIO = '%s';";			
			int result = stmt.executeUpdate(String.format(sql, usuario));
			
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se han borrado %d usuarios", result));
			if (result == 1) {
			return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.WARNING , String.format("*Error borrando datos de la BBDD: %s",ex.getMessage()));
			
			return false;
		}		
	}	
	
	//Metodo creado para usar en la ventana EditarCliente
	public boolean borrarDatosCliente(String dni) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM CLIENTE WHERE DNI = '%s';";			
			int result = stmt.executeUpdate(String.format(sql, dni));
			
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se han borrado %d clientes", result));
			if (result == 1) {
			return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.WARNING , String.format("*Error borrando datos de la BBDD: %s",ex.getMessage()));
			
			return false;
		}		
	}	
	
	public boolean borrarDatosAnimal(int id) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM ANIMAL WHERE ID = '%d';";			
			int result = stmt.executeUpdate(String.format(sql, id));
			
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se han borrado %d animales", result));
			if (result == 1) {
			return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error borrando datos de la BBDD: %s",ex.getMessage()));
			
			return false;
		}		
	}	
	
	public boolean actualizarAnimal(Animal animal, String Dni_AC, String Dni_AD) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "UPDATE ANIMAL SET DNIC_AC = '%s', DNIC_AD = '%s'  WHERE ID = '%d';";
				
				int result = stmt.executeUpdate(String.format(sql, Dni_AC, Dni_AD, animal.getId()));
				
				VentanaPrincipal.logger.log(Level.INFO, String.format("- Se ha actualizado %d animales", result));
				return true;
			} catch (Exception ex) {
				VentanaPrincipal.logger.log(Level.WARNING , String.format("*Error actualizando datos de la BBDD: %s",ex.getMessage()));
				return false;				
			}		
		}
	
	public boolean actualizarCliente(String DNI_C, int permiso) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			 Statement stmt = con.createStatement()){
			String sql = "UPDATE CLIENTE SET PERMISO = '%d' WHERE DNI = '%s';";
			int result = stmt.executeUpdate(String.format(sql,permiso, DNI_C));
			VentanaPrincipal.logger.log(Level.INFO,String.format("- Se ha actualizado %d clientes",result));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			VentanaPrincipal.logger.log(Level.SEVERE , String.format("*Error actualizando datos de la BBDD: %s",e.getMessage()));
			return false;
		}
	}
	//Metodo creado para usar en la ventana EditarCliente solo permite actualizar 1 cliente por llamada
	public boolean actualizarClienteYaExistente(String DNI_C, int tel, String dir) {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			 Statement stmt = con.createStatement()){
			String sql = "UPDATE CLIENTE SET TEL = '%d', DIR = '%s' WHERE DNI = '%s';";
			int result = stmt.executeUpdate(String.format(sql,tel,dir, DNI_C));
			VentanaPrincipal.logger.log(Level.INFO, String.format("- Se ha actualizado %d clientes",result));
			if (result != 1) {
				return false;
			} else {
				return true;
			} 
		} catch (Exception e) {
			// TODO: handle exception
			VentanaPrincipal.logger.log(Level.SEVERE ,String.format("*Error actualizando datos de la BBDD: %s" ,e.getMessage()));
			
			return false;
		}
	}
	
}
		

