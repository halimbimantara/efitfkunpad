package org.efit.mobile.detailactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.R;
import org.efit.mobile.activity.DetailMenuSarapan;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.model.ModelDetailAir;
import org.efit.mobile.model.ResponseEfitBasic;
import org.efit.mobile.model.dataharian.ModelInAsupanharian;
import org.efit.mobile.model.dataharian.ModelMasterAsupan;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivityMinum extends BaseActivity {
    @BindView(R.id.et_input_water)
    EditText etInputWater;
    private int jenis_kategori = 0;
    private String tanggal_inputan = "";
    private int total = 0;
    private DatabaseHandler dbHelper = null;
    private int onInternet = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_minum);
        ButterKnife.bind(this);
        dbHelper = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        Bundle bundle = getIntent().getExtras();

        if (bundle.getInt("TIPE") > 0) {
            jenis_kategori = bundle.getInt("TIPE");
            tanggal_inputan = bundle.getString("TGL_IN");
        } else {
            finish();
        }
    }

    @OnClick({R.id.btn_100, R.id.btn_250, R.id.btn_500, R.id.btn_1000})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_100:
                addVolumes(100);
                etInputWater.setText(Integer.toString(total));
                break;
            case R.id.btn_250:
                addVolumes(250);
                etInputWater.setText(Integer.toString(total));
                break;
            case R.id.btn_500:
                addVolumes(500);
                etInputWater.setText(Integer.toString(total));
                break;
            case R.id.btn_1000:
                addVolumes(1000);
                etInputWater.setText(Integer.toString(total));
                break;
        }
    }

    private int addVolumes(int addVolume) {
        total += addVolume;
        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmark_menu:
                saveWater();
                return true;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void saveWater() {
        String kode_trx = tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID);
        int waterinput = Integer.parseInt(etInputWater.getText().toString());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        int cek_master = dbHelper.getLastIDMasterAsupan(tanggal_inputan);
        if (cek_master > 0) {
            ModelDetailAir inair = new ModelDetailAir();
            inair.setKode_transaksi(kode_trx);
            inair.setTanggal(tanggal_inputan);
            inair.setVolume_air(waterinput);
            Call<ResponseEfitBasic> updateAsupanMakanan = restClient.getTentorApi().updateAsupanAir(Sharepref.getString(Constant.USERID), etInputWater.getText().toString(), tanggal_inputan, kode_trx);
            updateAsupanMakanan.enqueue(new Callback<ResponseEfitBasic>() {
                @Override
                public void onResponse(Call<ResponseEfitBasic> call, Response<ResponseEfitBasic> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equals("success")) {
                            onInternet = 1;
//                            ShowMessages(DetailMenuSarapan.this, response.body().getMessage());
                        } else {
                            onInternet = 0;
//                            ShowMessages(DetailMenuSarapan.this, response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseEfitBasic> call, Throwable t) {
                    onInternet = 0;
                }
            });
            if (onInternet == 0) {
                inair.setStatus_update("Belum");
            } else {
                inair.setStatus_update("Selesai");
            }
//            inair.setStatus_update(onInternet);
            long inDetail =  dbHelper.addDetailAir(inair);
            if (inDetail != -1) {
                Intent log = new Intent(DetailActivityMinum.this, MainMenuActivity.class);
                log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(log);
            } else {
                ShowMessages(DetailActivityMinum.this, "Gagal Menyimpan IN0..!");
            }
        } else {
            //set KT
            ModelMasterAsupan inAsupan = new ModelMasterAsupan();
            inAsupan.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            inAsupan.setTanggal(tanggal_inputan);
            long statusInMster = dbHelper.addDataMasterAsupan(inAsupan);
            if (statusInMster != -1) {
                ModelDetailAir inair = new ModelDetailAir();
                inair.setKode_transaksi(kode_trx);
                inair.setTanggal(tanggal_inputan);
                inair.setVolume_air(waterinput);
                Call<ResponseEfitBasic> updateAsupanMakanan = restClient.getTentorApi().updateAsupanAir(Sharepref.getString(Constant.USERID), etInputWater.getText().toString(), tanggal_inputan, kode_trx);
                updateAsupanMakanan.enqueue(new Callback<ResponseEfitBasic>() {
                    @Override
                    public void onResponse(Call<ResponseEfitBasic> call, Response<ResponseEfitBasic> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getStatus().equals("success")) {
                                onInternet = 1;
//                            ShowMessages(DetailMenuSarapan.this, response.body().getMessage());
                            } else {
                                onInternet = 0;
//                            ShowMessages(DetailMenuSarapan.this, response.body().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseEfitBasic> call, Throwable t) {
                        onInternet = 0;
                    }
                });
                if (onInternet == 0) {
                    inair.setStatus_update("Belum");
                } else {
                    inair.setStatus_update("Selesai");
                }
//            inair.setStatus_update(onInternet);

                long inDetail =  dbHelper.addDetailAir(inair);
                if (inDetail != -1) {
                    Intent log = new Intent(DetailActivityMinum.this, MainMenuActivity.class);
                    log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(log);
                } else {
                    ShowMessages(DetailActivityMinum.this, "Gagal Menyimpan IN0..!");
                }
            } else {
                ShowMessages(DetailActivityMinum.this, "Gagal Menyimpan MMinum..!");
            }
        }
    }
}
