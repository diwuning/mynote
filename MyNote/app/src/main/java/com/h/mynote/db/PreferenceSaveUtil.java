package com.h.mynote.db;

import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by h on 2016/3/28 0028.
 */
public class PreferenceSaveUtil {
    public static void putString(Preference p,String key,String value)
    {
        SharedPreferences.Editor sharedata= p.getSharedPreferences().edit();
        sharedata.putString(key, value);
        sharedata.commit();
    }

    public static String getString(Preference p,String key,String defValue)
    {
        String s=null;
        try {
            s = p.getSharedPreferences().getString(key, defValue);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return s;
    }
}