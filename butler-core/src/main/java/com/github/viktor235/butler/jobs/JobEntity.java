package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.utils.AppException;

public interface JobEntity {
    void process(Context context, Informer informer) throws AppException;
}
