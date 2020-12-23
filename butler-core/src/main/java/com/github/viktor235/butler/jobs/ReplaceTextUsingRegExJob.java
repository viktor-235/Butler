package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.config.jobs.ReplaceTextUsingRegEx;
import com.github.viktor235.butler.utils.AppException;

import java.util.regex.Pattern;

public class ReplaceTextUsingRegExJob implements JobEntity {
    private final String contextFrom;
    private final String contextTo;
    private final Pattern pattern;
    private final String substitution;

    public ReplaceTextUsingRegExJob(ReplaceTextUsingRegEx conf) {
        this(conf.getContextFrom(), conf.getContextTo(), conf.getRegEx(), conf.getSubstitution(), getFlags(conf.getFlags()));
    }

    public ReplaceTextUsingRegExJob(String contextFrom, String contextTo, String regex, String substitution, int flags) {
        this.contextFrom = contextFrom;
        this.contextTo = contextTo;
        pattern = Pattern.compile(regex, flags);
        this.substitution = substitution;
    }

    @Override
    public void process(Context c, Informer info) throws AppException {
        String text = c.get(contextFrom);
        text = doJob(text);
        c.put(contextTo, text);
    }

    private static int getFlags(ReplaceTextUsingRegEx.Flags flags) {
        if (flags == null) return 0;
        int result = 0;
        if (flags.isUnixLines()) result |= Pattern.UNIX_LINES;
        if (flags.isCaseInsensitive()) result |= Pattern.CASE_INSENSITIVE;
        if (flags.isComments()) result |= Pattern.COMMENTS;
        if (flags.isMultiline()) result |= Pattern.MULTILINE;
        if (flags.isLiteral()) result |= Pattern.LITERAL;
        if (flags.isDotall()) result |= Pattern.DOTALL;
        if (flags.isUnicodeCase()) result |= Pattern.UNICODE_CASE;
        if (flags.isCanonEq()) result |= Pattern.CANON_EQ;
        if (flags.isUnicodeCharacterClass()) result |= Pattern.UNICODE_CHARACTER_CLASS;
        return result;
    }

    public String doJob(String text) throws AppException {
        if (text == null)
            throw new AppException("Text is null");
        return pattern.matcher(text).replaceAll(substitution);
    }
}
