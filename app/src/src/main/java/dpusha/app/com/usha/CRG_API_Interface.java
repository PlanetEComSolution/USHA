package dpusha.app.com.usha;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

public interface CRG_API_Interface {

    @FormUrlEncoded
    @POST("ForgotPassword")
    Call<List<GetLoginResponse>> ForgotPassword();



}
