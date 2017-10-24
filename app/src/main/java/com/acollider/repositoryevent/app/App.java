package com.acollider.repositoryevent.app;


import android.app.Application;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
            .androidModule(new AndroidModule(this))
            .build();
    }
}