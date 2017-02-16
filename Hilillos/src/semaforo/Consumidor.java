package semaforo;
import java.util.Arrays;

public class Consumidor implements Runnable {

	private Character[] cadena;
	private Monitor monitor;

	public Consumidor(Character[] cadena, Monitor monitor) {
		this.cadena = cadena;
		this.monitor = monitor;
	}

	@Override
	public void run() {
		leer();
	}

	private void leer() {
		
		while (true) {
				try {					
					monitor.getSemaforo().acquire();
					if (cadena[9] != null) {						
					
						System.out.println("Consumidor: Leyendo cadena...");
						System.out.println("Consumidor: " + Arrays.toString(cadena));						
						System.out.println("Consumidor: Vaciando cadena...\n");
						Arrays.fill(cadena, null);
											
					}
					monitor.getSemaforo().release();	
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				
			}
		}
	}
}
