package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.config.JobType;
import com.github.viktor235.butler.config.jobs.*;
import com.github.viktor235.butler.utils.AppException;

import java.util.ArrayList;
import java.util.List;

public class JobFactory {
    public JobEntity createJob(JobType config) throws AppException {
//            Class<?> clazz = Class.forName(LoadTextFromFile.class.getSimpleName() + "Job");
//            Constructor<?> ctor = clazz.getConstructor(String.class);
//            Object object = ctor.newInstance("ctorArgument");


        if (config instanceof LoadTextFromFile)
            return new LoadTextFromFileJob((LoadTextFromFile) config);
        else if (config instanceof ReplaceTextUsingRegEx)
            return new ReplaceTextUsingRegExJob((ReplaceTextUsingRegEx) config);
        else if (config instanceof InsertToText)
            return new InsertToTextJob((InsertToText) config);
        else if (config instanceof WriteTextToConsole)
            return new WriteTextToConsoleJob((WriteTextToConsole) config);
        else if (config instanceof WriteTextToFile)
            return new WriteTextToFileJob((WriteTextToFile) config);
        else
            throw new AppException("Unknown job type");
    }

    public List<JobEntity> createJobs(List<JobType> jobs) throws AppException {
        List<JobEntity> result = new ArrayList<>();
        for (JobType job : jobs)
            result.add(createJob(job));
        return result;
    }
}
