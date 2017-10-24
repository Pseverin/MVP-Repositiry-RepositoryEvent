package com.acollider.repositoryevent.utils.functions;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public interface Action2<T1, T2> {

    void call(T1 param1, T2 param2);
}
