package org.efit.mobile.restapi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TheMac on 1/6/17.
 */

public class RestClient {
    private static RestClient instance = null;
    private TentorAPI TentorApi;
    private String URL_HTTP_API;

    // now you can use client`
    private RestClient() {
        if (Sharepref.getString(Constant.BASE_URI) == null) {
            URL_HTTP_API = Constant.BASE_API;
        } else {
            URL_HTTP_API =Sharepref.getString(Constant.BASE_URI);
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(60, TimeUnit.SECONDS);
        b.writeTimeout(60, TimeUnit.SECONDS).addNetworkInterceptor(interceptor);
         b.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("X-API-KEY","16d7b0011b4f54c1a8f382342c5790b69ab46516")
                        .addHeader("CLIENT_ID","fit_7"); // <-- this is the important line
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
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

    public static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public TentorAPI getTentorApi() {
        return TentorApi;
    }
}
