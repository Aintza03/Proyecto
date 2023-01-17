package General;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import Ventanas.VentanaPrincipal;

public class Contrato {
	public static String obtenerPlantilla(String fichero) {
		BufferedReader br;
		String linea;
		String documento = "";
		try {
			br = new BufferedReader(new FileReader(fichero));
			documento = br.readLine();
			while((linea = br.readLine())!= null) {
				documento = documento + "\r\n" + linea;
			}
			br.close();
			documento = documento + "\r\n\r\n\r\n";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VentanaPrincipal.logger.log(Level.INFO,"Se ha recuperado la plantilla");
		return documento;
	}
	public static boolean guardarContrato(String contrato,int id) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("Contratos/contrato" + id +".txt"));
			bf.write(contrato);
			
			bf.close();
			VentanaPrincipal.logger.log(Level.INFO,"Se ha guardado el contrato");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			VentanaPrincipal.logger.log(Level.WARNING,"No se ha guardado el contrato");
			return false;
		}
	}
	public static boolean borrarContrato(int id) {
		File f = new File("Contratos/contrato" + id + ".txt");
		boolean a = f.delete();
		if (a) {
			VentanaPrincipal.logger.log(Level.INFO,"Se ha borrado el contrato al devolverse el animal");
		} else {
			VentanaPrincipal.logger.log(Level.WARNING,"No se ha borrado el contrato");
		}
		return a;
	}
}
