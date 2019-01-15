package com.example.guest.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.TravelMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int overview = 0;
    MapView mapView;
    GoogleMap googleMap;
    private LatLng startPlace;
    private LatLng destinationPlace;
    private ListView routesListView;
    private RoutesAdapter routesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        routesListView = findViewById(R.id.routesList);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            startPlace = extras.getParcelable("startPlace");
            destinationPlace = extras.getParcelable("destinationPlace");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
        ArrayList<Route> routes = getRoutes();
        routesAdapter = new RoutesAdapter(MapActivity.this, routes);
        routesListView.setAdapter(routesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions()
                .position(startPlace)
                .title("Adresa pornire"));
        googleMap.addMarker(new MarkerOptions()
                .position(destinationPlace)
                .title("Adresa destinatie"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(startPlace));

//        DirectionsResult results = getDirectionsDetails(startPlace.latitude + "," + startPlace.longitude,
//                destinationPlace.latitude + "," + destinationPlace.longitude,TravelMode.DRIVING);
//        if (results != null) {
//            positionCamera(results.routes[overview], googleMap);
//        }
    }

    private DirectionsResult getDirectionsDetails(String origin, String destination, TravelMode mode) {
        DirectionsApiRequest req = DirectionsApi.getDirections(getGeoContext(), origin, destination);
        try {
            DirectionsResult res = req.await();
            return res;
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void positionCamera(DirectionsRoute route, GoogleMap mMap) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(route.legs[overview].startLocation.lat, route.legs[overview].startLocation.lng), 12));
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext
                .setQueryRateLimit(3)
                .setApiKey("AIzaSyBrPt88vvoPDDn_imh-RzCXl5Ha2F2LYig");
    }

    ArrayList<Route> getRoutes() {
        ArrayList<Route> routes = new ArrayList<>();
        routes.add(new Route("Cel mai rapid", "Cu masina", "14 minute", "5 lei", "3,3 km"));
        routes.add(new Route("Cel mai ieftin", "Bus", "26 minute", "2.5 lei", "3.7"));
        routes.add(new Route("Cel mai rapid", "Taxify", "14 minute", "15 lei", "3,3 km"));
        routes.add(new Route("Bagaj", "Uber", "15 minute", "12 lei", "3,3"));
        return routes;
    }
}
