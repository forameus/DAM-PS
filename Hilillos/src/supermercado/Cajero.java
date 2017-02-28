package supermercado;

public class Cajero extends Thread{
	private boolean ocupado;
	private Cola cola;
	private Monitor m;
	private int vecesCajeroNoOcupado;
	private int clientesAntendidos;
	
	

	public Cajero(Monitor m){		
		ocupado = true;
		cola = new Cola();
		this.m = m;
		vecesCajeroNoOcupado = 0;
		clientesAntendidos = 0;
		setOcupado(false);
	}

	public void run(){
		while(true){
			if(!ocupado)
				siguienteCliente();	
			try {			     
				Thread.sleep(50);			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void siguienteCliente(){
		//Si hay un cliente
		if(!cola.getCola().isEmpty()){
			if(cola.getCola().get(0)!=null)
				atenderCliente(cola.getCola().get(0));
			
		}
		else
			//si no hay
			setOcupado(false);
	}
	
	
	public void atenderCliente(Cliente cliente) {		
		setOcupado(true);
		clientesAntendidos++;
		cola.getCola().remove(cliente);
		cliente.pagar();
		
		//esperar tambien??
		try {			     
			Thread.sleep(cliente.getTP());			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cliente.setEsperar(false);	
		ocupado = false;
		
	}

	public boolean isOcupado() {		
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {		
		if(!ocupado && ocupado!=this.ocupado)
			this.vecesCajeroNoOcupado++;
		this.ocupado = ocupado;
	}


	public Cola getCola(){
		return this.cola;
	}
	
	public int getVecesCajeroNoOcupado() {
		return vecesCajeroNoOcupado;
	}
	
	public int getClientesAtendidos() {
		return clientesAntendidos;
	}
}
