package com.sleep.commonlib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;


public class SPUtil {
    private static final String KEY = "config";
    private static SharedPreferences sharedPreferences;
    private static Editor editor;
    private static SPUtil spUtil;
    private static Context mContext;

    public static void init(Context context) {
        mContext=context;
    }
    private SPUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, 0);
        editor = sharedPreferences.edit();
    }

    public static SPUtil getInstance() {
        if (spUtil == null)
            spUtil = new SPUtil(mContext);
        return spUtil;
    }


    public String getString(FragmentActivity activity, String key) {
        String string = null;
        try {
            string = sharedPreferences.getString(key, StringUtil.EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return string;
    }
    public String getString(String key) {
        String mString = getString(key, "");
        return mString;
    }

    public String getString(String key, String defaultValue)  {
        String mString = null;
        try {
            mString = sharedPreferences.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return mString;
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }


    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void put(String key, Object obj) {
        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (long) obj);
        }
        editor.commit();
    }
}
