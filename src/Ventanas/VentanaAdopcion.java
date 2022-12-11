package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

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
	protected JButton botonAtras;
	protected VentanaAcoger v2;
	
		
	public VentanaAdopcion(Properties p, GestorBD b, String dni) {
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, 3));
		
		JPanel acogidos = new JPanel(new BorderLayout());
		JPanel botonesCentro = new JPanel(new GridLayout(4,1));
		JPanel adoptados = new JPanel(new BorderLayout());
		
		modeloAcogido = new DefaultListModel<Animal>();
		Cliente cliente = cargarCliente(dni, b);
		modeloAcogido.addAll(cliente.getAnimalesAcogidos());
		
		
		
		listaAcogido = new JList(modeloAcogido);
		listaAcogido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollAcogidos = new JScrollPane(listaAcogido);
		
		botonAtras = new JButton("<-Volver");
		
		acogidos.add(new JLabel("Animales acogidos:"), BorderLayout.NORTH);
		acogidos.add(scrollAcogidos, BorderLayout.CENTER);
		acogidos.add(botonAtras, BorderLayout.SOUTH);
		
		botonAdoptar = new JButton("Adoptar->");
		botonDevolver= new JButton("<-Devolver");
		
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
				if(!(listaAcogido.isSelectionEmpty())) {
					for (int i = 0; i< modeloAcogido.size(); i++) {
						if(!(listaAcogido.getSelectedValue().equals(modeloAcogido.get(i)))) {
						listaAdo.add(modeloAcogido.get(i));
						}
					}
					b.actualizarAnimal(listaAcogido.getSelectedValue(), "noAcogido","noAdoptado");
					modeloAcogido.removeAllElements();
					modeloAcogido.addAll(listaAdo);
				} else if(!(listaAdoptado.isSelectionEmpty())) {
					for (int i = 0; i< modeloAdoptado.size(); i++) {
						if(!(listaAdoptado.getSelectedValue().equals(modeloAdoptado.get(i)))) {
						listaAdoSi.add(modeloAdoptado.get(i));
						}
					}
					b.actualizarAnimal(listaAdoptado.getSelectedValue(), "noAcogido" , "noAdoptado");
					modeloAdoptado.removeAllElements();
					modeloAdoptado.addAll(listaAdoSi);
				}
			}
		});
		
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				v2 = new VentanaAcoger(b, p, dni);
				setVisible(false);
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

		adoptados.add(new JLabel("Animales adoptados:"), BorderLayout.NORTH);
		adoptados.add(scrollAdoptado, BorderLayout.CENTER);	
		
		
		cp.add(acogidos);
		cp.add(botonesCentro);
		cp.add(adoptados);
		
		this.setTitle("Adopción");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void recargarModelos() {
		modeloAcogido.removeAllElements();
		
		modeloAdoptado.removeAllElements();
	}
	
	public Cliente cargarCliente(String dni, GestorBD bd) {
		
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
