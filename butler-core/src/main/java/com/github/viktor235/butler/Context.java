package com.github.viktor235.butler;

import java.util.HashMap;
import java.util.Map;

public class Context {
    protected Map<String, Object> context;

    public void put(String key, Object field) {
        if (context == null)
            context = createContext();
        context.put(key, field);
    }

    public <T> T get(String key) {
        if (context == null)
            return null;
        else
            //noinspection unchecked
            return (T) context.get(key);
    }

    protected Map<String, Object> createContext() {
        return new HashMap<>();
    }
}
