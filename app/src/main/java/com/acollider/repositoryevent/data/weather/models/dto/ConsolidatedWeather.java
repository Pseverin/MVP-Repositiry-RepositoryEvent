package com.acollider.repositoryevent.data.weather.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsolidatedWeather {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("weather_state_name")
    @Expose
    private String weatherStateName;
    @SerializedName("weather_state_abbr")
    @Expose
    private String weatherStateAbbr;
    @SerializedName("wind_direction_compass")
    @Expose
    private String windDirectionCompass;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("applicable_date")
    @Expose
    private String applicableDate;
    @SerializedName("min_temp")
    @Expose
    private Float minTemp;
    @SerializedName("max_temp")
    @Expose
    private Float maxTemp;
    @SerializedName("the_temp")
    @Expose
    private Float theTemp;
    @SerializedName("wind_speed")
    @Expose
    private Float windSpeed;
    @SerializedName("wind_direction")
    @Expose
    private Float windDirection;
    @SerializedName("air_pressure")
    @Expose
    private Float airPressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("visibility")
    @Expose
    private Float visibility;
    @SerializedName("predictability")
    @Expose
    private Integer predictability;

    public Long getId() {
        return id;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public String getCreated() {
        return created;
    }

    public String getApplicableDate() {
        return applicableDate;
    }

    public Float getMinTemp() {
        return minTemp;
    }

    public Float getMaxTemp() {
        return maxTemp;
    }

    public Float getTheTemp() {
        return theTemp;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public Float getWindDirection() {
        return windDirection;
    }

    public Float getAirPressure() {
        return airPressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Float getVisibility() {
        return visibility;
    }

    public Integer getPredictability() {
        return predictability;
    }
}