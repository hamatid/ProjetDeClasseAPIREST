package projet.composant.service;

import java.util.List;

import projet.composant.model.Cours;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProgrammerService {

    @GET("get_all_cours")
    Call<List<Cours>> apiRead();

    @POST("create_cours")
    Call<Cours> apiCreate(@Body Cours programmer);

    @PUT("modifier_cours/{id_cours}")
    Call<Cours> apiUpdate(@Path("id_cours") int id, @Body Cours programmer);

    @DELETE("supprimer_cours/{id_cours}")
    Call<Cours> apiDelete(@Path("id_cours") int id);


}
