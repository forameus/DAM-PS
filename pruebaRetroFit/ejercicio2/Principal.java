package ejercicio2;


import java.util.Scanner;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal {
	
	private final static String SERVER_URL = "https://putsreq.com/";
	//private final static String SERVER_URL = "http://requestb.in/";

	public static void main(String[] args) {
		
		Retrofit retrofit;
		ProductoCallback productoCallback = new ProductoCallback();
		
		retrofit = new Retrofit.Builder()
							   .baseUrl(SERVER_URL)
							   .addConverterFactory(GsonConverterFactory.create())
							   .build();
		
		ProductoInterface productoInter = retrofit.create(ProductoInterface.class);
		
		
		
		//Preguntar por los filtros	
		int min = 0, max = 0; char letra = 'l';
		boolean filtroN = false, filtroL = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Desea filtrar por un rango de precios?(S/N)");
		
		if(Character.toUpperCase(sc.next().charAt(0)) == 'S'){
			filtroN=true;
			System.out.print("Precio mínimo: ");
			min = sc.nextInt();
			System.out.print("Precio máximo: ");
			max = sc.nextInt();
		}
		
		System.out.println("Desea filtrar por una letra?(S/N)");
		
		if(Character.toUpperCase(sc.next().charAt(0)) == 'S'){
			filtroL=true;
			System.out.print("Letra: ");
			letra = sc.next().charAt(0);
		}
		
		sc.close();		
		
		
		
		//Aplicar filtros y devolver respuesta
		String s = ("?");
		if(filtroL){
			s+="letra="+letra;
			if(filtroN)
				s+="&min="+min+"&max="+max;
		}
		else
			if(filtroN)
				s+="min="+min+"&max="+max;
		
		
		System.out.println(s);
		
		if(filtroN || filtroL)
			productoInter.getProductos(s).enqueue(productoCallback);
		else{
			s="";
			productoInter.getProductos(s).enqueue(productoCallback);
		}
		
		
		
			
	}

}