package org.efit.mobile.welcome;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity {
    Animation anims;
    @BindView(R.id.tv_semboyan)
    TextView tvSemboyan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
        anims = AnimationUtils.loadAnimation(this, R.anim.splashfade);
        tvSemboyan.setAnimation(anims);
    }

    @OnClick({R.id.btn_masuk, R.id.btn_daftar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_masuk:
                open(LoginActivity.class);
                break;
            case R.id.btn_daftar:
                open(ActivityDaftar.class);
                break;
        }
    }
}
