package dpusha.app.com.usha;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface Dp_Usha_Interface {


    @FormUrlEncoded
    @POST("ForgotPassword")
    Call<List<ForgotPassword>> ForgetPasswordApi(@Header("Authorization")String token,
                                         @Field("UserId") String UserId

    );


}
