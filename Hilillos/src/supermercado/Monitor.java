package supermercado;

import java.util.ArrayList;
import java.util.Random;

public class Monitor {
	private final int numeroDeCajeros = 4;
	private final int numeroDeClientesPorMinuto = 100;
	private int tiempoTotal = 3000;
	private Cajero[] cajeros;
	private ArrayList<Cliente> clientes;	
	
	private int vecesClienteEnCola = 0;
	private int tiempoTotalEsperado = 0;
	private int clientesTotales = 0;
	
	public Monitor(){
		cajeros = new Cajero[numeroDeCajeros];
		clientes = new ArrayList<Cliente>(); 
		
	}
	
	public void ejecutar(){
		for (int i = 0; i < cajeros.length; i++) {
			cajeros[i] = new Cajero(this);
			cajeros[i].start();
		}		
			
		
		while(tiempoTotal>0){
			for (int i = 0; i < numeroDeClientesPorMinuto; i++) {
				Cliente c = new Cliente(this);				
				clientes.add(c);
				c.start();		
				clientesTotales++;
			}
			
			try {
			    Thread.sleep(100);                 
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			tiempoTotal-=100;			
		}		
		
		
		//Tiempo extra para que terminen los rezagados
		try {
		    Thread.sleep(2000);                 
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		mostrarInforme();		
	}
	
	
	
	//devuelve true si tiene que esperar en una cola
	public boolean entrarEnCola(Cliente cliente) {		
		//intentar entra en algun cajero vacio
		boolean res = true;
		for (int i = 0; i < cajeros.length && res; i++) {
			if(!cajeros[i].isOcupado()){
				cajeros[i].atenderCliente(cliente);
				res = false;
			}
		}
		//si no, meterse en la cola con menos clientes		
		if(res){	
			int rnd = new Random().nextInt(cajeros.length);
			cajeros[rnd].getCola().anadirCliente(cliente);			
		}		
		return res;
	}
	

	public void abandonarSuper(Cliente cliente) {		
		clientes.remove(cliente);
		cliente = null;
	}
	
	

	public void apuntarTiempoEsperado(int tiempoEsperando) {		
		tiempoTotalEsperado+=tiempoEsperando;		
	}
	
	public void apuntarClienteEnCola(){
		vecesClienteEnCola++;
	}
	
	public void mostrarInforme(){
		//System.out.println("vecesCajeroNoOcupado "+vecesCajeroNoOcupado);
		//System.out.println("vecesClienteEnCola "+vecesClienteEnCola);	
		
		double media = 0;	
		if(vecesClienteEnCola!=0)
			media = (double)(tiempoTotalEsperado/vecesClienteEnCola)/10;		
		
		
		System.out.println("\n///////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("Clientes totales: "+clientesTotales);
		//System.out.println("Alguno de los cajeros ha estado esperando sin hacer nada "+vecesCajeroNoOcupado+" veces.");
		System.out.println(vecesClienteEnCola+" clientes han tenido que esperar un tiempo medio de "+media+" minutos.");
		for (int i = 0; i < cajeros.length; i++) {
			System.out.println("Cajero "+i+" ha estado esperando sin hacer nada "+cajeros[i].getVecesCajeroNoOcupado()+" veces. Y ha atendido a "+cajeros[i].getClientesAtendidos()+" clientes.");
		}
		System.out.println("La tienda ha cerrado y "+clientes.size()+" clientes se han quedado atrapados dentro.");
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////\n");
	}

	
}
