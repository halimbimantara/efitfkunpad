package org.efit.mobile.core;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;


/**
 * Created by TheMac on 6/8/17.
 */

public class BaseFragment extends Fragment {
    private ProgressDialog progressDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (progressDialog == null)
            progressDialog = new ProgressDialog(getContext());
    }

    protected void showProgress() {
        if (progressDialog != null)
            if (!progressDialog.isShowing()) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }
    }

    protected void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

//    public void setActiveFragment(Fragment fragment) {
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.main_lay, fragment)
//                .addToBackStack(null)
//                .commit();
//    }

    public void open(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    public void finish() {
        getActivity().finish();
    }

    public View inflate(ViewGroup container, int layoutRes) {
        return LayoutInflater.from(container.getContext()).inflate(layoutRes, container, false);
    }

    public boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public String getString(EditText editText) {
        return editText.getText().toString();
    }

    public void showSnack(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void setTitle(String title) {
        ((AppCompatActivity) getActivity()).setTitle(title);
    }

    public String getTanggalNow() {
        Date date = new Date();
        SimpleDateFormat dateFormatWithZone = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormatWithZone.format(date);
        return currentDate;
    }

    public String getTanggalTimeNow() {
        Date date = new Date();
        SimpleDateFormat dateFormatWithZone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDate = dateFormatWithZone.format(date);
        return currentDate;
    }
}
