package com.acollider.repositoryevent.api.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {

    private final static String LOG_TAG = "NioxinOkHttp";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        logD(String.format("--> Sending %s request %s on %s%n%s",
            request.method(), request.url(), chain.connection(), request.headers()));

        if (request.headers() != null) {
            for (int i = 0; i < request.headers().size(); i++) {
                logD(request.headers().name(i) + ": " + request.headers().value(i));
            }
        }

        if (request.body() != null) {
            Buffer requestBuffer = new Buffer();
            request.body().writeTo(requestBuffer);
            logD(requestBuffer.readUtf8());
        } else {
            logD("Request body is empty");
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        logD(String.format("<-- Received %s response for %s in %.1fms%n%s",
            response.code(), response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        MediaType contentType = response.body().contentType();
        String content = response.body().string();

        int maxLogSize = 2000;
        for (int i = 0; i <= content.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > content.length() ? content.length() : end;
            logV(content.substring(start, end));
        }

        ResponseBody wrappedBody = ResponseBody.create(contentType, content);
        return response.newBuilder().body(wrappedBody).build();
    }

    private void logV(String s) {
        Log.v(LOG_TAG, s);
    }

    private void logD(String s) {
        Log.d(LOG_TAG, s);
    }
}