package dpusha.app.com.usha.network;


import java.util.List;

import dpusha.app.com.usha.GetLoginResponse;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.orders_home.model.OrderList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface API_Interface {

    @GET("/api/TrackOrder/GetOrdersByUserId")// get list of order
    Observable<List<OrderList>> GetOrderList(@Header("Authorization") String fina_token, @Query("id") String id);

    @FormUrlEncoded
    @POST("token")
    Call<ResponseBody> getAuthorizationToken(@Field("UserName") String UserName,
                                                 @Field("Password") String Password,
                                                 @Field("grant_type") String grant_type,
                                                 @Field("scope") String scope);


    @FormUrlEncoded
    @POST("api/Login/GetUser")
    Call<ResponseBody> getUser(@Field("UserId") String UserId,
                               @Field("Password") String Password
            );


    //get orderList

    @GET("/api/TrackOrder/GetOrdersByUserId")// get list of order
    Call<ResponseBody> getOrderList(@Query("id") String id);


    // get orderList Details
    @GET("/api/TrackOrder/GetDetailsByOrderId")// get list of order
    Call<ResponseBody> getOrderListDetails(@Query("id") String id);

    @GET("api/ProductCategory/GetProductCategory")
    Call<ResponseBody> getProductCategory(@Query("Id") String UserId
    );


    @FormUrlEncoded
    @POST("api/ProductCategory/GetDropDownsByProductCategory")
    Call<ResponseBody> getDivisionByProductCategory(@Field("UserId") String UserId,
                               @Field("Catcode") String Catcode,@Field("CallType") String CallType
    );

    @FormUrlEncoded
    @POST("/api/Order/GetProductForAutoComplete")
    Call<ResponseBody> getSKU(@Field("UserId") String UserId,
                                                    @Field("PreFix") String PreFix,@Field("DivCode") String DivCode,@Field("CallType") String CallType
    );
    @FormUrlEncoded
    @POST("/api/Order/GetProductForAutoComplete")
    Call<ResponseBody> getDescription(@Field("UserId") String UserId,
                              @Field("PreFix") String PreFix,@Field("DivCode") String DivCode,@Field("CallType") String CallType
    );

    @FormUrlEncoded
    @POST("/api/Template/InsertDraft")
    Call<ResponseBody>saveDraft(@Field("cartDTO") CartItem cartItem);


    @POST("/api/Template/InsertDraft")
    Call<ResponseBody>saveDraft2(@Body CartItem cartItem);


    @GET("api/Template/GetDraft")// GET /api/Template/GetDraft
    Call<ResponseBody> getDraft();

    @FormUrlEncoded
    @POST("/api/Template/InsertTemplate")
    Call<ResponseBody>saveTemplate(@Field("cartDTO") CartItem cartItem);

    @FormUrlEncoded
    @POST("/api/Order/InsertOrder")
    Call<ResponseBody>saveOrder(@Field("cartDTO") CartItem cartItem);


    @FormUrlEncoded
    @POST("http://172.16.1.31:8080/GetPrice")
    Call<ResponseBody>getPrice(@Field("?") Material material);


    @FormUrlEncoded
    @POST("/api/Common/GetDropDowns")
    Call<ResponseBody>getShipToParty(@Field("ColumnName") String ColumnName,
                                   @Field("ColumnValue") String ColumnValue,
                                   @Field("TableName") String TableName,
                                   @Field("LookupType") String LookupType);

}
