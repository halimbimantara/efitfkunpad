package org.efit.mobile.fragment.menu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.efit.mobile.R;
import org.efit.mobile.adapter.beranda.ListAdapterNotifikasi;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.model.kemendesa.absensi.ModelNotifikasi;
import org.efit.mobile.network.CheckConnection;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNotifikasi extends BaseFragment {
    protected RestClient apiClient = RestClient.getInstance();
    public static final String TAG = "ListcariMessages";
    View rootview;
    Context mconContext;
    Activity mActivity;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.lisswipe_lay)
    LinearLayout lisswipeLay;

    @BindView(R.id.gambar_message)
    ImageView gambarMessage;

    @BindView(R.id.message_status)
    TextView messageStatus;

    @BindView(R.id.warning_lay)
    LinearLayout warningLay;

    private List<ModelNotifikasi.DataEntity> listModel;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager layoutManager;
    ListAdapterNotifikasi listadapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        LoadDetail();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_listonchat, container, false);
        ButterKnife.bind(this, rootview);

        mActivity = getActivity();
        mconContext = this.getContext();
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlueSea, R.color.colorOrange, R.color.colorgreen);

        warningLay.setVisibility(View.VISIBLE);
        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void LoadDetail() {
        if (CheckConnection.isNetworkAvailable()) {
            loadData();

        } else {
            Log.e(TAG, "LoadDetail: no koneksi");
            warningLay.setVisibility(View.VISIBLE);
            messageStatus.setText("Tidak ada koneksi !!");
        }
    }

    private void loadData() {
        Call<ModelNotifikasi> getNotifikasi = apiClient.getTentorApi().getNotifikasi(Sharepref.getString(Constant.NIP),Constant.KEY_EO);
        getNotifikasi.enqueue(new Callback<ModelNotifikasi>() {
            @Override
            public void onResponse(Call<ModelNotifikasi> call, Response<ModelNotifikasi> response) {
                if (response.isSuccessful()) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.body().isStatus()) {
                        if (response.body().getData().size() > 0) {
                            listModel = response.body().getData();
                            listadapter = new ListAdapterNotifikasi(mconContext, listModel);
                            recyclerView.setAdapter(listadapter);
                        } else {
                            warningLay.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Log.e(TAG, "onResponse: unsucces");
                    warningLay.setVisibility(View.VISIBLE);
                }
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<ModelNotifikasi> call, Throwable throwable) {
                warningLay.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
                swipeRefreshLayout.setVisibility(View.GONE);
                lisswipeLay.setVisibility(View.GONE);
            }
        });
    }
}
