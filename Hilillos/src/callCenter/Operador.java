import java.util.ArrayList;
import java.util.Random;

public class Operador extends Thread{

	private int numeroDeVentas;
	private int numeroDeLlamadasAtendidas;
	private Monitor monitor;
	private int numero;
	private ArrayList<Cliente> cola;
	private Random random;
	private boolean seguir;
	private boolean haFinalizado;
	
	public Operador(Monitor monitor, ArrayList<Cliente> cola, int numero){
		this.monitor = monitor;
		this.cola = cola;
		this.numero = numero;
		this.seguir = true;
		setHaFinalizado(false);
		random = new Random();	
	}
	
	public void run(){
		while(seguir){
			atenderSiguienteLlamada();	
			try {			     
				Thread.sleep((int)Math.random()*500);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setHaFinalizado(true);
	}

	private void atenderSiguienteLlamada() {
		//Intentar coger otro cliente
		if(monitor.getSemaforo().tryAcquire()){
			Cliente c = cola.get(0);		
			cola.remove(c);				
			try {		
				//Atender cliente
				int segundos = random.nextInt(Settings.TMAX_LLAMADA-Settings.TMIN_LLAMADA)+Settings.TMIN_LLAMADA;
				System.out.println("Operador "+numero+" atendiendo llamada "+c.getNumero()+" durante "+segundos+" segundos");
				Thread.sleep(segundos*1000);
				
				//Si compra
				if(Math.random()<= Settings.PROB_COMPRA){
					System.out.println("VENTA: Operador "+numero+" a llamada "+c.getNumero());
					boolean compraCompletada = false;
					while(!compraCompletada){
						if(!monitor.isCompraEnProceso()){
							//Iniciar Compra
							monitor.setCompraEnProceso(true);
							segundos = random.nextInt(Settings.TMAX_COMPRA-Settings.TMIN_COMPRA)+Settings.TMIN_COMPRA;
							System.out.println("Operador "+numero+" PROCESANDO compra durante "+segundos+" segundos");
							Thread.sleep(segundos*1000);
							
							monitor.setCompraEnProceso(false);							
							compraCompletada = true;
							setNumeroDeVentas(getNumeroDeVentas() + 1);
							monitor.setStockActual(monitor.getStockActual()-1);
							monitor.setProductosVendidos(monitor.getProductosVendidos()+1);
						}
						else{
							System.out.println("Operador "+numero+" ESPERANDO PARA PROCESAR COMPRA");
							Thread.sleep(1000);
						}
					}
					
					
				}else
					System.out.println("Llamada "+c.getNumero()+" CUELGA sin comprar");
				
				//Mandar estadísticas al Monitor
				setNumeroDeLlamadasAtendidas(getNumeroDeLlamadasAtendidas() + 1);
				monitor.setNumeroDeLlamadasAtendidas(monitor.getNumeroDeLlamadasAtendidas()+1);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	public boolean isSeguir() {
		return seguir;
	}

	public void setSeguir(boolean seguir) {
		this.seguir = seguir;
	}

	public int getNumeroDeVentas() {
		return numeroDeVentas;
	}

	public void setNumeroDeVentas(int numeroDeVentas) {
		this.numeroDeVentas = numeroDeVentas;
	}

	public int getNumeroDeLlamadasAtendidas() {
		return numeroDeLlamadasAtendidas;
	}

	public void setNumeroDeLlamadasAtendidas(int numeroDeLlamadasAtendidas) {
		this.numeroDeLlamadasAtendidas = numeroDeLlamadasAtendidas;
	}

	public boolean isHaFinalizado() {
		return haFinalizado;
	}

	public void setHaFinalizado(boolean haFinalizado) {
		this.haFinalizado = haFinalizado;
	}

	
}
