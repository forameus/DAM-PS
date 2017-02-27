package supermercado;

import java.util.concurrent.Semaphore;

public class Cajero {
	private Semaphore semaforo;
	private boolean ocupado;
	private Monitor m;
	
	public Cajero(Monitor m){
		semaforo = new Semaphore(1);
		ocupado = false;
		this.m = m;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {	
		this.ocupado = ocupado;
	}

	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	
	
	
	
}
