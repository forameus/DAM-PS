package pruebaRetroFit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface LibroInterface {
	@GET("Libro/{id}")
	Call<Libro> getLibro (@Path("id") int id);
}