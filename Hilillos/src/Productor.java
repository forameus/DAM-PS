import java.util.Random;

public class Productor implements Runnable{
	
	private Character[] cadena;
	private Random r;
	private int contador = 0;
	private Monitor monitor;
	private boolean recienDespertado;
	
	public Productor(Character[] cadena, Monitor monitor){
		this.cadena=cadena;
		this.monitor=monitor;
		r = new Random();
		recienDespertado = true;
	}
	
	@Override
	public void run() {		
		escribir();
	}	
	
	
	private void escribir(){
		while(true){
			synchronized(monitor){
				try {
					while(cadena[9]!=null){
						contador=0;
						System.out.println("Productor: Ja dormí!");
						recienDespertado = true;
						monitor.wait();				
					}
					if(recienDespertado){
						System.out.println("Productor: Beunos Dioas¡!");
						recienDespertado=false;
					}
					System.out.println("Productor: Escribiendo...");
					cadena[contador] = (char)(r.nextInt(26) + 'a');
					contador++;
					monitor.notify();					
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	
	
}