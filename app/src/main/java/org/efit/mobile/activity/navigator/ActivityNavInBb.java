package org.efit.mobile.activity.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;

public class ActivityNavInBb extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputbb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.bookmark_menu:
//                saveDaily();
//                return true;
//            case android.R.id.home:
//                finish();
//                break;
//        }
        return true;
    }
}
