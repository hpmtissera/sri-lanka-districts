package com.lanka_guide.districts;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

/**
 * Created by chanya on 2017/10/05.
 */

public class Preferences {
    private static final String KEY_LOCALE = "locale";
    private static final String PREFER_NAME = "preferencesMain";

    Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public Preferences(Context context) {
        this.context = context;
        setPreferences();
    }

    private void setPreferences() {
        preferences = context.getSharedPreferences(PREFER_NAME, context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void cleanPreferences() {
        editor.clear();
        editor.commit();
    }

    public Locale getLocale() {
        String locale = preferences.getString(KEY_LOCALE, null);
        if (locale != null) {
            return new Locale(locale);
        }
        return null;
    }

    public void setLocale(Locale locale) {
        editor.putString(KEY_LOCALE, locale.getLanguage());
        editor.commit();
    }
}
