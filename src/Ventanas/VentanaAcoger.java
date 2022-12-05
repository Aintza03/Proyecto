package Ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import General.Animal;


public class VentanaAcoger extends JFrame{
	
	private List<Animal> animales;
	
	private JTable tablaAnimales;
	private DefaultTableModel modeloDatosAnimales;
	
	private int mouseRow = -1;
	private int mouseCol = -1;
	
	public VentanaAcoger(List<Animal> animales) {
		this.animales = animales;
		
		Container cp = this.getContentPane();
		
		this.initTable();
		this.loadAnimal();
		
		//La tabla de comics se inserta en un panel con scroll
		JScrollPane scrollPaneAnimales = new JScrollPane(this.tablaAnimales);
		scrollPaneAnimales.setBorder(new TitledBorder("Acoger"));
		this.tablaAnimales.setFillsViewportHeight(true);
		
		cp.add(scrollPaneAnimales);
		this.setTitle("Ventana Acoger");		
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		
	}
	
	private void initTable() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraAnimales = new Vector<String>(Arrays.asList( "ID", "TIPO", "FECHA_NAC", "ESPECIAL", "RAZA", "DNI_AC", "DNI_AD"));				
		//Se crea el modelo de datos para la tabla de comics sÃ³lo con la cabecera		
		this.modeloDatosAnimales = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAnimales);
		//Se crea la tabla de comics con el modelo de datos		
		this.tablaAnimales = new JTable(this.modeloDatosAnimales);
		
		//Render para las celdas de la Editorial se define como un Label un logo
		DefaultTableCellRenderer a = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = new JLabel();
	
				//El label se alinea a la izquierda
				label.setHorizontalAlignment(JLabel.LEFT);
						
				//Se diferencia el color de fondo en filas pares e impares
				if (row % 2 == 0) {
					label.setBackground(new Color(224, 224, 224));
				} else {
					label.setBackground(Color.WHITE);
				}
				
				//Si la celda estÃ¡ seleccionada se asocia un color de fondo y letra
				if (mouseRow == row && mouseCol == column) {
					label.setBackground(Color.PINK);
					label.setForeground(Color.WHITE);
				}
				
				//Si la celda estÃ¡ seleccionada se asocia un color de fondo y letra
				if (isSelected) {
					label.setBackground(table.getSelectionBackground());
					label.setForeground(table.getSelectionForeground());
				}

				//Es necesaria esta sentencia para pintar correctamente el color de fondo
				label.setOpaque(true);
				
				return label;
			}
		};
		
		this.tablaAnimales.addMouseListener(new MouseAdapter() {						
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				System.out.println(String.format("Se ha pulsado el botÃ³n %d en la fila %d, columna %d", e.getButton(), row, col));
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());

				System.out.println(String.format("Se ha liverado el botÃ³n %d en la fila %d, columna %d", e.getButton(), row, col));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tablaAnimales.rowAtPoint(e.getPoint());
				int col = tablaAnimales.columnAtPoint(e.getPoint());
				
				System.out.println(String.format("Se ha hecho click con el botÃ³n %d en la fila %d, columna %d", e.getButton(), row, col));
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
		
	}
	
	// Corregir el metodo loadAnimal la lista que utiliza tiene que ser la misma la cual se usa para obtener los datos de la BD con el metodo ObtenerDatosAnimal
	// el problema es que devuelve una lista de 
	
	private void loadAnimal() {
		//Se borran los datos del modelo de datos
		this.modeloDatosAnimales.setRowCount(0);
		
		//Se aÃ±ade al modelo una fila de datos por cada comic
		for (Animal a : this.animales) {
			this.modeloDatosAnimales.addRow( new Object[] {a.getId(), a.getTipo(), a.getFechaNac(), a.getEspecial(), a.getRaza()} );
		}		
	}
}

