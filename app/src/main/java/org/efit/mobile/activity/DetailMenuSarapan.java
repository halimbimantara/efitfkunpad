package org.efit.mobile.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.model.ResponseEfitBasic;
import org.efit.mobile.model.dataharian.ModelDetailAsupanInput;
import org.efit.mobile.model.dataharian.ModelHarianSarapan;
import org.efit.mobile.model.dataharian.ModelInAsupanharian;
import org.efit.mobile.model.dataharian.ModelMasterAsupan;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMenuSarapan extends BaseActivity {
    @BindView(R.id.tv_judul_nmserving)
    TextView tvJudulNmserving;
    @BindView(R.id.tv_total_nmserving)
    TextView tvTotalNmserving;
    @BindView(R.id.cv_numberserving)
    CardView cvNumberserving;
    @BindView(R.id.chart)
    PieChartView chart;
    @BindView(R.id.cv_serving_cart)
    CardView cvServingCart;
    @BindView(R.id.tv_total_calories)
    TextView tvTotalCalories;
    @BindView(R.id.cv_serving_calories)
    CardView cvServingCalories;
    @BindView(R.id.tv_total_fat)
    TextView tvTotalFat;
    @BindView(R.id.tv_total_saturated)
    TextView tvTotalSaturated;
    @BindView(R.id.tv_nama_makanan)
    TextView tvNamaMakanan;
    @BindView(R.id.tv_karbo_gr)
    TextView tvKarboGr;
    @BindView(R.id.tv_lemak_gr)
    TextView tvLemakGr;
    @BindView(R.id.tv_protein_gr)
    TextView tvProteinGr;
    @BindView(R.id.tv_total_protein)
    TextView tvTotalProtein;
    @BindView(R.id.tv_total_serat)
    TextView tvTotalSerat;
    @BindView(R.id.tv_total_kalsium)
    TextView tvTotalKalsium;
    @BindView(R.id.tv_total_fosfor)
    TextView tvTotalFosfor;
    @BindView(R.id.tv_total_vit_c)
    TextView tvTotalVitC;
    @BindView(R.id.tv_total_vit_a)
    TextView tvTotalVitA;
    private int id_dkbm;
    List<SliceValue> pieData;
    private DatabaseHandler dbHelper = null;
    private ModelHarianSarapan modelHarianSarapan;
    private String inLemak = "", inEnergi = "", inKalsium = "", inFosfor = "", inProtein = "", inSerat = "", inKarbohidrat = "", inVitC = "", inZink = "", inVitA = "";
    private int takaran_persaji = 1, tipe_jam_makan = 0, onInternet = 0, onInternet2 = 0;
    private String tanggal_inputan = "", jam_now = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu_sarapan);
        ButterKnife.bind(this);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss");
        jam_now = dateformat.format(c.getTime());

        dbHelper = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        Bundle bundle = getIntent().getExtras();
        if (bundle.getInt("ID") != 0) {
            id_dkbm = bundle.getInt("ID");
            tanggal_inputan = bundle.getString("TGL_IN");
            tipe_jam_makan = bundle.getInt("JAM_MAKAN");
            loadData(id_dkbm);
        } else {
            finish();
            ShowMessages(DetailMenuSarapan.this, "Gagal");
        }
        pieData = new ArrayList<>();
        tvTotalNmserving.setText(Integer.toString(takaran_persaji));
        cvNumberserving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sDialogTotalPersaji();
            }
        });
        tvTotalNmserving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sDialogTotalPersaji();
            }
        });
    }

    private void sDialogTotalPersaji() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Input Jumlah Takaran");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setRawInputType(Configuration.KEYBOARD_12KEY);
        input.setText("1");
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for OK button here
                String value = input.getText().toString();
                takaran_persaji = Integer.parseInt(value);
                tvTotalNmserving.setText(Integer.toString(takaran_persaji));
            }
        });
        alert.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Put actions for CANCEL button here, or leave in blank
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void loadData(final int id_dkbm) {
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("please wait...");
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);
        pDialog.show();

        new AsyncTask<Object, Integer, ModelHarianSarapan>() {
            @Override
            protected ModelHarianSarapan doInBackground(Object... objects) {
                modelHarianSarapan = dbHelper.detailMakanan(id_dkbm);
                return modelHarianSarapan;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                pDialog.setMessage("please wait..." + values[0]);
                pDialog.show();
            }

            @Override
            protected void onPostExecute(ModelHarianSarapan model) {
                tvNamaMakanan.setText(model.getBAHAN_MAKANAN());
                inLemak = model.getLEMAK();
                inEnergi = model.getENERGI();
                inProtein = model.getPROTEIN();
                inFosfor = model.getFOSFOR();
                inKalsium = model.getKALSIUM();
                inVitC = model.getVIT_C();
                inKarbohidrat = model.getKH();
                inSerat = model.getSERAT();
                inZink = model.getZN();
                inVitA = model.getRETNOL();

                tvTotalFat.setText(model.getLEMAK());
                tvTotalCalories.setText(model.getENERGI());
                tvProteinGr.setText(model.getPROTEIN());
                tvTotalSerat.setText(model.getSERAT());
                tvTotalSaturated.setText(model.getKH());
                tvTotalKalsium.setText(model.getKALSIUM());
                tvTotalFosfor.setText(model.getFOSFOR());
                tvTotalVitC.setText(model.getVIT_C());
                tvTotalVitA.setText(model.getRETNOL());

                tvKarboGr.setText(model.getKH());
                tvProteinGr.setText(model.getPROTEIN());
                tvLemakGr.setText(model.getLEMAK());

                pieData.add(new SliceValue(Math.round(Conversi(inProtein)), getResources().getColor(R.color.colorgreen)));
                pieData.add(new SliceValue(Math.round(Conversi(inLemak)), getResources().getColor(R.color.colorRed)));
                pieData.add(new SliceValue(Math.round(Conversi(inKarbohidrat)), getResources().getColor(R.color.colorBlue)));

                PieChartData pieChartData = new PieChartData(pieData);
                pieChartData.setHasCenterCircle(true).setCenterText1("520").setCenterText1FontSize(12).setCenterText1Color(getResources().getColor(R.color.colorBlack));
                pieChartData.setHasCenterCircle(true).setCenterText2("Cal").setCenterText1FontSize(15);
                chart.setPieChartData(pieChartData);
                pDialog.dismiss();
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmark_menu:
                saveDaily();
                return true;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void saveDaily() {
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        int cek_master = dbHelper.getLastIDMasterAsupan(tanggal_inputan);
        if (cek_master > 0) {
            //insert internet
            ModelInAsupanharian posting = new ModelInAsupanharian();
            ModelInAsupanharian.Detail pdetail = new ModelInAsupanharian.Detail();

            pdetail.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            pdetail.setCalsium(inKalsium);
            pdetail.setProtein(inProtein);
            pdetail.setVit_c(inVitC);
            pdetail.setEnergi(inEnergi);
            pdetail.setZinc(inZink);
            pdetail.setVit_a(inVitA);
            pdetail.setTanggal_input(tanggal_inputan+" "+jam_now);

            pdetail.setId_user(Sharepref.getString(Constant.USERID));
            pdetail.setJumlah(Integer.toString(takaran_persaji));
            pdetail.setId_dkbm(Integer.toString(id_dkbm));
            pdetail.setJam_makan(JamMakan(tipe_jam_makan));

//            posting.setId_master_asupan(String.valueOf(cek_master));
            posting.setTanggal(tanggal_inputan);
            posting.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            posting.setStatus("Selesai");
            posting.setId_user(Sharepref.getString(Constant.USERID));

            List<ModelInAsupanharian.Detail> listd = new ArrayList<>();
            listd.add(pdetail);
            posting.setDetail(listd);

//            Gson gson = new Gson();
//            String json = gson.toJson(posting);
//            System.out.println(json);

            List<ModelInAsupanharian> aa = new ArrayList<>();
            aa.add(posting);

            Call<ResponseEfitBasic> updateAsupanMakanan = restClient.getTentorApi().updateAsupanMakanan(aa);
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
                    ShowMessages(DetailMenuSarapan.this, t.getMessage());
                }
            });
            Log.i("sve", "saveDaily: " + inVitA);

            //masukan detailnua
            ModelDetailAsupanInput indetail = new ModelDetailAsupanInput();
            indetail.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            indetail.setCalcium(Double.parseDouble(inKalsium));
            indetail.setProtein(Double.parseDouble(inProtein));
            indetail.setJam_makan(JamMakan(tipe_jam_makan));
            indetail.setJumlah(takaran_persaji);
            indetail.setVit_c(Double.parseDouble(inVitC));
            indetail.setVit_a(Double.parseDouble(inVitA == "0" ? "0.0" : inVitA));
            indetail.setId_dkbm(id_dkbm);
            indetail.setEnergi(Double.parseDouble(inEnergi));
            indetail.setId_master_asupan(cek_master);
            long inDetail = dbHelper.addDetailAsupan(indetail);
            if (inDetail != -1) {
                Intent log = new Intent(DetailMenuSarapan.this, MainMenuActivity.class);
                log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(log);
            } else {
                ShowMessages(DetailMenuSarapan.this, "Gagal Menyimpan..!");
            }
        } else {
            //masukan kedalam table master
            ModelInAsupanharian posting = new ModelInAsupanharian();
            ModelInAsupanharian.Detail pdetail = new ModelInAsupanharian.Detail();

            pdetail.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            pdetail.setCalsium(inKalsium);
            pdetail.setProtein(inProtein);
            pdetail.setVit_c(inVitC);
            pdetail.setEnergi(inEnergi);
            pdetail.setZinc(inZink);
            pdetail.setVit_a(inVitA == "0" ? "0.0" : inVitA);
            pdetail.setTanggal_input(tanggal_inputan + " " +jam_now);

            pdetail.setId_user(Sharepref.getString(Constant.USERID));
            pdetail.setJumlah(Integer.toString(takaran_persaji));
            pdetail.setId_dkbm(Integer.toString(id_dkbm));
            pdetail.setJam_makan(JamMakan(tipe_jam_makan));

            posting.setId_master_asupan(String.valueOf(cek_master));
            posting.setTanggal(tanggal_inputan);
            posting.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            posting.setId_master_asupan(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            posting.setStatus("Selesai");
            posting.setId_user(Sharepref.getString(Constant.USERID));

            List<ModelInAsupanharian.Detail> listd = new ArrayList<>();
            listd.add(pdetail);
            posting.setDetail(listd);

            Gson gson = new Gson();
            String json = gson.toJson(posting);
            System.out.println(json);

            List<ModelInAsupanharian> aas = new ArrayList<>();
            aas.add(posting);

            Call<ResponseEfitBasic> updateAsupanMakanan = restClient.getTentorApi().updateAsupanMakanan(aas);
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
                    ShowMessages(DetailMenuSarapan.this, t.getMessage());
                    onInternet = 0;
                }
            });

            ModelMasterAsupan inAsupan = new ModelMasterAsupan();
            if (onInternet == 0) {
                inAsupan.setStatus("Belum");
            } else {
                inAsupan.setStatus("Selesai");
            }
            inAsupan.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
            inAsupan.setTanggal(tanggal_inputan);
            long statusInMster = dbHelper.addDataMasterAsupan(inAsupan);
            if (statusInMster != -1) {
                int cek_masters = dbHelper.getLastIDMasterAsupan(tanggal_inputan);
                Log.i("in Master", "Berhasil: ");
                ModelDetailAsupanInput indetail = new ModelDetailAsupanInput();
                indetail.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
                indetail.setCalcium(Double.parseDouble(inKalsium));
                indetail.setProtein(Double.parseDouble(inProtein));
                indetail.setJam_makan(JamMakan(tipe_jam_makan));
                indetail.setJumlah(takaran_persaji);
                indetail.setVit_c(Double.parseDouble(inVitC));
                indetail.setVit_a(Double.parseDouble(inVitA));
                indetail.setId_dkbm(id_dkbm);
                indetail.setEnergi(Double.parseDouble(inEnergi));
                indetail.setId_master_asupan(cek_masters);
                long inDetail = dbHelper.addDetailAsupan(indetail);
                if (inDetail != -1) {
                    Intent log = new Intent(DetailMenuSarapan.this, MainMenuActivity.class);
                    log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(log);
                } else {
                    ShowMessages(DetailMenuSarapan.this, "Gagal Menyimpan IN0..!");
                }
            } else {
                //tambah master kemudian tambah detail
                ShowMessages(DetailMenuSarapan.this, "Gagal Menyimpan M00..!");
            }
        }
    }

    private int Conversi(String input) {
        double x = Double.parseDouble(input);
        int value = (int) x;
        return value;
    }

    private String JamMakan(int tipe) {
        String jam_makan = "";
        switch (tipe) {
            case 1:
                jam_makan = "makan_sarapan";
                break;
            case 2:
                jam_makan = "makan_siang";
                break;
            case 3:
                jam_makan = "makan_malam";
                break;
            case 6:
                jam_makan = "makanan_ringan";
                break;
            default:
                jam_makan = "makan_sarapan";
                break;
        }
        return jam_makan;
    }

}
