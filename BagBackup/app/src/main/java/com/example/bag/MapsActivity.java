package com.example.bag;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {
    private LocationManager lm;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GPSInfo gps;
    private double latitude;
    private double longitude;
    private LocationListener locationListener;
    private int cnt;
    private String GPSprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        MapsInitializer.initialize(getApplicationContext());

        init();
    }

    private void init() {
        cnt = 0;
        GooglePlayServicesUtil.isGooglePlayServicesAvailable(MapsActivity.this);
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        // GPS ������� ��������
        gps = new GPSInfo(MapsActivity.this);
        locationListener = new MyLocationListener();

        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            // Showing the current location in Google Map
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Map �� zoom �մϴ�.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            mMap.addMarker(new MarkerOptions().position(latLng).title("My"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                init();
                //setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Toast.makeText(getBaseContext(), "Location changed : Lat: " + location.getLatitude() + " Lng: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                MarkerOptions optFirst = new MarkerOptions();
                optFirst.position(latLng);// 위도 • 경도
                optFirst.title("Current Position");// 제목 미리보기
                optFirst.snippet("Snippet" + ++cnt);
                optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.circle));
                mMap.addMarker(optFirst).showInfoWindow();

                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);       // 정확도
                criteria.setPowerRequirement(Criteria.POWER_LOW);  // 전원 소비량
                criteria.setAltitudeRequired(false);              // 고도, 높이 값을 얻어 올지를 결정
                criteria.setBearingRequired(false);
                criteria.setSpeedRequired(false);                 // 속도
                criteria.setCostAllowed(true);                    // 위치 정보를 얻어 오는데 들어가는 금전적 비용
                GPSprovider = lm.getBestProvider(criteria, true);
                lm.requestLocationUpdates(GPSprovider, 2000, 10, this);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
            lm.removeUpdates(this);
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
    }
}