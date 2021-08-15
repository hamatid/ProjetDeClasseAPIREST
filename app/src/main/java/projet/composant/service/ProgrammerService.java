package projet.composant.service;

import java.util.List;

import okhttp3.RequestBody;
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
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProgrammerService {

    @GET("api/")
    Call<List<Cours>> apiRead();

    @POST("api/create/")
    Call<Cours> apiCreate(@Body Cours programmer);


    @PUT("api/update/{id}/")
    Call<Cours> apiUpdate(@Path("id") int id,@Body Cours programmer);


    @DELETE("api/delete/{id}/")
    Call<Cours> apiDelete(@Path("id") int id);


}
