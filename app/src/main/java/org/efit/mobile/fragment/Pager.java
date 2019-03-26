package org.efit.mobile.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.efit.mobile.fragment.grafik.FragmentGrafikAsupan;
import org.efit.mobile.fragment.grafik.FragmentGrafikOlahraga;

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;

    public Pager(FragmentManager fm, int tabc) {
        super(fm);
        this.tabCount = tabc;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentGrafikAsupan tab1 = new FragmentGrafikAsupan();
                return tab1;
            case 1:
                FragmentGrafikOlahraga tab2 = new FragmentGrafikOlahraga();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
