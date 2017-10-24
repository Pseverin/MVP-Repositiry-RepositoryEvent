package com.acollider.repositoryevent.ui.common;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */

public interface CommonView {

    void blockUi();

    void unblockUi();

    void showToastMessage(String text);

    void showToastMessage(int textRes);

    void goBack();

    void close();
}
