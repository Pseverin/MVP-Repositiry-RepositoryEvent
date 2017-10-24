package com.acollider.repositoryevent.app;

import java.util.concurrent.TimeUnit;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project NioxinAndroid
 */
public class AppSettings {

    public final static long CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(10);
    public final static String SERVER_URL = "https://www.metaweather.com/";
}