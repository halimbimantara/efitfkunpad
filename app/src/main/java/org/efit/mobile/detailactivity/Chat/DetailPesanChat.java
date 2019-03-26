package org.efit.mobile.detailactivity.Chat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.model.ApiResponse;
import org.efit.mobile.model.datachat.ModelPesanPribadi;
import org.efit.mobile.network.CheckConnection;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ThinkPad T430 on 06/01/2018.
 */

public class DetailPesanChat extends BaseActivity {
    public static final String TAG = DetailPesanChat.class.getSimpleName();
    protected RestClient restClient = RestClient.getInstance();

    @BindView(R.id.recycler_view_detailchatroom)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.message)
    EditText message;
    @BindView(R.id.ll_enter_message)
    LinearLayout llEnterMessage;
    @BindView(R.id.gambar_message)
    ImageView gambarMessage;
    @BindView(R.id.message_status)
    TextView messageStatus;
    @BindView(R.id.warning_lay)
    LinearLayout warningLay;

    private List<ModelPesanPribadi> listModel;
//    private ListAdapterPesanDetail mAdapters;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayoutManager layoutManager;
    private String nameperson = "", personid;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_pesanpertemanan);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        mContext = this;
        if (bundle != null) {
            nameperson = bundle.getString("personname");
            personid = bundle.getString("personid");
            Log.d(TAG, "id :" + personid);
        }
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlueSea, R.color.colorOrange, R.color.colorgreen);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(nameperson);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadDetail();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                LoadDetail();
            }
        });

    }

    private void LoadDetail() {
        if (CheckConnection.isNetworkAvailable()) {
            Log.d(TAG, "LoadDetail: ada koneksi");
            swipeRefreshLayout.setRefreshing(true);
            loadData();
        } else {
            Log.e(TAG, "LoadDetail: no koneksi");
            warningLay.setVisibility(View.VISIBLE);
            messageStatus.setText("Tidak ada koneksi !!");
        }
    }

    private void loadData() {
        Call<ApiResponse<List<ModelPesanPribadi>>> res = restClient.getTentorApi().getDetailchat(Sharepref.getString(Constant.NIP), personid);
        res.enqueue(new Callback<ApiResponse<List<ModelPesanPribadi>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<ModelPesanPribadi>>> call, Response<ApiResponse<List<ModelPesanPribadi>>> response) {
                if (response.isSuccessful()) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.body().getSuccess()) {
                        if (response.body().getResult().size() > 0) {
                            listModel = response.body().getResult();
//                            mAdapters = new ListAdapterPesanDetail(mContext,listModel,Sharepref.getString(Constant.NIP));
//                            recyclerView.setAdapter(mAdapters);
//                            mAdapters.notifyDataSetChanged();
//                            if (mAdapters.getItemCount() > 1) {
////                                recyclerView.scrollToPosition(mAdapters.getItemCount() - 1);
//                                recyclerView.getLayoutManager().scrollToPosition(mAdapters.getItemCount() - 1);
//
////                            recyclerView.getLayoutManager().canScrollVertically();
//                            }
//                            Log.d(TAG, "chat e aktif lek");
                        } else {
                            warningLay.setVisibility(View.VISIBLE);
                            swipeRefreshLayout.setRefreshing(false);
                            swipeRefreshLayout.setVisibility(View.GONE);
                        }
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    warningLay.setVisibility(View.VISIBLE);
                }
                recyclerView.setLayoutManager(layoutManager);
            }


            @Override
            public void onFailure(Call<ApiResponse<List<ModelPesanPribadi>>> call, Throwable t) {
                warningLay.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                swipeRefreshLayout.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.btn_sendprivmessages)
    public void onViewClicked() {
        String pesan=message.getText().toString();
        if(pesan.isEmpty()){
            message.setError("Harap Isi Pesan Anda");
        }else {
            Call<ApiResponse> send = restClient.getTentorApi().postKirimPesan(Sharepref.getString(Constant.NIP), message.getText().toString(), personid,nameperson);
            send.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()) {
                            message.setText("");
                            warningLay.setVisibility(View.GONE);
                            swipeRefreshLayout.setVisibility(View.VISIBLE);
                            LoadDetail();
                        } else {
                            Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Gagal Mengirim Pesan !!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
