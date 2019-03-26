package org.efit.mobile.detailactivity.Diklat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.model.ApiResponse;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivityDiklat extends BaseActivity {
    @BindView(R.id.judulLoker)
    TextView judulLoker;
    @BindView(R.id.posisiLoker)
    TextView posisiLoker;
    @BindView(R.id.tglendLoker)
    TextView tglendLoker;
    @BindView(R.id.syaratLoker)
    TextView syaratLoker;

    private static final String TAG = "DetLoker";
    //API
    protected RestClient restClient = RestClient.getInstance();
    @BindView(R.id.btn_lamar)
    Button btnLamar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_fasilitator)
    TextView titleFasilitator;
    @BindView(R.id.title_tgl)
    TextView titleTgl;
    @BindView(R.id.title_kelas)
    TextView titleKelas;
    @BindView(R.id.progressBar_apply)
    ProgressBar progressBarApply;
    private String iddiklat, posisi, tgl, syarat, judul;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailloker);
        ButterKnife.bind(this);
        btnLamar.setVisibility(View.GONE);

        title.setText("Nama Kegiatan");
        titleTgl.setText("Tanggal Kegiatan");
        titleFasilitator.setText("Fasilitator");
        titleKelas.setText("Kelas");

        Bundle a = getIntent().getExtras();
        if (a.getString("iddiklat") != null) {
            iddiklat = a.getString("iddiklat");
            judul = a.getString("judul");
            syarat = a.getString("kelas");
            tgl = a.getString("tgl");
            posisi = a.getString("fasilitator");

            judulLoker.setText(judul);
            posisiLoker.setText(posisi);
            tglendLoker.setText(tgl);
            syaratLoker.setText(syarat);
            LoadCheck(iddiklat);
        } else {
        }
    }

    private void LoadCheck(String iddiklat) {
        Call<ApiResponse> cek = restClient.getTentorApi().getDetailDiklat(iddiklat, Sharepref.getString(Constant.NIP));
        cek.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    progressBarApply.setVisibility(View.GONE);
                    if (response.body().getSuccess()) {
                        btnLamar.setVisibility(View.GONE);
                    } else {
                        btnLamar.setVisibility(View.VISIBLE);
                        btnLamar.setText("DAFTAR");
                    }
                }else{
                    progressBarApply.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressBarApply.setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.btn_lamar)
    public void onViewClicked() {
        showProgress();
        Call<ApiResponse> postlamar = restClient.getTentorApi().postDiklat(iddiklat, Sharepref.getString(Constant.NIP), "pagi");
        postlamar.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().getSuccess()) {
                        Toast.makeText(DetailActivityDiklat.this, "Berhasil Mendaftar !!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(DetailActivityDiklat.this, "Gagal Mendaftar !!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    hideProgress();
                    Toast.makeText(DetailActivityDiklat.this, "Gagal Mendaftar !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                hideProgress();
                Toast.makeText(DetailActivityDiklat.this, "Gagal Mendaftar !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
