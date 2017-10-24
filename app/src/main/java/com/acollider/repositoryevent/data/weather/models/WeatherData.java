package com.acollider.repositoryevent.data.weather.models;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public class WeatherData {

    public final String minTemp;
    public final String maxTemp;

    public WeatherData(Float minTemp, Float maxTemp) {
        this.minTemp = minTemp.toString();
        this.maxTemp = maxTemp.toString();
    }
}
