package org.efit.mobile.welcome;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import org.efit.mobile.BuildConfig;
import org.efit.mobile.MainMenuActivity;
import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.Config;
import org.efit.mobile.helper.DialogHelper;
import org.efit.mobile.model.login.EfitLogin;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.NotificationUtils;
import org.efit.mobile.utility.Sharepref;
import org.efit.mobile.welcome.quizioner.ActivityQuiz;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TheMac on 10/5/17.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    //API
    protected RestClient restClient = RestClient.getInstance();
    @BindView(R.id.tv_version)
    TextView tvVersion;
    private String versionName = BuildConfig.VERSION_NAME;
    @BindView(R.id.Et_Username)
    EditText EtUsername;
    @BindView(R.id.Et_Password)
    EditText EtPassword;
    DialogHelper dialog;
    @BindView(R.id.btn_english)
    Button btnEnglish;
    @BindView(R.id.tvlang)
    TextView tvlang;
    @BindView(R.id.tv_lupapwd)
    TextView tvLupapwd;
    @BindView(R.id.pengumuman_marque)
    TextView pengumumanMarque;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private String bahasa = "in", imei = "", regId = "";
    private double an = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        requestReadPhone();
        dialog = new DialogHelper(this);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        imei = telephonyManager.getDeviceId().isEmpty() ? "testing device" : telephonyManager.getDeviceId();

        if (Sharepref.getString(Constant.BAHASA) != null) {
            if (Sharepref.getString(Constant.BAHASA).equals("en")) {
                Log.i(TAG, "onBahasa: Di en");
                //yang tampil icon en
                bahasa = "in";
                btnEnglish.setBackground(getBaseContext().getResources().getDrawable(R.drawable.eng_flag));
                tvlang.setText(" ENG");
            } else {
                //tersimpan en
                //yg tampil change to in
                Log.i(TAG, "onBahasa: Di ina");
                btnEnglish.setBackground(getBaseContext().getResources().getDrawable(R.drawable.ina_flag));
                tvlang.setText(" INA");
                bahasa = "en";
            }
        } else {
            Log.i(TAG, "onBahasa: tidak ada");
            Sharepref.saveString(Constant.BAHASA, bahasa);
        }
        Bundle logemail = getIntent().getExtras();
        if (logemail != null) {
            EtUsername.setText(logemail.getString("email"));
        } else {
            EtUsername.setText("");
        }

        tvLupapwd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                open(ActivitysetUrl.class);
                return false;
            }
        });
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

//                    txtMessage.setText(message);
//                    masuk ke notif fragment
                }
            }
        };
        //marque
        pengumumanMarque.setSelected(true);
        pengumumanMarque.setVisibility(View.GONE);
//        displayFirebaseRegId();
//        tvVersion.setText(versionName);
    }

    private void displayFirebaseRegId() {
        regId = Sharepref.getString(Constant.FIREBASEID) == null ? "" : Sharepref.getString("regId");
        Log.e(TAG, "Firebase reg id: " + regId);

//        ShowMessages(LoginActivity.this, regId);
    }

    @OnClick(R.id.btn_login)
    public void Login() {
        showProgress();
        String email = EtUsername.getText().toString();
        String pwd = EtPassword.getText().toString();

        if (email.trim().equals("") && email.length() < 5) {
            hideProgress();
            EtUsername.setError("Username Tidak Boleh Kosong");
        } else if (pwd.trim().equals("")) {
            hideProgress();
            EtPassword.setError("Password Belum Di isi");
        } else {
            cekUName(email, pwd);
        }

    }

    private void cekUName(final String email, String pwd) {
        final DialogHelper dialogs = new DialogHelper(LoginActivity.this);
        Call<EfitLogin> login = restClient.getTentorApi().loginefit(email, pwd);
        login.enqueue(new Callback<EfitLogin>() {
            @Override
            public void onResponse(Call<EfitLogin> call, Response<EfitLogin> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().getStatus().equals("success")) {
                        Sharepref.saveString(Constant.EMAIL, email);
                        Sharepref.saveString(Constant.NAMA_LENGKAP, response.body().getData_user().getDisplay_name());
                        Sharepref.saveString(Constant.USERID, response.body().getData_user().getId());
                        Sharepref.saveString(Constant.SESSION, response.body().getData_user().toString());
                        Sharepref.saveString(Constant.JENKEL, response.body().getData_user().getGender());
                        open(ActivityQuiz.class);
                    } else {
                        dialogs.ShowErrorDialogv2(LoginActivity.this, "Informasi Login", "Nip atau Password Salah..");
                    }
                } else {
                    hideProgress();
                    dialogs.ShowErrorDialogv2(LoginActivity.this, "Informasi Login", "Mohon Ulangi Lagi");
                }
            }

            @Override
            public void onFailure(Call<EfitLogin> call, Throwable throwable) {
                hideProgress();
                dialog.ShowErrorDialogv2(LoginActivity.this, "Informasi Login", "Harap periksa koneksi anda !!");
            }
        });


    }

    @OnClick(R.id.tv_lupapwd)
    public void onViewClicked() {
        String toNumber = "+00000"; // Replace with mobile phone number without +Sign or leading zeros.
        try {
            String text = "Saya Mengalami Kendala Login :";// Replace with your message.
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
//         register new push message receiver
//         by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
//         clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
        Log.d(TAG, "onResume: ");
    }

    @OnClick(R.id.btn_english)
    public void changeLang() {
        ChangeLanguage(bahasa);
        Back();
    }

    private void ChangeLanguage(String lang) {
        Log.i(TAG, "ChangeLanguage: " + lang);
        Sharepref.saveString(Constant.BAHASA, lang);
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        //noinspection deprecation
        config.locale = myLocale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
        if (lang.equals("en")) {
            bahasa = "in";
            Log.i(TAG, "Bahasa: in");
        } else {
            bahasa = "en";
            Log.i(TAG, "Bahasa: en");
        }
    }

    private void Back() {
        Intent a = new Intent(LoginActivity.this, LoginActivity.class);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(a);
    }

    private void ceklogineabsensi(String nip, String idlok, String imei) {

    }

    public void requestReadPhone() {
        String[] permissions = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA};
        String rationale = "Pengecekan ID DEVICE";
        Permissions.Options options = new Permissions.Options()
                .setRationaleDialogTitle("Info")
                .setSettingsDialogTitle("Warning");

        Permissions.check(this, permissions, rationale, options, new PermissionHandler() {
            @Override
            public void onGranted() {
//                ShowMessages(LoginActivity.this, "Access Granted ...");
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
//                ShowMessages(LoginActivity.this, "Not Granted...");
                finish();
            }
        });
    }

    @OnClick(R.id.tv_signup)
    public void daftar() {
        open(ActivityDaftar.class);
    }
}
