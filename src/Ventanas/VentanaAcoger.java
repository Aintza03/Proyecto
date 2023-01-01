package Ventanas;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import General.Animal;
import General.Animal.tipo;
import General.Cliente;
import bbdd.GestorBD;

public class VentanaAcoger extends JFrame{
	
	protected ArrayList<Animal> animales;
	protected Properties p;
	
	protected JTable tablaAnimales;
	protected DefaultTableModel modeloDatosAnimales;
	
	protected JButton boton;
	protected JTabbedPane pestaña;
	
	protected int mouseRow = -1;
	protected int mouseCol = -1;
	
	protected VentanaAdopcion v1;
	protected JLabel Animal;
	public VentanaAcoger(GestorBD v, Properties p, String dni) {
	
		this.animales = recorrerdos(recorrer((ArrayList<Cliente>) v.obtenerDatosCliente()), v.obtenerDatosAnimal((ArrayList<Cliente>) v.obtenerDatosCliente()).get(0)) ;
		this.p = p;
		Container cp = this.getContentPane();
		
		this.initTable();
		this.loadAnimal();
		
		boton = new JButton("->");
		Animal = new JLabel("");
		
		//La tabla de comics se inserta en un panel con scroll
		JScrollPane scrollPaneAnimales = new JScrollPane(this.tablaAnimales);
		scrollPaneAnimales.setBorder(new TitledBorder(p.get("acoger").toString()));
		
		JPanel cosa = new JPanel();
		cosa.setLayout(new GridLayout(2,3));
		cosa.add(new JLabel(""));
		cosa.add(boton);
		cosa.add(new JLabel(""));
		cosa.add(new JLabel(""));
		cosa.add(new JLabel(""));
		cosa.add(new JLabel(""));
		
		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(2,1));
		abajo.add(Animal);
		abajo.add(cosa);
		this.tablaAnimales.setFillsViewportHeight(true);
		
		
		//cp.add(pestaña);
		cp.setLayout(new GridLayout(2,1));
		cp.add(scrollPaneAnimales);
		cp.add(abajo);
		this.setTitle(p.getProperty("ventanaAcoger"));		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						try {
							int d = 0; 
							d = Integer.parseInt(modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(), 0).toString());
							for (Animal animal : animales) {
								if(animal.getId() == d) {
							v.actualizarAnimal(animal, dni, null);
								}
								}
						} catch (Exception e2) {
							System.err.println("No se ha escogido animal");
						}
						v1 = new VentanaAdopcion(p,v,dni);
						setVisible(false);
						
					}
		
			
		});
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					boton.doClick();
				}
				if (e.isControlDown() && e.getKeyCode()== KeyEvent.VK_A) {
					tablaAnimales.clearSelection();
				}
			}
		};
		this.tablaAnimales.addKeyListener(keyListener);
		this.boton.addKeyListener(keyListener);
		
		Thread hilo = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int b = 0;
				boolean cambio = true;
				while(isVisible()) {
					if(!(b == 255) && cambio) {
						b++;
					} else if (b == 255) {
						b--;
						cambio = false;
					}else if (b == 0) {
						b++;
						cambio = true;
					} else {
						b--;
					}
					Color color = new Color(0,0,b);
					Animal.setForeground(color);
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		hilo.start();
	}
	
	private void initTable() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraAnimales = new Vector<String>(Arrays.asList( "ID", p.get("tipo").toString() , p.get("fecha_nac").toString(), p.get("raza").toString(), p.get("especial").toString()));				
		//Se crea el modelo de datos para la tabla de comics sÃ³lo con la cabecera		
		this.modeloDatosAnimales = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAnimales) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		//Se crea la tabla de comics con el modelo de datos		
		this.tablaAnimales = new JTable(this.modeloDatosAnimales);
		tablaAnimales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Render para las celdas de la Editorial se define como un Label un logo
		DefaultTableCellRenderer a = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				
				//Cambiar el texto de tipo Gato/Perro por imagenes 
				//tipo tipo = (tipo) value;	
				//label.setIcon(new ImageIcon(tipo.getIcon()));
	
				//El label se alinea a la izquierda
				label.setHorizontalAlignment(JLabel.LEFT);
						
				//Se diferencia el color de fondo en filas pares e impares
				if (row % 2 == 0) {
					label.setForeground(Color.BLACK);
					label.setBackground(Color.CYAN);
				} else {
					label.setForeground(Color.BLACK);
					label.setBackground(Color.WHITE);
				}
				
				//Si la celda estÃ¡ seleccionada se asocia un color de fondo y letra
				if (mouseRow == row ) {
					label.setBackground(Color.GREEN);
					label.setForeground(Color.BLACK);
				}
				
				//Si la celda estÃ¡ seleccionada se asocia un color de fondo y letra
				if (isSelected) {
					label.setBackground(table.getSelectionBackground().BLUE);
					label.setForeground(table.getSelectionForeground().WHITE);
					Animal.setText("Id: " + modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(),0) + " Tipo: " + modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(),1) + " " + modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(), 3) + " (" + modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(), 2) + ") Necesidades Especiales: " + modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(),4));
				}

				//Es necesaria esta sentencia para pintar correctamente el color de fondo
				label.setOpaque(true);
				
				return label;
			}
			
		};
		
		for(int i = 0; i < this.tablaAnimales.getColumnModel().getColumnCount(); i++ ) {
			this.tablaAnimales.getColumnModel().getColumn(i).setCellRenderer(a);
			
		}
					
			
		this.tablaAnimales.addMouseListener(new MouseAdapter() {						
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				
				System.out.println(String.format("Se ha pulsado el boton %d en la fila %d, columna %d", e.getButton(), row, col));
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());

				System.out.println(String.format("Se ha liverado el boton %d en la fila %d, columna %d", e.getButton(), row, col));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				System.out.println(String.format("Se ha hecho click con el boton %d en la fila %d, columna %d", e.getButton(), row, col));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				System.out.println(String.format("Se ha entrado en la fila %d, columna %d", e.getButton(), row, col));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());

				System.out.println(String.format("Se ha salido de la fila %d, columna %d", e.getButton(), row, col));

				//Cuando el ratÃ³n sale de la tabla, se resetea la columna/fila sobre la que estÃ¡ el ratÃ³n				
				mouseRow = -1;
				mouseCol = -1;
			}
			
		});
		
		tablaAnimales.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				mouseRow = row;
				mouseCol = col;
				
				repaint();

			}
		});
	}
	
	
	private void loadAnimal() {
		//Se borran los datos del modelo de datos Animales
		this.modeloDatosAnimales.setRowCount(0);
		
		//Se aÃ±ade al modelo una fila de datos por cada comic
		for (Animal a : this.animales) {
			this.modeloDatosAnimales.addRow( new Object[] {a.getId(), a.getTipo(), a.getFechaNac(),  a.getRaza(), a.getEspecial()} );
		}		
	}
	
	public HashMap<String, ArrayList<Animal>> recorrer(ArrayList<Cliente> clientes) {
		
		HashMap<String, ArrayList<Animal>> animales = new HashMap<String, ArrayList<Animal>>();
		animales.put("Acogidos", new ArrayList<Animal>());
		animales.put("Adoptados", new ArrayList<Animal>());
		
		for (Cliente cliente : clientes) {
			ArrayList<Animal> Acogida = cliente.getAnimalesAcogidos();
			for (Animal animal : Acogida) {
				animales.get("Acogidos").add(animal);
			}
			ArrayList<Animal> Adoptados = cliente.getAnimalesAdoptados();
			for (Animal animal2 : Adoptados) {
				animales.get("Adoptados").add(animal2);
			}
		}
		return animales;
		
	}
	
	
	public ArrayList<Animal> recorrerdos(HashMap<String, ArrayList<Animal>> animalesMap, ArrayList<Animal> animales ) {
		ArrayList<Animal> res = new ArrayList<Animal>();
		ArrayList<Animal> listaAco = animalesMap.get("Acogidos");
		ArrayList<Animal> listaAdo = animalesMap.get("Adoptados");
		for (Animal animal : animales) {
			boolean listaAd = true;
			boolean listaAc = true;
			for (Animal animal2 : listaAdo) {
				if (animal.equals(animal2)) {
					listaAd = false;
				}
			}
			for (Animal animal3 : listaAco) {
				if (animal.equals(animal3)) {
					listaAc = false;
				}
			}
			if (listaAd && listaAc) {
				res.add(animal);
			}
		}
		return res;
	}

}

