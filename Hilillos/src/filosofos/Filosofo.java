package filosofos;

import java.util.Random;

public class Filosofo {

	private String nombre;

	public Filosofo() {
		nombre = generadorNombre();
	}

	public Filosofo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Filosofo " + nombre;
	}

	private String generadorNombre() {
		String res = "";
		Random r = new Random();

		// Crear arrays
		char[] vocales = "aeiou".toCharArray();
		char[] consonantes = "bcdfhjlmnprstvz".toCharArray();
		boolean vocal = true;

		// Empiezo a meter letras
		res += Character.toUpperCase(consonantes[r.nextInt(consonantes.length)]);
		for (int i = 0; i < r.nextInt(15)+5; i++) {
			if (vocal) {
				res += (char) vocales[r.nextInt(5)];
				vocal = false;
			} else {
				res += (char) consonantes[r.nextInt(consonantes.length)];
				vocal = true;
			}
		}

		if (r.nextInt(10) == 1)
			res = "Fernando";

		return res;
	}

}
