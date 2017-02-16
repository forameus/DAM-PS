package semaforo;
import java.util.Random;

public class Productor implements Runnable{
	
	private Character[] cadena;
	private Random r;	
	private Monitor monitor;	
	
	public Productor(Character[] cadena, Monitor monitor){
		this.cadena=cadena;
		this.monitor=monitor;
		r = new Random();		
	}
	
	@Override
	public void run() {		
		escribir();
	}	
	
	
	private void escribir(){		
		while(true){			
				try {				
					monitor.getSemaforo().acquire();
					if(cadena[9] == null){	
						System.out.println("Productor: Escribiendo...");
						for (int i = 0; i < cadena.length; i++) {
							cadena[i] = (char)(r.nextInt(26) + 'a');
						}
						System.out.println("Productor: Escritura completa\n");
						//System.out.println("Se han concedido "+monitor.getSemaforo().drainPermits()+" permisos.");
					}
					monitor.getSemaforo().release();
					Thread.sleep(1000);
					
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				
			}
		}
		
		
	}
	
	
	
}