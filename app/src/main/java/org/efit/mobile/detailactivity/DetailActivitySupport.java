package org.efit.mobile.detailactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivitySupport extends BaseActivity {
    @BindView(R.id.call_wa)
    TextView callWa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_support);
        ButterKnife.bind(this);
        callWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toNumber = "+62811887661"; // Replace with mobile phone number without +Sign or leading zeros.
                try {
                    String text = "Saya Mengalami Kendala ...";// Replace with your message.
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
