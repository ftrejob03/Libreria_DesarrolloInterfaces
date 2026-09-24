package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Estanteria;
import modelo.Libro;
import vista.UI;

public class ParaUI extends UI {
	private static final String TITULO_EXITO = "Éxito";
	private static final String TITULO_AVISO = "Aviso";
	
	private Estanteria estanteria = new Estanteria();
	private boolean iniciado = false;
	
	public ParaUI() {
		btConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarLibro();
			}
		});
		
		btGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarLibro();
			};
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
	}
	
	private void iniciarEstanteria() {
		if (iniciado) {
			mostrarAviso("La estantería ya se encuentra inicializada.");
			return;
		}
		estanteria.anadirLibros(new Libro("9788420471839", "Cien años de soledad", "G. García Márquez", "Debolsillo", "15.95"));
		estanteria.anadirLibros(new Libro("9788439736966", "1984", "George Orwell", "Debolsillo", "12.50"));
		estanteria.anadirLibros(new Libro("9788420684093", "El hobbit", "J.R.R. Tolkien", "Minotauro", "18.00"));
		estanteria.anadirLibros(new Libro("9788437604947", "Don Quijote de la Mancha", "Miguel de Cervantes", "Cátedra", "14.25"));
		estanteria.anadirLibros(new Libro("9788497593083", "Fahrenheit 451", "Ray Bradbury", "Debolsillo", "9.95"));
		estanteria.anadirLibros(new Libro("9788478887194", "El Principito", "Antoine de Saint-Exupéry", "Salamandra", "8.50"));
		estanteria.anadirLibros(new Libro("9788497594257", "Un mundo feliz", "Aldous Huxley", "Debolsillo", "10.95"));
		
		estanteria.rellenarTabla(tablaLibros);
		
		iniciado = true;
		mostrarInfo("Estantería inicializada.");
	}
	
	private void guardarLibro() {
		// Validación de que la estantería esté incializada
		if (!iniciado) {
			mostrarAviso("Primero debes pulsar INICIAR para inicializar la estantería.");
			return;
		}
		
		// Control de que no sobrepase más de 10 libros
		if (estanteria.getEstanteria().size() >= 10) {
			mostrarAviso("La estantería está lleno con 10 libros, no se puede llenar más.");
			return;
		}
	
		if (!validarFormulario()) {
			return;
		}
		
		// Proceso de guardado de datos que insertamos en los campos.
		String ISBN = txISBN.getText().trim();
		String titulo = txTitulo.getText().trim();
		String autor = txAutor.getText().trim();
		String editorial = txEditorial.getText().trim();
		String precio = txPrecio.getText().trim();
		
		Libro libro = new Libro(ISBN, titulo, autor, editorial, precio);
		estanteria.anadirLibros(libro);
		estanteria.rellenarTabla(tablaLibros);
		
		limpiarCampos();
		mostrarInfo("Libro guardado con éxito.");
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
			mostrarInfo("Libro borrado");
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
			mostrarAviso("Por favor, selecciona un libro en la tabla para consultar.");
			return;
		}
		
		if (!validarFormulario()) {
			return;
		}
		
		String ISBN = txISBN.getText().trim();
		String titulo = txTitulo.getText().trim();
		String autor = txAutor.getText().trim();
		String editorial = txEditorial.getText().trim();
		String precio = txPrecio.getText().trim();
		
		if (confirmarAccion("¿Deseas guardar los cambios de este libro?", "Confirmar modificación")) {
			Libro libromodificado = new Libro(ISBN, titulo, autor, editorial, precio);
			estanteria.modificarLibro(indice, libromodificado);
			estanteria.rellenarTabla(tablaLibros);
			limpiarCampos();
			mostrarInfo("Libro modificado correctamente.");
		}
	}
	//////////////////////////////
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
	
	private void limpiarCampos() {
		txISBN.setText("");
		txTitulo.setText("");
		txAutor.setText("");
		txEditorial.setText("");
		txPrecio.setText("");
		
		txISBN.setEditable(true);
		txISBN.requestFocus();
	}
	
	private String validarISBN(String ISBN) {
		if (ISBN.isEmpty()) {
			return "El campo ISBN no puede estar vacío";
		}
		if (!ISBN.matches("\\d{13}")) {
			return "El ISBN debe contener exactamente 13 dígitos numéricos (sin letras ni guiones).";
		}
		return null; // Válido
	}
	
	private String validarPrecio(String precioStr) {
		if (precioStr.isEmpty()) {
			return "El campo Precio no puede estar vacío";
		}
		try {
			double precio = Double.parseDouble(precioStr.replace(",", "."));
			if (precio <= 0) {
				return "El precio debe ser un número mayor que 0.";
			}
		} catch (NumberFormatException e) {
			return "El formato del precio no es válido (ejemplo: 15.95).";
		}
		return null; // Válido
	}
	
	private String validarTexto(String texto, String nombreCampo) {
		if (texto.isEmpty()) {
			return "El campo" + nombreCampo + " no puede estar vacío.";
		}
		return null; // Válido
	}
	
	private boolean validarFormulario() {
		String error;
		
		error = validarISBN(txISBN.getText().trim());
		if (error != null) {
			mostrarAviso(error);
			txISBN.requestFocus();
			return false;
		}
		
		error = validarTexto(txTitulo.getText().trim(), "Título");
		if (error != null) {
			mostrarAviso(error);
			txTitulo.requestFocus();
			return false;
		}

		error = validarTexto(txAutor.getText().trim(), "Autor");
		if (error != null) {
			mostrarAviso(error);
			txAutor.requestFocus();
			return false;
		}
		
		error = validarTexto(txEditorial.getText().trim(), "Editorial");
		if (error != null) {
			mostrarAviso(error);
			txEditorial.requestFocus();
			return false;
			
		}
		
		error = validarPrecio(txPrecio.getText().trim());
		if (error != null) {
			mostrarAviso(error);
			txPrecio.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean isIniciado() {
		return iniciado;
	}
}
