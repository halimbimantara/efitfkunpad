package org.efit.mobile.fragment.grafik;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.network.CheckConnection;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentGrafikOlahraga extends BaseFragment {
    private static final String TAG = "FragmentGrafik";
    @BindView(R.id.website_view_grafik)
    WebView myWebView;

    private Context mContext;
    private Activity mActivity;
    View rooView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LoadDetail();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rooView = inflater.inflate(R.layout.fragment_grafik_or, container, false);
        ButterKnife.bind(this, rooView);
        mContext = getActivity();
        mActivity = getActivity();
        return rooView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url = "http://fit.coderantik.id/webapp/fit_lapasupanmakan/grafikolahraga?id_user=" + Sharepref.getString(Constant.USERID);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        // Enable Javascript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setInitialScale((int) 1.0);
        myWebView.loadUrl(url);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void LoadDetail() {
        if (CheckConnection.isNetworkAvailable()) {
            Log.d(TAG, "LoadDetail: ada koneksi");
        } else {
            Log.e(TAG, "LoadDetail: no koneksi");
        }
    }

}
