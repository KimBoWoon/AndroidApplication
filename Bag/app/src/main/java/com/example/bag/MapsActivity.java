package com.example.bag;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
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
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

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

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                init();
            }
        }
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.ACCURACY_FINE);       // 정확도
                criteria.setPowerRequirement(Criteria.POWER_LOW);  // 전원 소비량
                criteria.setAltitudeRequired(false);              // 고도, 높이 값을 얻어 올지를 결정
                criteria.setBearingRequired(false);
                criteria.setSpeedRequired(false);                 // 속도
                criteria.setCostAllowed(true);                    // 위치 정보를 얻어 오는데 들어가는 금전적 비용
                GPSprovider = lm.getBestProvider(criteria, true);

                latitude = location.getLatitude();
                longitude = location.getLongitude();
                LatLng latLng = new LatLng(latitude, longitude);
                MarkerOptions optFirst = new MarkerOptions();
                optFirst.position(latLng);// 위도 • 경도
                optFirst.title("Current Position");// 제목 미리보기
                optFirst.snippet("Snippet" + ++cnt);
                mMap.addMarker(optFirst).remove();
                mMap.addMarker(optFirst);
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