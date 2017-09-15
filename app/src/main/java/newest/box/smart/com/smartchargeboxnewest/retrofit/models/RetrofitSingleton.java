package newest.box.smart.com.smartchargeboxnewest.retrofit.models;

import newest.box.smart.com.smartchargeboxnewest.Utils;
import newest.box.smart.com.smartchargeboxnewest.retrofit.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Deividas on 2017-09-02.
 */

public class RetrofitSingleton {

    private static RetrofitSingleton instance = null;
    private ApiService service;

    private RetrofitSingleton() {
        buildRetrofit();
    }

    public static RetrofitSingleton getInstance() {
        if (instance == null) {
            instance = new RetrofitSingleton();
        }
        return instance;
    }

    private void buildRetrofit() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpBuilder.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Utils.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpBuilder.build());
        Retrofit retrofit = builder.build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService() {
        return service;
    }
}
