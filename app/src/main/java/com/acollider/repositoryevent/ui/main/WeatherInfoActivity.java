package com.acollider.repositoryevent.ui.main;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.acollider.repositoryevent.R;
import com.acollider.repositoryevent.app.AppComponent;
import com.acollider.repositoryevent.other.PerInstance;
import com.acollider.repositoryevent.ui.common.common_activity.CommonActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.acollider.repositoryevent.app.App.getAppComponent;

public class WeatherInfoActivity extends CommonActivity implements WeatherInfoView {

    @BindView(R.id.et_city_name)
    EditText etCityName;

    @BindView(R.id.tv_min_temp)
    TextView tvMinTemp;

    @BindView(R.id.tv_max_temp)
    TextView tvMaxTemp;

    @BindView(R.id.tv_city_name)
    TextView tvCityName;

    @BindView(R.id.tv_weather_type)
    TextView tvWeatherType;

    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;

    @Inject
    WeatherInfoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
        setupView();
    }

    private void setupView() {
        etCityName.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                presenter.onSearchClick();
                return true;
            }
            return false;
        });
    }

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_weather_info;
    }

    @OnTextChanged(R.id.et_city_name)
    void onCityChanged(CharSequence text) {
        presenter.onCityTextChanged(text.toString());
    }

    @OnClick(R.id.btn_search)
    void onSearchClick(View v) {
        presenter.onSearchClick();
    }

    @Override
    protected void initComponent() {
        DaggerWeatherInfoActivity_Component.builder()
            .appComponent(getAppComponent())
            .build().inject(this);
    }

    @Override
    public void fillWithWeatherData(String cityName, String minTemp, String maxTemp,
                                    String weatherType, String windSpeed) {
        tvCityName.setText(cityName);
        tvMinTemp.setText(minTemp);
        tvMaxTemp.setText(maxTemp);
        tvWeatherType.setText(weatherType);
        tvWindSpeed.setText(windSpeed);
    }

    @PerInstance
    @dagger.Component(
        dependencies = {
            AppComponent.class
        },
        modules = {
            WeatherInfoModule.class
        }
    )
    interface Component {
        void inject(WeatherInfoActivity activity);
    }
}
