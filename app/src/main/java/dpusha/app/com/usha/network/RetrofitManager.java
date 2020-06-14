package dpusha.app.com.usha.network;
import okhttp3.OkHttpClient;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

//import dpusha.app.com.usha.BuildConfig;
import dpusha.app.com.usha.R;
import dpusha.app.com.usha.activity.USHAApplication;
import dpusha.app.com.usha.model.CartItem;
import dpusha.app.com.usha.model.Material;
import dpusha.app.com.usha.orders_home.util.Constants;
import dpusha.app.com.usha.orders_home.util.Dialogs;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

//import com.bumptech.glide.request.RequestListener;
import com.android.volley.BuildConfig;


public class RetrofitManager implements OnRetryCallback {

    public static Retrofit retrofit = null;
    public static RetrofitManager retrofitManager = null;
    public static API_Interface retroService = null;
    private Call<ResponseBody> call = null;
    //private Call<AuthenticationToken> call2 = null;

    private Callback<ResponseBody> mCallback = null;
    private final String format = "json";
    private String TAG = "RetrofitManager";
    AppCompatActivity activity = null;
    private OnRetryCallback mRetryCallback = this;
    private static String BASE_URL;

    static {

        BASE_URL = Constants.BASE_URL_LIVE;

    }


    private RetrofitManager() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(10, TimeUnit.SECONDS);
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.addInterceptor(new ConnectivityInterceptor(USHAApplication.get()));
        httpClient.addInterceptor(new SupportInterceptor());

        if (BuildConfig.DEBUG) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        retroService = retrofit.create(API_Interface.class);

    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager();
        }
        return retrofitManager;
    }


    /**
     * Method to resolve the API callbacks
     *
     * @param mRequestListener
     * @param mContext
     * @param mApiType
     * @param showProgress
     */
    public void performCallback(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final boolean showProgress) {

        activity = (AppCompatActivity) mContext;

     /*   if (mContext == null) {
            Util.DEBUG_LOG(1, TAG, "context is null");
            return;
        }*/

        if (showProgress) {
            Dialogs.showProgressDialog(activity, "Please wait..");
        }

        mCallback = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    APIError error = null;
                    try {
                        // error = ErrorUtils.parseError(response, retrofitManager);

                        if (error == null) {
                           // Log.e("Log_Response", response.body().string());
                            mRequestListener.onSuccess(response, mApiType);


                        } else {
                            //Log.e("Log_Error", response.toString());
                            mRequestListener.onApiException(error, response, mApiType);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    try {

                        String strResponse = response.errorBody().string();
                        Log.d(TAG, strResponse);
                        JSONObject obj = new JSONObject(strResponse);
                        String s = obj.getString("details");
                        if (s.equalsIgnoreCase("Expired token") || s.equalsIgnoreCase("Signature verification failed")) {
                            // Intent in = new Intent(activity, SplashActivity.class);
                            //  activity.startActivity(in);
                            //  activity.finish();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                  //  Log.e("Log_Failure", response.toString());
                    mRequestListener.onFailure(response, mApiType);
                }

                Dialogs.hideProgressDialog(mContext);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                try {
                    if (t instanceof SocketTimeoutException) {
                        Dialogs.showTryAgainDialog(mContext, mContext.getString(R.string.ERROR_SOCKET), mRetryCallback);

                    } else if (t instanceof NoConnectivityException) {
                        Dialogs.showAlert(mContext, mContext.getString(R.string.ERROR_INTERNET));

                    } else if (t instanceof NetworkErrorException) {
                        // Dialogs.showAlert(mContext, t.getMessage());
                        Dialogs.showAlert(mContext, mContext.getString(R.string.ERROR_SOMETHING_WENT_WRONG));
                    }
                    Dialogs.hideProgressDialog(mContext);
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                mRequestListener.onFailure(t, mApiType);


            }
        };

        call.enqueue(mCallback);
    }


    @Override
    public void OnRetry(boolean isRetry) {
        if (isRetry) {
            Dialogs.showProgressDialog(activity, "Please wait..");
            call.clone().enqueue(mCallback);
        } else {

        }
    }

    /**
     * Method to cancel the call
     */
    public void cancelRequest() {
        call.cancel();
    }


    public void getAuthToken(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userId,
            final String password,
            final boolean showProgress) {

        call = retroService.getAuthorizationToken(userId, password, "password", "U");
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void Login(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final String password,
            final boolean showProgress) {

        call = retroService.getUser(userID, password);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getPassword(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            //test
            //String token,
            final boolean showProgress){
        call = retroService.getPassword(userID);
        //   call = retroService.getUser(SharedPreferencesUtil.getAuthToken(mContext),userID,password);
        performCallback(mRequestListener, mContext, mApiType, showProgress);
    }

    public void changePassword(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            String oldPass,
            String password,
            String newPass,

            String token,
            final boolean showProgress){
        call = retroService.changePassword(token,oldPass,password,newPass);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }
    // get orderList

    public void GetOrderList(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final boolean showProgress) {

        call = retroService.getOrderList(userID);
        //   call = retroService.getUser(SharedPreferencesUtil.getAuthToken(mContext),userID,password);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    //get orderList Details


    public void GetOrderListDetails(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final boolean showProgress) {

        call = retroService.getOrderListDetails(userID);
        //   call = retroService.getUser(SharedPreferencesUtil.getAuthToken(mContext),userID,password);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void getProductCategory(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final boolean showProgress) {

        call = retroService.getProductCategory(userID);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getDivisionByProductCategory(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final String catCode,
            final String callType,
            final boolean showProgress) {

        call = retroService.getDivisionByProductCategory(userID, catCode, callType);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }
    public void getDivisionByCategoryCode(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String catCode,
            final boolean showProgress) {

        call = retroService.getDivisionByCategoryCode(catCode);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }
    public void getCategoryTypeByProductCategory(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String CatCode,
            final String DivCode,
            final String callType,
            final boolean showProgress) {

        call = retroService.getCategoryTypeByProductCategory(CatCode, DivCode, callType);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getSubCategoryByProductCategory(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String CatCode,
            final String DivCode,
            final String CatType,
            final String callType,
            final boolean showProgress) {

        call = retroService.getSubCategoryByProductCategory(CatCode, DivCode, CatType, callType);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void getProductsForList(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,

            final String DivCode,
            final String CategoryType,
            final String SubCategory,
            final String callType,
            final int PageNo,
            final int PageSize,
            final boolean showProgress) {
//        Log.e("Log_ Api: GetProductsForList", "DivCode " + DivCode + " CategoryType " + CategoryType + " SubCategory " + SubCategory + " callType " + callType + " PageNo " + PageNo + " PageSize " + PageSize);
        call = retroService.getProductsForList(DivCode, CategoryType, SubCategory, callType, PageNo, PageSize);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void getSKU(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final String preFix,
            final String divCode,
            final String callType,
            final boolean showProgress) {

        call = retroService.getSKU(userID, preFix, divCode, callType);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getDescription(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String userID,
            final String preFix,
            final String divCode,
            final String callType,
            final boolean showProgress) {

        call = retroService.getDescription(userID, preFix, divCode, callType);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void saveDraft(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final CartItem cartItem,

            final boolean showProgress) {

        call = retroService.saveDraft(cartItem);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getDraft(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,

            final boolean showProgress) {

        call = retroService.getDraft();
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void saveTemplate(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final CartItem cartItem,

            final boolean showProgress) {

        call = retroService.saveTemplate(cartItem);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void placeOrder(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final CartItem cartItem,
            final boolean showProgress) {

        call = retroService.saveOrder(cartItem);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void updateOrder(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final CartItem cartItem,
            final boolean showProgress) {

        call = retroService.updateOrder(cartItem);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void getPrice(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final Material material,

            final boolean showProgress) {

        call = retroService.getPrice(material);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void getProductDetails(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String SKU,

            final boolean showProgress) {

        call = retroService.getProductDetails(SKU);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getShipToParty(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final boolean showProgress) {

        call = retroService.getShipToParty("LookupName", "LookupValue", "LookUps", "ShipToParty");
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void getTemplate(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final boolean showProgress
    ) {

        call = retroService.getTemplate();
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


    public void getTemplateDetailsByTemplateId(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String TemplateId,
            final boolean showProgress
    ) {

        call = retroService.getTemplateDetails(TemplateId);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }

    public void deleteTemplate(
            final RequestListener mRequestListener,
            final Context mContext,
            final Constants.API_TYPE mApiType,
            final String TemplateId,
            final boolean showProgress
    ) {

        call = retroService.deleteTemplate(TemplateId);
        performCallback(mRequestListener, mContext, mApiType, showProgress);

    }


}
