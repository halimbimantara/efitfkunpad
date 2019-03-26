package org.efit.mobile.activity.article;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.utility.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TheMac on 8/19/17.
 */

public class DetailArtikel extends BaseActivity {
    @BindView(R.id.webview_artikel)
    WebView webviewArtikel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailartikel);
        ButterKnife.bind(this);
        Bundle a = getIntent().getExtras();
        String url = Constant.BASE_API + "detailArtikel/" + a.getString("id_artikel");
        Log.i("url s", url);
        webviewArtikel.setWebViewClient(new CustomWebViewClient());
        WebSettings webSetting = webviewArtikel.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);
        webviewArtikel.loadUrl(url);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
