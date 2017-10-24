package com.acollider.repositoryevent.data.weather.models.dto;

import com.acollider.repositoryevent.data.weather.models.WeatherData;
import com.annimon.stream.Stream;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherApiResponseDTO {

    @SerializedName("consolidated_weather")
    @Expose
    public List<ConsolidatedWeather> consolidatedWeather = null;
    @SerializedName("time")
    @Expose
    public String time;
    @SerializedName("sun_rise")
    @Expose
    public String sunRise;
    @SerializedName("sun_set")
    @Expose
    public String sunSet;
    @SerializedName("timezone_name")
    @Expose
    public String timezoneName;
    @SerializedName("parent")
    @Expose
    public Parent parent;
    @SerializedName("sources")
    @Expose
    public List<Source> sources = null;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("location_type")
    @Expose
    public String locationType;
    @SerializedName("woeid")
    @Expose
    public Integer woeid;
    @SerializedName("latt_long")
    @Expose
    public String lattLong;
    @SerializedName("timezone")
    @Expose
    public String timezone;

    public List<ConsolidatedWeather> getConsolidatedWeather() {
        return consolidatedWeather;
    }

    public WeatherData toWeatherData() {
        return Stream.of(consolidatedWeather).findFirst().map(weather ->
            new WeatherData(weather.getMinTemp(), weather.getMaxTemp())).orElse(null);
    }
}