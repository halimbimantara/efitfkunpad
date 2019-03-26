package org.efit.mobile.restapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TheMac on 1/6/17.
 */

public class RestClientEabsen {
    private static RestClientEabsen instance = null;
    private TentorAPI TentorApi;
    private String URL_HTTP_API;

    // now you can use client`
    private RestClientEabsen() {
        if (Sharepref.getString(Constant.BASE_URI) == null) {
            URL_HTTP_API = Constant.BASE_API_EABSENSI;
        } else {
            URL_HTTP_API =Sharepref.getString(Constant.BASE_URI);
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(60, TimeUnit.SECONDS);
        b.writeTimeout(60, TimeUnit.SECONDS).addNetworkInterceptor(interceptor);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = b.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_HTTP_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TentorApi = retrofit.create(TentorAPI.class);
    }

    public static RestClientEabsen getInstance() {
        if (instance == null) {
            instance = new RestClientEabsen();
        }
        return instance;
    }

    public TentorAPI getEabsen() {
        return TentorApi;
    }
}
