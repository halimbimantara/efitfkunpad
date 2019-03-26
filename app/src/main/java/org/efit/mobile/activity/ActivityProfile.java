package org.efit.mobile.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.detailactivity.profil.EditProgres;
import org.efit.mobile.detailactivity.profil.EditSasaran;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ThinkPad T430 on 17/12/2017.
 */

public class ActivityProfile extends BaseActivity {
    Context mycContext;
    @BindView(R.id.tv_nm_profil)
    TextView tvNmProfil;
    @BindView(R.id.tv_bb_profil)
    TextView tvBbProfil;
    @BindView(R.id.tv_bb_sasaran)
    TextView tvBbSasaran;
    @BindView(R.id.tv_kalori_sasaran)
    TextView tvKaloriSasaran;
    @BindView(R.id.tv_hasil_karbo_ssrn)
    TextView tvHasilKarboSsrn;
    @BindView(R.id.tv_hasil_lemak_ssrn)
    TextView tvHasilLemakSsrn;
    @BindView(R.id.tv_hasil_protein_ssrn)
    TextView tvHasilProteinSsrn;
    @BindView(R.id.tv_installed_apps)
    TextView tvInstalledApps;
    private PackageManager pm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_datainduk);
        ButterKnife.bind(this);
        pm = getPackageManager();
        mycContext = this;
        tvNmProfil.setText(Sharepref.getString(Constant.NAMA_LENGKAP));
        String bb_now = Sharepref.getString(Constant.BB) != null ? Sharepref.getString(Constant.BB) : "0";
        String t_kalori = Sharepref.getString(Constant.T_KALORI) != null ? Sharepref.getString(Constant.T_KALORI) : "0";
        tvBbProfil.setText(bb_now);
        tvKaloriSasaran.setText(t_kalori);
        if (isPackageInstalled("com.sec.android.app.shealth", pm)) {
           tvInstalledApps.setText("Terpasang");
        } else {
            tvInstalledApps.setText("Belum Terinstall");
            tvInstalledApps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.sec.android.app.shealth"));
                    startActivity(intent);
                }
            });
        }
    }


    @OnClick({R.id.btn_edit_progress_ssrn, R.id.btn_edit_ssrn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_edit_progress_ssrn:
                open(EditProgres.class);
                break;
            case R.id.btn_edit_ssrn:
                open(EditSasaran.class);
                break;
        }
    }

    private boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
