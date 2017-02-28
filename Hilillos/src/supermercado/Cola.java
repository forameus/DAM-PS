package supermercado;

import java.util.ArrayList;

public class Cola{

	public ArrayList<Cliente> cola;
	
	
	public Cola(){
		cola = new ArrayList<>();		
	}	
	
	public ArrayList<Cliente> getCola(){
		return this.cola;
	}
	
	
	public void anadirCliente(Cliente c){
		cola.add(c);
	}
	
	public void eliminarCliente(Cliente c){
		cola.remove(c);
	}
}
