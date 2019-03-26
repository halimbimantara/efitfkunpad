package org.efit.mobile;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import java.util.Locale;

/**
 * Created by TheMac on 10/4/17.
 */

public class Myapplication extends Application {
    private static Myapplication context;
    private LocaleLanguage LocaleUtils;
    private Locale locale;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Configuration config = getBaseContext().getResources().getConfiguration();
        String lang = "in";
        if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleUtils.updateConfig(this, newConfig);
    }
    public static synchronized Myapplication getContext() {
        return context;
    }
//
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
