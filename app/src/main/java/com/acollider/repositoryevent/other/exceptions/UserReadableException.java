package com.acollider.repositoryevent.other.exceptions;

/**
 * @author Yevhen Nechyporenko <nechiporenko.evgeniy@gmail.com>
 * @copyright (c) Grossum. (http://www.grossum.com)
 * @project Nioxin
 */
public class UserReadableException extends RuntimeException {
    public UserReadableException() {
    }

    public UserReadableException(String message) {
        super(message);
    }

    public UserReadableException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserReadableException(Throwable cause) {
        super(cause);
    }
}
