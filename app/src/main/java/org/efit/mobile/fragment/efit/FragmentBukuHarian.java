package org.efit.mobile.fragment.efit;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.R;
import org.efit.mobile.activity.ActivityCariMenu;
import org.efit.mobile.activity.DetailMenuSarapan;
import org.efit.mobile.adapter.harian.ListAdapterHarian;
import org.efit.mobile.adapter.harian.ListAdapterHarianAir;
import org.efit.mobile.adapter.harian.ListAdapterHarianOlahraga;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.detailactivity.DetailActivityMinum;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.model.ModelListAir;
import org.efit.mobile.model.dataharian.ModelDetailAsupan;
import org.efit.mobile.model.dataharian.ModelDetailOlahraga;
import org.efit.mobile.network.CheckConnection;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentBukuHarian extends BaseFragment implements ListAdapterHarian.actionMakanan, ListAdapterHarianAir.actionMinum {
    private static final String TAG = "FragmentListJob";
    //API
    protected RestClient restClient = RestClient.getInstance();
    @BindView(R.id.ll_action_sarapan)
    LinearLayout llActionSarapan;
    @BindView(R.id.rv_sarapan)
    RecyclerView rvSarapan;
    @BindView(R.id.ll_action_msiang)
    LinearLayout llActionMsiang;
    @BindView(R.id.rv_mknsiang)
    RecyclerView rvMknsiang;
    @BindView(R.id.ll_action_mmlam)
    LinearLayout llActionMmlam;
    @BindView(R.id.rv_mknmalam)
    RecyclerView rvMknmalam;
    @BindView(R.id.imv_back_qz)
    ImageView imvBackQz;
    @BindView(R.id.tv_changetanggal)
    TextView tvChangetanggal;
    @BindView(R.id.imv_next_qz)
    ImageView imvNextQz;
    @BindView(R.id.rv_olahraga)
    RecyclerView rvOlahraga;
    @BindView(R.id.rv_airminum)
    RecyclerView rvAirminum;

    @BindView(R.id.et_in_total_sasaran)
    EditText etInTotalSasaran;
    @BindView(R.id.et_in_totalMakanan)
    EditText etInTotalMakanan;
    @BindView(R.id.et_in_total_olahraga)
    EditText etInTotalOlahraga;
    @BindView(R.id.et_in_total_tersisa)
    EditText etInTotalTersisa;
    @BindView(R.id.tv_total_sarapan)
    TextView tvTotalSarapan;
    @BindView(R.id.tv_total_msiang)
    TextView tvTotalMsiang;
    @BindView(R.id.imv_add_airmnum)
    ImageView imvAddAirmnum;
    @BindView(R.id.imv_add_olhrga)
    ImageView imvAddOlhrga;
    @BindView(R.id.ll_action_mnum)
    LinearLayout llActionMnum;
    @BindView(R.id.ll_action_mknrinan)
    LinearLayout llActionMknrinan;
    @BindView(R.id.rv_mknringan)
    RecyclerView rvMknringan;
    private Context mContext;

    private List<ModelDetailAsupan> listModelSarapan;
    private List<ModelDetailAsupan> listModelMakanSiang;
    private List<ModelDetailAsupan> listModelMakanMalam;
    private List<ModelDetailAsupan> listModelMakanRingan;
    private List<ModelListAir> listModelAir;
    private List<ModelDetailOlahraga> listModelSports;

    //action
    private ListAdapterHarian.actionMakanan actionedit;
    private ListAdapterHarian listAdapterSarapan;
    //air
    private ListAdapterHarianAir.actionMinum actioneditAir;
    private ListAdapterHarianAir listAdapterAir;
    //olahraga
    private ListAdapterHarianOlahraga listAdapterSports;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager layoutManager;
    private DatabaseHandler dbHelper = null;

    Calendar myCalendar;
    private String tanggal_set = "";
    private double tot_sasaran = 0, tot_makanan = 0.0, tot_olahraga, tot_or = 0.0, tot_hasil = 0.0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.fragment_bukuharian, container, false);
        ButterKnife.bind(this, rooView);
        mContext = getActivity();
        actionedit = this;
        actioneditAir = this;
        tot_sasaran = Sharepref.getString(Constant.T_KALORI) != null ? Integer.parseInt(Sharepref.getString(Constant.T_KALORI)) : 0;
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(mContext, resId);
        layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvSarapan.setLayoutManager(new LinearLayoutManager(mContext));
        rvSarapan.setLayoutAnimation(animation);

        rvMknsiang.setLayoutManager(new LinearLayoutManager(mContext));
        rvMknsiang.setLayoutAnimation(animation);

        rvMknmalam.setLayoutManager(new LinearLayoutManager(mContext));
        rvMknmalam.setLayoutAnimation(animation);

        rvMknringan.setLayoutManager(new LinearLayoutManager(mContext));
        rvMknringan.setLayoutAnimation(animation);

        rvAirminum.setLayoutManager(new LinearLayoutManager(mContext));
        rvAirminum.setLayoutAnimation(animation);

        rvOlahraga.setLayoutManager(new LinearLayoutManager(mContext));
        rvOlahraga.setLayoutAnimation(animation);

        loadData();
        llActionSarapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity(1);
            }
        });
        llActionMsiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity(2);
            }
        });
        llActionMmlam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity(3);
            }
        });
        llActionMnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity(4);
            }
        });
        llActionMknrinan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity(6);
            }
        });

        tanggal_set = getTanggalNow();

        tvChangetanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance(TimeZone.getDefault()); // Get current date
                DatePickerDialog datePicker = new DatePickerDialog(mContext,
                        R.style.AppTheme, datePickerListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                datePicker.setCancelable(false);
                datePicker.setTitle("Select the date");
                datePicker.show();
            }

        });


        imvBackQz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(dateFormat.parse(tanggal_set));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, -1);
                String currentDate = dateFormat.format(c.getTime());
                tanggal_set = currentDate;
                if (getTanggalNow().equals(currentDate)) {
                    tvChangetanggal.setText("Hari ini");
                } else {
                    tvChangetanggal.setText(currentDate);
                }
                loadData();
            }
        });

        imvNextQz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(dateFormat.parse(tanggal_set));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, 1);
                String currentDate = dateFormat.format(c.getTime());
                tanggal_set = currentDate;
                if (getTanggalNow().equals(currentDate)) {
                    tvChangetanggal.setText("Hari ini");
                } else {
                    tvChangetanggal.setText(currentDate);
                }
                loadData();
            }
        });

        return rooView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
        loadData();
        tot_sasaran = Sharepref.getString(Constant.T_KALORI) != null ? Integer.parseInt(Sharepref.getString(Constant.T_KALORI)) : 0;
    }

    private void OpenActivity(int tipe) {
        /**
         * 1.sarapan
         * 2.siang
         * 3.malam
         * 5.olahraga
         * 4.Minum
         * 6.makan ringan
         */
        if (tipe == 4) {
            Intent minum = new Intent(mContext, DetailActivityMinum.class);
            minum.putExtra("TIPE", tipe);
            minum.putExtra("TGL_IN", tanggal_set);
            mContext.startActivity(minum);
        } else {
            Intent a = new Intent(mContext, ActivityCariMenu.class);
            a.putExtra("TIPE", tipe);
            a.putExtra("TGL_IN", tanggal_set);
            mContext.startActivity(a);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void LoadDetail() {
        if (CheckConnection.isNetworkAvailable()) {
            Log.d(TAG, "LoadDetail: ada koneksi");
            loadData();
        } else {
            Log.e(TAG, "LoadDetail: no koneksi");
        }
    }

    // Listener
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            String year1 = String.valueOf(selectedYear);
            String month1 = String.valueOf(selectedMonth + 1);
            String day1 = String.valueOf(selectedDay);
            if (selectedDay < 10) {
                day1 = "0" + day1;
            }

            if ((selectedMonth + 1) < 10) {
                month1 = "0" + month1;
            }

            tanggal_set = year1 + "-" + month1 + "-" + day1;
            Log.d(TAG, "onDateSet: " + tanggal_set);
            tvChangetanggal.setText(tanggal_set);
            if (getTanggalNow().equals(tanggal_set)) {
                tvChangetanggal.setText("Hari ini");
            } else {
                tvChangetanggal.setText(tanggal_set);
            }
            loadData();
        }
    };

    private void loadData() {
        dbHelper = new DatabaseHandler(mContext, mContext.getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        new AsyncTask<Object, Integer, List<ModelDetailAsupan>>() {
            @Override
            protected List<ModelDetailAsupan> doInBackground(Object... objects) {
                listModelSarapan = dbHelper.showListHarian(tanggal_set, "makan_sarapan");
                return listModelSarapan;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelDetailAsupan> list) {
                listAdapterSarapan = new ListAdapterHarian(mContext, list, actionedit);
                rvSarapan.setAdapter(listAdapterSarapan);
                listAdapterSarapan.notifyDataSetChanged();
                rvSarapan.getAdapter().notifyDataSetChanged();
                rvSarapan.scheduleLayoutAnimation();
//                rvSarapan.setLayoutManager(layoutManager);
                //lv.setOnItemClickListener can be put here
            }
        }.execute();
        //makan siang
        new AsyncTask<Object, Integer, List<ModelDetailAsupan>>() {
            @Override
            protected List<ModelDetailAsupan> doInBackground(Object... objects) {
                listModelMakanSiang = dbHelper.showListHarian(tanggal_set, "makan_siang");
                return listModelMakanSiang;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelDetailAsupan> list) {
                listAdapterSarapan = new ListAdapterHarian(mContext, list, actionedit);
                rvMknsiang.setAdapter(listAdapterSarapan);
                listAdapterSarapan.notifyDataSetChanged();
                rvMknsiang.getAdapter().notifyDataSetChanged();
                rvMknsiang.scheduleLayoutAnimation();
//                rvMknsiang.setLayoutManager(layoutManager);
                //lv.setOnItemClickListener can be put here
            }
        }.execute();

        //makan malam
        new AsyncTask<Object, Integer, List<ModelDetailAsupan>>() {
            @Override
            protected List<ModelDetailAsupan> doInBackground(Object... objects) {
                listModelMakanMalam = dbHelper.showListHarian(tanggal_set, "makan_malam");
                return listModelMakanMalam;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelDetailAsupan> list) {
                listAdapterSarapan = new ListAdapterHarian(mContext, list, actionedit);
                rvMknmalam.setAdapter(listAdapterSarapan);
                listAdapterSarapan.notifyDataSetChanged();
                rvMknmalam.getAdapter().notifyDataSetChanged();
                rvMknmalam.scheduleLayoutAnimation();
//                rvMknmalam.setLayoutManager(layoutManager);
                //lv.setOnItemClickListener can be put here
            }
        }.execute();

        //mknan ringan
        new AsyncTask<Object, Integer, List<ModelDetailAsupan>>() {
            @Override
            protected List<ModelDetailAsupan> doInBackground(Object... objects) {
                listModelMakanRingan = dbHelper.showListHarian(tanggal_set, "makanan_ringan");
                return listModelMakanRingan;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelDetailAsupan> list) {
                listAdapterSarapan = new ListAdapterHarian(mContext, list, actionedit);
                rvMknringan.setAdapter(listAdapterSarapan);
                listAdapterSarapan.notifyDataSetChanged();
                rvMknringan.getAdapter().notifyDataSetChanged();
                rvMknringan.scheduleLayoutAnimation();
//                rvMknmalam.setLayoutManager(layoutManager);
                //lv.setOnItemClickListener can be put here
            }
        }.execute();

        //air
        new AsyncTask<Object, Integer, List<ModelListAir>>() {
            @Override
            protected List<ModelListAir> doInBackground(Object... objects) {
                listModelAir = dbHelper.showListAirHarian(tanggal_set);
                return listModelAir;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelListAir> list) {
                listAdapterAir = new ListAdapterHarianAir(mContext, list, actioneditAir);
                rvAirminum.setAdapter(listAdapterAir);
                listAdapterAir.notifyDataSetChanged();
                rvAirminum.getAdapter().notifyDataSetChanged();
                rvAirminum.scheduleLayoutAnimation();
            }
        }.execute();


        //olahraga
        new AsyncTask<Object, Integer, List<ModelDetailOlahraga>>() {
            @Override
            protected List<ModelDetailOlahraga> doInBackground(Object... objects) {
                listModelSports = dbHelper.showListOlahraga(tanggal_set);
                return listModelSports;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
            }

            @Override
            protected void onPostExecute(List<ModelDetailOlahraga> list) {
                listAdapterSports = new ListAdapterHarianOlahraga(mContext, list);
                rvOlahraga.setAdapter(listAdapterSports);
                listAdapterSports.notifyDataSetChanged();
                rvOlahraga.getAdapter().notifyDataSetChanged();
                rvOlahraga.scheduleLayoutAnimation();
            }
        }.execute();

        tot_makanan = dbHelper.getTotalEnergi(tanggal_set);
        tot_olahraga = dbHelper.getTotalOlahraga(tanggal_set);
        etInTotalMakanan.setText(Double.toString(tot_makanan));
        etInTotalSasaran.setText(Double.toString(tot_sasaran));
        etInTotalOlahraga.setText(Double.toString(tot_olahraga));
        double total_hasil = tot_sasaran - (tot_makanan + tot_olahraga);
        etInTotalTersisa.setText(Double.toString(total_hasil));
        etInTotalTersisa.setEnabled(false);
        etInTotalMakanan.setEnabled(false);
        etInTotalSasaran.setEnabled(false);
        etInTotalOlahraga.setEnabled(false);
    }

    @Override
    public void EditMakanan(int id) {
        long delete = dbHelper.DeleteMakanan(id);
        if (delete != -1) {
            Toast.makeText(mContext, "Berhasil Menghapus..", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Gagal Menghapus..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void EditAir(int id) {}
}
