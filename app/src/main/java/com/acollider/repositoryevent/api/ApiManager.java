package com.acollider.repositoryevent.api;

import android.content.Context;

import com.acollider.repositoryevent.api.utils.LoggingInterceptor;
import com.acollider.repositoryevent.app.AppSettings;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.acollider.repositoryevent.utils.NetUtils.isNetworkConnected;
import static com.acollider.repositoryevent.utils.NetUtils.noConnectionException;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public class ApiManager {

    private final static long HTTP_TIMEOUT = AppSettings.CONNECTION_TIMEOUT;

    private final OkHttpClient okHttpClient;

    private Retrofit retrofit;

    @Inject
    public ApiManager(String baseUrl, Gson gson, Context appContext) {
        okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .addInterceptor(new ConnectionCheckInterceptor(appContext))
            .addInterceptor(chain -> {
                Request original = chain.request();

                Request request = original.newBuilder()
                    .header("Accept", "*/*")
                    .method(original.method(), original.body())
                    .build();

                return chain.proceed(request);
            })
            .addInterceptor(new LoggingInterceptor())
            .build();

        retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private static class ConnectionCheckInterceptor implements Interceptor {

        private Context appContext;

        public ConnectionCheckInterceptor(Context appContext) {
            this.appContext = appContext;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!isNetworkConnected(appContext)) {
                throw noConnectionException();
            }
            return chain.proceed(chain.request());
        }
    }
}