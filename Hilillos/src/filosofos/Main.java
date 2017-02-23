package filosofos;

import java.util.concurrent.Semaphore;

public class Main {

	final static int numeroFilosofos = 5;
	final static int[] palillosFilosofo = {0,1,2,3,4,5};
	final static Semaphore[] palillos_semaforo = new Semaphore[numeroFilosofos];
	
	
	public static void main(String[] args) {
		Monitor m = new Monitor();
		
		 for (int i = 0; i < numeroFilosofos; i++) {
			 palillos_semaforo[i] = new Semaphore(1);		 
			 System.out.print("Álvaro\n");
		 }
	}
	
	
	
}



