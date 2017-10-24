package com.acollider.repositoryevent.data;

import android.content.res.Resources;

import com.acollider.repositoryevent.api.contracts.WeatherApi;
import com.acollider.repositoryevent.data.weather.WeatherInteractor;
import com.acollider.repositoryevent.data.weather.repository.WeatherApiRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
@Module
@Singleton
public class DataModule {

    @Provides
    @Singleton
    WeatherApiRepository provideDialogManager(WeatherApi weatherApi) {
        return new WeatherApiRepository(weatherApi);
    }

    @Provides
    @Singleton
    WeatherInteractor provideWeatherInteractor(Resources resources, WeatherApiRepository weatherApiRepository) {
        return new WeatherInteractor(resources, weatherApiRepository);
    }
}
