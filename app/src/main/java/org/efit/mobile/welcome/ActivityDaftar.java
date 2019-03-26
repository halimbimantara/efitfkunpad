package org.efit.mobile.welcome;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DialogHelper;
import org.efit.mobile.model.ResponseEfit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDaftar extends BaseActivity {

    @BindView(R.id.Et_Username)
    EditText EtEmail;
    @BindView(R.id.Et_Password)
    EditText EtPassword;
    @BindView(R.id.Et_Password2)
    EditText EtPassword2;
    @BindView(R.id.Et_Namalengkap)
    EditText EtNamalengkap;
    @BindView(R.id.rg_jenkel)
    RadioGroup rgJenkel;
    @BindView(R.id.tv_signin)
    TextView tvSignin;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_cpr)
    TextView tvCpr;
    private String jenkel = "";
    private DialogHelper dialogHelper;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        ButterKnife.bind(this);
        mContext = this;
        dialogHelper = new DialogHelper(this);
        rgJenkel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radiojButton = (RadioButton) findViewById(checkedId);
                jenkel = radiojButton.getText().toString();
                if (jenkel.equals("Laki - Laki")) {
                    jenkel = "L";
                } else {
                    jenkel = "P";
                }
            }
        });
    }

    @OnClick(R.id.tv_signin)
    public void onViewClicked() {
        open(LoginActivity.class);
    }

    @OnClick(R.id.btn_daftar)
    public void onViewdaftar() {

        String email = getEditToString(EtEmail);
        String pwd_1 = getEditToString(EtPassword);
        String pwd_2 = getEditToString(EtPassword2);
        String disname = getEditToString(EtNamalengkap);

        if (email.isEmpty()) {
            hideProgress();
            EtEmail.setError("Username Tidak Boleh Kosong");
        } else if (disname.isEmpty()) {
            hideProgress();
            EtNamalengkap.setError("Nama Belum Di isi");
        } else if (pwd_1.isEmpty()) {
            hideProgress();
            EtPassword.setError("Password Kosong");
        } else if (pwd_2.isEmpty()) {
            hideProgress();
            EtPassword2.setError("Password Kosong");
        } else if (!pwd_1.equals(pwd_2)) {
            hideProgress();
            EtPassword2.setError("Password Tidak Sama");
        } else {
            showProgress();
            Call<ResponseEfit> registrasi = restClient.getTentorApi().registrasi(email, disname, pwd_1, pwd_2, "in", "UTC+7", jenkel);
            registrasi.enqueue(new Callback<ResponseEfit>() {
                @Override
                public void onResponse(Call<ResponseEfit> call, Response<ResponseEfit> response) {
                    if (response.isSuccessful()) {
                        hideProgress();
                        if (response.body().getStatus().equals("success")) {
                            EtEmail.setText("");
                            EtPassword.setText("");
                            EtPassword2.setText("");
                            EtNamalengkap.setText("");
                            open(LoginActivity.class);
                        } else {
                            dialogHelper.ShowErrorDialogv2(mContext, "Informasi", response.body().getMessage());
                        }
                    } else {
                        hideProgress();
                        ShowMessages(ActivityDaftar.this, response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<ResponseEfit> call, Throwable t) {
                    hideProgress();
                    ShowMessages(ActivityDaftar.this, t.getMessage());
                }
            });
        }
    }
}
