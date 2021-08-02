package projet.composant.service;

import java.util.List;

import projet.composant.model.Cours;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProgrammerService {

    @GET("get_all_cours")
    Call<List<Cours>> apiRead();

    @POST("create_cours")
    Call<Cours> apiCreate(@Body Cours programmer);
    /*
    @POST("modifier_cours")
    @Headers({"Accept: application/json"})
    Call<Cours> apiUpdate(@Header("id_cours") String id,@Header("vh") String vh,@Header("filiere") String filiere,@Header("classe") String classe,@Header("matiere") String matiere);
    */

    @POST("modifier_cours")
    Call<Cours> apiUpdate(@Body Cours programmer);

    @POST("supprimer_cours")
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    Call<Cours> apiDelete(@Field("id_cours") String id_cours);


}
