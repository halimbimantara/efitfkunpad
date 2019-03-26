package org.efit.mobile.detailactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;
import org.efit.mobile.model.ApiResponse;
import org.efit.mobile.restapi.ModelLayanan;
import org.efit.mobile.restapi.RestClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrder extends BaseActivity implements LocationListener, OnMapReadyCallback, GoogleMap.OnMapLongClickListener {
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    @BindView(R.id.spin_jasa)
    Spinner spinJasa;
    @BindView(R.id.spin_layanan)
    Spinner spinLayanan;
    @BindView(R.id.rl_maps)
    RelativeLayout rlMaps;
    @BindView(R.id.fab_mypos)
    FloatingActionButton fabMypos;
    @BindView(R.id.lv_delivery)
    LinearLayout lvDelivery;
    private SupportMapFragment map;
    //Our Map
    private GoogleMap mMap;
    //To store longitude and latitude from map
    private double longitude;
    private double latitude;
    private String id_jasa, harga, idlayanan;
    private String[] layanan = {"Booking", "Goshow"};

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    //Google ApiClient
    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;
    //API
    private static final String TAG = "ActivityDetail";
    //API
    protected RestClient restClient = RestClient.getInstance();
    private int status_edit = 0;
    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;
    FragmentManager fragmentManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        ButterKnife.bind(this);
        context = getApplicationContext();
        activity = this;
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps_add_praktek));
        fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction().hide(map).commit();
        ShowJasa();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(DetailOrder.this, android.R.layout.simple_spinner_item, layanan);
        spinLayanan.setAdapter(itemsAdapter);
        spinLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idlayanan = layanan[i];
                Log.d(TAG, "onItemSelected: " + idlayanan);
                if (i == 1) {
                    fragmentManager1.beginTransaction().show(map).commit();
                    fabMypos.setVisibility(View.VISIBLE);
                    Initialize();
                    InitMaps();
                } else {
                    fragmentManager1.beginTransaction().hide(map).commit();
                    fabMypos.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void getHargaGoShow() {
        lvDelivery.setVisibility(View.VISIBLE);
        showProgress();
    }
    private void CheckPermissionsMaps() {
        if (!checkPermission()) {
            requestPermission();
        } else {
            Toast.makeText(context, "Gps Already Granted", Toast.LENGTH_SHORT).show();
        }
    }
    private void Initialize() {
        CheckPermissionsMaps();
        map.getMapAsync(this);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(LocationServices.API)
                .build();
    }
    private void InitMaps() {
        startFusedLocation();
        createLocationRequest();
        registerRequestUpdate(this);
    }
    public boolean isGoogleApiClientConnected() {
        return googleApiClient != null && googleApiClient.isConnected();
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    //Getting current location
    private void getCurrentLocation() {
        mMap.clear();
        //Creating a location object
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            //moving the map to location
            moveMap();
        }
    }
    //Function to move the map
    private void moveMap() {
        //String to display current latitude and longitude
        String msg = latitude + ", " + longitude;
        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(getLatitude(), getLongitude());
        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(true) //Making the marker draggable
                .title("Posisi Saya")); //Adding a title
        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //Displaying current coordinates in toast
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        Log.d("Pindah :", Double.toString(getLatitude()) + "," + Double.toString(getLongitude()));
    }
    public void startFusedLocation() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnectionSuspended(int cause) {
                        }

                        @Override
                        public void onConnected(Bundle connectionHint) {
                            Toast.makeText(activity, "Connecting", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult result) {
                            Toast.makeText(activity, "Not Connected", Toast.LENGTH_SHORT).show();
                        }
                    }).build();
            googleApiClient.connect();
        } else {
            googleApiClient.connect();
        }
    }
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);

    }
    public void registerRequestUpdate(final LocationListener listener) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1200000); // every second
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, DetailOrder.this);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();

                    if (!isGoogleApiClientConnected()) {
                        googleApiClient.connect();
                    }
                    registerRequestUpdate(listener);
                }
            }
        }, 1000);
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(context, "GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
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
        setLatitude(location.getLatitude());
        setLongitude(location.getLongitude());
//        if (mMap.isMyLocationEnabled()) {
//            mMap.clear();
//        }
        moveMap();
    }

    @OnClick(R.id.fab_mypos)
    public void onViewClicked() {
        getCurrentLocation();
    }

    private void ShowJasa() {
        //getProvinsi
        Call<ApiResponse<List<ModelLayanan>>> apires = restClient.getTentorApi().getSpinnerLayanan();
        apires.enqueue(new Callback<ApiResponse<List<ModelLayanan>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<ModelLayanan>>> call, final Response<ApiResponse<List<ModelLayanan>>> response) {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < response.body().getResult().size(); i++) {
                    list.add(response.body().getResult().get(i).getNama_layanan());
                }

                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(DetailOrder.this, android.R.layout.simple_list_item_1, list);
                spinJasa.setAdapter(itemsAdapter);
                spinJasa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(ActivityAddLokPraktek.this,response.body().getData().getProvinsi().get(i).getKode(), Toast.LENGTH_SHORT).show();
                        id_jasa = response.body().getResult().get(i).getId_layanan();
                        harga = response.body().getResult().get(i).getHarga();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }

            @Override
            public void onFailure(Call<ApiResponse<List<ModelLayanan>>> call, Throwable throwable) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Initializing our map
        mMap = googleMap;
        //Creating a random coordinate
        LatLng latLng = new LatLng(getLatitude(), getLongitude());
        //Adding marker to that coordinate
        mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Setting onMarkerDragListener to track the marker drag
//        mMap.setOnMarkerDragListener(this);
        //Adding a long click listener to the map
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        //Clearing all the markers
        mMap.clear();
        //Adding a new marker to the current pressed position
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));
        setLatitude(latLng.latitude);
        setLongitude(latLng.longitude);
    }

    @Override
    protected void onStart() {
//        googleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
//        googleApiClient.disconnect();
        this.finish();
        super.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
//        googleApiClient.disconnect();
        super.onResume();
    }
}
