package com.acollider.repositoryevent.app;

import android.content.Context;
import android.content.res.Resources;

import com.acollider.repositoryevent.api.ApiModule;
import com.acollider.repositoryevent.data.DataModule;
import com.acollider.repositoryevent.data.weather.WeatherInteractor;
import com.acollider.repositoryevent.managers.DialogManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
@Component(modules = {
    AndroidModule.class,
    CommonModule.class,
    ApiModule.class,
    DataModule.class
})
@Singleton
public interface AppComponent {

    Context getAppContext();

    Resources getResources();

    DialogManager getDialogManager();

    Gson getGson();

    WeatherInteractor getWeatherInteractor();
}
