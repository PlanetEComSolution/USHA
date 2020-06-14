package helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/Login/GetUser")
    Call<ResponseBody> getUserDetails(@Field("UserId") String UserId,
                               @Field("Password") String Password
    );




}
