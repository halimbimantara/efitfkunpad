package org.efit.mobile.welcome;

import android.os.Bundle;
import android.widget.EditText;

import org.efit.mobile.R;

import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.restapi.RestClient;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by ThinkPad T430 on 07/11/2017.
 */

public class ActivityLupaPwd extends BaseActivity {
    RestClient restClient = RestClient.getInstance();
    public static final String TAG = "ActivityLupaPwd";
    @BindView(R.id.Et_frgtPwd)
    EditText EtFrgtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpwd);
        ButterKnife.bind(this);
//        dialog = new DialogHelper();
    }

    /**
    private void ResetPassword(String email) {
        showProgress();
        Call<ApiResponse> postResetPassword = restClient.getTentorApi().postResetPassword(email);
        postResetPassword.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    hideProgress();
                    if (response.body().getSuccess()) {
                        dialog.ShowSuccessDialog(ActivityLupaPwd.this, response.body().getMessage(), response.message());
                    } else {
                        dialog.ShowErrorDialog(ActivityLupaPwd.this, response.body().getMessage(), response.message());
                    }
                } else {
                    hideProgress();
                    dialog.ShowErrorDialog(ActivityLupaPwd.this, "Gagal ..!", "Harap Coba Kembali !!");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                hideProgress();
                dialog.ShowErrorDialog(ActivityLupaPwd.this, "Gagal ..!", t.getMessage());

            }
        });
    }

    @OnClick(R.id.Btn_lpapwd)
    public void onViewClicked() {
        ResetPassword(EtFrgtPwd.getText().toString());
    }
    */
}
