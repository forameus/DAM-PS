package supermercado;

import java.util.Random;

public class Cliente extends Thread {
	
	private String nombre;
	private String apellido;
	private int tc;
	private int tp;
	private Monitor m;
	private boolean noHaPagado = true;
	private int tiempoEsperando = 0;
	
	public Cliente(Monitor m){
		nombre = generadorNombre();
		apellido = generadorNombre();
		tc = (int)(Math.random()*(600 - 50))+ 50;
		tp = (int)(Math.random()*((tp/10) - (tp/60)))+(tp/60);
		this.m=m;
	}
	
	
	public void comprar(){
		try {
			//System.out.println("Cliente " + nombre +" "+apellido+" está comprando.");        
			Thread.sleep(tc);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void pagar(){
		try {
			//System.out.println("Cliente " + nombre +" "+apellido+" está pagando.");        
			Thread.sleep(tp);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void entrarEnCajero(){
		Cajero caj = m.buscarCajero();
		if(caj!=null){
			caj.setOcupado(true);
			caj.getSemaforo().tryAcquire();
			pagar();
			caj.getSemaforo().release();
			caj.setOcupado(false);
			noHaPagado = false;
			m.apuntarTiempoEsperado(tiempoEsperando);
		}
		else{			
			try {
				//System.out.println("Cliente " + nombre +" "+apellido+" está ESPERANDO.");       
				Thread.sleep(10);
				tiempoEsperando+=10;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	public void run() {		
		comprar();
		while(noHaPagado){
			entrarEnCajero();
		}	
		//System.out.println("Cliente " + nombre +" "+apellido+" ha salido satisfecho :D"); 
		m.abandonarSuper(this);
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
