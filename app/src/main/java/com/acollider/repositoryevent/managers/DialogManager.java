package com.acollider.repositoryevent.managers;

import android.app.ProgressDialog;
import android.content.Context;

import com.acollider.repositoryevent.R;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public class DialogManager {

    private final String TAG = getClass().getSimpleName();

    private ProgressDialog progressDialog;

    public void showProgressProcessingDialog(Context context) {
        showProgressDialog(context.getString(R.string.please_wait), context);
    }

    public void showProgressDialog(String message, Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
