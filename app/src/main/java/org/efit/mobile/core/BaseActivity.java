package org.efit.mobile.core;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.efit.mobile.R;

import org.efit.mobile.helper.DialogHelper;
import org.efit.mobile.restapi.RestClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by TheMac on 6/8/17.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    protected RestClient restClient = RestClient.getInstance();
    private ProgressDialog progressDialog;
    protected DialogHelper dialog;

    @Override
    protected void onStart() {
        super.onStart();
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }else{
            progressDialog.dismiss();
        }
    }

    protected void showProgress() {
        if (!progressDialog.isShowing()) {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }
    }

    protected void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void setActiveFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    public void open(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void ShowMessages(Activity activity, String messages) {
        Toast.makeText(activity, messages, Toast.LENGTH_SHORT).show();
    }

    public static void applyFont(final Context context, final View root, final String fontName) {
        try {
            if (root instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) root;
                for (int i = 0; i < viewGroup.getChildCount(); i++)
                    applyFont(context, viewGroup.getChildAt(i), fontName);
            } else if (root instanceof TextView)
                ((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));
        } catch (Exception e) {
            Log.e("ProjectName", String.format("Error occured when trying to apply %s font for %s view", fontName, root));
            e.printStackTrace();
        }
    }
    protected String getEditToString(EditText a) {
        String x = a.getText().toString();
        return x;
    }

    public String getTanggalNow() {
        Date date = new Date();
        SimpleDateFormat dateFormatWithZone = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormatWithZone.format(date);
        return currentDate;
    }

}
