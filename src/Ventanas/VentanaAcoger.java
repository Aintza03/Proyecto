package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import General.Animal;
import General.Animal.tipo2;
import General.Cliente;
import bbdd.GestorBD;

public class VentanaAcoger extends JFrame{
	
	protected ArrayList<Animal> animales;
	protected Properties p;
	
	protected JTable tablaAnimales;
	protected DefaultTableModel modeloDatosAnimales;
	
	protected JLabel TIPO;
	protected JLabel FECHA_NAC;
	protected JLabel RAZA;
	protected JLabel ESPECIAL;
	protected JLabel Error;
	protected JLabel Error2;
	protected JLabel ID;
	
	protected JTextField tipo;
	protected JTextField fecha_nac;
	protected JTextField raza;
	protected JTextField especial;
	protected JTextField id;
	
	
	protected JButton boton;
	protected JButton boton33;
	protected JButton eliminarAnimal;
	protected JTabbedPane pestaña;
	protected JComboBox combo;
	
	protected int mouseRow = -1;
	protected int mouseCol = -1;
	
	protected VentanaAdopcion v1;
	protected JLabel Animal;
	public VentanaAcoger(GestorBD v, Properties p, String dni) {
	
		this.animales = VentanaAcoger.recorrerdos(VentanaAcoger.recorrer((ArrayList<Cliente>) v.obtenerDatosCliente()), v.obtenerDatosAnimal((ArrayList<Cliente>) v.obtenerDatosCliente()).get(0));
		this.p = p;
		this.pestaña = new JTabbedPane();
		Container cp = this.getContentPane();
		
		this.initTable(v, dni);
		this.loadAnimal();
		
		
		boton = new JButton("->");
		boton33 = new JButton("->");
		Animal = new JLabel("");
		combo = new JComboBox();
		combo.addItem(p.getProperty("gato"));
		combo.addItem(p.getProperty("perro"));
		combo.addItem(p.getProperty("sinFiltro"));
		combo.addItem("Animales Ordenados Por Raza");
		
		 RowFilter<Object,Object> startsWithAFilter = new RowFilter<Object,Object>() {
			   public boolean include(Entry<? extends Object, ? extends Object> entry) {
			     
			       if (entry.getStringValue(1).equals("Gato")) {
			         // The value starts with "a", include it
			         return true;
			       }else {
			     
			     // None of the columns start with "a"; return false so that this
			     // entry is not shown
			     return false;
			       }
			   }
			 };
			 RowFilter<Object,Object> startsWithAFilter1 = new RowFilter<Object,Object>() {
				   public boolean include(Entry<? extends Object, ? extends Object> entry) {
				     
				       if (entry.getStringValue(1).equals("Perro")) {
				         // The value starts with "a", include it
				         return true;
				       }else {
				     
				     // None of the columns start with "a"; return false so that this
				     // entry is not shown
				     return false;
				       }
				   }
				 };
			 combo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(combo.getSelectedIndex() == 0) {
							TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloDatosAnimales);
							sorter.setRowFilter(startsWithAFilter);
							tablaAnimales.setRowSorter(sorter);
							VentanaPrincipal.logger.log(Level.INFO,"Se aplica el filtro Gato");
						}else if(combo.getSelectedIndex() == 1){
							TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modeloDatosAnimales);
							sorter.setRowFilter(startsWithAFilter1);
							tablaAnimales.setRowSorter(sorter);
							VentanaPrincipal.logger.log(Level.INFO,"Se aplica el filtro Perro");
						}else if(combo.getSelectedIndex() == 2) {
							tablaAnimales.setRowSorter(null);
							VentanaPrincipal.logger.log(Level.INFO,"No se aplica ningun filtro");
						}
						if (combo.getSelectedIndex() == 3){
							Collections.sort(animales);
							loadAnimal();
						} else {
							Comparator<Animal> comparator = (a1,a2) -> {
								return Integer.compare(a1.getId(), a2.getId());
							};
							Collections.sort(animales,comparator);
							loadAnimal();
						}
						}	
					
				});
		
		//La tabla de animales se inserta en un panel con scroll
		JScrollPane scrollPaneAnimales = new JScrollPane(this.tablaAnimales);
		scrollPaneAnimales.setBorder(new TitledBorder(p.get("acoger1").toString()));
		
		JPanel cosa = new JPanel();
		cosa.setLayout(new GridLayout(2,3));
		cosa.add(new JLabel(""));
		cosa.add(boton);
		cosa.add(new JLabel(""));
		cosa.add(new JLabel(""));
		cosa.add(new JLabel(""));
		cosa.add(new JLabel(""));
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(2,1));
		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(2,2));
		centro.add(combo);
		centro.add(Animal);
		abajo.add(centro);
		abajo.add(cosa);
		this.tablaAnimales.setFillsViewportHeight(true);
		
		
		
		JPanel cp1 = new JPanel();
		cp1.setLayout(new GridLayout(2,1));
		cp1.add(scrollPaneAnimales);
		cp1.add(abajo);
		JPanel cp2 = new JPanel();
		JPanel arriba2 = new JPanel();
		JPanel centro2 = new JPanel();
		JPanel abajo2 = new JPanel();
		TIPO = new JLabel(p.get("tipo").toString());
		FECHA_NAC = new JLabel(p.get("fecha_nac").toString() + " (DD/MM/YYYY)");
		RAZA = new JLabel(p.get("raza").toString());
		ESPECIAL = new JLabel(p.get("especial").toString());
		Error = new JLabel("");
		tipo = new JTextField("");
		fecha_nac = new JTextField("");
		raza = new JTextField("");
		especial = new JTextField("");
		JScrollPane c = new JScrollPane(Error);
		cp2.setLayout(new GridLayout(3,1));
		arriba2.setLayout(new GridLayout(1,4));
		centro2.setLayout(new GridLayout(1,4));
		abajo2.setLayout(new GridLayout(1,2));
		arriba2.add(TIPO);
		arriba2.add(tipo);
		arriba2.add(FECHA_NAC);
		arriba2.add(fecha_nac);
		centro2.add(RAZA);
		centro2.add(raza);
		centro2.add(ESPECIAL);
		centro2.add(especial);
		abajo2.add(c);
		abajo2.add(boton33);
		cp2.add(arriba2);
		cp2.add(centro2);
		cp2.add(abajo2);
		
		JPanel cp3 = new JPanel();
		cp3.setLayout(new GridLayout(2,2));
		ID = new JLabel("ID: ");
		Error2 = new JLabel("");
		JScrollPane b = new JScrollPane(Error2);
		id = new JTextField();
		eliminarAnimal = new JButton(p.getProperty("eliminar"));
		
		cp3.add(ID);
		cp3.add(id);
		cp3.add(b);
		cp3.add(eliminarAnimal);
		
		
		boton33.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
						if(tipo.getText().equals("Gato")||tipo.getText().equals("Perro")) {
							if(fecha_nac.getText().length() == 10 ) {
								if(!(raza.getText().isEmpty())) {
									if(!(especial.getText().isEmpty())) {
										Animal a = new Animal (v.obtenerDatosAnimal((ArrayList<Cliente>) v.obtenerDatosCliente()).get(0).size() + 1, raza.getText(), especial.getText(), tipo.getText(), fecha_nac.getText());
										v.insertarDatosAnimal(a);
										Error.setText("");
										modeloDatosAnimales.addRow( new Object[] {a.getId(), a.getTipo(), a.getFechaNac(),  a.getRaza(), a.getEspecial(), new JButton("->")});
										}else {
										Animal a = new Animal (v.obtenerDatosAnimal((ArrayList<Cliente>) v.obtenerDatosCliente()).get(0).size() + 1, raza.getText(), "nada", tipo.getText(), fecha_nac.getText());
										v.insertarDatosAnimal(a);
										modeloDatosAnimales.addRow( new Object[] {a.getId(), a.getTipo(), a.getFechaNac(),  a.getRaza(), a.getEspecial(), new JButton("->")});
										
										Error.setText("");
										VentanaPrincipal.logger.log(Level.INFO,"Se ha insertado un nuevo animal en la tabla en VentanaAcoger");
									}
								}else {
									Error.setText(p.getProperty("error10"));
									VentanaPrincipal.logger.log(Level.WARNING, "Los valores introducidos no son correctos en VentanaAcoger");
								}
							}else {
								Error.setText(p.getProperty("error11"));
								VentanaPrincipal.logger.log(Level.WARNING, "La fecha es incorrecta en VentanaAcoger");
							}
						}else {
							Error.setText(p.getProperty("error12"));
							VentanaPrincipal.logger.log(Level.WARNING, "No se admite ese tipo de animales en el sistema");
						}
					}
		
			
		});
		eliminarAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
				String a = id.getText();
				int b = Integer.parseInt(a);
				boolean ocurre = v.borrarDatosAnimal(b);
				if (ocurre) {
					Error2.setText(p.getProperty("error13"));
					VentanaPrincipal.logger.info("Se ha borrado el animal en VentanaAcoger");
					for (int i = 0; i < modeloDatosAnimales.getRowCount(); i++) {
						String c = modeloDatosAnimales.getValueAt(i, 0).toString();
						int d = Integer.parseInt(c);
						if (b == d) {
							modeloDatosAnimales.removeRow(i);
						}
					}
				} else {
					Error2.setText(p.getProperty("error14"));
					VentanaPrincipal.logger.log(Level.WARNING,"No se ha encontrado el animal en VentanaAcoger");
				}
			}catch(Exception e1) {
				Error2.setText(p.getProperty("error15"));
				VentanaPrincipal.logger.log(Level.WARNING,"el ID esta vacio o tiene numeros en VentanaAcoger");
			}
			} 
		});
		
		pestaña.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(pestaña.getSelectedIndex() == 0) {
					setSize(800,600);
				}else if (pestaña.getSelectedIndex() == 1) {
					setSize(800,150);
				}else if (pestaña.getSelectedIndex() == 2) {
					setSize(800, 150);
				}
			}
		});
		
		this.setTitle(p.getProperty("ventanaAcoger"));		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		this.setIconImage(new ImageIcon("images/image.jpg").getImage());
		combo.setSelectedIndex(2);
		cp.setLayout(new GridLayout(1,1));
		pestaña.addTab(p.getProperty("tablaAnimales"), cp1);
		pestaña.addTab(p.getProperty("introducirAnimal"), cp2);
		pestaña.addTab(p.getProperty("eliminarAnimal"), cp3);
		cp.add(pestaña);
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				VentanaPrincipal.finLogger();
				System.exit(0);
			}
		});
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
						v1 = new VentanaAdopcion(p,v,dni);
						VentanaPrincipal.logger.log(Level.INFO,"Se abre la ventana de adopción");
						setVisible(false);
					}
		});
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (pestaña.getSelectedIndex() == 0) {
					boton.doClick();
				} else if (pestaña.getSelectedIndex() == 1) {
					boton33.doClick();
				} else {
					eliminarAnimal.doClick();
				}
				}
				
			if (e.isControlDown() && e.getKeyCode()== KeyEvent.VK_A) {
				if (pestaña.getSelectedIndex() == 0) {
					tablaAnimales.clearSelection();
				}
				}
			}
		};
		this.tablaAnimales.addKeyListener(keyListener);
		this.pestaña.addKeyListener(keyListener);
		this.tipo.addKeyListener(keyListener);
		this.fecha_nac.addKeyListener(keyListener);
		this.raza.addKeyListener(keyListener);
		this.especial.addKeyListener(keyListener);
		this.id.addKeyListener(keyListener);
		this.combo.addKeyListener(keyListener);
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
					TIPO.setForeground(color);
					FECHA_NAC.setForeground(color);
					RAZA.setForeground(color);
					ESPECIAL.setForeground(color);
					Error.setForeground(color);
					ID.setForeground(color);
					Error2.setForeground(color);
					
					try {
						Thread.sleep(25);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						VentanaPrincipal.logger.log(Level.WARNING , "Error al ejecutar hilo de ventanaAcoger");
					}
				}
			}
		});
		hilo.start();
	}
	
	private void initTable(GestorBD v, String dni ) {
		//Cabecera del modelo de datos
		Vector<String> cabeceraAnimales = new Vector<String>(Arrays.asList( "ID", p.get("tipo").toString() , p.get("fecha_nac").toString(), p.get("raza").toString(), p.get("especial").toString(), p.getProperty("acoger2")));				
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
				
				if(!(value instanceof JButton)) {
				JLabel label = new JLabel(value.toString());
				
				if(label.getText().equals("Gato")) {
					label.setIcon(new ImageIcon(tipo2.GATO.getIcon()));
				} else if(label.getText().equals("Perro")) {
					label.setIcon(new ImageIcon(tipo2.PERRO.getIcon()));
				}
				//El label se alinea a la izquierda
				label.setHorizontalAlignment(JLabel.CENTER);
						
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
				} else {
					JButton boton1 = (JButton) value;
					return boton1;
				}
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
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				if(col == 5) {
					try {
						int d = 0; 
						d = Integer.parseInt(modeloDatosAnimales.getValueAt(tablaAnimales.getSelectedRow(), 0).toString());
						for (Animal animal : animales) {
							if(animal.getId() == d) {
						v.actualizarAnimal(animal, dni, null);
						modeloDatosAnimales.removeRow(row);
							}
							}
					} catch (Exception e2) {
						System.err.println("No se ha escogido animal");
					}	
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
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
			this.modeloDatosAnimales.addRow( new Object[] {a.getId(), a.getTipo(), a.getFechaNac(),  a.getRaza(), a.getEspecial(), new JButton("->")} );
		}		
	}
	
	public static HashMap<String, ArrayList<Animal>> recorrer(ArrayList<Cliente> clientes) {
		
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
	
	
	public static ArrayList<Animal> recorrerdos(HashMap<String, ArrayList<Animal>> animalesMap, ArrayList<Animal> animales ) {
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

