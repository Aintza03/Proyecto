package Ventanas;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.*;
import General.Animal;
import General.Cliente;
import General.Contrato;
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
	protected JTabbedPane pestaña;
	protected JButton descartar;
	protected JButton guardar;
	protected JTextArea area;
	protected Cliente cliente;
	protected int contratoA = 0;
	public VentanaAdopcion(Properties p, GestorBD b, String dni) {
		Container cp = this.getContentPane();
		JPanel cp1 = new JPanel();
		cp1.setLayout(new GridLayout(1, 3));
		JButton descartar = new JButton(p.getProperty("descartar"));
		JButton guardar = new JButton(p.getProperty("guardar"));
		pestaña = new JTabbedPane();
		pestaña.addTab(p.getProperty("ventanaAdoptar"), cp1);
		JPanel acogidos = new JPanel(new BorderLayout());
		JPanel botonesCentro = new JPanel(new GridLayout(4,1));
		JPanel adoptados = new JPanel(new BorderLayout());
		
		modeloAcogido = new DefaultListModel<Animal>();
		cliente = VentanaAdopcion.cargarCliente(dni, b);
		modeloAcogido.addAll(cliente.getAnimalesAcogidos());
		
		
		
		listaAcogido = new JList(modeloAcogido);
		listaAcogido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollAcogidos = new JScrollPane(listaAcogido);
		
		JLabel acoger = new JLabel(p.getProperty("acoger") + ":");
		acogidos.add(acoger, BorderLayout.NORTH);
		acogidos.add(scrollAcogidos, BorderLayout.CENTER);
		
		botonAdoptar = new JButton(p.getProperty("botonadoptar")+"->");
		botonDevolver= new JButton("<-"+p.getProperty("devolver"));
		JPanel cp2 = new JPanel();
		JPanel abajo2 = new JPanel();
		cp2.setLayout(new GridLayout(2,1));
		abajo2.setLayout(new GridLayout(1,2));
		descartar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pestaña.remove(1);
				cp2.removeAll();
				VentanaPrincipal.logger.info("Se ha descartado el contrato en VentanaAdopcion");
			}
		});
		guardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<Animal> listaAdo = new ArrayList<Animal>();
				ArrayList<Animal> listaAdoSi = new ArrayList<Animal>();
				try {
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
				Contrato.guardarContrato(area.getText(),listaAcogido.getSelectedValue().getId());
				modeloAcogido.removeAllElements();
				modeloAdoptado.removeAllElements();
				modeloAcogido.addAll(listaAdo);
				modeloAdoptado.addAll(listaAdoSi);
				VentanaPrincipal.logger.log(Level.WARNING,"Se ha guardado el contrato");
			} catch (Exception e1) {
				VentanaPrincipal.logger.log(Level.WARNING,"La lista de acogida estaba vacia al pulsar boton Adoptar en VentanaAdopcion");
			}
			
				pestaña.remove(1);
				VentanaPrincipal.logger.info("Se ha guardado el contrato en VentanaAdopcion");
			}
		});
		botonAdoptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pestaña.getTabCount() < 2) {
					try {
					Animal ani = listaAcogido.getSelectedValue();
					String animal = "";
					if (ani.getTipo() == "Gato") {
						animal = p.getProperty("gato");
					}else {
						animal = p.getProperty("perro");
					}
					area = new JTextArea(String.format(Contrato.obtenerPlantilla(p.getProperty("plantilla")), cliente.getNombre(), cliente.getDni(), animal, ani.getId() + "", ani.getRaza()));
					area.setEditable(false);
					cp2.add(area);
					abajo2.add(descartar);
					abajo2.add(guardar);
					cp2.add(abajo2);
					contratoA = ani.getId();
					pestaña.addTab("contrato" + ani.getId() + ".txt", cp2);
					} catch(Exception e) {
						VentanaPrincipal.logger.log(Level.WARNING,"No hay nada seleccionado en lista de Adopcion en VentanaAdopcion");
					}
				} else {
				VentanaPrincipal.logger.log(Level.WARNING,"Ya hay un contrato abierto en VentanaAdopcion");
			}
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
					if(contratoA == listaAcogido.getSelectedValue().getId()) {
						descartar.doClick();
					}
					modeloAcogido.removeAllElements();
					modeloAcogido.addAll(listaAdo);
					
					}else {
						VentanaPrincipal.logger.log(Level.WARNING,"La lista de Animales acogidos esta vacia en VentanaAdopcion");
					}
				} catch (Exception e) {
					// TODO: handle exception
					VentanaPrincipal.logger.log(Level.WARNING,"No se ha selecionado nada en la lista de Acogida en VentanaAdopcion");
				}
				 
				try {
					if (!modeloAdoptado.isEmpty()) {
					for (int i = 0; i< modeloAdoptado.size(); i++) {
						if(!(listaAdoptado.getSelectedValue().equals(modeloAdoptado.get(i)))) {
						listaAdoSi.add(modeloAdoptado.get(i));
						}
					}
					Contrato.borrarContrato(listaAdoptado.getSelectedValue().getId());
					b.actualizarAnimal(listaAdoptado.getSelectedValue(), "noAcogido" , "noAdoptado");
					modeloAdoptado.removeAllElements();
					modeloAdoptado.addAll(listaAdoSi);
					} else {
						VentanaPrincipal.logger.log(Level.WARNING,"La lista de animales adoptados esta vacia en VentanaAdopcion");
					}
				} catch (Exception e) {
					// TODO: handle exception
					VentanaPrincipal.logger.log(Level.WARNING,"No se ha selecionado nada en la lista de Adopcion en VentanaAdopcion");
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
		
		
		cp1.add(acogidos);
		cp1.add(botonesCentro);
		cp1.add(adoptados);
		
		cp.add(pestaña);
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
						VentanaPrincipal.logger.log(Level.WARNING , "El hilo de ventana Adopcion no ha conseguido ejecutar la instruccion sleep");
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
					VentanaPrincipal.logger.info("Se ha vuelto a VentanaAcoger");
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
					VentanaPrincipal.logger.info("Se ha bloqueado al cliente" + dni + " en VentanaAdopcion");
					VentanaPrincipal.finLogger();
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
		this.pestaña.addKeyListener(keyListener);
		cp1.addKeyListener(keyListener);
	}
	
	public static Cliente cargarCliente(String dni, GestorBD bd) {
		
		ArrayList<Cliente> listaCliente = (ArrayList<Cliente>)bd.obtenerDatosCliente();
		Cliente a = null;
		
		for (Cliente cliente : listaCliente) {
			if(dni.equals(cliente.getDni())) {
				a = cliente;
			}
		}
		if (a == null) {
			VentanaPrincipal.logger.log(Level.WARNING, "No se ha encontrado el cliente");
		}
		return a;
	}
	
	
	
}
