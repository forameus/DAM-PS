package filosofos;

import java.util.Random;

public class Filosofo extends Thread {

	private String nombre;
	private Palillo palilloDerecho;
	private Palillo palilloIzquierdo;	
	private int intentos = 10;
	private int contador = 0;
	
	public Filosofo(Palillo palilloDerecho, Palillo palilloIzquierdo) {
		this.palilloDerecho = palilloDerecho;
		this.palilloIzquierdo = palilloIzquierdo;
		nombre = generadorNombre();
	}

	public Filosofo(Palillo palilloDerecho, Palillo palilloIzquierdo, String nombre) {
		this.palilloDerecho = palilloDerecho;
		this.palilloIzquierdo = palilloIzquierdo;
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
			System.out.println("Filosofo " + nombre+" está COMIENDO.");        
			Thread.sleep((int)(Math.random()*(4000 - 1000))+1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
	    while (intentos>contador) {
	    	contador++;
	    	pensar();
	    	if(cogerPalilloIzquierdo()){
	    		if(cogerPalilloDerecho()){
	    			System.out.println("Filósofo "+nombre+" cogió los palillos.");
	    			comer();
	    			contador = 0;	    		
	    			dejarPalilloDerecho();
	    			dejarPalilloIzquierdo();
	    			System.out.println("Filósofo "+nombre+" soltó los palillos.");	    		}	    		
	    		
	    		
	    	}else{
	    		dejarPalilloIzquierdo();
	    		System.out.println("Filósofo "+nombre+" intentó coger los palillos pero no pudo. Jajaja");
	    	}
	    }
	    System.out.println("Filósofo "+nombre+" ha muerto de inanición.");
	}

	private boolean cogerPalilloIzquierdo() {		
		return palilloIzquierdo.tryAcquire();		
	}
	
	private boolean cogerPalilloDerecho() {		
		return palilloDerecho.tryAcquire();		
	}
	
	private void dejarPalilloIzquierdo() {		
		palilloIzquierdo.release();		
	}
	
	private void dejarPalilloDerecho() {	
		palilloDerecho.release();
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
