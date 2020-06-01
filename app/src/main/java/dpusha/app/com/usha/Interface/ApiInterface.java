package dpusha.app.com.usha.Interface;

import java.util.List;

import dpusha.app.com.usha.APIConstant;
import dpusha.app.com.usha.GetLoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {



        @FormUrlEncoded
        @POST("GetUser")
        Call<List<GetLoginResponse>> GetUser(@Header("Authorization")String token,
                                           @Field("UserId") String UserId,
                                           @Field("Password") String Password
        );
    }

