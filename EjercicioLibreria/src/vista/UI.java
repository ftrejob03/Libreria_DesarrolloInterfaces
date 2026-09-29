package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JTextField txISBN;
	protected JTextField txTitulo;
	protected JTextField txAutor;
	protected JTextField txEditorial;
	protected JTextField txPrecio;
	
	protected JRadioButton rdbtnCartone;
	protected JRadioButton rdbtnRustica;
	protected JRadioButton rdbtnGrapada;
	protected JRadioButton rdbtnEspital;
	
	protected JRadioButton rdbtnRedicion;
	protected JRadioButton rdbtnNovedad;
	
	protected ButtonGroup grupoFormato;
	protected ButtonGroup grupoEstado;

	protected JLabel lbImagenLibro;

	protected JButton btConsultar;
	protected JButton btGuardar;
	protected JButton btBorrar;
	protected JButton btModificar;
	protected JButton btIniciar;
	protected JButton btSalir;
	protected JTable tablaLibros;

	public UI() {
		setAutoRequestFocus(false);
		setEnabled(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 530);
		
		JPanel contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// --- CABECERA ---
		JPanel p_superior = new JPanel();
		p_superior.setBackground(new Color(128, 255, 0));
		contentPane.add(p_superior, BorderLayout.NORTH);
		
		JLabel LBSuperior = new JLabel("LIBRERIA");
		LBSuperior.setForeground(Color.WHITE);
		LBSuperior.setFont(new Font("Tahoma", Font.BOLD, 20));
		p_superior.add(LBSuperior);
		
		// --- BOTONERA INFERIOR ---
		JPanel p_inferior = new JPanel();
		p_inferior.setBackground(new Color(255, 105, 180));
		contentPane.add(p_inferior, BorderLayout.SOUTH);
		
		btConsultar = new JButton("CONSULTAR");
		p_inferior.add(btConsultar);

		btGuardar = new JButton("GUARDAR");
		p_inferior.add(btGuardar);
		
		btBorrar = new JButton("BORRAR");
		p_inferior.add(btBorrar);
		
		btModificar = new JButton("MODIFICAR");
		p_inferior.add(btModificar);
		
		btIniciar = new JButton("INICIAR");
		p_inferior.add(btIniciar);
		
		btSalir = new JButton("SALIR");		
		p_inferior.add(btSalir);
		
		// --- PESTAÑAS ---
		JTabbedPane tbPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tbPane, BorderLayout.CENTER);
		
		JPanel LIBRO = new JPanel(new GridBagLayout());
		LIBRO.setBackground(new Color(250, 250, 210));
		tbPane.addTab("LIBRO", null, LIBRO, null);
		
		Dimension tamCaja = new Dimension(160, 24);

		// --- FILA 0: ISBN ---
		JLabel lbISBN = new JLabel("ISBN:");
		GridBagConstraints gbc_lbISBN = new GridBagConstraints();
		gbc_lbISBN.anchor = GridBagConstraints.WEST;
		gbc_lbISBN.insets = new Insets(10, 15, 5, 5);
		gbc_lbISBN.gridx = 0; gbc_lbISBN.gridy = 0;
		LIBRO.add(lbISBN, gbc_lbISBN);

		txISBN = new JTextField();
		txISBN.setPreferredSize(tamCaja);
		GridBagConstraints gbc_txISBN = new GridBagConstraints();
		gbc_txISBN.anchor = GridBagConstraints.WEST;
		gbc_txISBN.insets = new Insets(10, 5, 5, 15);
		gbc_txISBN.gridx = 1; gbc_txISBN.gridy = 0;
		LIBRO.add(txISBN, gbc_txISBN);

		// --- FILA 1: TITULO ---
		JLabel lblTitulo = new JLabel("Titulo:");
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.WEST;
		gbc_lblTitulo.insets = new Insets(5, 15, 5, 5);
		gbc_lblTitulo.gridx = 0; gbc_lblTitulo.gridy = 1;
		LIBRO.add(lblTitulo, gbc_lblTitulo);

		txTitulo = new JTextField();
		txTitulo.setPreferredSize(tamCaja);
		GridBagConstraints gbc_txTitulo = new GridBagConstraints();
		gbc_txTitulo.anchor = GridBagConstraints.WEST;
		gbc_txTitulo.insets = new Insets(5, 5, 5, 15);
		gbc_txTitulo.gridx = 1; gbc_txTitulo.gridy = 1;
		LIBRO.add(txTitulo, gbc_txTitulo);

		// --- FILA 2: AUTOR ---
		JLabel lbAutor = new JLabel("Autor:");
		GridBagConstraints gbc_lbAutor = new GridBagConstraints();
		gbc_lbAutor.anchor = GridBagConstraints.WEST;
		gbc_lbAutor.insets = new Insets(5, 15, 5, 5);
		gbc_lbAutor.gridx = 0; gbc_lbAutor.gridy = 2;
		LIBRO.add(lbAutor, gbc_lbAutor);

		txAutor = new JTextField();
		txAutor.setPreferredSize(tamCaja);
		GridBagConstraints gbc_txAutor = new GridBagConstraints();
		gbc_txAutor.anchor = GridBagConstraints.WEST;
		gbc_txAutor.insets = new Insets(5, 5, 5, 15);
		gbc_txAutor.gridx = 1; gbc_txAutor.gridy = 2;
		LIBRO.add(txAutor, gbc_txAutor);

		// --- FILA 3: EDITORIAL ---
		JLabel lbEditorial = new JLabel("Editorial:");
		GridBagConstraints gbc_lbEditorial = new GridBagConstraints();
		gbc_lbEditorial.anchor = GridBagConstraints.WEST;
		gbc_lbEditorial.insets = new Insets(5, 15, 5, 5);
		gbc_lbEditorial.gridx = 0; gbc_lbEditorial.gridy = 3;
		LIBRO.add(lbEditorial, gbc_lbEditorial);

		txEditorial = new JTextField();
		txEditorial.setPreferredSize(tamCaja);
		GridBagConstraints gbc_txEditorial = new GridBagConstraints();
		gbc_txEditorial.anchor = GridBagConstraints.WEST;
		gbc_txEditorial.insets = new Insets(5, 5, 5, 15);
		gbc_txEditorial.gridx = 1; gbc_txEditorial.gridy = 3;
		LIBRO.add(txEditorial, gbc_txEditorial);

		// --- FILA 4: PRECIO ---
		JLabel lbPrecio = new JLabel("Precio:");
		GridBagConstraints gbc_lbPrecio = new GridBagConstraints();
		gbc_lbPrecio.anchor = GridBagConstraints.WEST;
		gbc_lbPrecio.insets = new Insets(5, 15, 10, 5);
		gbc_lbPrecio.gridx = 0; gbc_lbPrecio.gridy = 4;
		LIBRO.add(lbPrecio, gbc_lbPrecio);

		txPrecio = new JTextField();
		txPrecio.setPreferredSize(tamCaja);
		GridBagConstraints gbc_txPrecio = new GridBagConstraints();
		gbc_txPrecio.anchor = GridBagConstraints.WEST;
		gbc_txPrecio.insets = new Insets(5, 5, 10, 15);
		gbc_txPrecio.gridx = 1; gbc_txPrecio.gridy = 4;
		LIBRO.add(txPrecio, gbc_txPrecio);

		// --- COLUMNA 2: IMAGEN (Filas 0 a 4) ---
		lbImagenLibro = new JLabel();
		lbImagenLibro.setHorizontalAlignment(SwingConstants.CENTER);
		
		URL urlImagen = UI.class.getResource("/vista/Libreria.png");
		if (urlImagen != null) {
			ImageIcon originalIcon = new ImageIcon(urlImagen);
			Image imgEscalada = originalIcon.getImage().getScaledInstance(180, 160, Image.SCALE_SMOOTH);
			lbImagenLibro.setIcon(new ImageIcon(imgEscalada));
		}

		GridBagConstraints gbc_img = new GridBagConstraints();
		gbc_img.gridx = 2; gbc_img.gridy = 0;
		gbc_img.gridheight = 5; // Abarca 5 filas de alto
		gbc_img.fill = GridBagConstraints.BOTH;
		gbc_img.weightx = 1.0;
		gbc_img.insets = new Insets(10, 10, 10, 15);
		LIBRO.add(lbImagenLibro, gbc_img);

		// --- FILA 5: FORMATO ---
		JLabel lbFormato = new JLabel("Formato:");
		GridBagConstraints gbc_lbFormato = new GridBagConstraints();
		gbc_lbFormato.anchor = GridBagConstraints.WEST;
		gbc_lbFormato.insets = new Insets(10, 15, 5, 5);
		gbc_lbFormato.gridx = 0; gbc_lbFormato.gridy = 5;
		LIBRO.add(lbFormato, gbc_lbFormato);

		JPanel panelFormato = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
		panelFormato.setBackground(new Color(250, 250, 210));
		panelFormato.setBorder(new LineBorder(new Color(255, 165, 0), 1));

		rdbtnCartone = new JRadioButton("Cartoné");
		rdbtnCartone.setBackground(new Color(250, 250, 210));
		rdbtnRustica = new JRadioButton("Rústica");
		rdbtnRustica.setBackground(new Color(250, 250, 210));
		rdbtnGrapada = new JRadioButton("Grapada");
		rdbtnGrapada.setBackground(new Color(250, 250, 210));
		rdbtnEspital = new JRadioButton("Espiral");
		rdbtnEspital.setBackground(new Color(250, 250, 210));

		panelFormato.add(rdbtnCartone);
		panelFormato.add(rdbtnRustica);
		panelFormato.add(rdbtnGrapada);
		panelFormato.add(rdbtnEspital);

		grupoFormato = new ButtonGroup();
		grupoFormato.add(rdbtnCartone);
		grupoFormato.add(rdbtnRustica);
		grupoFormato.add(rdbtnGrapada);
		grupoFormato.add(rdbtnEspital);

		GridBagConstraints gbc_panelFormato = new GridBagConstraints();
		gbc_panelFormato.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelFormato.insets = new Insets(10, 5, 5, 15);
		gbc_panelFormato.gridx = 1; gbc_panelFormato.gridy = 5;
		gbc_panelFormato.gridwidth = 2; // Abarca las columnas de texto e imagen
		LIBRO.add(panelFormato, gbc_panelFormato);

		// --- FILA 6: ESTADO ---
		JLabel lbEstado = new JLabel("Estado:");
		GridBagConstraints gbc_lbEstado = new GridBagConstraints();
		gbc_lbEstado.anchor = GridBagConstraints.WEST;
		gbc_lbEstado.insets = new Insets(5, 15, 10, 5);
		gbc_lbEstado.gridx = 0; gbc_lbEstado.gridy = 6;
		LIBRO.add(lbEstado, gbc_lbEstado);

		JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
		panelEstado.setBackground(new Color(250, 250, 210));
		panelEstado.setBorder(new LineBorder(new Color(255, 165, 0), 1));

		rdbtnRedicion = new JRadioButton("Reedición");
		rdbtnRedicion.setBackground(new Color(250, 250, 210));
		rdbtnNovedad = new JRadioButton("Novedad");
		rdbtnNovedad.setBackground(new Color(250, 250, 210));

		panelEstado.add(rdbtnRedicion);
		panelEstado.add(rdbtnNovedad);

		grupoEstado = new ButtonGroup();
		grupoEstado.add(rdbtnRedicion);
		grupoEstado.add(rdbtnNovedad);

		GridBagConstraints gbc_panelEstado = new GridBagConstraints();
		gbc_panelEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEstado.insets = new Insets(5, 5, 10, 15);
		gbc_panelEstado.gridx = 1; gbc_panelEstado.gridy = 6;
		gbc_panelEstado.gridwidth = 2; // Abarca las columnas de texto e imagen
		LIBRO.add(panelEstado, gbc_panelEstado);

		LIBRO.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txISBN, txTitulo, txAutor, txEditorial, txPrecio}));
		
		// --- PESTAÑA LISTA DE LIBROS ---
		JPanel ESTANTERIA = new JPanel(new BorderLayout());
		ESTANTERIA.setBackground(new Color(250, 250, 210));
		tbPane.addTab("LISTA DE LIBROS", null, ESTANTERIA, null);
		
		tablaLibros = new JTable();
		tablaLibros.setRowHeight(32);
		tablaLibros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaLibros.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		JScrollPane scrollPane = new JScrollPane(tablaLibros);
		ESTANTERIA.add(scrollPane, BorderLayout.CENTER);
	}
}