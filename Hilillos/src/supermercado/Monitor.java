package supermercado;

import java.util.ArrayList;

public class Monitor {
	private final int numeroDeCajeros = 1;
	private final int numeroDeClientesPorMinuto = 100;
	private int tiempoTotal = 3000;
	private Cajero[] cajeros;
	private ArrayList<Cliente> clientes;
	
	private int vecesCajeroNoOcupado = 0;
	private int vecesClienteEnCola = 0;
	private int tiempoTotalEsperado = 0;
	
	public Monitor(){
		cajeros = new Cajero[numeroDeCajeros];
		clientes = new ArrayList<Cliente>(); 
		//[numeroDeClientesPorMinuto];
	}
	
	public void ejecutar(){
		for (int i = 0; i < cajeros.length; i++) {
			cajeros[i] = new Cajero(this);
		}
	
		
		
		while(tiempoTotal>0){
			for (int i = 0; i < numeroDeClientesPorMinuto; i++) {
				Cliente c = new Cliente(this);
				clientes.add(c);
				c.start();				
			}
			
			try {
			    Thread.sleep(100);                 
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			tiempoTotal-=100;			
		}
		//System.out.println("\n///////////////////////////////////////////////////////////////////////////////////////////////////");
		mostrarInforme();
	}
	
	
	public Cajero buscarCajero(){
		Cajero res = null;
		for (int i = 0; i < cajeros.length && res==null ; i++) {
			if(!cajeros[i].isOcupado())
				res = cajeros[i];
		}		
		return res;
	}

	public void abandonarSuper(Cliente cliente) {		
		clientes.remove(cliente);
	}

	public void apuntarCajeroNoOcupado() {
		vecesCajeroNoOcupado++;		
	}

	public void apuntarTiempoEsperado(int tiempoEsperando) {		
		tiempoTotalEsperado+=tiempoEsperando;
		vecesClienteEnCola++;
	}
	
	public void mostrarInforme(){
		System.out.println("\n///////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("Alguno de los cajeros ha estado esperando sin hacer nada "+vecesCajeroNoOcupado+" veces.");
		System.out.println(vecesClienteEnCola+" clientes han tenido que esperar un tiempo medio de "+(double)(tiempoTotalEsperado/vecesClienteEnCola)/10+" minutos.");
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////\n");
	}
	
}
