package com.acollider.repositoryevent.data.weather.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLocationApiResponseDTO {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("woeid")
    @Expose
    private Integer woeid;
    @SerializedName("latt_long")
    @Expose
    private String lattLong;

    public String getTitle() {
        return title;
    }

    public String getLocationType() {
        return locationType;
    }

    public Integer getWoeid() {
        return woeid;
    }

    public String getLattLong() {
        return lattLong;
    }
}