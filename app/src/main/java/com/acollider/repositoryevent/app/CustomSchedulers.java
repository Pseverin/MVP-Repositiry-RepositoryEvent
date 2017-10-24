package com.acollider.repositoryevent.app;

import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.RxThreadFactory;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public class CustomSchedulers {

    private static Scheduler realmScheduler = Schedulers.from(Executors
        .newSingleThreadExecutor(new RxThreadFactory("realm")));
    private static Scheduler syncScheduler = Schedulers.from(Executors
        .newSingleThreadExecutor(new RxThreadFactory("sync")));

    public static Scheduler realm() {
        return realmScheduler;
    }

    public static Scheduler sync() {
        return syncScheduler;
    }

    public static Scheduler net() {
        return Schedulers.io();
    }
}
