package dpusha.app.com.usha.network

import android.util.Log
import dpusha.app.com.usha.activity.USHAApplication
//import dpusha.app.com.usha.orders_home.shared_preference.SharedPreferencesUtil
import dpusha.app.com.usha.shared_preference.SharedPreferencesUtil

import okhttp3.*
import org.json.JSONObject
import retrofit2.http.Headers

/**
 * Created by Unknown on 1/15/2020.
 **/

class SupportInterceptor: Interceptor, Authenticator {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticationRequest = request(originalRequest)
        val initialResponse = chain.proceed(authenticationRequest)

        Log.e("Log_originalRequest",originalRequest.toString());
        Log.e("Log_initialResponse",initialResponse.toString());
        when {
            //initialResponse.code() == 401 -> {
             initialResponse.code == 401 -> {

               var uid=  SharedPreferencesUtil.getUserId(USHAApplication.get());
                 var password=  SharedPreferencesUtil.getPassword(USHAApplication.get());
                val responseNewTokenLoginModel =  RetrofitManager.retrofit.create(API_Interface::class.java).getAuthorizationToken(uid,password,"password","U").execute()
                 //   val responseNewTokenLoginModel =  RetrofitManager.retrofit.create(API_Interface::class.java).getAuthorizationToken("0000106955","1234","password","U").execute()

                 Log.e("Log_uid",uid);
                 Log.e("Log_password",password);
                 Log.e("Log_responseNewTokenLoginModel",responseNewTokenLoginModel.toString());
                  when {
                    responseNewTokenLoginModel == null || responseNewTokenLoginModel.code() != 200 -> {
                       return initialResponse
                    }
                    else -> {
                       // val body = initialResponse.body?.string();

                        //initialResponse.body()?.close();
                        initialResponse.body?.close();
                        responseNewTokenLoginModel.body()?.close()
                      var  strResponse = responseNewTokenLoginModel.body()?.string()

                        val jsonObject = JSONObject(strResponse)
                        val accesstoken: String = jsonObject.getString("access_token")
                        val token_type = jsonObject.getString("token_type")

                        SharedPreferencesUtil.setAuthToken(USHAApplication.get(), "$token_type $accesstoken")


                        val newAuthenticationRequest = request(originalRequest)
                       return  chain.proceed(newAuthenticationRequest)
                    }
                }
            }
            else -> return initialResponse


        }

    }

    private fun request(originalRequest: Request): Request {
        val token= SharedPreferencesUtil.getAuthToken(USHAApplication.get())
        Log.e("Log_request",token);
        return originalRequest.newBuilder()
                .addHeader("Authorization",token.toString())
                .addHeader("Content-Type","application/json")
                .build()

    }

    /**
     * Authenticator for when the authToken need to be refresh and updated
     * everytime we get a 401 error code
     */

    override fun authenticate(route: Route?, response: Response): Request? {
        var requestAvailable: Request? = null
        val token: String = SharedPreferencesUtil.getAuthToken(USHAApplication.get())
      //  val token= SharedPreferencesUtil(LebanonNewsApplication.get(),null).getStringValue(Constants.ACCESS_TOKEN,"bearer")
           Log.e("Log_ token auth",token.toString());
      //  if (token.toString().equals(response.request().header("Authorization"))) {
        if (token.toString().equals(response.request.header("Authorization"))) {
            return null; // If we already failed with these credentials, don't retry.
        }

        try {

           // requestAvailable = response.request().newBuilder()
            requestAvailable = response.request.newBuilder()
                    .addHeader("Authorization", token.toString())
                    .addHeader("Content-Type","application/json")
                .build()
            return requestAvailable
        } catch (ex: Exception) { }
        return requestAvailable   }

}