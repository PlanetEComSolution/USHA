package dpusha.app.com.usha.Interface;

import java.util.List;

import dpusha.app.com.usha.Apiclint.Changepasswordclint;
import dpusha.app.com.usha.GetLoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Anouncementinterface {

   /* @FormUrlEncoded
    @GET("GetAll")
    Call<List<GetLoginResponse>> GetAll(@Header("Authorization")String token

    );*/
    @GET("GetAll")
    Call<Changepasswordclint> GetAll(@Header("Authorization") String token); /*@Query("id") String id)*/
}

