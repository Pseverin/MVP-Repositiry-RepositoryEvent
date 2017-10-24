package com.acollider.repositoryevent.ui.common.common_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.acollider.repositoryevent.R;
import com.acollider.repositoryevent.app.AppComponent;
import com.acollider.repositoryevent.managers.DialogManager;
import com.acollider.repositoryevent.other.PerInstance;
import com.acollider.repositoryevent.ui.common.CommonView;

import javax.inject.Inject;

import butterknife.ButterKnife;

import static com.acollider.repositoryevent.app.App.getAppComponent;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public abstract class CommonActivity extends AppCompatActivity implements CommonView {

    @Inject
    public DialogManager dialogManager;

    @Override
    public void onStop() {
        super.onStop();
        unblockUi();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());

        DaggerCommonActivity_Component.builder()
            .appComponent(getAppComponent())
            .build().inject(this);
        initComponent();
        ButterKnife.bind(this);
    }

    protected abstract void initComponent();

    protected abstract int getContentViewRes();

    @Override
    public void blockUi() {
        if (!isDestroyed()) {
            dialogManager.showProgressProcessingDialog(this);
        }
    }

    @Override
    public void unblockUi() {
        if (!isDestroyed()) {
            dialogManager.dismissProgressDialog();
        }
    }

    public void animateActivityChangingToRight() {
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void animateActivityChangingToLeft() {
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void showToastMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastMessage(int textRes) {
        Toast.makeText(this, getString(textRes), Toast.LENGTH_LONG).show();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }

    @Override
    public void goBack() {
        close();
        animateActivityChangingToLeft();
    }

    @PerInstance
    @dagger.Component(
        dependencies = AppComponent.class
    )
    interface Component {
        void inject(CommonActivity activity);
    }
}
