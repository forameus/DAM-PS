package filosofos;

import java.util.concurrent.Semaphore;

public class Monitor {
	private Semaphore semaforo;
	
	public Monitor(){
		 semaforo = new Semaphore(1,true);
	}
	
	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}
	
	public void obtenerPalillos(){
		try {
			semaforo.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
