package modelo;

public class Libro {
	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private String precio;
	private String formato; // Cartoné, Rústica, Grapada, Espiral
	private String estado; // Reedición, Novedad
	private int unidades; // Cantidad disponible en stock
	
	public Libro(String iSBN, String titulo, String autor, String editorial,
			String precio, String formato, String estado, int unidades) {
		super();
		ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.precio = precio;
		this.formato = formato;
		this.estado = estado;
		this.unidades = unidades;
	}
	
	public boolean vender(int cantidad) {
		if (this.unidades >= cantidad) {
			this.unidades -= cantidad;
			return true; // Venta realizada
		}
		return false; // Stock insuficiente
	}
	
	public void reponer(int cantidad) {
		this.unidades+=cantidad;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	@Override
	public String toString() {
		return ISBN+" - "+titulo+" (Stock: "+unidades+")";
	}

}
