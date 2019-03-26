package org.efit.mobile.fragment.efit;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.efit.mobile.R;
import org.efit.mobile.adapter.beranda.ListAdapterAbout;
import org.efit.mobile.adapter.beranda.ListAdapterNotifikasi;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.model.ModalAbout;
import org.efit.mobile.network.CheckConnection;
import org.efit.mobile.restapi.RestClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBantuan extends BaseFragment {
    private static final String TAG = "FragmentBantuan";
    protected RestClient restClient = RestClient.getInstance();
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lisswipe_lay)
    LinearLayout lisswipeLay;
    private Context mContext;
    private List<ModalAbout.Data> listModel;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager layoutManager;
    ListAdapterAbout listadapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LoadDetail();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, rooView);
        mContext = getActivity();

        swipeRefreshLayout.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorpurple, R.color.colorPink);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                LoadData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                LoadData();
            }
        });
        return rooView;
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

    private void LoadData() {
        Call<ModalAbout> getPage = restClient.getTentorApi().getPage();
        getPage.enqueue(new Callback<ModalAbout>() {
            @Override
            public void onResponse(Call<ModalAbout> call, Response<ModalAbout> response) {
                if (response.isSuccessful()) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.body().getStatus().equals("success")) {
                        if (response.body().getData().size() > 0) {
                            listModel = response.body().getData();
                            listadapter = new ListAdapterAbout(mContext, listModel);
                            recyclerView.setAdapter(listadapter);
                        } else {
                        }
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e(TAG, "onResponse: unsucces");
                }
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ModalAbout> call, Throwable t) {

            }
        });
    }


}
