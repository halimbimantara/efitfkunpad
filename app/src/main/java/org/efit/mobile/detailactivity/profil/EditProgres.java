package org.efit.mobile.detailactivity.profil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProgres extends BaseActivity {

    @BindView(R.id.tv_edit_bb)
    EditText tvEditBb;
    @BindView(R.id.tv_hari_ini)
    TextView tvHariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_progres);
        ButterKnife.bind(this);
        tvHariIni.setText(getTanggalNow());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmark_menu:
                saveDaily();
                return true;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void saveDaily() {
        Sharepref.saveString(Constant.BB, getEditToString(tvEditBb));
        ShowMessages(EditProgres.this, "Berhasil Menyimpan");

    }
}
