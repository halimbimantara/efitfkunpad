package org.efit.mobile.welcome.quizioner;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.model.ResponseEfit;
import org.efit.mobile.model.ResponseEfitBasic;
import org.efit.mobile.model.pertanyaan.ModelPertanyaan;
import org.efit.mobile.model.quesioner.ModelUserQuesioner;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;
import org.efit.mobile.welcome.FirstActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityQuiz extends BaseActivity {
    @BindView(R.id.lay_question)
    LinearLayout layQuestion;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll_dots)
    LinearLayout llDots;
    @BindView(R.id.et_jam)
    Spinner etJam;
    @BindView(R.id.et_menit)
    Spinner etMenit;
    @BindView(R.id.ll_jawaban_tipe1)
    LinearLayout llJawabanTipe1;
    @BindView(R.id.et_hari)
    Spinner etHari;
    @BindView(R.id.ll_jawaban_tipe2)
    LinearLayout llJawabanTipe2;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.ll_jawaban_tipe3)
    LinearLayout llJawabanTipe3;
    @BindView(R.id.imv_back_qz)
    ImageView imvBackQz;
    @BindView(R.id.imv_next_qz)
    ImageView imvNextQz;
    @BindView(R.id.lay_jawaban)
    LinearLayout layJawaban;
    @BindView(R.id.rg_jawaban)
    RadioGroup rgJawaban;
    @BindView(R.id.et_bb)
    EditText etBb;
    @BindView(R.id.et_kalori)
    EditText etKalori;
    @BindView(R.id.ll_jawaban_tipe4)
    LinearLayout llJawabanTipe4;
    @BindView(R.id.et_tb)
    EditText etTb;
    @BindView(R.id.et_umur)
    EditText etUmur;

    private TextView[] Tips = new TextView[1000];
    private TextView[] Qs = new TextView[1000];
    private TextView[] Description = new TextView[1000];
    private ImageView[] dots = new ImageView[20];

    private EditText[] tv = new EditText[10];
    private Button[] click = new Button[10];

    private int lastPosition = 0;
    private String l_idPertanyaan;
    private int total_qz;
    private int index_qz;
    private Context mContext;
    int postnext = 0;

    private DatabaseHandler dbHelper = null;
    RelativeLayout.LayoutParams params;
    List<ModelPertanyaan> list = null;
    private static final String TAG = "Quiz";
    private String pertanyaan = "", keterangan = "";

    String[] jam_aktifitas = {"6", "5", "4", "3", "2", "1"};

    int tipe_soal = 0;
    Toolbar mActionBarToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle("Quesioner - Step " + 1);

        mContext = this;
        dbHelper = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        list = dbHelper.showPertanyaan();
        total_qz = list.size();
        loadSoal(index_qz);
        init();
    }

    private void init() {
        List<String> a = new ArrayList<>();
        List<String> jam = new ArrayList<>();
        for (int i = 1; i <= 23; i++) {
            jam.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, jam);
        etJam.setAdapter(adapter);
        a.add("0");
        for (int i = 10; i <= 50; i += 10) {
            a.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, a);
        etMenit.setAdapter(adapters);
        etHari.setAdapter(adapter);
    }

    private void loadSoal(int index_qz) {
        for (int i = 0; i < total_qz; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(24, 24);
            lp.setMargins(4, 4, 4, 4);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                dots[i] = new ImageView(mContext);
                dots[i].setLayoutParams(lp);
                if (i == index_qz) {
                    dots[i].setImageResource(R.drawable.circle_step);
                } else {
                    dots[i].setImageResource(R.drawable.circle_active);
                }
            }
            llDots.addView(dots[i]);
        }
        Tips[0] = new TextView(mContext);
        Qs[0] = new TextView(mContext);
        Description[0] = new TextView(mContext);
        tv[0] = new EditText(mContext);

        params = new RelativeLayout.LayoutParams((int) RelativeLayout.LayoutParams.MATCH_PARENT, (int) RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 5;

        Qs[0].setTextSize((float) 18);
        Qs[0].setPadding(10, 2, 10, 10);
        Qs[0].setLayoutParams(params);
        Qs[0].setText(list.get(index_qz).getPertanyaan());

        Description[0].setTextSize((float) 16);
        Description[0].setText(list.get(index_qz).getDescription());
        Description[0].setPadding(20, 7, 20, 10);

        Tips[0].setTextSize((float) 19);
        Tips[0].setText(list.get(index_qz).getKategori());
        Tips[0].setPadding(20, 50, 20, 50);
        pertanyaan = list.get(index_qz).getPertanyaan();
        if (list.get(index_qz).getTipe_soal() == 1) {
            //
            llJawabanTipe1.setVisibility(View.GONE);
            llJawabanTipe2.setVisibility(View.GONE);
            llJawabanTipe4.setVisibility(View.GONE);
            llJawabanTipe3.setVisibility(View.VISIBLE);
        } else if (list.get(index_qz).getTipe_soal() == 2) {
            //
            llJawabanTipe1.setVisibility(View.GONE);
            llJawabanTipe2.setVisibility(View.VISIBLE);
            llJawabanTipe3.setVisibility(View.GONE);
            llJawabanTipe4.setVisibility(View.GONE);
        } else if (list.get(index_qz).getTipe_soal() == 4) {
            llJawabanTipe1.setVisibility(View.GONE);
            llJawabanTipe2.setVisibility(View.GONE);
            llJawabanTipe3.setVisibility(View.GONE);
            llJawabanTipe4.setVisibility(View.VISIBLE);
        } else {
            //
            llJawabanTipe1.setVisibility(View.VISIBLE);
            llJawabanTipe2.setVisibility(View.GONE);
            llJawabanTipe3.setVisibility(View.GONE);
            llJawabanTipe4.setVisibility(View.GONE);
        }
        //tampilkan button finish
        layQuestion.addView(Tips[0]);
        layQuestion.addView(Description[0]);
        layJawaban.addView(Qs[0]);
    }

    @OnClick({R.id.imv_back_qz, R.id.imv_next_qz})
    public void onViewClicked(View view) {
        int tipesoal = list.get(index_qz).getTipe_soal();
        switch (view.getId()) {
            case R.id.imv_next_qz:
                if (index_qz == (total_qz - 1)) {
                    ShowMessages(this,"Harap tunggu untuk pemrosesan");
//                    postjawabdb(tipesoal);
                    if (getEditToString(etTb).isEmpty()) {
                        etTb.setError("Harus Di Isi");
                    } else if (getEditToString(etBb).isEmpty()) {
                        etBb.setError("Harus Di Isi");
                    } else if (getEditToString(etUmur).isEmpty()) {
                        etUmur.setError("Harus Di Isi");
                    } else {
                        Sharepref.saveString(Constant.T_KALORI,Integer.toString(getrumusKalori()));
                        etKalori.setText(Integer.toString(getrumusKalori()));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                postTarget(getEditToString(etTb), getEditToString(etBb), getEditToString(etKalori));
                                open(MainMenuActivity.class);
                            }
                        }, 5000);
                    }

                } else {
                    layQuestion.removeAllViews();
                    layJawaban.removeAllViews();
                    llDots.removeAllViews();
                    //post jawaban
                    postjawabdb(tipesoal);
                    index_qz++;
                    int total = index_qz + 1;
                    int last_sesion = (total_qz - 2);
                    if (last_sesion == total) {
                        mActionBarToolbar.setTitle("Input Sasaran");
                    } else {
                        mActionBarToolbar.setTitle("Quesioner - Step " + total);
                    }
//                    System.out.println("Pertanyaan ke " + index_qz + "| " + pertanyaan);
                    loadSoal(index_qz);
                }
                break;
            case R.id.imv_back_qz:
                if (index_qz > 0) {
                    int cek_isijawaban = dbHelper.CekJawabanUserQuesioner(list.get(index_qz).getID());
                    if (cek_isijawaban > 0) {
                        Log.d("cekjawaban", "jawaban ada");
                    } else {
                        Log.e("cekjawaban", "jawaban tidak ada");
                    }
                    mActionBarToolbar.setTitle("Quesioner - Step " + index_qz);
                    layQuestion.removeAllViews();
                    layJawaban.removeAllViews();
                    llDots.removeAllViews();
                    index_qz--;
                    loadSoal(index_qz);
                }
                break;
        }
    }

    private void postTarget(String tb, String bb, String kalori) {
        showProgress();
        Sharepref.saveString(Constant.BB, bb);
        Sharepref.saveString(Constant.TB, tb);
        Sharepref.saveString(Constant.T_KALORI, kalori);

        Call<ResponseEfitBasic> updateTargetSasaran = restClient.getTentorApi().updateTargetSasaran(Sharepref.getString(Constant.USERID), kalori, bb, tb);
        updateTargetSasaran.enqueue(new Callback<ResponseEfitBasic>() {
            @Override
            public void onResponse(Call<ResponseEfitBasic> call, Response<ResponseEfitBasic> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().getStatus().equals("success")) {
//                        ShowMessages(ActivityQuiz.this, "Berhasil Insert");
                        Log.d(TAG, "onResponse: Berhasil Insert");
                    } else {
                        Log.d(TAG, "onResponse: Gagal Insert");
//                        ShowMessages(ActivityQuiz.this, "Gagal Insert");
                    }
                } else {
                    hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ResponseEfitBasic> call, Throwable t) {
                hideProgress();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void postjawabdb(int tipesoal) {
        int cek_isijawaban = dbHelper.CekJawabanUserQuesioner(list.get(index_qz).getID());
        String QJawaban = "";
        if (tipesoal == 1) {
            int selectedId = rgJawaban.getCheckedRadioButtonId();
            RadioButton radioButton;
            radioButton = (RadioButton) findViewById(selectedId);
            QJawaban = radioButton.getText().toString();
        } else if (tipesoal == 2) {
            QJawaban = etHari.getSelectedItem().toString();
        } else {
            int menit_i = Integer.parseInt(etMenit.getSelectedItem().toString());
            String menit_tambahan = "";
            if (menit_i < 10) {
                menit_tambahan = "0";
            }
            QJawaban = "0" + etJam.getSelectedItem().toString() + ":" + menit_tambahan + etMenit.getSelectedItem().toString();

        }
        ModelUserQuesioner jawaban = new ModelUserQuesioner();
        jawaban.setId_quesioner(list.get(index_qz).getID());
        jawaban.setId_user(Integer.parseInt(Sharepref.getString(Constant.USERID)));
        jawaban.setJawaban(QJawaban);

        if (cek_isijawaban > 0) {
            Log.d("cekjawaban", "jawaban ada");
            int statusupdate = dbHelper.UpdateUserQuesioner(jawaban, cek_isijawaban);
            Log.d("cekjawaban", "postjawabdb: " + statusupdate);
            UpdateJawaban(Integer.toString(list.get(index_qz).getID()), QJawaban);
        } else {
            Log.e("cekjawaban", "jawaban tidak ada");
            dbHelper.addData(jawaban);
            postJawaban(Integer.toString(list.get(index_qz).getID()), QJawaban);
        }
    }

    private void postJawaban(String id_quiz, String jawaban) {
        showProgress();
        Call<ResponseEfit> postJawabanQuesioner = restClient.getTentorApi().postJawabanQuesioner(Sharepref.getString(Constant.USERID), id_quiz, jawaban);
        postJawabanQuesioner.enqueue(new Callback<ResponseEfit>() {
            @Override
            public void onResponse(Call<ResponseEfit> call, Response<ResponseEfit> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().getStatus().equals("success")) {
//                        ShowMessages(ActivityQuiz.this, "Berhasil Insert");
                        Log.d(TAG, "onResponse: Berhasil Insert");
                    } else {
                        Log.d(TAG, "onResponse: Gagal Insert");
//                        ShowMessages(ActivityQuiz.this, "Gagal Insert");
                    }
                } else {
                    hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ResponseEfit> call, Throwable t) {
                hideProgress();
            }
        });
    }

    private void UpdateJawaban(String id_quiz, String jawaban) {
        showProgress();
        Call<ResponseEfit> postJawabanQuesioner = restClient.getTentorApi().updateJawabanQuesioner(Sharepref.getString(Constant.USERID), id_quiz, jawaban);
        postJawabanQuesioner.enqueue(new Callback<ResponseEfit>() {
            @Override
            public void onResponse(Call<ResponseEfit> call, Response<ResponseEfit> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().getStatus().equals("success")) {
//                        ShowMessages(ActivityQuiz.this, "Berhasil Update");
                        Log.d(TAG, "onResponse: Berhasil Update");
                    } else {
//                        ShowMessages(ActivityQuiz.this, "Gagal Update");
                        Log.d(TAG, "onResponse: Gagal Update");
                    }
                } else {
                    hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ResponseEfit> call, Throwable t) {
                hideProgress();
            }
        });
    }

    private int getrumusKalori() {
        int kalori = 0;
        String jenkel = Sharepref.getString(Constant.JENKEL);
        int bb = Integer.parseInt(getEditToString(etBb));
        int tb = Integer.parseInt(getEditToString(etTb));
        int umur = Integer.parseInt(getEditToString(etUmur));
        if (jenkel.equals("L")) {
            //berat badan + tinggi badan - umur + 5
            kalori = (bb + tb) - umur + 5;
        } else {
            kalori = (bb + tb) - umur + 161;
        }
        return kalori;
    }
}
