package com.zxs.mylibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by zxs
 * on 2022/2/11
 * .
 */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String SP_NAME = "sp_name";

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesUtil(Context context, String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static SharedPreferencesUtil get(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtil(context);
        }
        return instance;
    }

    public static SharedPreferencesUtil get(Context context, String spName) {
        if (instance == null) {
            instance = new SharedPreferencesUtil(context, spName);
        }
        return instance;
    }

    public boolean contains(String key) {
        return sp.contains(key);
    }

    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    /******************** String ********************/

    public SharedPreferencesUtil put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return this;
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public String getString(String key, String def) {
        return sp.getString(key, def);
    }

    /******************** int ********************/

    public SharedPreferencesUtil put(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public int getInt(String key, int def) {
        return sp.getInt(key, def);
    }

    /******************** long ********************/

    public SharedPreferencesUtil put(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
        return this;
    }

    public long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public long getLong(String key, long def) {
        return sp.getLong(key, def);
    }

    /******************** float ********************/

    public SharedPreferencesUtil put(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
        return this;
    }

    public float getFloat(String key) {
        return sp.getFloat(key, 0f);
    }

    public float getFloat(String key, float def) {
        return sp.getFloat(key, def);
    }

    /******************** boolean ********************/

    public SharedPreferencesUtil put(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
        return this;
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean def) {
        return sp.getBoolean(key, def);
    }

}
