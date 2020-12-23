package com.github.viktor235.butler.cli;

import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.utils.AppException;
import com.github.viktor235.butler.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CliInformer implements Informer {
    private final Logger logger = LogManager.getLogger(CliInformer.class.getName());

    @Override
    public void reportMessage(String message) {
        logger.info(message);
        System.out.println(message);
    }

    @Override
    public void reportMessage(String format, Object... args) {
        reportMessage(String.format(format, args));
    }

    @Override
    public void reportError(String message) {
        logger.error(message);
        System.err.println(message);
    }

    @Override
    public void reportError(Throwable throwable) {
        logger.error("Error", throwable);
        System.out.println(Utils.extractExceptionMessage(throwable));
    }

    @Override
    public void reportError(String message, AppException throwable) {
        reportError(message);
        reportError(throwable);
    }
}
