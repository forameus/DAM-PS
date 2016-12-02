package ejercicio2;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductoInterface {
	@GET("iuxWhsUdRHKwz48vo2Ny{s}")
	//@GET("17jxp0r1{s}")

	Call<List<Producto>> getProductos (@Path("s") String s);

}
