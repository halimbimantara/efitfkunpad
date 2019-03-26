package org.efit.mobile.fragment.efit;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.fragment.Pager;
import org.efit.mobile.network.CheckConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentGrafik extends BaseFragment implements TabLayout.OnTabSelectedListener {
    private static final String TAG = "FragmentGrafik";
    @BindView(R.id.tab_grafik)
    TabLayout tabGrafik;
    @BindView(R.id.pager_grafik)
    ViewPager pagerGrafik;
    private Context mContext;
    private Activity mActivity;
    View rooView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LoadDetail();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rooView = inflater.inflate(R.layout.fragment_grafik, container, false);
        ButterKnife.bind(this, rooView);
        mContext = getActivity();
        mActivity = getActivity();

        tabGrafik.addTab(tabGrafik.newTab().setText("Asupan Makanan"));
        tabGrafik.addTab(tabGrafik.newTab().setText("Olahraga"));
        tabGrafik.setTabGravity(TabLayout.GRAVITY_FILL);

        Pager adapter = new Pager(getFragmentManager(), tabGrafik.getTabCount());
        pagerGrafik.setAdapter(adapter);

        tabGrafik.setOnTabSelectedListener(this);
        return rooView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void LoadDetail() {
        if (CheckConnection.isNetworkAvailable()) {
            Log.d(TAG, "LoadDetail: ada koneksi");
        } else {
            Log.e(TAG, "LoadDetail: no koneksi");
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        pagerGrafik.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
