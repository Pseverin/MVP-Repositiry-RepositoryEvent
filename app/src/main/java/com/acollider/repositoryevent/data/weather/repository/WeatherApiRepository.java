package com.acollider.repositoryevent.data.weather.repository;

import com.acollider.repositoryevent.api.contracts.WeatherApi;
import com.acollider.repositoryevent.data.weather.models.WeatherData;
import com.acollider.repositoryevent.data.weather.models.dto.GetLocationApiResponseDTO;
import com.acollider.repositoryevent.data.weather.models.dto.WeatherApiResponseDTO;
import com.acollider.repositoryevent.other.RepositoryEvent;
import com.acollider.repositoryevent.utils.NetUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public class WeatherApiRepository {

    private WeatherApi weatherApi;

    public WeatherApiRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Observable<RepositoryEvent<WeatherData>> getWeaterData(Integer cityId) {
        return weatherApi.getWeatherData(cityId)
            .compose(obs -> NetUtils.decorateAndMap(obs, WeatherApiResponseDTO::toWeatherData));
    }

    public Observable<RepositoryEvent<List<GetLocationApiResponseDTO>>> getLocationByQuery(String cityQuery) {
        return weatherApi.getLocationByQuery(cityQuery)
            .compose(NetUtils::decorate);
    }
}
