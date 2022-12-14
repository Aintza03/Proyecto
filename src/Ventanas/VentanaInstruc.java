package Ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;


public class VentanaInstruc extends JFrame{
	protected JTextArea instru;
	protected Properties idioma;
	protected static int Ventana = 0;
	protected Thread hilo;
	
	public VentanaInstruc() {
		Container cp = this.getContentPane();
		instru = new JTextArea();
		cp.add(instru);
		
		Font font = new Font("Segoe Script", Font.BOLD, 15);
		instru.setFont(font);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(300,100);
		this.setTitle("Instrucciones");
		//this.setLocationRelativeTo(null);
		
		hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
				if(Ventana == 0) {
					instru.setText(idioma.get("instru").toString());
					setLocation(1200, 500);
				} else if (Ventana == 1) {
					instru.setText(idioma.get("instru2").toString() + "\r\n" + idioma.get("instru21").toString());
					setLocation(1200, 500);
				} else if (Ventana == 2) {
					instru.setText(idioma.get("instru").toString());
					setLocation(1200, 500);
				} else if (Ventana == 3) {
					instru.setText(idioma.get("instru3").toString());
					setLocation(1400, 500);
				} else if (Ventana == 4) {
					instru.setText(idioma.get("instru4").toString());
					setLocation(1300, 500);
				}
				pack();
				}
			}
		});
				
				
	
	
	
	}
	
	
}
