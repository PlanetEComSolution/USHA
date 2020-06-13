package dpusha.app.com.usha.Interface;

import java.util.List;

import dpusha.app.com.usha.model.Changpassword;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ChangpasswordInterface {

    @FormUrlEncoded
    @POST("ChangePassword")
    Call<List<Changpassword>> ChangePassword(String s, @Header("Authorization") String token,
                                             @Field("UserId") String UserId,
                                             @Field("NewPassword") String NewPassword


    );
/*
    Call<List<Changpassword>> ChangePassword(String bearer__, String user_id, String oldPassword, String newPassword);
    */
    /*@FormUrlEncoded
    @GET("/posts")
    Observable<JsonElement> getDataFromService(
            @Header("Authorization") token: String = "Bearer " + util.getToken(),
    @QueryMap HashMap<String, Object> queryParams
);
    here's my Request:

    @GET("user/wishlist")
    Call<WishListModel> getWishList(@Header("Authorization") String BearerToken);
    and here's the call:

    Retrofit retrofit = new Retrofit.Builder().baseUrl("URL").addConverterFactory(GsonConverterFactory.create()).build();
    RequestInterface requestInterface = retrofit.create(RequestInterface.class);
    Call<WishListModel> call = requestInterface.getWishList("Bearer "+token);*/
  /*  {
        "UserId": "0000106849",


            "NewPassword": "1234",
            "OldPassword": "1234"

    }*/

    /* Call<List<Changpassword>> ChangePassword(String bearer, String newPassword, String oldPassword);
    */
}
