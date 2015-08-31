package com.example.user.application.maps;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.user.application.R;
import com.example.user.application.beauty.Beauty;
import com.example.user.application.beauty.BeautyActivity;
import com.example.user.application.beauty.BeautyParser;
import com.example.user.application.food.Food;
import com.example.user.application.food.FoodActivity;
import com.example.user.application.food.FoodParser;
import com.example.user.application.health.Health;
import com.example.user.application.health.HealthActivity;
import com.example.user.application.health.HealthParser;
import com.example.user.application.lodge.Lodge;
import com.example.user.application.lodge.LodgeActivity;
import com.example.user.application.lodge.LodgeParser;
import com.example.user.application.performance.Performance;
import com.example.user.application.performance.PerformanceActivity;
import com.example.user.application.performance.PerformanceParser;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 11.
 */
public class MapActivity extends FragmentActivity {

    LocationManager locationManager;
    String provider;
    GPSInfo gps;
    Criteria criteria;
    ArrayList<Health> hos;
    ArrayList<Food> food;
    ArrayList<Beauty> beauty;
    ArrayList<Performance> per;
    ArrayList<Lodge> lodge;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //TextView statusText;
    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //mMap.clear();
            mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Marker"));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
//            String text = "";
//
//            switch (status) {
//                case LocationProvider.OUT_OF_SERVICE:
//                    text = "서비스 사용 불가";
//                    break;
//                case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                    text = "일시적 사용 불가";
//                    break;
//                case LocationProvider.AVAILABLE:
//                    text = "서비스 사용 가능";
//                    break;
//            }
//            statusText.setText(provider + " 상태 " + text);
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(mListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        locationManager.requestLocationUpdates(provider, 2000, 10, mListener);
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
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googlemap)).getMap();
            gps = new GPSInfo(this);
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            criteria.setAltitudeRequired(false);
            criteria.setCostAllowed(false);
            provider = locationManager.getBestProvider(criteria, true);
            if (gps.isGetLocation() == false) {
                gps.showSettingsAlert();
            }
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(gps.getLatitude(), gps.getLongitude())));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        mMap.addMarker(new MarkerOptions().position(new LatLng(gps.getLatitude(), gps.getLongitude())).title("MY"));

        food = FoodActivity.foodList;
        lodge = LodgeActivity.lodgesList;
        beauty = BeautyActivity.beautyList;
        hos = HealthActivity.hospitalList;
        per = PerformanceActivity.persList;

        //lat 위도 lon경도 37.5062883 127.0248064
//        double lat1 = 37.5046343;
//        double lon1 = 127.0250319;
////        double lat_Do1 = 37;
////        double lat_Min1 = 29;
////        double lat_Sec1 = 43.200000000011656;
////
////        double lon_Do1 = 127;
////        double lon_Min1 = 1;
////        double lon_Sec1 = 41.400000000023283;
//
//        double lat2 = 37.495332;
//        double lon2 = 127.0283364;
//
//        double lat_Do1 = Math.floor(lat1);
//        double lat_Min_temp1 = (lat1 - lat_Do1) * 60;
//        double lat_Min1 = Math.floor(lat_Min_temp1);
//        double lat_Sec1 = (lat_Min_temp1 - lat_Min1) * 60;
//
//        double lon_Do1 = Math.floor(lon1);
//        double lon_Min_temp1 = (lon1 - lon_Do1) * 60;
//        double lon_Min1 = Math.floor(lon_Min_temp1);
//        double lon_Sec1 = (lon_Min_temp1 - lon_Min1) * 60;
//
//        double lat_Do2 = Math.floor(lat2);
//        double lat_Min_temp2 = (lat2 - lat_Do2) * 60;
//        double lat_Min2 = Math.floor(lat_Min_temp2);
//        double lat_Sec2 = (lat_Min_temp2 - lat_Min2) * 60;
//
//        double lon_Do2 = Math.floor(lon2);
//        double lon_Min_temp2 = (lon2 - lon_Do2) * 60;
//        double lon_Min2 = Math.floor(lon_Min_temp2);
//        double lon_Sec2 = (lon_Min_temp2 - lon_Min2) * 60;
//
//        double lat_Do = lat_Do1 - lat_Do2;
//        double lat_Min = lat_Min1 - lat_Min2;
//        double lat_Sec = lat_Sec1 - lat_Sec2;
//
//        double lon_Do = lon_Do1 - lon_Do2;
//        double lon_Min = lon_Min1 - lon_Min2;
//        double lon_Sec = lon_Sec1 - lon_Sec2;
//
//        double Dis_lat = lat_Do * 111 + lat_Min * 1.85 + lat_Sec * 0.031;
//        double Dis_lon = lon_Do * 88.8 + lon_Min * 1.48 + lon_Sec * 0.025;
//
//        double Distance = Math.sqrt(Math.pow(Dis_lat, 2) + Math.pow(Dis_lon, 2));

//        double distance;
//
//        Location locationA = new Location("A");
//
//        locationA.setLatitude(gps.getLatitude());
//        locationA.setLongitude(gps.getLongitude());
//
//        Location locationB = new Location("B");
//
//        locationB.setLatitude(food.get(0).getyPos());
//        locationB.setLongitude(food.get(0).getxPos());
//
//        distance = locationA.distanceTo(locationB) / 1000;


        for (int i = 0; i < food.size(); i++) {
            double distance;

            Location locationA = new Location("A");

            locationA.setLatitude(gps.getLatitude());
            locationA.setLongitude(gps.getLongitude());

            Location locationB = new Location("B");

            locationB.setLatitude(food.get(i).getyPos());
            locationB.setLongitude(food.get(i).getxPos());

            distance = locationA.distanceTo(locationB) / 1000;

            if (distance < 10)
                mMap.addMarker(new MarkerOptions().position(new LatLng(food.get(i).getyPos(), food.get(i).getxPos())).title(food.get(i).getName()));
        }
        for (int i = 0; i < lodge.size(); i++) {
            double distance;

            Location locationA = new Location("A");

            locationA.setLatitude(gps.getLatitude());
            locationA.setLongitude(gps.getLongitude());

            Location locationB = new Location("B");

            locationB.setLatitude(lodge.get(i).getyPos());
            locationB.setLongitude(lodge.get(i).getxPos());

            distance = locationA.distanceTo(locationB) / 1000;
            if (distance < 10)
                mMap.addMarker(new MarkerOptions().position(new LatLng(lodge.get(i).getyPos(), lodge.get(i).getxPos())).title(lodge.get(i).getName()));
        }
        for (int i = 0; i < beauty.size(); i++) {
            double distance;

            Location locationA = new Location("A");

            locationA.setLatitude(gps.getLatitude());
            locationA.setLongitude(gps.getLongitude());

            Location locationB = new Location("B");

            locationB.setLatitude(beauty.get(i).getyPos());
            locationB.setLongitude(beauty.get(i).getxPos());

            distance = locationA.distanceTo(locationB) / 1000;
            if (distance < 10)
                mMap.addMarker(new MarkerOptions().position(new LatLng(beauty.get(i).getyPos(), beauty.get(i).getxPos())).title(beauty.get(i).getName()));
        }
        for (int i = 0; i < hos.size(); i++) {
            double distance;

            Location locationA = new Location("A");

            locationA.setLatitude(gps.getLatitude());
            locationA.setLongitude(gps.getLongitude());

            Location locationB = new Location("B");

            locationB.setLatitude(hos.get(i).getyPos());
            locationB.setLongitude(hos.get(i).getxPos());

            distance = locationA.distanceTo(locationB) / 1000;
            if (distance < 10)
                mMap.addMarker(new MarkerOptions().position(new LatLng(hos.get(i).getyPos(), hos.get(i).getxPos())).title(hos.get(i).getName()));
        }
        for (int i = 0; i < per.size(); i++) {
            double distance;

            Location locationA = new Location("A");

            locationA.setLatitude(gps.getLatitude());
            locationA.setLongitude(gps.getLongitude());

            Location locationB = new Location("B");

            locationB.setLatitude(per.get(i).getyPos());
            locationB.setLongitude(per.get(i).getxPos());

            distance = locationA.distanceTo(locationB) / 1000;
            if (distance < 10)
                mMap.addMarker(new MarkerOptions().position(new LatLng(per.get(i).getyPos(), per.get(i).getxPos())).title(per.get(i).getName()));
        }
        //mMap.addCircle(new CircleOptions().center(new LatLng(gps.getLatitude(), gps.getLongitude())).radius(100).strokeColor(Color.RED).fillColor(Color.BLUE));
    }
}