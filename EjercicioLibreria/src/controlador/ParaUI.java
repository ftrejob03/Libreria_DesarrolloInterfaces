package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.Estanteria;
import modelo.Libro;
import utiles.Validaciones;
import vista.UI;

public class ParaUI extends UI {
	private static final String TITULO_EXITO = "Éxito";
	private static final String TITULO_AVISO = "Aviso";
	
	private static final Color COLOR_ERROR = Color.RED;
	private static final Color COLOR_EXITO = new Color(0, 150, 0); // Verde oscuro para buena legibilidad

	private Estanteria estanteria = new Estanteria();
	private boolean iniciado = false;

	public ParaUI() {
		super();
		
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarLibro();
			}
		});

		btGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarLibro();
			}
		});

		btBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarLibro();
			}
		});

		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarLibro();
			}
		});

		btIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarEstanteria();
			}
		});

		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (confirmarAccion("¿Deseas cerrar la aplicación?", "Salir")) {
					dispose();
				}
			}
		});
		
		btAceptarReponer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btAceptarVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		tbPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				
			}
		});
	}
	
	// MÉTODOS DE LOS BOTONES
	private void iniciarEstanteria() {
		if (iniciado) {
			mostrarAviso("La estantería ya se encuentra inicializada.");
			return;
		}
		
		// Recordar poner 3 atributos más de las que le faltan
		estanteria.anadirLibros(new Libro("9788420471839", "Clean Code", "Robert C. Martin", "Pearson Prentice Hall", "15.95", "Cartoné", "Reedición", 5));
		estanteria.anadirLibros(new Libro("9788439736966", "Refactoring", "Martin Fowler", "Addison-Wesley", "12.50", "Rústica", "Novedad", 3));
		estanteria.anadirLibros(new Libro("9788420684093", "El lenguaje de programación C", "Brian Kernighan y Dennis Ritchie", "Pearson Prentice Hall", "18.00", "Espiral", "Reedición", 8));
		estanteria.anadirLibros(new Libro("9788437604947", "Redes de computadoras", "Andrew Tanenbaum", "Pearson Prentice Hall", "14.25", "Cartoné", "Reedición", 2));
		estanteria.anadirLibros(new Libro("9788497593083", "Organización y diseño de computadoras", "David Patterson y John Hennessy", "McGraw Hill", "9.95", "Cartoné", "Reedición", 2));
		estanteria.anadirLibros(new Libro("9788478887194", "Fundamentos de sistemas de bases de datos", "Ramez Elmasri y Shamkant Navathe", "Addison-Wesley", "8.50", "Grapada", "Novedad", 10));
		estanteria.anadirLibros(new Libro("9788497594257", "Sistemas Operativos", "William Stallings", "Pearson Prentice Hall", "10.95", "Rústica", "Reedición", 7));

		estanteria.rellenarTabla(tablaLibros);
		iniciado = true;
		limpiarCampos();
		mostrarInfo("Estantería inicializada.");
	}

	private void guardarLibro() {
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}
		
		if (estanteria.getEstanteria().size() >= 10) {
			mostrarAviso("Está lleno con 10 libros, no se puede llenar más.");
			return;
		}

		// Validamos todos los campos y mostramos sus estados visuales
		if (validarCampos(false)) {
			String ISBN = txISBN.getText().trim();
			String titulo = txTitulo.getText().trim();
			String autor = txAutor.getText().trim();
			String editorial = txEditorial.getText().trim();
			String precio = txPrecio.getText().trim();
			int unidades = Integer.parseInt(txUnidades.getText().trim());
			String formato = obtenerSeleccionRadioButton(grupoFormato);
			String estado = obtenerSeleccionRadioButton(grupoEstado);

			Libro libro = new Libro(ISBN, titulo, autor, editorial, precio, formato, estado, unidades);
			estanteria.anadirLibros(libro);
			estanteria.rellenarTabla(tablaLibros);

			limpiarCampos();
			mostrarInfo("Libro guardado correctamente.");
		}
	}

	private void borrarLibro() {
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}

		int indice = estanteria.obtenerIdSeleccionado(tablaLibros);
		if (indice == -1) {
			mostrarAviso("Por favor, selecciona un libro en la tabla para borrar.");
			return;
		}

		if (confirmarAccion("¿Deseas borrar este libro?", "Confirmar borrado")) {
			estanteria.borrarLibros(indice);
			estanteria.rellenarTabla(tablaLibros);
			limpiarCampos();
			mostrarInfo("Libro borrado.");
		}
	}

	private void consultarLibro() {
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}

		int indice = estanteria.obtenerIdSeleccionado(tablaLibros);
		if (indice == -1) {
			mostrarAviso("Por favor, selecciona un libro en la tabla para consultar.");
			return;
		}

		Libro libro = estanteria.getEstanteria().get(indice);
		txISBN.setText(libro.getISBN());
		txTitulo.setText(libro.getTitulo());
		txAutor.setText(libro.getAutor());
		txEditorial.setText(libro.getEditorial());
		txPrecio.setText(libro.getPrecio());
		txUnidades.setText(String.valueOf(libro.getUnidades()));
		

		// Marcamos todos como válidos (verde) ya que vienen de la estantería
		marcarExito(txISBN, lbErrorISBN, "Cargado");
		marcarExito(txTitulo, lbErrorTitulo, "Correcto");
		marcarExito(txAutor, lbErrorAutor, "Correcto");
		marcarExito(txEditorial, lbErrorEditorial, "Correcto");
		marcarExito(txPrecio, lbErrorPrecio, "Correcto");

		txISBN.setEnabled(false);
		mostrarInfo("Datos cargados, puedes modificar los campos (excepto ISBN) y pulsar MODIFICAR.");
	}

	private void modificarLibro() {
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}

		int indice = estanteria.obtenerIdSeleccionado(tablaLibros);
		if (indice == -1) {
			mostrarAviso("Por favor, selecciona un libro en la tabla para modificar.");
			return;
		}

		// Validamos campos (pasando true indicamos que es modificación)
		if (validarCampos(true)) {
			String ISBN = txISBN.getText().trim();
			String titulo = txTitulo.getText().trim();
			String autor = txAutor.getText().trim();
			String editorial = txEditorial.getText().trim();
			String precio = txPrecio.getText().trim();
			int unidades = Integer.parseInt(txUnidades.getText().trim());
			String formato = obtenerSeleccionRadioButton(grupoFormato);
			String estado = obtenerSeleccionRadioButton(grupoEstado);

			if (confirmarAccion("¿Deseas guardar los cambios de este libro?", "Confirmar modificación")) {
				Libro libromodificado = new Libro(ISBN, titulo, autor, editorial, precio, formato, estado, unidades);
				estanteria.modificarLibro(indice, libromodificado);
				estanteria.rellenarTabla(tablaLibros);
				limpiarCampos();
				mostrarInfo("Libro modificado correctamente.");
			}
		}
	}

	// LÓGICA PARA PESTAÑA REPONER
	private void ejecutarReposicion() {
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}
		Libro seleccionado = (Libro) cbLibrosReponer.getSelectedItem();
		if (seleccionado == null) {
			mostrarAviso("Selecciona un libro del desplegable.");
			return;
		}
		
		String cantText = txCantidadReponer.getText().trim();
		try {
			int cantidad = Integer.parseInt(cantText);
			if (cantidad <= 0) {
				mostrarAviso("La cantidad a reponer debe ser mayor a 0.");
				return;
			}
			seleccionado.reponer(cantidad);
			estanteria.rellenarTabla(tablaLibros);
			txCantidadReponer.setText("");
			mostrarInfo("Se han añadido"+cantidad+" unidades a: "+seleccionado.getTitulo());
		} catch (NumberFormatException e) {
			mostrarAviso("Introduce un número entero válido.");
		}
	}

	// LÓGICA PARA PESTAÑA VENDER
	private void ejecutarVenta() {
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}
		Libro seleccionado = (Libro) cbLibrosReponer.getSelectedItem();
		if (seleccionado == null) {
			mostrarAviso("Selecciona un libro del desplegable.");
			return;
		}
		String cantText = txCantidadVender.getText().trim();
		try {
			int cantidad = Integer.parseInt(cantText);
			if (cantidad <= 0) {
				mostrarAviso("La cantidad a vender debe ser mayor a 0.");
				return;
			}
			if (seleccionado.vender(cantidad)) {
				estanteria.rellenarTabla(tablaLibros);
				txCantidadReponer.setText("");
				mostrarInfo("Venta realizada: "+cantidad+" unidades de: "+seleccionado.getTitulo());
			} else {
				mostrarAviso("No hay suficiente stock. Stock disponible: "+seleccionado.getTitulo());
			}
		} catch (NumberFormatException e) {
			mostrarAviso("Introduce un número entero válido.");
		}
	}
	
	// MÉTODOS AUXILIARES Y VALIDACIÓN
	private void actualizarDesplegablesLibros() {
		cbLibrosReponer.removeAllItems();
		cbLibrosVender.removeAllItems();
		for (Libro libro : estanteria.getEstanteria()) {
			cbLibrosReponer.addItem(libro);
			cbLibrosVender.addItem(libro);
		}
	}
	
	private boolean validarCampos(boolean esModificacion) {
		boolean valido = true;

		String ISBN = txISBN.getText().trim();
		String titulo = txTitulo.getText().trim();
		String autor = txAutor.getText().trim();
		String editorial = txEditorial.getText().trim();
		String precio = txPrecio.getText().trim();
		String unidades = txUnidades.getText().trim();

		// 1. Validar ISBN (Solo al guardar un nuevo libro)
		if (!esModificacion) {
			if (!Validaciones.validaISBN(ISBN)) {
				marcarError(txISBN, lbErrorISBN, "Debe ser 13 dígitos numéricos");
				valido = false;
			} else if (estanteria.existeISBN(ISBN)) {
				marcarError(txISBN, lbErrorISBN, "El ISBN ya existe");
				valido = false;
			} else {
				marcarExito(txISBN, lbErrorISBN, "ISBN correcto");
			}
		}

		// 2. Validar Título
		if (titulo.isEmpty()) {
			marcarError(txTitulo, lbErrorTitulo, "Campo obligatorio");
			valido = false;
		} else {
			marcarExito(txTitulo, lbErrorTitulo, "Titulo correcto");
		}

		// 3. Validar Autor
		if (autor.isEmpty() || !Validaciones.validaLetters(autor)) {
			marcarError(txAutor, lbErrorAutor, "Solo letras y espacios");
			valido = false;
		} else {
			marcarExito(txAutor, lbErrorAutor, "Autor correcto");
		}

		// 4. Validar Editorial
		if (editorial.isEmpty() || !Validaciones.validaLetters(editorial)) {
			marcarError(txEditorial, lbErrorEditorial, "Solo letras y espacios");
			valido = false;
		} else {
			marcarExito(txEditorial, lbErrorEditorial, "Editorial correcto");
		}

		// 5. Validar Precio
		if (precio.isEmpty() || !Validaciones.isNumberFloat(precio)) {
			marcarError(txPrecio, lbErrorPrecio, "Debe ser un formato ej: 15.95");
			valido = false;
		} else {
			marcarExito(txPrecio, lbErrorPrecio, "Precio correcto");
		}
		
		// 6. Validar Unidades
		try {
			int cantidad = Integer.parseInt(unidades);
			if (cantidad < 0) {
				marcarError(txUnidades, lbErrorUnidades, "Debe ser mayor que 0.");
				valido = false;
			} else {
				marcarExito(txUnidades, lbErrorUnidades, "Correcto.");
			}
		} catch (NumberFormatException e) {
			marcarError(txUnidades, lbErrorUnidades, "Número entero.");
		}
			
		// 7. Validar Formato y Estado
		if (obtenerSeleccionRadioButton(grupoFormato) == null) {
			lbErrorFormato.setText("Selecciona formato");
			valido = false;
		} else {
			lbErrorFormato.setText("");
		}
		if (obtenerSeleccionRadioButton(grupoEstado) == null) {
			lbErrorEstado.setText("Selecciona estado");
			valido = false;
		} else {
			lbErrorEstado.setText("");
		}
		
		return valido;
	}

	private void marcarError(JTextField campo, JLabel lbError, String mensaje) {
		campo.setBorder(new LineBorder(COLOR_ERROR, 2));
		lbError.setForeground(COLOR_ERROR);
		lbError.setText(mensaje);
	}

	private void marcarExito(JTextField campo, JLabel lbError, String mensaje) {
		campo.setBorder(new LineBorder(COLOR_EXITO, 1));
		lbError.setForeground(COLOR_EXITO);
		lbError.setText(mensaje);
	}

	private void limpiarCampos() {
		txISBN.setText("");
		txTitulo.setText("");
		txAutor.setText("");
		txEditorial.setText("");
		txPrecio.setText("");

		if (grupoFormato != null) grupoFormato.clearSelection();
		if (grupoEstado != null) grupoEstado.clearSelection();

		restablecerCampo(txISBN, lbErrorISBN);
		restablecerCampo(txTitulo, lbErrorTitulo);
		restablecerCampo(txAutor, lbErrorAutor);
		restablecerCampo(txEditorial, lbErrorEditorial);
		restablecerCampo(txPrecio, lbErrorPrecio);

		txISBN.setEnabled(true);
		txISBN.requestFocus();
	}

	private void restablecerCampo(JTextField campo, JLabel lbError) {
		campo.setBorder(new JTextField().getBorder());
		campo.setBackground(Color.WHITE);
		lbError.setText("");
	}

	private void mostrarInfo(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, TITULO_EXITO, JOptionPane.INFORMATION_MESSAGE);
	}

	private void mostrarAviso(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, TITULO_AVISO, JOptionPane.WARNING_MESSAGE);
	}

	private boolean confirmarAccion(String mensaje, String titulo) {
		int respuesta = JOptionPane.showConfirmDialog(this, mensaje, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return respuesta == JOptionPane.YES_OPTION;
	}
	
	private String obtenerSeleccionRadioButton(ButtonGroup grupo) {
		for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null; // Ninguna opción seleccionada
	}

}