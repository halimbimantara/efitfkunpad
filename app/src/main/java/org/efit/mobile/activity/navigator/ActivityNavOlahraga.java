package org.efit.mobile.activity.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthDataService;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.helper.samsung.StepCountReader;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityNavOlahraga extends BaseActivity {
    @BindView(R.id.ll_setdevice)
    LinearLayout llSetdevice;
    private final String TAG = "nav_or";
    @BindView(R.id.tv_tes)
    TextView tvTes;
    private HealthDataStore mStore;
    private StepCountReader mReporter;
    private long mCurrentStartTime;
    private DatabaseHandler dbHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputolahraga);
        ButterKnife.bind(this);
        dbHelper = new DatabaseHandler(this, getFilesDir().getAbsolutePath());
        mCurrentStartTime = StepCountReader.TODAY_START_UTC_TIME;
        HealthDataService healthDataService = new HealthDataService();
        try {
            healthDataService.initialize(ActivityNavOlahraga.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Create a HealthDataStore instance and set its listener
        mStore = new HealthDataStore(ActivityNavOlahraga.this, mConnectionListener);
        // Request the connection to the health data store
        mStore.connectService();
        mReporter = new StepCountReader(mStore, mStepCountObserver);
        llSetdevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the start time of today in local
//                mDayTv.setText(getFormattedTime());
                requestPermission();
            }
        });
    }

    public final HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult> mPermissionListener =
            new HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult>() {
                @Override
                public void onResult(HealthPermissionManager.PermissionResult result) {
                    Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = result.getResultMap();
                    // Show a permission alarm and clear step count if permissions are not acquired
                    if (resultMap.values().contains(Boolean.FALSE)) {
//                        updateStepCountView("");
                        showPermissionAlarmDialog();
                    } else {
                        // Get the daily step count of a particular day and display it
                        mReporter.requestDailyStepCount(mCurrentStartTime);

                    }
                }
            };

    public void updateStepCountView(final String count) {
        // Display the today step count so far
        runOnUiThread(() -> tvTes.setText(count));
    }

    public void showPermissionAlarmDialog() {
        if (isFinishing()) {
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(ActivityNavOlahraga.this);
        alert.setTitle(R.string.notice)
                .setMessage(R.string.msg_perm_acquired)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    public final HealthDataStore.ConnectionListener mConnectionListener = new HealthDataStore.ConnectionListener() {
        @Override
        public void onConnected() {
            Log.d(TAG, "onConnected");
            if (isPermissionAcquired()) {
                mReporter.requestDailyStepCount(mCurrentStartTime);
            } else {
                requestPermission();
            }
        }

        @Override
        public void onConnectionFailed(HealthConnectionErrorResult error) {
            Log.d(TAG, "onConnectionFailed");
            showConnectionFailureDialog(error);
        }

        @Override
        public void onDisconnected() {
            Log.d(TAG, "onDisconnected");
            if (!isFinishing()) {
                mStore.connectService();
            }
        }
    };

    public boolean isPermissionAcquired() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Check whether the permissions that this application needs are acquired
            Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(generatePermissionKeySet());
            return !resultMap.values().contains(Boolean.FALSE);
        } catch (Exception e) {
            Log.e(TAG, "Permission request fails.", e);
        }
        return false;
    }

    public void requestPermission() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Show user permission UI for allowing user to change options
            pmsManager.requestPermissions(generatePermissionKeySet(), ActivityNavOlahraga.this)
                    .setResultListener(mPermissionListener);
        } catch (Exception e) {
            Log.e(TAG, "Permission setting fails.", e);
        }
    }

    public Set<HealthPermissionManager.PermissionKey> generatePermissionKeySet() {
        Set<HealthPermissionManager.PermissionKey> pmsKeySet = new HashSet<>();
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(StepCountReader.STEP_SUMMARY_DATA_TYPE_NAME, HealthPermissionManager.PermissionType.READ));
        pmsKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.Exercise.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        return pmsKeySet;
    }

    public void showConnectionFailureDialog(final HealthConnectionErrorResult error) {
        if (isFinishing()) {
            return;
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (error.hasResolution()) {
            switch (error.getErrorCode()) {
                case HealthConnectionErrorResult.PLATFORM_NOT_INSTALLED:
                    alert.setMessage(R.string.msg_req_install);
                    break;
                case HealthConnectionErrorResult.OLD_VERSION_PLATFORM:
                    alert.setMessage(R.string.msg_req_upgrade);
                    break;
                case HealthConnectionErrorResult.PLATFORM_DISABLED:
                    alert.setMessage(R.string.msg_req_enable);
                    break;
                case HealthConnectionErrorResult.USER_AGREEMENT_NEEDED:
                    alert.setMessage(R.string.msg_req_agree);
                    break;
                default:
                    alert.setMessage(R.string.msg_req_available);
                    break;
            }
        } else {
            alert.setMessage(R.string.msg_conn_not_available);
        }

        alert.setPositiveButton(R.string.ok, (dialog, id) -> {
            if (error.hasResolution()) {
                error.resolve(ActivityNavOlahraga.this);
            }
        });

        if (error.hasResolution()) {
            alert.setNegativeButton(R.string.cancel, null);
        }

        alert.show();
    }

    public final StepCountReader.StepCountObserver mStepCountObserver = new StepCountReader.StepCountObserver() {
        @Override
        public void onChanged(int count) {
//            updateStepCountView(String.valueOf(count));
            Log.d(TAG, "Walk: " + count);
        }

        @Override
        public void onChangedExercise(int count, String devicename) {
            Log.d(TAG, "onChangedExercise: " + devicename);
            String cs = Integer.toString(count);
//            updateStepCountView(cs);

        }

        @Override
        public void onChangedKalori(int count, String perangkat) {

        }

        @Override
        public void onBinningDataChanged(List<StepCountReader.StepBinningData> stepBinningDataList) {
//            updateBinningChartView(stepBinningDataList);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bookmark_menu:
//                saveDaily();
                return true;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    @Override
    public void onResume() {
        super.onResume();
        mReporter.requestDailyStepCount(mCurrentStartTime);
    }
}
