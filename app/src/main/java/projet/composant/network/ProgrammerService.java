package projet.composant.network;

import java.util.List;

import projet.composant.model.Programmer;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProgrammerService {

    @GET("get_all_cours")
    Call<List<Programmer>> apiRead();

    @POST("create_cours")
    Call<Programmer> apiCreate(@Body Programmer programmer);

    @PUT("modifier_cours/{id_cours}")
    Call<Programmer> apiUpdate(@Path("id_cours") int id,@Body Programmer programmer);

    @DELETE("supprimer_cours/{id_cours}")
    Call<Programmer> apiDelete(@Path("id_cours") int id);


}
