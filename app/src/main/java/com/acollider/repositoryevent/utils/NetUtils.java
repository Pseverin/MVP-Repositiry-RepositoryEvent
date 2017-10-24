package com.acollider.repositoryevent.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.acollider.repositoryevent.R;
import com.acollider.repositoryevent.app.App;
import com.acollider.repositoryevent.other.exceptions.ApiException;
import com.acollider.repositoryevent.other.exceptions.NoConnectionException;
import com.acollider.repositoryevent.other.RepositoryEvent;
import com.acollider.repositoryevent.other.exceptions.UserReadableException;
import com.acollider.repositoryevent.utils.functions.Function1;
import com.annimon.stream.Optional;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import java.net.HttpURLConnection;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import retrofit2.Response;

import static com.acollider.repositoryevent.app.CustomSchedulers.net;
import static com.annimon.stream.Optional.empty;
import static com.annimon.stream.Optional.ofNullable;
import static io.reactivex.Observable.fromCallable;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public class NetUtils {

    public static <R> Observable<RepositoryEvent<R>> toApiObservable(Callable<R> callable) {
        return fromCallable(callable)
            .map(RepositoryEvent::apiEvent)
            .subscribeOn(net())
            .startWith(RepositoryEvent.longAction());
    }

    /**
     * Decorates retrofit API observable, returning observable of api object.
     * Throws 'onError'-handled exceptions in case http result contains errors
     */
    public static <AppType, ApiType> Observable<RepositoryEvent<ApiType>> decorate(
        Observable<Result<ApiType>> apiObservable) {

        return apiObservable
            .subscribeOn(net())
            .map(NetUtils::getOrThrowError)
            .map(RepositoryEvent::apiEvent)
            .startWith(RepositoryEvent.longAction());
    }

    /**
     * Decorates retrofit API observable, returning observable of application domain types instead.
     * Throws 'onError'-handled exceptions in case http result contains errors
     */
    public static <AppType, ApiType> Observable<RepositoryEvent<AppType>> decorateAndMap(
        Observable<Result<ApiType>> apiObservable, Function1<ApiType, AppType> toAppTypeMapper) {

        return apiObservable
            .subscribeOn(net())
            .map(NetUtils::getOrThrowError)
            .map(toAppTypeMapper::call)
            .map(RepositoryEvent::apiEvent)
            .startWith(RepositoryEvent.longAction());
    }

    /**
     * Decorates retrofit API observable, returning observable of application domain types instead.
     * Wraps objects into observable and returns RepositoryEvent<Optional.empty()> in case of error
     */
    public static <AppType, ApiType> Observable<RepositoryEvent<Optional<AppType>>> decorateAndMapOptional(
        Observable<Result<ApiType>> apiObservable, Function1<ApiType, AppType> toAppTypeMapper) {
        return apiObservable
            .subscribeOn(net())
            .map(NetUtils::getOptionalFromResult)
            .map(optionalResult -> optionalResult.map(toAppTypeMapper::call))
            .map(RepositoryEvent::apiEvent)
            .startWith(RepositoryEvent.longAction());
    }

    public static <T> Optional<T> getOptionalFromResult(Result<T> apiResult) {
        try {
            return ofNullable(getOrThrowError(apiResult));
        } catch (Exception ex) {
            if (ex instanceof ApiException
                && ((ApiException) ex).getCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                return empty();
            } else {
                throw ex;
            }
        }
    }

    public static <T> T getOrThrowError(Result<T> apiResult) {
        return getOrThrowError(App.getAppComponent().getGson(), apiResult);
    }

    public static <T> T getOrThrowError(Gson gson, Result<T> apiResult) {
        if (apiResult.isError()) {
            @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
            Throwable error = apiResult.error();
            if (error instanceof UserReadableException) {
                throw ((UserReadableException) error);
            } else {
                throw new RuntimeException(apiResult.error());
            }
        } else {
            Response<T> response = apiResult.response();
            if (response.isSuccessful()) {
                T body = response.body();
                if (body == null) {
                    throw new ApiException(response.code(), "Unexpected successful response with empty body");
                }
                return body;
            } else {
                throw new ApiException(response.code(), "Unexpected error response with empty error body");
            }
        }
    }

    public static NoConnectionException noConnectionException(String text) {
        return new NoConnectionException(App.getAppComponent()
            .getResources().getString(R.string.no_internet_connection) + " " + text);
    }

    public static NoConnectionException noConnectionException() {
        return noConnectionException("");
    }


    public static boolean isNetworkConnected() {
        return isNetworkConnected(App.getAppComponent().getAppContext());
    }

    public static boolean isNetworkConnected(Context ctx) {
        return ofNullable(ctx.getSystemService(Context.CONNECTIVITY_SERVICE))
            .map(ConnectivityManager.class::cast)
            .map(ConnectivityManager::getActiveNetworkInfo)
            .map(NetworkInfo::isConnected)
            .orElse(false);
    }
}