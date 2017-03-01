import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitor {

	// Crear variables
	private int segundosTranscurrido;
	private int numeroDeLlamadas;
	private int numeroDeLlamadasPerdidas;
	private int numeroDeLlamadasAtendidas;
	private int productosVendidos;
	private int stockActual;
	private boolean compraEnProceso;
	private ArrayList<Cliente> cola;
	private Semaphore semaforo;
	Operador[] operadores = new Operador[Settings.NUM_OPERADORES];	
	
	public void ejecutar() {
		// Inicializar variables
		segundosTranscurrido = 0;
		numeroDeLlamadas = 0;
		numeroDeLlamadasPerdidas = 0;
		numeroDeLlamadasAtendidas = 0;
		productosVendidos = 0;
		stockActual = Settings.NUM_PRODUCTOS;
		setCompraEnProceso(false);
		cola = new ArrayList<>();
		semaforo = new Semaphore(0);
		
		//Crear operadores
		for (int i = 0; i < operadores.length; i++) {
			operadores[i] = new Operador(this, cola, i);
			operadores[i].start();
		}

		
		// Iniciar simulación
		while (segundosTranscurrido < Settings.TIEMPO_SIMULACION && stockActual>0) {
			// Si llaman...
			if (Math.random() <= Settings.PROB_LLAMADA){
				//Si la cola no está llena, añadir el cliente a ésta y añadir un permiso al semáforo
				if(cola.size() < Settings.TAM_COLA_ESPERA) {					
					System.out.println("Llamada "+ numeroDeLlamadas + " pasa a cola de espera. Hay "+ cola.size()+" llamadas esperando.");
					cola.add(new Cliente(numeroDeLlamadas));
					semaforo.release(1);
				//Si la cola está llena no se hace nada
				}else{
					System.out.println("Llamada "+numeroDeLlamadas+" se ha PERDIDO. Cola llena.");
					numeroDeLlamadasPerdidas++;
				}
				
				numeroDeLlamadas++;
			}
			// Esperar 1 segundo
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			segundosTranscurrido++;
		}
		
		//Parar todos los operadores
		for (int i = 0; i < operadores.length; i++) {			
			operadores[i].setSeguir(false);
		}
		
		//Esperar a que todas las llamadas hayan finalizado
		boolean hanFinalizado = false;
		while(!hanFinalizado){
			hanFinalizado = true;
			for (int i = 0; i < operadores.length; i++)	
				hanFinalizado = hanFinalizado && operadores[i].isHaFinalizado();			
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		
		
		if(segundosTranscurrido >= Settings.TIEMPO_SIMULACION)
			System.out.println("TIEMPO AGOTADO");
		else
			System.out.println("STOCK AGOTADO "+stockActual);
		
		
		
		mostrarResumen();
	}		
	

	private void mostrarResumen() {
		System.out.println("\nRESUMEN:");
		System.out.println("Se ha vendido un total de "+productosVendidos+" productos.");
		for (int i = 0; i < operadores.length; i++) {
			System.out.println("Operador "+i+" ha vendido "+ operadores[i].getNumeroDeVentas() +" productos. Y ha atendido "+operadores[i].getNumeroDeLlamadasAtendidas()+" llamadas.");
		}
		System.out.println("Se han perdido "+numeroDeLlamadasPerdidas+" llamadas por estar la cola llena.");
		System.out.println("Se han atendido "+numeroDeLlamadasAtendidas+" llamadas.");
	}


	//GETTERS & SETTERS

	public int getSegundosTranscurrido() {
		return segundosTranscurrido;
	}



	public void setSegundosTranscurrido(int segundosTranscurrido) {
		this.segundosTranscurrido = segundosTranscurrido;
	}



	public int getNumeroDeLlamadas() {
		return numeroDeLlamadas;
	}



	public void setNumeroDeLlamadas(int numeroDeLlamadas) {
		this.numeroDeLlamadas = numeroDeLlamadas;
	}



	public int getNumeroDeLlamadasPerdidas() {
		return numeroDeLlamadasPerdidas;
	}



	public void setNumeroDeLlamadasPerdidas(int numeroDeLlamadasPerdidas) {
		this.numeroDeLlamadasPerdidas = numeroDeLlamadasPerdidas;
	}



	public int getNumeroDeLlamadasAtendidas() {
		return numeroDeLlamadasAtendidas;
	}



	public void setNumeroDeLlamadasAtendidas(int numeroDeLlamadasAtendidas) {
		this.numeroDeLlamadasAtendidas = numeroDeLlamadasAtendidas;
	}



	public int getProductosVendidos() {
		return productosVendidos;
	}



	public void setProductosVendidos(int productosVendidos) {
		this.productosVendidos = productosVendidos;
	}



	public int getStockActual() {
		return stockActual;
	}



	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}



	public boolean isCompraEnProceso() {
		return compraEnProceso;
	}



	public void setCompraEnProceso(boolean compraEnProceso) {
		this.compraEnProceso = compraEnProceso;
	}



	public ArrayList<Cliente> getCola() {
		return cola;
	}



	public void setCola(ArrayList<Cliente> cola) {
		this.cola = cola;
	}



	public Operador[] getOperadores() {
		return operadores;
	}



	public void setOperadores(Operador[] operadores) {
		this.operadores = operadores;
	}


	public Semaphore getSemaforo() {
		return semaforo;
	}


	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	
	
	
	
}
