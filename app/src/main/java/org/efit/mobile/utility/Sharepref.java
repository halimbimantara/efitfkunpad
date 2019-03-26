package org.efit.mobile.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.efit.mobile.Myapplication;

/**
 * Created by TheMac on 1/6/17.
 */

public class Sharepref {

    private static final String TAG = "SharedPref";
    // Shared Preferences
    private SharedPreferences pref;
    private Context _context;


    public Sharepref(Context context){
        this._context = context;
    }
    // Constructor
    private static SharedPreferences getPref(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static SharedPreferences getPref() {
        Context context = Myapplication.getContext();
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void saveString(String key, String value) {
        Log.d(TAG, "saveString: " + value);
        getPref().edit()
                .putString(key, value)
                .apply();
    }

    public static String getString(String key) {
//        Log.d(TAG, "getString: " + getPref().getString(key, null));
        return getPref().getString(key, null);
    }


    public static void deleteString(String key) {
        getPref().edit()
                .remove(key)
                .apply();
    }

    public static void saveInt(String key, int value) {
        Log.d(TAG, "saveInt: " + value);
        getPref().edit()
                .putInt(key, value)
                .apply();
    }

    public static int getInt(String key) {
        return getPref().getInt(key, 0);
    }

    public static void deleteInt(String key) {
        getPref().edit()
                .remove(key)
                .apply();
    }

    public static void saveBol(String key, boolean value) {
        getPref().edit()
                .putBoolean(key, value)
                .apply();
    }

    public static boolean getBol(String key) {
        return getPref().getBoolean(key, false);
    }

    public static void deleteBol(String key) {
        getPref().edit()
                .remove(key)
                .apply();
    }



    public static void deleteAll(){
        getPref().edit().clear();
    }
}

