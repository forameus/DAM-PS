package semaforo;

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

	public void ejecutar(){
		Character[] cadena = new Character[10];		
		
			
		Productor p = new Productor(cadena, this);
		Consumidor c = new Consumidor(cadena, this);
	
		Thread hiloP = new Thread(p);
		Thread hiloC = new Thread(c);
			
		hiloC.start();
		hiloP.start();
		
		
		try {
			hiloC.join();
			hiloP.join();		
			
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
}
