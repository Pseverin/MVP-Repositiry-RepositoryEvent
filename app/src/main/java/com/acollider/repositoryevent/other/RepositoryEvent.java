package com.acollider.repositoryevent.other;

import com.acollider.repositoryevent.utils.functions.Action1;
import com.acollider.repositoryevent.utils.functions.Function1;
import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Predicate;

import io.reactivex.Flowable;
import io.reactivex.Observable;

import static com.annimon.stream.Optional.ofNullable;
import static io.reactivex.Flowable.just;

public class RepositoryEvent<T> {

    private EventType eventType;
    private T data;

    public RepositoryEvent(EventType eventType, T data) {
        this.eventType = eventType;
        this.data = data;
    }

    public RepositoryEvent(EventType eventType) {
        this.eventType = eventType;
    }


    public enum EventType {
        MEMORY_DATA,
        DB_DATA,
        API_DATA,
        START_LONG_OPERATION_ACTION
    }

    public boolean isDataEvent() {
        return eventType == EventType.MEMORY_DATA ||
            eventType == EventType.DB_DATA ||
            eventType == EventType.API_DATA;
    }

    public EventType getEventType() {
        return eventType;
    }

    public T getData() {
        return data;
    }

    public static <T> RepositoryEvent<T> apiEvent(T data) {
        return new RepositoryEvent<>(EventType.API_DATA, data);
    }

    public static <T> RepositoryEvent<T> dbEvent(T data) {
        return new RepositoryEvent<>(EventType.DB_DATA, data);
    }

    public static <T> RepositoryEvent<T> memoryEvent(T data) {
        return new RepositoryEvent<>(EventType.MEMORY_DATA, data);
    }

    public static <T> RepositoryEvent<T> longAction() {
        return new RepositoryEvent<>(EventType.START_LONG_OPERATION_ACTION);
    }

    public RepositoryEvent<T> filter(Predicate<T> predicate) {
        return new RepositoryEvent<>(eventType, Optional.ofNullable(data).filter(predicate).orElse(null));
    }

    public void ifPresent(Consumer<T> consumer) {
        ofNullable(data).ifPresent(consumer);
    }

    public <R> Flowable<RepositoryEvent<R>> flatMap(Function1<T, Flowable<R>> mapper) {
        return ofNullable(getData()).map(mapper::call).map(obs -> obs.map(data -> new RepositoryEvent<>(eventType, data)))
            .orElse(just(new RepositoryEvent<>(eventType, null)));
    }

    public <R> RepositoryEvent<R> map(Function1<T, R> mapper) {
        return new RepositoryEvent<>(eventType, ofNullable(getData()).map(mapper::call).orElse(null));
    }

    public void mapAndDo(Action1<T> action) {
        ofNullable(getData()).ifPresent(t -> action.call(data));
    }

    public <R> Observable<RepositoryEvent<R>> flatMapEventObs(Function1<T, Observable<RepositoryEvent<R>>> mapper) {
        return ofNullable(getData()).map(mapper::call).orElse(Observable.just(new RepositoryEvent<>(eventType, null)));
    }

    public Observable<RepositoryEvent<T>> toFlowable() {
        return Observable.just(this);
    }

    @Override
    public String toString() {
        return "RepositoryEvent{" +
            "eventType=" + eventType +
            ", data=" + data +
            '}';
    }
}