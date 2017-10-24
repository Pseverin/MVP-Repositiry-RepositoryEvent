package com.acollider.repositoryevent.ui.main;

import com.acollider.repositoryevent.ui.common.CommonView;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public interface WeatherInfoView extends CommonView {

    void fillWithWeatherData(String cityName, String minTemp, String maxTemp,
                             String weatherType, String windSpeed);
}
