package filosofos;


public class Main {

	final static int numeroFilosofos = 5;
	//final static int[] palillosFilosofo = {0,1,2,3,4,5};
	//final static int[] palillosFilosofo = new int[numeroFilosofos];
	final static Filosofo[] filosofos = new Filosofo[numeroFilosofos];
	final static Palillo[] palillos = new Palillo[numeroFilosofos];
	
	
	public static void main(String[] args) {		
		
		 for (int i = 0; i < numeroFilosofos; i++) {
			 palillos[i] = new Palillo(1);		 
		 }
		 
		 for (int i = 0; i < numeroFilosofos; i++) {
			 int j = i-1;
			 if(j==-1)
				 j=numeroFilosofos-1;
			 filosofos[i] = new Filosofo(palillos[j], palillos[i], ""+i);
			 Thread hiloFilosofo = new Thread(filosofos[i]);
			 hiloFilosofo.start();
		 }	
		 
		 		 
	}
}



