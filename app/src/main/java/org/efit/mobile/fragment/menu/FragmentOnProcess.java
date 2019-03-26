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
import org.efit.mobile.adapter.beranda.ListAdapterMyOrder;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.model.ApiResponse;
import org.efit.mobile.model.kemendesa.absensi.ModelHistoryMingguan;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TheMac on 10/8/17
 */
public class FragmentOnProcess extends BaseFragment {
    private static final String TAG = "frHistory";
    //API
    protected RestClient restClient = RestClient.getInstance();
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
    private Context mContext;
    LinearLayoutManager layoutManager;
    private List<ModelHistoryMingguan> listModel;
    private ListAdapterMyOrder adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("FragmentOnprocess ", "onAttach: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_listpegawai, container, false);
        ButterKnife.bind(this, rootview);
        mContext = getContext();
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlueSea, R.color.colorOrange, R.color.colorgreen);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                LoadDetail();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                LoadDetail();
            }
        });
        return rootview;
    }

    private void LoadDetail() {
        Call<ApiResponse<List<ModelHistoryMingguan>>> getHistory =restClient.getTentorApi().getHistory(Sharepref.getString(Constant.NIP),Constant.KEY);
        getHistory.enqueue(new Callback<ApiResponse<List<ModelHistoryMingguan>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<ModelHistoryMingguan>>> call, Response<ApiResponse<List<ModelHistoryMingguan>>> response) {
                if (response.isSuccessful()) {
                    swipeRefreshLayout.setRefreshing(false);
                    if (response.body().getSuccess()) {
                        if (response.body().getResult().size() > 0) {
                            listModel = response.body().getResult();
                            adapter = new ListAdapterMyOrder(mContext, listModel);
                            recyclerView.setAdapter(adapter);
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
            public void onFailure(Call<ApiResponse<List<ModelHistoryMingguan>>> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
