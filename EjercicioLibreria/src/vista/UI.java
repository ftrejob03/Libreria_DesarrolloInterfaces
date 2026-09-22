package vista;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.nio.channels.GatheringByteChannel;

import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class UI extends JFrame{

	private static final long serialVersionUID = 1L;
	protected JTextField txISBN;
	protected JTextField txTitulo;
	protected JTextField txAutor;
	protected JTextField txEditorial;
	protected JTextField txPrecio;
	protected JButton btConsultar;
	protected JButton btGuardar;
	protected JButton btBorrar;
	protected JButton btIniciar;
	protected JButton btSalir;
	protected JTable tablaLibros;

	
	public UI() {
		setAutoRequestFocus(false);
		setEnabled(true); // Recordar revisar esta línea en caso de estar bloqueado la pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		JPanel contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel p_superior = new JPanel();
		p_superior.setBackground(new Color(128, 255, 0));
		contentPane.add(p_superior, BorderLayout.NORTH);
		
		JLabel LBSuperior = new JLabel("LIBRERIA");
		LBSuperior.setBackground(new Color(128, 255, 0));
		LBSuperior.setForeground(new Color(0, 64, 0));
		LBSuperior.setFont(new Font("Tahoma", Font.PLAIN, 18));
		p_superior.add(LBSuperior);
		
		JPanel p_inferior = new JPanel();
		p_inferior.setBackground(new Color(255, 128, 128));
		contentPane.add(p_inferior, BorderLayout.SOUTH);
		
		btConsultar = new JButton("CONSULTAR");
		p_inferior.add(btConsultar);
		
		btGuardar = new JButton("GUARDAR");
		p_inferior.add(btGuardar);
		
		btBorrar = new JButton("BORRAR");
		p_inferior.add(btBorrar);
		
		btIniciar = new JButton("INICIAR");
		p_inferior.add(btIniciar);
		
		btSalir = new JButton("SALIR");		
		p_inferior.add(btSalir);
		
		JTabbedPane tbPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tbPane, BorderLayout.CENTER);
		
		JPanel LIBRO = new JPanel(new GridBagLayout());
		LIBRO.setBackground(new Color(255, 255, 128));
		LIBRO.setToolTipText("LIBRO");
		tbPane.addTab("LIBRO", null, LIBRO, null);
		
		// --- FILA 0: ISBN ---
		JLabel lbISBN = new JLabel("ISBN");
		GridBagConstraints gbc_lbISBN = new GridBagConstraints();
		gbc_lbISBN.insets = new Insets(5, 5, 5, 5);
		gbc_lbISBN.anchor = GridBagConstraints.WEST;
		gbc_lbISBN.gridx = 0;
		gbc_lbISBN.gridy = 0;
		LIBRO.add(lbISBN, gbc_lbISBN);

		txISBN = new JTextField();
		GridBagConstraints gbc_txISBN = new GridBagConstraints();
		gbc_txISBN.insets = new Insets(5, 5, 5, 5);
		gbc_txISBN.fill = GridBagConstraints.HORIZONTAL;
		gbc_txISBN.weightx = 1.0;
		gbc_txISBN.gridx = 1;
		gbc_txISBN.gridy = 0;
		LIBRO.add(txISBN, gbc_txISBN);

		// --- FILA 1: TÍTULO ---
		JLabel lblTitulo = new JLabel("Titulo");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(5, 5, 5, 5);
		gbc_lblTitulo.anchor = GridBagConstraints.WEST;
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		LIBRO.add(lblTitulo, gbc_lblTitulo);

		txTitulo = new JTextField();
		GridBagConstraints gbc_txTitulo = new GridBagConstraints();
		gbc_txTitulo.insets = new Insets(5, 5, 5, 5);
		gbc_txTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txTitulo.weightx = 1.0;
		gbc_txTitulo.gridx = 1;
		gbc_txTitulo.gridy = 1;
		LIBRO.add(txTitulo, gbc_txTitulo);

		// --- FILA 2: AUTOR ---
		JLabel lbAutor = new JLabel("Autor");
		GridBagConstraints gbc_lbAutor = new GridBagConstraints();
		gbc_lbAutor.insets = new Insets(5, 5, 5, 5);
		gbc_lbAutor.anchor = GridBagConstraints.WEST;
		gbc_lbAutor.gridx = 0;
		gbc_lbAutor.gridy = 2;
		LIBRO.add(lbAutor, gbc_lbAutor);

		txAutor = new JTextField();
		GridBagConstraints gbc_txAutor = new GridBagConstraints();
		gbc_txAutor.insets = new Insets(5, 5, 5, 5);
		gbc_txAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txAutor.weightx = 1.0;
		gbc_txAutor.gridx = 1;
		gbc_txAutor.gridy = 2;
		LIBRO.add(txAutor, gbc_txAutor);

		// --- FILA 3: EDITORIAL ---
		JLabel lbEditorial = new JLabel("Editorial");
		GridBagConstraints gbc_lbEditorial = new GridBagConstraints();
		gbc_lbEditorial.insets = new Insets(5, 5, 5, 5);
		gbc_lbEditorial.anchor = GridBagConstraints.WEST;
		gbc_lbEditorial.gridx = 0;
		gbc_lbEditorial.gridy = 3;
		LIBRO.add(lbEditorial, gbc_lbEditorial);

		txEditorial = new JTextField();
		GridBagConstraints gbc_txEditorial = new GridBagConstraints();
		gbc_txEditorial.insets = new Insets(5, 5, 5, 5);
		gbc_txEditorial.fill = GridBagConstraints.HORIZONTAL;
		gbc_txEditorial.weightx = 1.0;
		gbc_txEditorial.gridx = 1;
		gbc_txEditorial.gridy = 3;
		LIBRO.add(txEditorial, gbc_txEditorial);

		// --- FILA 4: PRECIO ---
		JLabel lbPrecio = new JLabel("Precio");
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.insets = new Insets(5, 5, 5, 5);
		gbc_lbPrecio.anchor = GridBagConstraints.WEST;
		gbc_lbPrecio.gridx = 0;
		gbc_lbPrecio.gridy = 4;
		LIBRO.add(lbPrecio, gbc_lbPrecio);

		txPrecio = new JTextField();
		GridBagConstraints gbc_txPrecio = new GridBagConstraints();
		gbc_txPrecio.insets = new Insets(5, 5, 5, 5);
		gbc_txPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txPrecio.weightx = 1.0;
		gbc_txPrecio.gridx = 1;
		gbc_txPrecio.gridy = 4;
		LIBRO.add(txPrecio, gbc_txPrecio);
		
		LIBRO.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txISBN, txTitulo, txAutor, txEditorial, txPrecio}));
		
		JPanel ESTANTERIA = new JPanel(new BorderLayout());
		tbPane.addTab("ESTANTERIA", null, ESTANTERIA, null);
		
		tablaLibros = new JTable();
		JScrollPane scrollPane = new JScrollPane(tablaLibros);
		ESTANTERIA.add(scrollPane, BorderLayout.CENTER);

	}

}
