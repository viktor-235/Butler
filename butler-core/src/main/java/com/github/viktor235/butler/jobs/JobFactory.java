package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.task.JobType;
import com.github.viktor235.butler.task.jobs.*;
import com.github.viktor235.butler.utils.AppException;

import java.util.ArrayList;
import java.util.List;

public class JobFactory {
    public JobEntity createJob(JobType jobType) throws AppException {
//            Class<?> clazz = Class.forName(LoadTextFromFile.class.getSimpleName() + "Job");
//            Constructor<?> ctor = clazz.getConstructor(String.class);
//            Object object = ctor.newInstance("ctorArgument");


        if (jobType instanceof LoadTextFromFile)
            return new LoadTextFromFileJob((LoadTextFromFile) jobType);
        else if (jobType instanceof ReplaceTextUsingRegEx)
            return new ReplaceTextUsingRegExJob((ReplaceTextUsingRegEx) jobType);
        else if (jobType instanceof InsertToText)
            return new InsertToTextJob((InsertToText) jobType);
        else if (jobType instanceof WriteTextToConsole)
            return new WriteTextToConsoleJob((WriteTextToConsole) jobType);
        else if (jobType instanceof WriteTextToFile)
            return new WriteTextToFileJob((WriteTextToFile) jobType);
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
