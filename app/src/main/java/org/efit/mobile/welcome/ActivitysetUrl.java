package org.efit.mobile.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivitysetUrl extends BaseActivity {
    @BindView(R.id.Et_urlip)
    EditText EtUrlip;
    @BindView(R.id.btn_save_uri)
    Button btnSaveUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityseturl);
        ButterKnife.bind(this);

        if (Sharepref.getString(Constant.BASE_URI) == null) {
            EtUrlip.setText(Constant.BASE_API);
        } else {
            EtUrlip.setText(Sharepref.getString(Constant.BASE_URI));
        }

        btnSaveUri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sharepref.saveString(Constant.BASE_URI, EtUrlip.getText().toString());
                ShowMessages(ActivitysetUrl.this, "Berhasil Menyimpan");
            }
        });

    }
}
