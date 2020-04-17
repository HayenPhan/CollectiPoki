package collectipoki.com;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import com.google.android.gms.location.LocationListener;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

// FragmentActivity is basically the Google Maps activity

public class LocationActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
        {

    private GoogleMap mMap;
    private GoogleApiClient ApiClient;

    // Pokemon locations
    private static final LatLng BALBASAUR = new LatLng(51.543200, 4.476770);
    private static final LatLng CHARMANDER = new LatLng(51.550529, 4.478660);


    // Last location
    private Location lastLocation;

    // Use this to detect moving object, person etc.
    private LocationRequest locationRequest;

    private Marker currentMarker;

    // Set marker pokemons
    private Marker mBalbasaur;
    private Marker mCharmander;

    private static final int User_Location_Code = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkLocationPermission();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add pokemon markers to map, and add data object to each marker

        // Balbasaur
        mBalbasaur = mMap.addMarker(new MarkerOptions()
                .position(BALBASAUR)
                .title("Balbasaur")
                    .snippet("Find Balbasaur at this location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mBalbasaur.setTag(0);

        // Charmander
        mCharmander = mMap.addMarker(new MarkerOptions()
                .position(CHARMANDER)
                .title("Charmander")
                    .snippet("Find Charmander at this location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mCharmander.setTag(0);


        // Build Api Client
        buildApiClient();

        // Enable user location
        mMap.setMyLocationEnabled(true);

    }

    // Check if permission is granted or not

    public boolean checkLocationPermission() {

        // If permission is not granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, User_Location_Code);
            }

            // It will return false is user chooses for the don't ask for permission
            return false;
        } else {
            return true;
        }
    }

    // This method will handle the permission request response
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Check if permission is granted or not
        switch (requestCode) {
            case User_Location_Code:
                // This means the permission is granted
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if(ApiClient == null) {
                            // Will build new client
                            buildApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }

                // If permission is not granted
                else {
                    Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }

    protected synchronized void buildApiClient() {
        ApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        ApiClient.connect();
    }

            // Belongs to GoogleApiClient.LocationListener. Gets called when location has been changed
            @Override
            public void onLocationChanged(Location location) {
                lastLocation = location;

                // Check if marker has already been set to a previous location and remove that marker.
                // Null means it has been set to another location
                if(currentMarker != null) {
                    currentMarker.remove();
                }

                // Set new location

                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                // Current position user

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                // Set title current location of user
                markerOptions.title("Current location");

                // Set color of marker
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                // Now set it on current location
                currentMarker = mMap.addMarker(markerOptions);

                // Move camera to location

                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomBy(12)); // change numbers

                // Start location update

                if(ApiClient != null) {
                    LocationServices.FusedLocationApi.removeLocationUpdates(ApiClient, this);
                }
            }

            // Belongs to GoogleApiClient.connectionCallbacks. Gets called when user is connected
            @Override
            public void onConnected(@Nullable Bundle bundle) {

                locationRequest = new LocationRequest();
                locationRequest.setInterval(1100);
                locationRequest.setFastestInterval(1100);
                locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

                // Check permission
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(ApiClient, locationRequest, this);
                }
            }

            @Override
            public void onConnectionSuspended(int i) {

            }

            // Belongs to GoogleApiClient.OnConnectionFailedListener. Gets called when user is disconnected
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        }
