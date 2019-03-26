package org.efit.mobile.detailactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.fragment.menu.FragmentModule;

public class ActivityProgram extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Fragment fragment3 = new FragmentModule();
        FragmentManager fragmentManager3 = getSupportFragmentManager();
        fragmentManager3.beginTransaction().replace(R.id.content_program, fragment3).commit();
    }
}
