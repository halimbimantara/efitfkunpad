package org.efit.mobile.activity.article;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.model.ApiResponse;
import org.efit.mobile.model.dataartikel.ModelArtikel;
import org.efit.mobile.restapi.RestClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TheMac on 8/19/17.
 */

public class ActivityArticle extends BaseActivity implements ListAdapterArtikel.onDetail {
    //API
    private static final String TAG = "ActivityArticle";
    protected RestClient restClient = RestClient.getInstance();


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh;
    @BindView(R.id.lisswipe_lay)
    LinearLayout lisswipeLay;

    private LinearLayoutManager layoutManager;
    private ListAdapterArtikel adapter;
    private ListAdapterArtikel.onDetail listener;
    private List<ModelArtikel> listProduk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_listswipe);
        ButterKnife.bind(this);
        listener = this;
        swipe_refresh.setVisibility(View.VISIBLE);
        swipe_refresh.setColorSchemeResources(R.color.colorOrange, R.color.colorAccent, R.color.colorPrimary);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: ");
                swipe_refresh.setRefreshing(true);
                LoadData("");
            }
        });

        swipe_refresh.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: ");
                swipe_refresh.setRefreshing(true);
                LoadData("");
            }
        });
    }

    private void LoadData(String judul) {
        Call<ApiResponse<List<ModelArtikel>>> apiResponseCall = restClient.getTentorApi().getListArtikel();
        apiResponseCall.enqueue(new Callback<ApiResponse<List<ModelArtikel>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<ModelArtikel>>> call, Response<ApiResponse<List<ModelArtikel>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
//                        hideProgress();
                        swipe_refresh.setRefreshing(false);
                        listProduk = response.body().getResult();
//                        adapter = new ListAdapterArtikel(listProduk, ActivityArticle.this, listener);
//                        recyclerView.setAdapter(adapter);

                    } else {
//                        hideProgress();
                        swipe_refresh.setRefreshing(false);
                    }
                } else {
//                    hideProgress();
                    swipe_refresh.setRefreshing(false);
                    Toast.makeText(ActivityArticle.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<ModelArtikel>>> call, Throwable t) {
                swipe_refresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void ShowDetail(String id) {
        Intent a = new Intent(this, DetailArtikel.class);
        a.putExtra("id_artikel", id);
        startActivity(a);
    }
}
