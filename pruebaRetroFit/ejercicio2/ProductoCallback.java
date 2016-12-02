package ejercicio2;


import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoCallback implements Callback<List<Producto>>{

	@Override
	public void onFailure(Call<List<Producto>> arg0, Throwable arg1) {		
		
	}

	
	@Override
	public void onResponse(Call<List<Producto>> arg0, Response<List<Producto>> resp) {	
		
		try{
			System.out.println(resp.raw());
			
			//Obtener List con los productos
			List<Producto> productos = resp.body();
	
	        //Imprimir por pantalla
			for (Producto temp : productos)
	        	System.out.println(temp.getNombre()+", "+temp.getPrecio()+"€");
			
			
		
		}catch(Exception e){
			System.out.println("ERROR: ");
			e.printStackTrace();
		}	
		
	}
}
