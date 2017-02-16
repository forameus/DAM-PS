package waitNotify;
import java.util.Arrays;

public class Consumidor implements Runnable {

	private Character[] cadena;
	private Monitor monitor;
	private boolean recienDespierto;

	public Consumidor(Character[] cadena, Monitor monitor) {
		this.cadena = cadena;
		this.monitor = monitor;
		recienDespierto = true;
	}

	@Override
	public void run() {
		leer();
	}

	private void leer() {
		while (true) {
			synchronized (monitor) {
				try {
					while (cadena[9] == null) {
						System.out.println("Consumidor: ¡A dormir!");
						recienDespierto = true;
						monitor.wait();
					}
					if(recienDespierto){
						System.out.println("Consumidor: ¡Buenos días!");
						recienDespierto = false;
					}
						
					
					System.out.println("Consumidor: Leyendo");
					System.out.println("Consumidor: " + Arrays.toString(cadena));

					Arrays.fill(cadena, null);

					monitor.notify();

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
