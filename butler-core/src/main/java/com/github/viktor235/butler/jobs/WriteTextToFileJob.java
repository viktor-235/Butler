package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.task.jobs.WriteTextToFile;
import com.github.viktor235.butler.utils.AppException;
import com.github.viktor235.butler.utils.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteTextToFileJob implements JobEntity {
    private final String contextFrom;
    private final Path path;

    public WriteTextToFileJob(WriteTextToFile conf) {
        this(conf.getContextFrom(), Paths.get(conf.getFile()));
    }

    public WriteTextToFileJob(String contextFrom, Path path) {
        this.contextFrom = contextFrom;
        this.path = path;
    }

    @Override
    public void process(Context c, Informer info) throws AppException {
        String text = c.get(contextFrom);
        doJob(text);
    }

    public void doJob(String text) throws AppException {
        Utils.writeFile(path, text);
    }
}
