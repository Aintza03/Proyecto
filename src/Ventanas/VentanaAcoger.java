package Ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class VentanaAcoger extends JFrame{
	
	private JTable tablaAnimales;
	private DefaultTableModel modeloDatosAnimales;
	
	private int mouseRow = -1;
	private int mouseCol = -1;
	
	public VentanaAcoger() {

		this.initTable();
		
		//La tabla de comics se inserta en un panel con scroll
		JScrollPane scrollPaneComics = new JScrollPane(this.tablaAnimales);
		scrollPaneComics.setBorder(new TitledBorder("Comics"));
		this.tablaAnimales.setFillsViewportHeight(true);
		
		
		this.setTitle("Ventana Acoger");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
		DefaultTableCellRenderer editorialRenderer = new DefaultTableCellRenderer() {
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
		
	}
}

