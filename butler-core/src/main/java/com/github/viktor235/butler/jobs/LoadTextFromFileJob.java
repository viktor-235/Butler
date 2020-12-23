package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.config.jobs.LoadTextFromFile;
import com.github.viktor235.butler.utils.AppException;
import com.github.viktor235.butler.utils.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadTextFromFileJob implements JobEntity {
    private final String contextTo;
    private final Path path;

    public LoadTextFromFileJob(LoadTextFromFile conf) {
        this(conf.getContextTo(), Paths.get(conf.getFile()));
    }

    public LoadTextFromFileJob(String contextTo, Path path) {
        this.contextTo = contextTo;
        this.path = path;
    }

    @Override
    public void process(Context c, Informer info) throws AppException {
        c.put(contextTo, doJob());
    }

    public String doJob() throws AppException {
        return Utils.readFile(path);
    }
}
