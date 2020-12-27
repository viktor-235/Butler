package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.task.jobs.InsertToText;
import com.github.viktor235.butler.utils.AppException;

public class InsertToTextJob implements JobEntity {
    private final String contextFrom;
    private final String contextTo;
    private final String beginningValue;
    private final String endingValue;

    public InsertToTextJob(InsertToText conf) {
        this(conf.getContextFrom(), conf.getContextTo(), conf.getBeginningValue(), conf.getEndingValue());
    }

    public InsertToTextJob(String contextFrom, String contextTo, String beginningValue, String endingValue) {
        this.contextFrom = contextFrom;
        this.contextTo = contextTo;
        this.beginningValue = beginningValue;
        this.endingValue = endingValue;
    }

    @Override
    public void process(Context c, Informer info) throws AppException {
        String text = c.get(contextFrom);
        text = doJob(text);
        c.put(contextTo, text);
    }

    public String doJob(String text) {
        StringBuilder builder = new StringBuilder();
        if (beginningValue != null)
            builder.append(beginningValue);
        if (text != null)
            builder.append(text);
        if (endingValue != null)
            builder.append(endingValue);
        return builder.toString();
    }
}
