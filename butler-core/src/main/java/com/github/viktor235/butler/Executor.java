package com.github.viktor235.butler;

import com.github.viktor235.butler.jobs.JobEntity;
import com.github.viktor235.butler.utils.AppException;

import java.util.List;

public class Executor {
    private final Context context = new Context();
    private final Informer informer;

    public Executor(Informer informer) {
        this.informer = informer;
    }

    public void execute(List<JobEntity> jobs) throws AppException {
        int jobCounter = 0;

        for (JobEntity job : jobs) {
            informer.reportMessage("Job %d/%d: %s", ++jobCounter, jobs.size(), job.getClass().getSimpleName());
            job.process(context, informer);
        }
    }
}
