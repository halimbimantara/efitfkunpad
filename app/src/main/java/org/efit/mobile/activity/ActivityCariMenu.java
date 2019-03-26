package org.efit.mobile.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.adapter.carimenu.ListAdapterCariMenu;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.model.dataharian.ModelHarianSarapan;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityCariMenu extends BaseActivity
        implements ListAdapterCariMenu.actionMakanan {

    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lisswipe_lay)
    LinearLayout lisswipeLay;
    @BindView(R.id.et_cari_menu)
    EditText etCariMenu;
    LinearLayoutManager layoutManager;
    @BindView(R.id.tv_clear_search)
    TextView tvClearSearch;
    private ListAdapterCariMenu adapterCariMenu;
    private DatabaseHandler dbHelper = null;
    private List<ModelHarianSarapan> modelHarianSarapan = null;
    private int total_model = 0;
    private Context mContext;
    private ListAdapterCariMenu.actionMakanan mAction;
    private int jenis_kategori = 0;
    private String tanggal_inputan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_menu);
        ButterKnife.bind(this);
        mAction = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle.getInt("TIPE") > 0) {
            jenis_kategori = bundle.getInt("TIPE");
            tanggal_inputan = bundle.getString("TGL_IN");
        } else {
            finish();
        }
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlueSea, R.color.colorOrange, R.color.colorgreen);
        mContext = this;
        etCariMenu.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    swipeRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(true);
                            performSearch(etCariMenu.getText().toString());
                        }
                    });
                    return true;
                }
                return false;
            }
        });

        tvClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etCariMenu.setText("");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!etCariMenu.getText().toString().isEmpty()) {
                    performSearch(etCariMenu.getText().toString());
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    etCariMenu.setError("Tidak Boleh Kosong");
                }
            }
        });

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                performSearch("nasi");
            }
        });
    }

    private void performSearch(final String cari) {
        Log.i("cari", "performSearch: " + cari);
        dbHelper = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        new AsyncTask<Object, Integer, List<ModelHarianSarapan>>() {
            @Override
            protected List<ModelHarianSarapan> doInBackground(Object... objects) {
                modelHarianSarapan = dbHelper.showCariMakanan(cari);
                return modelHarianSarapan;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelHarianSarapan> list) {
                adapterCariMenu = new ListAdapterCariMenu(mContext, list, mAction);
                recyclerView.setAdapter(adapterCariMenu);
                adapterCariMenu.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.setLayoutManager(layoutManager);
                //lv.setOnItemClickListener can be put here
            }
        }.execute();
    }

    @Override
    public void DetailMakanan(int id) {
        Intent detailMakanan = new Intent(mContext, DetailMenuSarapan.class);
        detailMakanan.putExtra("ID", id);
        detailMakanan.putExtra("JAM_MAKAN", jenis_kategori);
        detailMakanan.putExtra("TGL_IN", tanggal_inputan);
        startActivity(detailMakanan);
    }
}



