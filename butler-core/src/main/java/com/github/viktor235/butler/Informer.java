package com.github.viktor235.butler;

import com.github.viktor235.butler.utils.AppException;

public interface Informer {
    void reportMessage(String message);

    void reportMessage(String format, Object... args);

    void reportError(String message);

    void reportError(Throwable throwable);

    void reportError(String message, AppException throwable);
}
