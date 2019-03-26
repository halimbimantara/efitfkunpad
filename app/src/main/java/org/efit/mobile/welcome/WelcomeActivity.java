package org.efit.mobile.welcome;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import org.efit.mobile.BuildConfig;
import org.efit.mobile.R;

import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DialogHelper;
import org.efit.mobile.model.kemendesa.ApiResponse;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TheMac on 10/9/17.
 */

public class WelcomeActivity extends BaseActivity {
    private static final String TAG = "Welcome";
    //API
    protected RestClient restClient = RestClient.getInstance();
    DialogHelper dialog;
    @BindView(R.id.imv_splashlogo)
    ImageView imvSplashlogo;
    private String versionName = BuildConfig.VERSION_NAME;
    Animation anims;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        anims = AnimationUtils.loadAnimation(this, R.anim.splashfade);
        imvSplashlogo.setAnimation(anims);
        dialog = new DialogHelper(WelcomeActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Sharepref.getString(Constant.USERID) != null) {
                    open(MainMenuActivity.class);
                } else {
                    open(FirstActivity.class);
                }
                finish();
            }
        }, 5000);

    }
}
