package com.acollider.repositoryevent.data.weather.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

@SerializedName("title")
@Expose
public String title;
@SerializedName("slug")
@Expose
public String slug;
@SerializedName("url")
@Expose
public String url;
@SerializedName("crawl_rate")
@Expose
public Integer crawlRate;

}