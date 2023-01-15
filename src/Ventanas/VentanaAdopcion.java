package Ventanas;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.*;
import General.Animal;
import General.Cliente;
import General.Usuario;
import bbdd.GestorBD;


public class VentanaAdopcion extends JFrame {
	
	protected DefaultListModel<Animal> modeloAcogido;
	protected JList<Animal> listaAcogido;
	protected DefaultListModel<Animal> modeloAdoptado;
	protected JList<Animal> listaAdoptado;
	protected JButton botonAdoptar;
	protected JButton botonDevolver;
	protected VentanaAcoger v2;
	
		
	public VentanaAdopcion(Properties p, GestorBD b, String dni) {
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, 3));
		
		JPanel acogidos = new JPanel(new BorderLayout());
		JPanel botonesCentro = new JPanel(new GridLayout(4,1));
		JPanel adoptados = new JPanel(new BorderLayout());
		
		modeloAcogido = new DefaultListModel<Animal>();
		Cliente cliente = VentanaAdopcion.cargarCliente(dni, b);
		modeloAcogido.addAll(cliente.getAnimalesAcogidos());
		
		
		
		listaAcogido = new JList(modeloAcogido);
		listaAcogido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollAcogidos = new JScrollPane(listaAcogido);
		
		JLabel acoger = new JLabel(p.getProperty("acoger") + ":");
		acogidos.add(acoger, BorderLayout.NORTH);
		acogidos.add(scrollAcogidos, BorderLayout.CENTER);
		
		botonAdoptar = new JButton(p.getProperty("botonadoptar")+"->");
		botonDevolver= new JButton("<-"+p.getProperty("devolver"));
		
		botonAdoptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ArrayList<Animal> listaAdo = new ArrayList<Animal>();
				ArrayList<Animal> listaAdoSi = new ArrayList<Animal>();
				
				for (int i = 0; i< modeloAcogido.size() ; i++) {
					if(!(listaAcogido.getSelectedValue().equals(modeloAcogido.get(i)))) {
					listaAdo.add(modeloAcogido.get(i));
						
					}
				}
				for (int i = 0; i< modeloAdoptado.size(); i++) {
					listaAdoSi.add(modeloAdoptado.get(i));
					
				}
				listaAdoSi.add(listaAcogido.getSelectedValue());
				b.actualizarAnimal(listaAcogido.getSelectedValue(), "noAcogido" , dni);
				modeloAcogido.removeAllElements();
				modeloAdoptado.removeAllElements();
				modeloAcogido.addAll(listaAdo);
				modeloAdoptado.addAll(listaAdoSi);
			}
		});
		botonDevolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Animal> listaAdo = new ArrayList<Animal>();
				ArrayList<Animal> listaAdoSi = new ArrayList<Animal>();
				try {
					if (!modeloAcogido.isEmpty()) {
					for (int i = 0; i< modeloAcogido.size(); i++) {
						if(!(listaAcogido.getSelectedValue().equals(modeloAcogido.get(i)))) {
						listaAdo.add(modeloAcogido.get(i));
						}
					}
					b.actualizarAnimal(listaAcogido.getSelectedValue(), "noAcogido","noAdoptado");
					modeloAcogido.removeAllElements();
					modeloAcogido.addAll(listaAdo);
					}else {
						VentanaPrincipal.log.log(Level.FINE,"La lista de Animales acogidos esta vacia");
					}
				} catch (Exception e) {
					// TODO: handle exception
					VentanaPrincipal.log.log(Level.FINE,"No se ha selecionado nada en la lista de Acogida");
				}
				 
				try {
					if (!modeloAdoptado.isEmpty()) {
					for (int i = 0; i< modeloAdoptado.size(); i++) {
						if(!(listaAdoptado.getSelectedValue().equals(modeloAdoptado.get(i)))) {
						listaAdoSi.add(modeloAdoptado.get(i));
						}
					}
					b.actualizarAnimal(listaAdoptado.getSelectedValue(), "noAcogido" , "noAdoptado");
					modeloAdoptado.removeAllElements();
					modeloAdoptado.addAll(listaAdoSi);
					} else {
						VentanaPrincipal.log.log(Level.FINE,"La lista de animales adoptados esta vacia");
					}
				} catch (Exception e) {
					// TODO: handle exception
					VentanaPrincipal.log.log(Level.FINE,"No se ha selecionado nada en la lista de Adopcion");
				}
				
			}
		});
		
		
		botonesCentro.add(new JLabel(""));
		botonesCentro.add(botonAdoptar);
		botonesCentro.add(botonDevolver);
		botonesCentro.add(new JLabel(""));
		
		modeloAdoptado = new DefaultListModel<Animal>();
		modeloAdoptado.addAll(cliente.getAnimalesAdoptados());
		listaAdoptado = new JList(modeloAdoptado);
		listaAcogido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollAdoptado = new JScrollPane(listaAdoptado);
		JLabel adoptar = new JLabel(p.getProperty("adoptar") + ":");
		adoptados.add(adoptar, BorderLayout.NORTH);
		adoptados.add(scrollAdoptado, BorderLayout.CENTER);	
		
		
		cp.add(acogidos);
		cp.add(botonesCentro);
		cp.add(adoptados);
		
		this.setTitle(p.getProperty("ventanaAdoptar"));
		this.setSize(800, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("images/image.jpg").getImage());
		Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int b = 0;
				boolean cambio = true;
				while(isVisible()) {
					if(!(b==255) && cambio) {
						b++;
					}else if(b == 255) {
						b--;
						cambio = false;
					} else if(b== 0) {
						b++;
						cambio = true;
					}else {
						b--;
					}
					Color color = new Color(0,0,b);
					acoger.setForeground(color);
					adoptar.setForeground(color);
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						VentanaPrincipal.log.log(Level.WARNING , "El hilo de ventana Adopcion no ha conseguido ejecutar la instruccion sleep",e);
					}
				}
			}
		});
		hilo.start();
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()== KeyEvent.VK_BACK_SPACE) {
					v2 = new VentanaAcoger(b, p, dni);
					setVisible(false);
				}
				if (e.isAltDown()) {
					b.actualizarCliente(dni, 0);
					for (int i = 0; i < modeloAdoptado.size() ;i++) {
						b.actualizarAnimal(modeloAdoptado.get(i),"noAcogido", "noAdoptado");
					}
					modeloAdoptado.removeAllElements();
					for (int i = 0; i < modeloAcogido.size(); i++) {
						b.actualizarAnimal(modeloAcogido.get(i), "noAcogido", "noAdoptado");
					}
					modeloAcogido.removeAllElements();
					System.exit(0);
				}
				
			}
			
		};
		this.addWindowListener(new WindowAdapter() {
			
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.finLogger();
				System.exit(0);
			}
		});
		this.listaAcogido.addKeyListener(keyListener);
		this.listaAdoptado.addKeyListener(keyListener);
		this.botonAdoptar.addKeyListener(keyListener);
		this.botonDevolver.addKeyListener(keyListener);
	}
	
	public static Cliente cargarCliente(String dni, GestorBD bd) {
		
		ArrayList<Cliente> listaCliente = (ArrayList<Cliente>)bd.obtenerDatosCliente();
		Cliente a = null;
		
		for (Cliente cliente : listaCliente) {
			if(dni.equals(cliente.getDni())) {
				a = cliente;
			}
		}
		
		return a;
	}
	
	
	
}
