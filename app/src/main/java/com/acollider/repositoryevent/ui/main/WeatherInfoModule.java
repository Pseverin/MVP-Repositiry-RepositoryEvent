package com.acollider.repositoryevent.ui.main;

import com.acollider.repositoryevent.data.weather.WeatherInteractor;
import com.acollider.repositoryevent.other.PerInstance;

import dagger.Module;
import dagger.Provides;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
@Module
public class WeatherInfoModule {
    @Provides
    @PerInstance
    WeatherInfoPresenter provideCreateClientPresenter(WeatherInteractor weatherInteractor) {
        return new WeatherInfoPresenter(weatherInteractor);
    }
}
