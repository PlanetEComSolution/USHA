package dpusha.app.com.usha.network;


import java.util.List;

import dpusha.app.com.usha.GetLoginResponse;
import dpusha.app.com.usha.Login;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.ForgotPassword;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.orders_home.model.OrderList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @FormUrlEncoded
    @POST("api/Login/ForgotPassword")
    Call<ResponseBody> getPassword(@Field("UserId") String id);

    @FormUrlEncoded
    @POST("api/Login/ChangePassword")
    Call<ResponseBody> changePassword(@Header("Authorization") String fina_token,
                                      @Field("OldPassword") String oldPass,
                                      @Field("Password") String password,
                                      @Field("NewPassword") String newPass);

    //get orderList

 @GET("api/ContactUs/GetUshaContactDetails")
    Call<ResponseBody> getContactDetails(@Header("Authorization") String token);

    @GET("/api/TrackOrder/GetOrdersByUserId")// get list of order
    Call<ResponseBody> getOrderList(@Query("UserId") String id);


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
    @POST("api/Division/GetDivisionsByCatCode")
    Call<ResponseBody> getDivisionByCategoryCode(
                                                    @Field("CatCode") String CatCode );


//  http://dpwebservicesbeta.businesstowork.com/api/ProductCategory/GetDropDownsByProductCategory

    @FormUrlEncoded
    @POST("api/ProductCategory/GetDropDownsByProductCategory")
    Call<ResponseBody> getCategoryTypeByProductCategory(@Field("CatCode") String CatCode,
                                                    @Field("DivCode") String DivCode,
                                                        @Field("CallType") String CallType
    );


    @FormUrlEncoded
    @POST("api/ProductCategory/GetDropDownsByProductCategory")
    Call<ResponseBody> getSubCategoryByProductCategory(@Field("CatCode") String CatCode,
                                                        @Field("DivCode") String DivCode,
                                                       @Field("CatType") String CatType,
                                                        @Field("CallType") String CallType
    );



    @FormUrlEncoded
    @POST("/api/Order/GetProductsForList")
    Call<ResponseBody>getProductsForList(@Field("DivCode") String DivCode,
                                         @Field("CategoryType") String CategoryType,
                                         @Field("SubCategory") String SubCategory,
                                                        @Field("CallType") String CallType,
                                                        @Field("PageNo") int PageNo,
                                         @Field("PageSize") int PageSize
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


    //@Headers( "Content-Type: application/json" )
    @POST("/api/Template/InsertDraft")
    Call<ResponseBody>saveDraft(@Body CartItem cartItem);


    @GET("api/Template/GetDraft")// GET /api/Template/GetDraft
    Call<ResponseBody> getDraft();

    @GET("api/Template/GetByUserId")// GET /api/Template/GetByUserId
    Call<ResponseBody> getTemplate();

    @GET("api/Template/GetDetailsByTemplateId")//api/Template/GetDetailsByTemplateId?id=1591373624457
    Call<ResponseBody> getTemplateDetails(@Query("id") String id);

    @DELETE("api/Template/Delete")//api/Template/GetDetailsByTemplateId?id=1591373624457
    Call<ResponseBody> deleteTemplate(@Query("Id") String id);


    @POST("/api/Template/InsertTemplate")
    Call<ResponseBody>saveTemplate(@Body CartItem cartItem);


    @POST("/api/Order/InsertOrder")
    Call<ResponseBody>saveOrder(@Body CartItem cartItem);

    // @Field parameters can only be used with form encoding.

    @FormUrlEncoded
    @POST("/api/Order/GetProductDetails")
    Call<ResponseBody>getProductDetails(@Field("SKU") String SKU);


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
