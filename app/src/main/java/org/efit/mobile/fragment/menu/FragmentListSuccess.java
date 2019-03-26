package org.efit.mobile.fragment.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.R;
import org.efit.mobile.core.BaseFragment;
import org.efit.mobile.model.kemendesa.ApiResponse;
import org.efit.mobile.model.kemendesa.CekAbsensiku;
import org.efit.mobile.model.kemendesa.absensi.CekLokasiKerja;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.restapi.RestClientEabsen;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.RumusRadius;
import org.efit.mobile.utility.Sharepref;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by TheMac on 10/8/17
 */

public class FragmentListSuccess extends BaseFragment {
    protected RestClient restClient = RestClient.getInstance();
    protected RestClientEabsen restClientAbsen = RestClientEabsen.getInstance();
    public static final String TAG = "ListcariPeg";

    private static final int REQUEST_CAPTURE_IMAGE = 100;
    View rootview;
    Context mcContext;
    Activity mActivity;
    LinearLayout lisswipeLay;
    @BindView(R.id.gambar_message)
    ImageView gambarMessage;
    @BindView(R.id.message_status)
    TextView messageStatus;
    @BindView(R.id.warning_lay)
    LinearLayout warningLay;
    @BindView(R.id.tv_jam)
    TextView tvJam;
    @BindView(R.id.fab_order)
    FloatingActionButton fabOrder;
    @BindView(R.id.absen_datang)
    TextView absenDatang;
    @BindView(R.id.absen_pulang)
    TextView absenPulang;
    @BindView(R.id.pg_inout)
    ProgressBar pgInout;
    @BindView(R.id.tv_infotap)
    TextView tvInfotap;
    @BindView(R.id.main_marquee)
    TextView mainMarquee;
    private String statusinout = "1", Tap = "";
    DateFormat tgl, jam;
    Date dateobj;
    // Animation
    Animation animBlink;
    public MainMenuActivity mainMenus;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mcContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_listonprocess, container, false);
        ButterKnife.bind(this, rootview);

        mcContext = getContext();
        mActivity = getActivity();
        mainMarquee.setSelected(true);
        animBlink = AnimationUtils.loadAnimation(mActivity, R.anim.blink);
        mainMarquee.setAnimation(animBlink);
        CheckAbsensi();
        mainMarquee.setVisibility(View.GONE);
        mainMenus = (MainMenuActivity) getActivity();

        //setjam
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                String dateString = sdf.format(date);
                                tvJam.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

//199005052014032006
        fabOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //capture image
                openCameraIntent();
//                checkjangkauan();
            }
        });

        return rootview;
    }

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
        );
        if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
//                mImageView.setImageBitmap(imageBitmap);
//                checkjangkauan();
                //
                Log.i(TAG, "onActivityResult: cek jangkauan");
                checkjangkauan();
//                postAbsensi();
            } else {
                Log.e(TAG, "Error ..parsing");
            }
        }
    }

    private void CekRadius() {
        RumusRadius a = new RumusRadius();
        double x = a.distance(6.173743, 106.821861, 6.177757, 106.805168, 'K');
        Log.i(TAG, "Radius cok: " + mainMenus.getLats());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void CheckAbsensi() {
        pgInout.setVisibility(View.VISIBLE);
        Call<CekAbsensiku> checkmyabsen = restClient.getTentorApi().checkmyabsen(Sharepref.getString(Constant.NIP));
        checkmyabsen.enqueue(new Callback<CekAbsensiku>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(Call<CekAbsensiku> call, Response<CekAbsensiku> response) {
                if (response.isSuccessful()) {
                    pgInout.setVisibility(View.GONE);
                    if (response.body().isStatus()) {
                        if (!response.body().getResult().get(0).getDatang().equals("00:00:00")) {
                            statusinout = "2";
                            fabOrder.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorRed)));
                            Log.i(TAG, "Fab abang cok: ");
                            Tap = "TAP OUT";
                        } else {
                            statusinout = "1";
                            Tap = "TAP IN";
                        }
                        tvInfotap.setText(Tap);
                        String datang = response.body().getResult().get(0).getDatang() == null ? "" : "Datang :" + response.body().getResult().get(0).getDatang();
                        absenDatang.setText(datang);
                        String pulang = response.body().getResult().get(0).getPulang() == null ? "-" : "Pulang :" + response.body().getResult().get(0).getPulang();
                        absenPulang.setText(pulang);
                    } else {
                        Log.e(TAG, "onResponse: " + response.body().getMessage());
                    }
                } else {
                    pgInout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CekAbsensiku> call, Throwable throwable) {
                pgInout.setVisibility(View.GONE);
            }
        });
    }

    public void postAbsensi() {
        tgl = new SimpleDateFormat("yyyy-MM-dd");
        jam = new SimpleDateFormat("HH:mm:ss");
        dateobj = new Date();
        String tgl_s = tgl.format(dateobj).toString();
        String jam_s = jam.format(dateobj).toString();
        showProgress();
        Call<ApiResponse> postAbsen = restClient.getTentorApi().postAbsen(Sharepref.getString(Constant.NIP), "900", tgl_s, jam_s, statusinout, Constant.KEY);
        postAbsen.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().isStatus()) {
                        CheckAbsensi();
                        if (statusinout.equals("2")) {
                            fabOrder.setVisibility(View.GONE);
                        }
                        Toast.makeText(mcContext, "Berhasil Absen", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mcContext, "Gagal Absen", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mcContext, "Failed ...", Toast.LENGTH_SHORT).show();
                    hideProgress();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                hideProgress();
                Log.e(TAG, "onFailure: " + throwable.getMessage());
            }
        });
    }

    private void checkjangkauan() {
        showProgress();
        final Call<CekLokasiKerja> postAbsen = restClient.getTentorApi().cekLokasiKerja(Sharepref.getString(Constant.LOK_KERJA));
        postAbsen.enqueue(new Callback<CekLokasiKerja>() {
            @Override
            public void onResponse(Call<CekLokasiKerja> call, Response<CekLokasiKerja> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().isStatus()) {
                        RumusRadius a = new RumusRadius();
                        //radius saya
                        System.out.println(mainMenus.getLats() + ":" + mainMenus.getLongs());
                        double x = a.distance(mainMenus.getLats(), mainMenus.getLongs(), Double.parseDouble(response.body().getResult().get(0).getLat()), Double.parseDouble(response.body().getResult().get(0).getLongi()), 'K');
                        Log.i(TAG, "Radius saya: " + x);
                        double y = Double.parseDouble(response.body().getResult().get(0).getRadius()) * 0.001;
                        Log.i(TAG, "Radius y: " + convert(y));
                        if (x > y) {
                            Toast.makeText(mcContext, "Diluar Jangkauan", Toast.LENGTH_SHORT).show();
                        } else {
                            postAbsensi();
//                            Toast.makeText(mcContext, "Dalam Jangkauan", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<CekLokasiKerja> call, Throwable throwable) {
                hideProgress();
                Toast.makeText(mcContext, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double convert(double x) {
        int angkaSignifikan = 2;
        double temp = Math.pow(10, angkaSignifikan);
        double y = (double) Math.round(x * temp) / temp;
        return y;
    }

    Float latHasil, longHasil;

    public void ceklatlon(String path) {
        String gpsLat = "";
        String gpsLong = "";
        String gpsLatRef = "";
        String gpsLongRef = "";

        File file;

        try {
            ExifInterface exifInterface = new ExifInterface(path);
            gpsLat = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            gpsLong = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            gpsLatRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            gpsLongRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (gpsLatRef.equals("N")) {
            latHasil = LocationConvertToDegree(gpsLat);
        } else {
            latHasil = 0 - LocationConvertToDegree(gpsLat);
        }

        if (gpsLongRef.equals("E")) {
            longHasil = LocationConvertToDegree(gpsLong);
        } else {
            longHasil = 0 - LocationConvertToDegree(gpsLong);
        }
    }

    /**
     * get latlon from image
     */
    public static Float LocationConvertToDegree(String stringDMS) {
        Float result = null;
        String[] DMS = stringDMS.split(",", 3);

        String[] stringD = DMS[0].split(",", 2);
        Double D0 = new Double(stringD[0]);
        Double D1 = new Double(stringD[1]);
        Double FloatD = D0 / D1;

        String[] stringM = DMS[1].split(",", 2);
        Double M0 = new Double(stringM[0]);
        Double M1 = new Double(stringM[1]);
        Double FloatM = M0 / M1;

        String[] stringS = DMS[2].split(",", 2);
        Double S0 = new Double(stringS[0]);
        Double S1 = new Double(stringS[1]);
        Double FloatS = S0 / S1;

        result = new Float(FloatD + (FloatM / 60) + (FloatS / 3600));

        return result;

    }

    ;
}
