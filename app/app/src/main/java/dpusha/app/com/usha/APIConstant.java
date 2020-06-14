package dpusha.app.com.usha;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConstant {

    public static final String BASE_URLE = "http://dpwebservicesbeta.businesstowork.com/api/Announcement/";
    private static Retrofit retrofit = null;
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(1000, TimeUnit.SECONDS)
            .build();




    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URLE)
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//if using RxJava only
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

