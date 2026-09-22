package modelo;

public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private String precio;
	
	public Libro(String iSBN, String titulo, String autor, String editorial, String precio) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public String getPrecio() {
		return precio;
	}
	
}
