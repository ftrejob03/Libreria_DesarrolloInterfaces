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
	
		// Proceso de guardado de datos que insertamos en los campos.
		String ISBN = txISBN.getText().trim();
		String titulo = txTitulo.getText().trim();
		String autor = txAutor.getText().trim();
		String editorial = txEditorial.getText().trim();
		String precio = txPrecio.getText().trim();
		
		if (ISBN.isEmpty() || titulo.isEmpty() || autor.isEmpty() || editorial.isEmpty() || precio.isEmpty()) {
			mostrarAviso("Por favor, completa los campos antes de guardar.");
			return;
		}
		
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
		mostrarInfo("Datos del libro cargados en el formulario.");
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
		txISBN.requestFocus();
	}
	
	public boolean isIniciado() {
		return iniciado;
	}
}
