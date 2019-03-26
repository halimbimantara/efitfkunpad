package org.efit.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthDataService;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import org.efit.mobile.activity.ActivityProfile;
import org.efit.mobile.activity.DetailMenuSarapan;
import org.efit.mobile.activity.navigator.ActivityNavOlahraga;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.fragment.efit.FragmentBantuan;
import org.efit.mobile.fragment.efit.FragmentBukuHarian;
import org.efit.mobile.fragment.efit.FragmentGrafik;
import org.efit.mobile.fragment.efit.FragmentKemajuan;
import org.efit.mobile.helper.DatabaseHandler;
import org.efit.mobile.helper.samsung.StepCountReader;
import org.efit.mobile.helper.samsung.StepCountReporter;
import org.efit.mobile.model.ResponseEfitBasic;
import org.efit.mobile.model.dataharian.ModelDetailOlahraga;
import org.efit.mobile.model.dataharian.ModelInAsupanharian;
import org.efit.mobile.model.dataharian.ModelMasterAsupan;
import org.efit.mobile.model.dataharian.ModelMasterOlahraga;
import org.efit.mobile.network.CheckConnection;
import org.efit.mobile.restapi.RestClient;
import org.efit.mobile.utility.Constant;
import org.efit.mobile.utility.Sharepref;
import org.efit.mobile.welcome.LoginActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainMenuActivity extends BaseActivity implements LocationListener {
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile, imvProfile;
    private TextView txtName, txtWebsite, txtNamaUser;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    public static final String TAG = "StepDiary";
    private static final String TAG_HOME = "MainMenuActivity";
    private static final String TAG_ORDER = "order";
    private static final String TAG_WALLET = "wallet";
    private static final String TAG_FAQ = "FAQ";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_SUPPORT = "support";
    private static final String TAG_ABOUT = "about";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    BottomNavigationViewEx navigation;
    CheckConnection checkConnection;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    /**
     * @param ;atlong
     */
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static int UPDATE_INTERVAL = 20000; // 20 sec
    private static int FATEST_INTERVAL = 10000; // 10 sec
    private static int DISPLACEMENT = 10; // 10 meters
    //Our Map
//    private GoogleMap mMap;

    //To store longitude and latitude from map
    private double longitude;
    private double latitude;
    //maps
    private double Lats = 0.00;
    private double Longs = 0.00;

    public double getLats() {
        return Lats;
    }

    public void setLats(double lats) {
        Lats = lats;
    }

    public double getLongs() {
        return Longs;
    }

    public void setLongs(double longs) {
        Longs = longs;
    }

    private Context context;
    private Activity activity;

    //Google ApiClient
    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;

    //API
    protected RestClient restClient = RestClient.getInstance();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private HealthDataStore mStore;
    private StepCountReader mReporter;

    //
    private long mCurrentStartTime;
    private DatabaseHandler dbHelper = null;
    private ActivityNavOlahraga navOlahraga;

    private int onInternet = 0;
    private String tanggal_inputan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        tanggal_inputan = getTanggalNow();
        navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        txtNamaUser = (TextView) findViewById(R.id.tv_userid);
        txtNamaUser = (TextView) toolbar.findViewById(R.id.tlbr_title);

//        txtNamaUser.setText(Sharepref.getString(Constant.NAMA_LENGKAP));
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.logo_privateq_);
        context = getApplicationContext();
        activity = this;
        toolbar.setNavigationIcon(drawable);

        checkConnection = new CheckConnection();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mHandler = new Handler();
//        setActiveFragment(new FragmentOnProcess());
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        fab = (FloatingActionButton) findViewById(R.id.fab);
        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);

        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
        imvProfile = (ImageView) navHeader.findViewById(R.id.btn_profile_setting);
        txtName.setText(Sharepref.getString(Constant.NAMA_LENGKAP));
        txtWebsite.setText(Sharepref.getString(Constant.EMAIL));
        imvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                open(ActivityProfile.class);
            }
        });

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();
        // initializing navigation menu
        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        dbHelper = new DatabaseHandler(this, getFilesDir().getAbsolutePath());

        initSports();
    }

    private void initSports() {
        mCurrentStartTime = StepCountReader.TODAY_START_UTC_TIME;
        HealthDataService healthDataService = new HealthDataService();
        try {
            healthDataService.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Create a HealthDataStore instance and set its listener
        mStore = new HealthDataStore(this, mConnectionListener);
        // Request the connection to the health data store
        mStore.connectService();
        mReporter = new StepCountReader(mStore, mStepCountObserver);

    }

    private void initOlahraga() {
        navOlahraga = new ActivityNavOlahraga();
        navOlahraga.requestPermission();
    }

    public void BadgeNav() {
        int countNotif = Sharepref.getInt(Constant.NOTIF) < 0 ? 0 : Sharepref.getInt(Constant.NOTIF);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                //onprocess
                case R.id.navigation_antrian:
                    txtNamaUser.setText("Buku Harian");
                    Fragment fragment = new FragmentBukuHarian();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content, fragment).commit();
                    return true;

                //oncompleted
                case R.id.navigation_histori:
                    txtNamaUser.setText("Grafik");
                    Fragment fragment1 = new FragmentGrafik();
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.content, fragment1).commit();
                    return true;

                //notif
                case R.id.navigation_notif:
                    txtNamaUser.setText("Bantuan");
                    Fragment fragment2 = new FragmentBantuan();
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    fragmentManager2.beginTransaction().replace(R.id.content, fragment2).commit();
                    return true;
            }
            return false;
        }

    };


    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
                .setBadgeNumber(number)
                .setGravityOffset(25, 1, true)
                .bindTarget(navigation.getBottomNavigationItemView(position))
                .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                    @Override
                    public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                            Toast.makeText(MainMenuActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText(Sharepref.getString(Constant.NAMA_LENGKAP));

        // loading header background image
        Glide.with(this).load(R.drawable.bg_ss)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);
//        imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.bg_ss));

        // Loading profile image
//        Glide.with(this).load("http://ropeg.setjen.kemendagri.go.id/foto/" + Sharepref.getString(Constant.NIP) + "/" + Sharepref.getString(Constant.PROFILE_PIC))
//                .crossFade()
//                .thumbnail(0.5f)
//                .bitmapTransform(new CircleTransform(this))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(imgProfile);

        // showing dot next to notifications label
//        navigationView.getMenu().getItem(1).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
//        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = new FragmentBukuHarian();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.content, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
            case 1:
                // photos
            case 2:
                // movies fragment
//                MoviesFragment moviesFragment = new MoviesFragment();
//                return moviesFragment;
            case 3:
                // notifications fragment
//                NotificationsFragment notificationsFragment = new NotificationsFragment();
//                return notificationsFragment;

            case 4:
                // settings fragment
//                SettingsFragment settingsFragment = new SettingsFragment();
//                return settingsFragment;
            default:
                return new FragmentBukuHarian();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment fragment = null;
                int isfragment = 1;
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_profile:
//                        open(ActivityProfile.class);
                        txtNamaUser.setText("Buku Harian");
                        fragment = new FragmentBukuHarian();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_akun:
                        isfragment = 0;
                        open(ActivityProfile.class);
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_ijin:
                        txtNamaUser.setText("Grafik");
                        fragment = new FragmentGrafik();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_aboutus:
                        txtNamaUser.setText("Bantuan");
                        fragment = new FragmentBantuan();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_device:
                        //logout
                        isfragment = 0;
                        open(ActivityNavOlahraga.class);
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_logout:
                        //logout
                        isfragment = 0;
                        Logout();
                        drawer.closeDrawers();
                        break;
                    default:
                        navItemIndex = 0;
                }
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                if (isfragment > 0) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    fragmentTransaction.replace(R.id.content, fragment, CURRENT_TAG);
                    fragmentTransaction.commitAllowingStateLoss();
                }
                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    public void Logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informasi")
                .setMessage("Apakah anda yakin untuk keluar ??")
                .setPositiveButton("Ya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                LogoutSession();
                            }
                        })
                .setNegativeButton("Tutup",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();


    }

    public void LogoutSession() {
        Sharepref.deleteString(Constant.USERID);
        Sharepref.deleteString(Constant.SESSION);
        finish();
        Intent log = new Intent(MainMenuActivity.this, LoginActivity.class);
        log.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        log.putExtra("email", Sharepref.getString(Constant.NIP));
        startActivity(log);
    }

    int k = 0;

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
        if (k == 1) {
            Toast.makeText(activity, "Tekan back sekali lagi untuk Logout ..!", Toast.LENGTH_SHORT).show();
        } else {
            Logout();
        }
//        super.onBackPressed();
    }

    // show or hide the fab
    private void toggleFab() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (CheckConnection.isNetworkAvailable()) {
        } else {
            Toast.makeText(this, "Harap Cek Koneksi Anda !!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Permission Granted, Now you can access location data", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Permission Denied, You cannot access location data.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        setLats(location.getLatitude());
        setLongs(location.getLongitude());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAPTURE_IMAGE && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                Log.i(TAG, "onActivityResultMenu: cek jangkauan");
            } else {
                Log.e(TAG, "Error Menu..parsing");
            }
        }
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

    private void showPermissionAlarmDialog() {
        if (isFinishing()) {
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(MainMenuActivity.this);
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

    private void showConnectionFailureDialog(final HealthConnectionErrorResult error) {
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
                error.resolve(MainMenuActivity.this);
            }
        });

        if (error.hasResolution()) {
            alert.setNegativeButton(R.string.cancel, null);
        }

        alert.show();
    }

    private boolean isPermissionAcquired() {
        HealthPermissionManager.PermissionKey permKey = new HealthPermissionManager.PermissionKey(HealthConstants.StepCount.CALORIE, HealthPermissionManager.PermissionType.READ);
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Check whether the permissions that this application needs are acquired
            Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(Collections.singleton(permKey));
            return resultMap.get(permKey);
        } catch (Exception e) {
            Log.e(TAG, "Permission request fails.", e);
        }
        return false;
    }

    public void requestPermission() {
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        try {
            // Show user permission UI for allowing user to change options
            pmsManager.requestPermissions(navOlahraga.generatePermissionKeySet(), MainMenuActivity.this)
                    .setResultListener(mPermissionListener);
        } catch (Exception e) {
            Log.e(TAG, "Permission setting fails.", e);
        }
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
//            dbHelper.addDataOlahraga();
            saveOlahraga(cs);
        }

        @Override
        public void onChangedKalori(int count, String perangkat) {
            Log.d(TAG, "onChangedKalori: " + count);
        }

        @Override
        public void onBinningDataChanged(List<StepCountReader.StepBinningData> stepBinningDataList) {
//            updateBinningChartView(stepBinningDataList);
        }
    };

    private void saveOlahraga(String cs) {
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e("Quiz", e.getMessage());
        }
        int cek_master = dbHelper.getLastIDMasterAsupan(tanggal_inputan);
        //ada master

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss");
        String jam_now = dateformat.format(c.getTime());

        ModelMasterOlahraga m_or = new ModelMasterOlahraga();
        ModelDetailOlahraga d_or = new ModelDetailOlahraga();

        m_or.setTanggal(tanggal_inputan);
        m_or.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));
        m_or.setId_user(Sharepref.getString(Constant.USERID));
        m_or.setSumber("Samsung Health");

        d_or.setNama_latihan("Latihan");
        d_or.setJumlah_kalori(cs);
        d_or.setTanggal_input(tanggal_inputan + " " + jam_now);
        d_or.setId_user(Sharepref.getString(Constant.USERID));
        d_or.setKode_transaksi(tanggal_inputan.replaceAll("-", "") + "-" + Sharepref.getString(Constant.USERID));

        List<ModelDetailOlahraga> listd = new ArrayList<>();
        listd.add(d_or);
        m_or.setDetail(listd);

        List<ModelMasterOlahraga> aa = new ArrayList<>();
        aa.add(m_or);

        Call<ResponseEfitBasic> updateOlahraga = restClient.getTentorApi().updateOlahraga(aa);
        updateOlahraga.enqueue(new Callback<ResponseEfitBasic>() {
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
                ShowMessages(MainMenuActivity.this, t.getMessage());
            }
        });

        if (cek_master > 0) {
            long inDetail = dbHelper.addDataOlahraga(d_or);
            if (inDetail != -1) {
                Log.d(TAG, "saveOlahraga: berhasil");
            } else {
                ShowMessages(MainMenuActivity.this, "Gagal Menyimpan OR1..!");
            }
        } else {
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
                long inDetail = dbHelper.addDataOlahraga(d_or);
                if (inDetail != -1) {
                    Log.d(TAG, "saveOlahraga: berhasil");
                } else {
                    ShowMessages(MainMenuActivity.this, "Gagal Menyimpan OR1..!");
                }
            } else {
                ShowMessages(MainMenuActivity.this, "Gagal Menyimpan MAS1..!");
            }
        }

        Fragment fragment = new FragmentBukuHarian();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.content, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void updateStepCountView(final String count) {
        runOnUiThread(() -> Log.d(TAG, "updateStepCountView: " + count)

        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mReporter.requestDailyStepCount(mCurrentStartTime);
    }
}
