package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.config.jobs.WriteTextToConsole;
import com.github.viktor235.butler.utils.AppException;

public class WriteTextToConsoleJob implements JobEntity {
    private final String contextFrom;

    public WriteTextToConsoleJob(WriteTextToConsole conf) {
        this(conf.getContextFrom());
    }

    public WriteTextToConsoleJob(String contextFrom) {

        this.contextFrom = contextFrom;
    }

    @Override
    public void process(Context c, Informer info) throws AppException {
        String text = c.get(contextFrom);
        doJob(text);
    }

    public void doJob(String text) {
        System.out.println(text);
    }
}
