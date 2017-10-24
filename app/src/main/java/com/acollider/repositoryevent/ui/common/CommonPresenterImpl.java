package com.acollider.repositoryevent.ui.common;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.Log;

import com.acollider.repositoryevent.BuildConfig;
import com.acollider.repositoryevent.R;
import com.acollider.repositoryevent.other.exceptions.NoConnectionException;
import com.acollider.repositoryevent.other.UiBlockHandler;
import com.acollider.repositoryevent.other.exceptions.UserReadableException;
import com.acollider.repositoryevent.utils.functions.Action2;
import com.annimon.stream.Optional;
import com.annimon.stream.function.Supplier;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author Severyn Parkhomenko <sparkhomenko@theappsolutions.com>
 * @copyright (c) Grossum. (http://www.theappsolutions.com)
 * @project MVP-Repository-RepositoryEvent_example_app
 */
public abstract class CommonPresenterImpl<V extends CommonView> implements CommonPresenter<V> {

    private static final String LOG_TAG = CommonPresenterImpl.class.getSimpleName();

    @Nullable
    private V view;
    private Resources resources;

    public CommonPresenterImpl() {
    }

    public CommonPresenterImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void setView(@Nullable V view) {
        this.view = view;
    }

    private Consumer<Throwable> errorConsumer = createErrorConsumer(() -> view);

    private UiBlockHandler uiBlockHandler = createUiBlockHandler(() -> view);

    public static UiBlockHandler createUiBlockHandler(Supplier<? extends CommonView> viewSupplier) {
        return new UiBlockHandler() {
            @Override
            public void onBlockUi() {
                CommonView view = viewSupplier.get();
                if (view != null) {
                    view.blockUi();
                }
            }

            @Override
            public void onUnBlockUi() {
                CommonView view = viewSupplier.get();
                if (view != null) {
                    view.unblockUi();
                }
            }
        };
    }

    public static Consumer<Throwable> createErrorConsumer(Supplier<? extends CommonView> viewSupplier) {
        return throwable -> {
            CommonView view = viewSupplier.get();
            Log.e(LOG_TAG, throwable.getMessage(), throwable);
            if (view != null) {
                if (throwable instanceof UserReadableException) {
                    view.showToastMessage(throwable.getMessage());
                } else {
                    if (BuildConfig.DEBUG) {
                        view.showToastMessage("(only for DEBUG)\n" + throwable.getMessage());
                    } else {
                        view.showToastMessage(R.string.something_going_wrong);
                    }
                }
                view.unblockUi();
            }
        };
    }

    public <D> CommonModelConsumer<D> withViewFrom(Action2<D, V> dataHandler, UiBlockHandler uiBlockHandler) {
        return new CommonModelConsumer<D>(uiBlockHandler) {
            @Override
            protected void onDataEmit(D data) {
                getViewOptional().ifPresent(view -> dataHandler.call(data, view));
            }
        };
    }

    protected Consumer<Throwable> getErrorConsumer() {
        return errorConsumer;
    }

    /**
     * Error consumer that ignores {@link NoConnectionException} exceptions, but handles all others
     */
    protected Consumer<Throwable> getIgnoreNoConnectionErrorConsumer(Runnable doOnNoConnection) {
        return throwable -> {
            if (!(throwable instanceof NoConnectionException)) {
                errorConsumer.accept(throwable);
            } else {
                if (doOnNoConnection != null) {
                    doOnNoConnection.run();
                }
            }
        };
    }

    /**
     * Error consumer that ignores {@link NoConnectionException} exceptions, but handles all others
     */
    protected Consumer<Throwable> getIgnoreNoConnectionErrorConsumer() {
        return getIgnoreNoConnectionErrorConsumer(null);
    }

    protected UiBlockHandler getUiBlockHandler() {
        return uiBlockHandler;
    }

    @Nullable
    protected V getView() {
        return view;
    }

    @NonNull
    protected Optional<V> getViewOptional() {
        return Optional.ofNullable(view);
    }
}


