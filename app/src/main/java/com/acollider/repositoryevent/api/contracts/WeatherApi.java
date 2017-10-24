package com.acollider.repositoryevent.api.contracts;

import com.acollider.repositoryevent.data.weather.models.dto.GetLocationApiResponseDTO;
import com.acollider.repositoryevent.data.weather.models.dto.WeatherApiResponseDTO;
import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public interface WeatherApi {

    @GET("api/location/{cityId}/")
    Observable<Result<WeatherApiResponseDTO>> getWeatherData(
        @Path("cityId") Integer cityId);

    @GET("api/location/search/")
    Observable<Result<List<GetLocationApiResponseDTO>>> getLocationByQuery(
        @Query("query") String cityQuery);
}
