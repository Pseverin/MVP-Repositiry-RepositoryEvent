package com.acollider.repositoryevent.data.weather;

import android.content.res.Resources;

import com.acollider.repositoryevent.R;
import com.acollider.repositoryevent.data.weather.models.WeatherData;
import com.acollider.repositoryevent.data.weather.models.dto.GetLocationApiResponseDTO;
import com.acollider.repositoryevent.data.weather.repository.WeatherApiRepository;
import com.acollider.repositoryevent.other.RepositoryEvent;
import com.acollider.repositoryevent.other.exceptions.UserReadableException;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Supplier;

import org.javatuples.Pair;

import io.reactivex.Observable;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public class WeatherInteractor {

    private WeatherApiRepository weatherApiRepository;
    private Resources resources;

    public WeatherInteractor(Resources resources, WeatherApiRepository weatherApiRepository) {
        this.weatherApiRepository = weatherApiRepository;
        this.resources = resources;
    }

    public Observable<RepositoryEvent<Pair<String, WeatherData>>> getWeatherDataByCity(String cityQuery) {
        return weatherApiRepository.getLocationByQuery(cityQuery)
            .flatMap(repEvent ->
                repEvent.flatMapEventObs(locationsList -> {
                    Optional<GetLocationApiResponseDTO> locationOptional = Stream.of(locationsList).findFirst();
                    return locationOptional.map(location ->
                        weatherApiRepository.getWeaterData(location.getWoeid()).map(weatherDataEvent ->
                            weatherDataEvent.map(weatherData ->
                                Pair.with(location.getTitle(), weatherData))))
                        .orElseThrow((Supplier<RuntimeException>) () ->
                            new UserReadableException(resources.getString(R.string.try_another_city_name)));
                }));
    }
}
