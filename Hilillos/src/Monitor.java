public class Monitor {
	
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
