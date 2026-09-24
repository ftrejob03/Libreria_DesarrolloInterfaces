package modelo;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Estanteria {
	private ArrayList<Libro> arrayLibro = new ArrayList<Libro>();
	
	public void rellenarTabla(JTable tablaLibros) {
		String nombresColumnas[] = {"ISBN", "TITULO", "AUTOR", "EDITORIAL", "PRECIO"};
		String[][] filasTabla = new String[this.arrayLibro.size()][5];
		for (int i = 0; i < this.arrayLibro.size(); i++) {
			filasTabla[i][0] = this.arrayLibro.get(i).getISBN();
			filasTabla[i][1] = this.arrayLibro.get(i).getTitulo();
			filasTabla[i][2] = this.arrayLibro.get(i).getAutor();
			filasTabla[i][3] = this.arrayLibro.get(i).getEditorial();
			filasTabla[i][4] = this.arrayLibro.get(i).getPrecio();
		}
		DefaultTableModel tablaCompleta = new DefaultTableModel(filasTabla, nombresColumnas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaLibros.setModel(tablaCompleta);
	}
	
	public int obtenerIdSeleccionado(JTable tablaLibros) {
		return tablaLibros.getSelectedRow();
	}
	
	public void anadirLibros(Libro libro) {
		arrayLibro.add(libro);
	}
	
	public void modificarLibro(int indice, Libro libromodificado) {
		if (indice >= 0 && indice < arrayLibro.size()) {
			arrayLibro.set(indice, libromodificado);
		}
	}
	
	public void borrarLibros(int indice) {
		if (indice >= 0 && indice < arrayLibro.size()) {
			arrayLibro.remove(indice);
		}
	}

	public ArrayList<Libro> getEstanteria() {
		return arrayLibro;
	}
}
