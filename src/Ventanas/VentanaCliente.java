package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import General.Animal;
import General.Cliente;
import bbdd.GestorBD;

public class VentanaCliente extends JFrame{
	protected JTextField DNI;
	protected JLabel ErrorCliente;
	protected JButton Buscar;
	protected JLabel dni;
	protected JLabel valor;
	//pestañas y JTree
	protected JTabbedPane pestaña;
	protected JTree tr1;
	protected JTree tr2;
	
	//para concatenar las ventanas
	protected VentanaAcoger v3;
	protected VentanaIntroducirCliente v2;
	protected VentanaUsuarios vus;
	protected VentanaEditarCliente ved;
	public VentanaCliente(Properties idioma, GestorBD gestorV, boolean admin) {
		Container cp = this.getContentPane();
		DNI = new JTextField();
		ErrorCliente = new JLabel("");
		Buscar = new JButton(idioma.get("buscar").toString());
		dni = new JLabel(idioma.get("dni").toString() + ":");
		pestaña = new JTabbedPane();
		JButton administrador = new JButton(idioma.getProperty("editUsu"));
		JButton editarCliente = new JButton (idioma.getProperty("editCli"));
		JScrollPane textoError = new JScrollPane(ErrorCliente);
		JPanel arriba = new JPanel();
		arriba.setLayout(new GridLayout(1,2));
		JPanel abajo = new JPanel();
		abajo.setLayout(new GridLayout(1,2));
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(1,2));
		valor = new JLabel();
		
		JPanel cp1 = new JPanel();
		cp1.setLayout(new GridLayout(4,1));
		
		arriba.add(dni);
		arriba.add(DNI);
		centro.add(textoError);
		centro.add(Buscar);
		abajo.add(administrador);
		abajo.add(editarCliente);
		cp1.add(arriba);
		cp1.add(valor);
		cp1.add(centro);
		cp1.add(abajo);
		
		if (!admin) {
			administrador.setEnabled(false);
		}
		pestaña.addTab(idioma.getProperty("introducir"), cp1);		
		cp.add(pestaña);
		administrador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vus = new VentanaUsuarios(gestorV,idioma);
				VentanaPrincipal.logger.info("Abierto ventanaUsuario");
				setVisible(false);
			}
		});
		
		editarCliente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ved = new VentanaEditarCliente(gestorV,idioma);
				VentanaPrincipal.logger.info("Abierto ventanaEditarCliente");
				setVisible(false);
			}
		});
		DNI.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				valor.setText(DNI.getText());
				if (VentanaIntroducirCliente.DNIAPTO(DNI.getText())) {
					int a = Integer.parseInt(DNI.getText());
					int b = a%23;
					String alfabeto = "TRWAGMYFPDXBNJZSQVHLCKE";
					valor.setText(DNI.getText() + alfabeto.charAt(b));
					ErrorCliente.setText("");
				} else {
					ErrorCliente.setText(idioma.getProperty("error17"));
					VentanaPrincipal.logger.warning("El dni no es correcto en VentanaCliente");
				
				}
			}
		});
		JPanel cp2 = new JPanel();
		DefaultMutableTreeNode Adoptado = new DefaultMutableTreeNode(idioma.getProperty("a1"));
		DefaultTreeModel modelo = new DefaultTreeModel(Adoptado);
		tr1 = new JTree(modelo);
		cp2.setLayout(new GridLayout(2,1));
		HashMap<Cliente, ArrayList<Animal>> hs1= VentanaCliente.hmadoptados(gestorV);
		for (Cliente c : hs1.keySet()) {
			DefaultMutableTreeNode adop = new DefaultMutableTreeNode(c.getNombre());
			if (hs1.get(c).size() != 0) {
			adop.add(new DefaultMutableTreeNode(hs1.get(c)));
			}
			Adoptado.add(adop);
		}
		cp2.add(tr1);
		DefaultTreeCellRenderer a = new DefaultTreeCellRenderer() {
			private static final long serialVersionUID = 1L;
			public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus) {
				JLabel label = new JLabel(value.toString());
				if (selected) {
					label.setForeground(Color.BLUE);
				}
				if(expanded && !selected) {
					label.setForeground(Color.GRAY);
				}
				if(leaf && !selected) {
					label.setForeground(Color.CYAN);
					for (Cliente t : hs1.keySet()) {
						if (value.toString().equals(t.getNombre()) && hs1.get(t).size() == 0) {
							if(t.isPermiso()) {
							label.setForeground(Color.YELLOW);
					}else {
						label.setForeground(Color.RED);
					}
						}
				}
					}
				setOpaque(false);
				
				return label;
			}
		};
		
		tr1.setCellRenderer(a);
		
		pestaña.addTab(idioma.getProperty("Adoptar"), cp2);	
		
		JPanel cp3 = new JPanel();
		DefaultMutableTreeNode Acogido = new DefaultMutableTreeNode(idioma.getProperty("a2"));
		DefaultTreeModel modelo1 = new DefaultTreeModel(Acogido);
		HashMap<Cliente, ArrayList<Animal>> hs2= VentanaCliente.hmacogidos(gestorV);
		tr2 = new JTree(modelo1);
		cp3.setLayout(new GridLayout(2,1));
		cp3.add(tr2);
		pestaña.addTab(idioma.getProperty("Acoger"), cp3);
		for(Cliente cl: hs2.keySet()) {
			DefaultMutableTreeNode acog = new DefaultMutableTreeNode(cl.getNombre());
			if(hs2.get(cl).size() != 0) {
				acog.add(new DefaultMutableTreeNode(hs2.get(cl)));
			}
			Acogido.add(acog);
		}
		DefaultTreeCellRenderer b = new DefaultTreeCellRenderer() {
			private static final long serialVersionUID = 1L;
			public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus) {
				JLabel label = new JLabel(value.toString());
				if (selected) {
					label.setForeground(Color.BLUE);
				}
				if(expanded && !selected) {
					label.setForeground(Color.GRAY);
				}
				if(leaf && !selected) {
					label.setForeground(Color.CYAN);
					for (Cliente t : hs2.keySet()) {
						if (value.toString().equals(t.getNombre()) && hs2.get(t).size() == 0) {
							if(t.isPermiso()) {
							label.setForeground(Color.YELLOW);
					}else {
						label.setForeground(Color.RED);
					}
						}
				}
					}
				setOpaque(false);
				
				return label;
			}
		};
		
		tr2.setCellRenderer(a);
		
		pestaña.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(pestaña.getSelectedIndex() == 0) {
					setSize(400,200);
				}else if (pestaña.getSelectedIndex() == 1) {
					setSize(400,300);
				} else if (pestaña.getSelectedIndex() == 2) {
					setSize(400,300);
				}
			}
		});

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(400,200);
		this.setTitle(idioma.getProperty("introducir"));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("images/image.jpg").getImage());
		Buscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String res = VentanaCliente.encontrarCliente(valor.getText(), gestorV, idioma);
			if (res.equals("Se ha encontrado el cliente")) {
			v3 = new VentanaAcoger(gestorV, idioma, valor.getText());
			VentanaPrincipal.logger.log(Level.INFO,"Cliente encontrado se abre la ventana acoger");
			setVisible(false);
			} else if (res.equals("El cliente no existe")) {
			v2 = new VentanaIntroducirCliente(valor.getText(),gestorV,idioma);
			VentanaPrincipal.logger.log(Level.INFO, "Cliente desconocido se abre la ventana introducir cliente");
			setVisible(false);
			} else {
			ErrorCliente.setText(res);
			VentanaPrincipal.logger.log(Level.WARNING,"DNI no valido o persona sin permiso para acoger/adoptar en VentanaCliente");
			}
			}
			});
			Thread hilo = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					int b = 0;
					boolean cambio = true;
					while(isVisible()) {
						if(!(b == 255) && cambio) {
							b++;
						}else if(b == 255) {
							b--;
							cambio = false;
						}else if (b==0) {
							b++;
							cambio = true;
						}else {
							b--;
						}
						Color color = new Color(0,0,b);
						ErrorCliente.setForeground(color);
						dni.setForeground(color);
						valor.setForeground(color);
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							VentanaPrincipal.logger.log(Level.WARNING , "El hilo de ventana cliente no ha podido ejecutar la instruccion sleep");
						}
					}
				}
			});
			hilo.start();
			
			KeyListener keyListener = new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						Buscar.doClick();
					}
					if (e.isAltDown()) {
						gestorV.actualizarCliente(valor.getText(), 1);
					}
				}
			};
			this.Buscar.addKeyListener(keyListener);
			this.DNI.addKeyListener(keyListener);
			this.valor.addKeyListener(keyListener);
			this.addWindowListener(new WindowAdapter() {
				
				
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					VentanaPrincipal.finLogger();
					System.exit(0);
				}
			});
			}
			public static String encontrarCliente(String DNIA, GestorBD gestorV, Properties idioma) {
			String resultado = "";
			ArrayList<Cliente> clientes = (ArrayList<Cliente>) gestorV.obtenerDatosCliente();
			for (Cliente cliente : clientes) {
			if (cliente.getDni().equals(DNIA)) {
			if (cliente.isPermiso()) {
			resultado = "Se ha encontrado el cliente";
			} else {
			resultado = idioma.get("mes3").toString();
			}
			break;
			} else {
				if (DNIA.length() == 9) {
			resultado = "El cliente no existe";
			} else {
				resultado = idioma.getProperty("mes9");
			}
			}
			}
			return resultado;
			}
			public static HashMap<Cliente, ArrayList<Animal>> hmadoptados(GestorBD gestorV){
				HashMap<Cliente, ArrayList<Animal>> ret= new HashMap<Cliente, ArrayList<Animal>>() ;
				ArrayList<Cliente> alcliente = (ArrayList<Cliente>) gestorV.obtenerDatosCliente();
				for(Cliente u: alcliente ) {
					ret.put(u, u.getAnimalesAdoptados());	
				}
				return ret;
				
			}
			public static HashMap<Cliente, ArrayList<Animal>> hmacogidos(GestorBD gestorV){
				HashMap<Cliente, ArrayList<Animal>> ret= new HashMap<Cliente, ArrayList<Animal>>() ;
				ArrayList<Cliente> alcliente = (ArrayList<Cliente>) gestorV.obtenerDatosCliente();
				for(Cliente u: alcliente ) {
					ret.put(u, u.getAnimalesAcogidos());	
				}
				return ret;
				
			}
			
			}
