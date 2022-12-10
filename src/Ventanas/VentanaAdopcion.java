package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import General.Animal;


public class VentanaAdopcion extends JFrame {
	protected DefaultListModel<Animal> modeloAcogido;
	protected JList<Animal> listaAcogido;
	protected DefaultListModel<Animal> modeloAdoptado;
	protected JList<Animal> listaAdoptado;
	protected JButton botonAdoptar;
	protected JButton botonDevolver;
	protected JButton botonAtras;
		
	public VentanaAdopcion() {
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(1, 3));
		
		JPanel acogidos = new JPanel(new BorderLayout());
		JPanel botonesCentro = new JPanel(new BorderLayout());
		JPanel adoptados = new JPanel(new BorderLayout());
		
		modeloAcogido = new DefaultListModel<Animal>();
		listaAcogido = new JList(modeloAcogido);
		listaAcogido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollAcogidos = new JScrollPane(listaAcogido);
		
		botonAtras = new JButton("<--");
		
		
		acogidos.add(new JLabel("Acogidos:"), BorderLayout.NORTH);
		acogidos.add(scrollAcogidos, BorderLayout.CENTER);
		acogidos.add(botonAtras, BorderLayout.SOUTH);
		
		
		
		botonAdoptar = new JButton("Adoptar");
		botonDevolver= new JButton("Devolver");
		
		botonAdoptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});

		botonDevolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		

		botonesCentro.add(botonAdoptar, BorderLayout.WEST);
		botonesCentro.add(botonDevolver, BorderLayout.EAST);
		
		modeloAdoptado = new DefaultListModel<Animal>();
		listaAdoptado = new JList(modeloAdoptado);
		listaAcogido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollAdoptado = new JScrollPane(listaAdoptado);

		adoptados.add(new JLabel("Animales adoptados;"), BorderLayout.NORTH);
		adoptados.add(scrollAdoptado, BorderLayout.CENTER);	
		
		
		cp.add(acogidos);
		cp.add(botonesCentro);
		cp.add(adoptados);
		
		this.setTitle("Adopcion");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
	}
	
	public void recargarModelos() {
		modeloAcogido.removeAllElements();
		
		modeloAdoptado.removeAllElements();
		
		
	}

	
}
