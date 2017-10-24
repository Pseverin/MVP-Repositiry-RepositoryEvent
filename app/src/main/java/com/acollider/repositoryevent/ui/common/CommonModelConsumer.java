package com.acollider.repositoryevent.ui.common;


import com.acollider.repositoryevent.other.RepositoryEvent;
import com.acollider.repositoryevent.other.UiBlockHandler;
import com.acollider.repositoryevent.utils.functions.Action1;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author Severyn Parkhomenko <sparkhomenko@grossum.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public abstract class CommonModelConsumer<D> implements Consumer<RepositoryEvent<D>> {

    private UiBlockHandler uiBlockHandler;

    public CommonModelConsumer(UiBlockHandler uiBlockHandler) {
        this.uiBlockHandler = uiBlockHandler;
    }

    @Override
    public void accept(@NonNull RepositoryEvent<D> event) throws Exception {
        if (event.getEventType() == RepositoryEvent.EventType.START_LONG_OPERATION_ACTION) {
            uiBlockHandler.onBlockUi();
        } else {
            onDataEmit(event.getData());
            uiBlockHandler.onUnBlockUi();
        }
    }

    protected abstract void onDataEmit(D data);

    public static <D> CommonModelConsumer<D> from(Action1<D> dataHandler, UiBlockHandler uiBlockHandler) {
        return new CommonModelConsumer<D>(uiBlockHandler) {
            @Override
            protected void onDataEmit(D data) {
                dataHandler.call(data);
            }
        };
    }
}
