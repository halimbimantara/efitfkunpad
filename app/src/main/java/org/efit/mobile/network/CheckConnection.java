package org.efit.mobile.network;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import org.efit.mobile.Myapplication;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TheMac on 10/4/17.
 */

public class CheckConnection {
    /**
     * Check Connection
     *
     * @return
     */
    public static boolean isNetworkAvailable() {
        Myapplication context = Myapplication.getContext();
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            // if no network is available networkInfo will be null
            // otherwise check if we are connected
            return (networkInfo != null && networkInfo.isConnected());
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isGpsEnabled(Context mconContext) {
        LocationManager service = (LocationManager) mconContext.getSystemService(mconContext.LOCATION_SERVICE);
        return service.isProviderEnabled(LocationManager.GPS_PROVIDER) && service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public static JSONObject getProfile() throws JSONException {
        String jProfile = Sharepref.getString(Constant.USERPROFILE);
        JSONObject jsonObj = null;
        JSONArray JsonProfile = null;
        try {
            jsonObj = new JSONObject(jProfile);
//            JsonProfile = jsonObj.getJSONArray("profile");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        JSONObject c = JsonProfile.getJSONObject(0);
        JSONObject c = jsonObj.getJSONObject("profile");
        return c;
    }

    public static JSONObject getProfiles(String nmObject) throws JSONException {
        String jProfile = Sharepref.getString(Constant.USERPROFILE);
        JSONObject jsonObj = null;
        JSONObject JsonProfile = null;
        try {
            //create json from sharepreferences
            jsonObj = new JSONObject(jProfile);
            JsonProfile = jsonObj.getJSONObject("profile");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        JSONObject c = JsonProfile.getJSONObject();
//        JSONObject s=c.getJSONObject(nmObject);
        JSONObject c = JsonProfile.getJSONObject(nmObject);
        return c;
    }
}
