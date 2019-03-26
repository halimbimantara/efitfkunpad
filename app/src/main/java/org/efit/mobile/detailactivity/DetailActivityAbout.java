package org.efit.mobile.detailactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivityAbout extends BaseActivity {
    @BindView(R.id.website_view)
    WebView myWebView;

    private String id = "", url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_about);
        ButterKnife.bind(this);

        Bundle a = getIntent().getExtras();
        if (a != null) {
            id = a.getString("ID");
            url = a.getString("URL");

            myWebView.getSettings().setUseWideViewPort(true);
            myWebView.getSettings().setLoadWithOverviewMode(true);
            myWebView.getSettings().setSupportZoom(true);
            myWebView.getSettings().setBuiltInZoomControls(true);
            myWebView.getSettings().setDisplayZoomControls(false);
            myWebView.setInitialScale((int) 1.0);
            myWebView.loadUrl("http://fit.coderantik.id/webapp/"+url);
        } else {

        }
    }
}
