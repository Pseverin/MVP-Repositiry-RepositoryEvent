package com.acollider.repositoryevent.data.weather.repository;

import com.acollider.repositoryevent.data.weather.models.WeatherData;
import com.acollider.repositoryevent.other.RepositoryEvent;
import com.annimon.stream.Optional;

import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public class WeatherMemoryRepository {

    private Map<String, WeatherData> weatherDataMap = new HashMap<>();

    public void addWeatherData(Pair<String, WeatherData> data) {
        weatherDataMap.put(data.getValue0(), data.getValue1());
    }

    public Observable<RepositoryEvent<Pair<String, WeatherData>>> getWeatherData(String cityName) {
        Optional<WeatherData> weatherData = Optional.ofNullable(weatherDataMap.get(cityName));
        return Observable.just(weatherData.map(data -> RepositoryEvent.memoryEvent(Pair.with(cityName, data)))
            .orElse(RepositoryEvent.memoryEvent(null)));
    }
}
