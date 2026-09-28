package utiles;

import java.util.regex.Pattern;

public class Validaciones {
	public static boolean validaLetters(String nombre) {
		return Pattern.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+", nombre);
	}
	
	public static boolean isNumber(String nombre) {
		return Pattern.matches("[0-9]*", nombre);
	}
	
	public static boolean validaISBN(String nombre) {
		return nombre.length() == 13 && isNumber(nombre);
	}
	
	public static boolean isNumberFloat(String nombre) {
		try {
			Float.parseFloat(nombre);
			char charAt = nombre.charAt(nombre.length() - 1);
			if (charAt == 'f' || charAt == 'd') {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
