package com.acollider.repositoryevent.api;

import android.content.Context;

import com.acollider.repositoryevent.api.contracts.WeatherApi;
import com.acollider.repositoryevent.app.AppSettings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
            .create();
    }

    @Provides
    @Singleton
    public ApiManager provideApiManager(Context appContext, Gson gson) {
        return new ApiManager(AppSettings.SERVER_URL, gson, appContext);
    }

    @Provides
    @Singleton
    public WeatherApi provideUserApi(ApiManager apiManager) {
        return apiManager.getRetrofit().create(WeatherApi.class);
    }
}
