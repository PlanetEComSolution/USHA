package helper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
public static String BaseUrl="http://dpwebservicesbeta.businesstowork.com/";
    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder().build()).build();
    public static ApiService getLogin(){
        return retrofit.create(ApiService.class);
    }


}
