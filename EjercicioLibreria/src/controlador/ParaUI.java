package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Estanteria;
import modelo.Libro;
import utiles.Validaciones;
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
		estanteria.anadirLibros(new Libro("9788420471839", "Clean Code", "Robert C. Martin", "Pearson Prentice Hall", "15.95"));
		estanteria.anadirLibros(new Libro("9788439736966", "Refactoring", "Martin Fowler", "Addison-Wesley", "12.50"));
		estanteria.anadirLibros(new Libro("9788420684093", "El lenguaje de programación C", "Brian Kernighan y Dennis Ritchie", "Pearson Prentice Hall", "18.00"));
		estanteria.anadirLibros(new Libro("9788437604947", "Redes de computadoras", "Andrew Tanenbaum", "Pearson Prentice Hall", "14.25"));
		estanteria.anadirLibros(new Libro("9788497593083", "Organización y diseño de computadoras", "David Patterson y John Hennessy", "McGraw Hill", "9.95"));
		estanteria.anadirLibros(new Libro("9788478887194", "Fundamentos de sistemas de bases de datos", "Ramez Elmasri y Shamkant Navathe", "Addison-Wesley", "8.50"));
		estanteria.anadirLibros(new Libro("9788497594257", "Sistemas Operativos", "William Stallings", "Pearson Prentice Hall", "10.95"));
		
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
			mostrarAviso("Está lleno con 10 libros, no se puede llenar más.");
			return;
		}

		String ISBN = txISBN.getText().trim();
		String titulo = txTitulo.getText().trim();
		String autor = txAutor.getText().trim();
		String editorial = txEditorial.getText().trim();
		String precio = txPrecio.getText().trim();
		
		if (!Validaciones.validaISBN(ISBN)) {
			marcarErrorCampo(txISBN, "El ISBN debe tener exactamente 13 digitos numericos.");
			return;
		}
		
		if (estanteria.existeISBN(ISBN)) {
			marcarErrorCampo(txISBN, "Ya existe un libro registrado con el ISBN "+ISBN);
			return;
		}
		
		if (titulo.isEmpty()) {
			marcarErrorCampo(txTitulo, "El campo Título no puede estar vacío.");
			return;
		}
		
		if (!Validaciones.validaLetters(autor)) {
			marcarErrorCampo(txAutor, "El campo Autor solo debe contener letras y espacios.");
			return;
		}
		
		if (!Validaciones.validaLetters(editorial)) {
			marcarErrorCampo(txEditorial, "El campo Editorial solo debe contener letras y espacios.");
			return;
		}
		
		if (!Validaciones.isNumberFloat(precio)) {
			marcarErrorCampo(txPrecio, "El formato del precio no es válido (ejemplo: 15.95)");
			return;
		}
		
		Libro libro = new Libro(ISBN, titulo, autor, editorial, precio);
		estanteria.anadirLibros(libro);
		estanteria.rellenarTabla(tablaLibros);
		
		limpiarCampos();
		mostrarInfo("Libro guardado correctamente.");
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
			mostrarAviso("Por favor, selecciona un libro en la tabla para modificar.");
			return;
		}
		
		String ISBN = txISBN.getText().trim();
		String titulo = txTitulo.getText().trim();
		String autor = txAutor.getText().trim();
		String editorial = txEditorial.getText().trim();
		String precio = txPrecio.getText().trim();
		
		if (titulo.isEmpty()) {
			marcarErrorCampo(txTitulo, "El campo Título no puede estar vacío.");
			return;
		}
		
		if (!Validaciones.validaLetters(autor)) {
			marcarErrorCampo(txAutor, "El campo Autor solo debe contener letras y espacios.");
			return;
		}
		
		if (!Validaciones.validaLetters(editorial)) {
			marcarErrorCampo(txEditorial, "El campo Editorial solo debe contener letras y espacios.");
			return;
		}
		
		if (!Validaciones.isNumberFloat(precio)) {
			marcarErrorCampo(txPrecio, "El formato del precio no es válido (ejemplo: 15.95)");
			return;
		}
		
		if (confirmarAccion("¿Deseas guardar los cambios de este libro?", "Confirmar modificación")) {
			Libro libromodificado = new Libro(ISBN, titulo, autor, editorial, precio);
			estanteria.modificarLibro(indice, libromodificado);
			estanteria.rellenarTabla(tablaLibros);
			limpiarCampos();
			mostrarInfo("Libro modificado correctamente.");
		}
	}
	//////////////////////////////
	private void limpiarCampos() {
		txISBN.setText("");
		txTitulo.setText("");
		txAutor.setText("");
		txEditorial.setText("");
		txPrecio.setText("");
		
		restaurarColores();
		txISBN.setEnabled(true);
		txISBN.requestFocus();
	}
	
	private void restaurarColores() {
		Color blanco = Color.WHITE;
		txISBN.setBackground(blanco);
		txTitulo.setBackground(blanco);
		txAutor.setBackground(blanco);
		txEditorial.setBackground(blanco);
		txPrecio.setBackground(blanco);
	}
	
	private void marcarErrorCampo(JTextField campo, String mensaje) {
		restaurarColores();
		campo.setBackground(new Color(255, 200, 200));
		campo.requestFocus();
		mostrarAviso(mensaje);
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
	
	
	public boolean isIniciado() {
		return iniciado;
	}
}
