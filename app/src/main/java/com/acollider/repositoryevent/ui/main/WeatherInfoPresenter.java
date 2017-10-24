package com.acollider.repositoryevent.ui.main;

import com.acollider.repositoryevent.data.weather.WeatherInteractor;
import com.acollider.repositoryevent.data.weather.models.WeatherData;
import com.acollider.repositoryevent.ui.common.CommonModelConsumer;
import com.acollider.repositoryevent.ui.common.CommonPresenterImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class WeatherInfoPresenter extends CommonPresenterImpl<WeatherInfoView> {

    private WeatherInteractor weatherInteractor;
    private String cityName;

    public WeatherInfoPresenter(WeatherInteractor weatherInteractor) {
        this.weatherInteractor = weatherInteractor;
    }

    public void onCityTextChanged(String cityName) {
        this.cityName = cityName;
    }

    public void onSearchClick() {
        weatherInteractor.getWeatherDataByCity(cityName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(CommonModelConsumer.from(weatherDataPair -> {
                String city = weatherDataPair.getValue0();
                WeatherData weatherData = weatherDataPair.getValue1();

                getViewOptional().ifPresent(view ->
                    view.fillWithWeatherData(city, weatherData.minTemp, weatherData.maxTemp));
            }, getUiBlockHandler()), getErrorConsumer());
    }
}
