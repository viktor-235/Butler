package com.github.viktor235.butler.jobs;

import com.github.viktor235.butler.Context;
import com.github.viktor235.butler.Informer;
import com.github.viktor235.butler.task.jobs.ReplaceTextUsingRegEx;
import com.github.viktor235.butler.utils.AppException;

import java.util.regex.Pattern;

public class ReplaceTextUsingRegExJob implements JobEntity {
    private final String contextFrom;
    private final String contextTo;
    private final String substitutionValue;
    private final String substitutionContextFrom;
    private final String regexValue;
    private final String regexContextFrom;
    private final int flags;

    public ReplaceTextUsingRegExJob(ReplaceTextUsingRegEx conf) {
        this(conf.getContextFrom(), conf.getContextTo(),
                conf.getRegEx().getValue(), conf.getRegEx().getContextFrom(),
                conf.getSubstitution().getValue(), conf.getSubstitution().getContextFrom(),
                getFlags(conf.getFlags()));
    }

    public ReplaceTextUsingRegExJob(String contextFrom, String contextTo,
                                    String regexValue, String regexContextFrom,
                                    String substitutionValue, String substitutionContextFrom,
                                    int flags) {
        this.contextFrom = contextFrom;
        this.contextTo = contextTo;
        this.regexValue = regexValue;
        this.regexContextFrom = regexContextFrom;
        this.substitutionValue = substitutionValue;
        this.substitutionContextFrom = substitutionContextFrom;
        this.flags = flags;
    }

    @Override
    public void process(Context c, Informer info) throws AppException {
        String text = c.get(contextFrom);
        text = doJob(text, c);
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

    public String doJob(String text, Context c) throws AppException {
        if (text == null)
            throw new AppException("Text is null");
        String regex = regexValue != null ? regexValue : c.get(regexContextFrom);
        String substitution = substitutionValue != null ? substitutionValue : c.get(substitutionContextFrom);
        Pattern pattern = Pattern.compile(regex, flags);
        return pattern.matcher(text).replaceAll(substitution);
    }
}
