package com.ppm.ppcomon.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.ppm.ppcomon.base.app.App;

import java.util.Set;

/**
 * Author dodoca_android.
 * Date 2017/4/1.
 */

public class CacheUtil {
    private static final String NAME_CONFIG = "config";
    private static final String NAME_CACHE = "cache";

    public static <T> void putConfig(String key, T value) {
        put(NAME_CONFIG, key, value);
    }

    public static <T> T getConfig(String key, T value) {
        return get(NAME_CONFIG, key, value);
    }

    public static <T> void putCache(String key, T value) {
        put(NAME_CACHE, key, value);
    }

    public static <T> T getCache(String key, T value) {
        return get(NAME_CACHE, key, value);
    }

    @SuppressWarnings("unchecked")
    private static <T> void put(String name, String key, T value) {
        SharedPreferences preferences = getSharedPreferences(name);
        if (value instanceof String) {
            preferences.edit().putString(key, (String) value).apply();
        } else if (value instanceof Set) {
            preferences.edit().putStringSet(key, (Set<String>) value).apply();
        } else if (value instanceof Integer) {
            preferences.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Long) {
            preferences.edit().putLong(key, (Long) value).apply();
        } else if (value instanceof Float) {
            preferences.edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Boolean) {
            preferences.edit().putBoolean(key, (Boolean) value).apply();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T get(String name, String key, T value) {
        SharedPreferences preferences = getSharedPreferences(name);
        Object o = null;
        if (value instanceof String) {
            o = preferences.getString(key, (String) value);
        } else if (value instanceof Set) {
            o = preferences.getStringSet(key, (Set<String>) value);
        } else if (value instanceof Integer) {
            o = preferences.getInt(key, (Integer) value);
        } else if (value instanceof Long) {
            o = preferences.getLong(key, (Long) value);
        } else if (value instanceof Float) {
            o = preferences.getFloat(key, (Float) value);
        } else if (value instanceof Boolean) {
            o = preferences.getBoolean(key, (Boolean) value);
        }

        return (T) o;
    }

    private static SharedPreferences getSharedPreferences(String name) {
        return App.getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
