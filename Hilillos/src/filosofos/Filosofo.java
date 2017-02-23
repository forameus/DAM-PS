package filosofos;

import java.util.Random;

public class Filosofo extends Thread {

	private String nombre;
	private Monitor monitor;

	public Filosofo(Monitor m) {
		monitor = m;
		nombre = generadorNombre();
	}

	public Filosofo(Monitor m,String nombre) {
		monitor = m;
		this.nombre = nombre;
	}
	
	public void pensar(){
		try {
			System.out.println("Filosofo " + nombre+" está pensando.");        
			Thread.sleep((int)(Math.random()*(4000 - 1000))+1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void comer(){
		try {
			System.out.println("Filosofo " + nombre+" está comiendo.");        
			Thread.sleep((int)(Math.random()*(4000 - 1000))+1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
	    while (true) {
	    	pensar();
	    	cogerPalillos();
	    	comer();	    	
	        dejarPalillos();
	    }
	}

	private void cogerPalillos() {
		
	}
	
	private void dejarPalillos() {		
		
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
