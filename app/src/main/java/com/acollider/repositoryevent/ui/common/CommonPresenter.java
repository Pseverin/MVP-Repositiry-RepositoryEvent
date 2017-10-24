package com.acollider.repositoryevent.ui.common;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */

public interface CommonPresenter<V extends CommonView> {

    void setView(V view);
}
